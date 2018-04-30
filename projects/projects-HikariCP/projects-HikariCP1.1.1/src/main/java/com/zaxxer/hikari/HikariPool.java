/*
 * Copyright (C) 2013 Brett Wooldridge
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zaxxer.hikari;

import java.lang.management.ManagementFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.javassist.HikariInstrumentationAgent;
import com.zaxxer.hikari.proxy.IHikariConnectionProxy;
import com.zaxxer.hikari.proxy.JavassistProxyFactoryFactory;
import com.zaxxer.hikari.util.ClassLoaderUtils;
import com.zaxxer.hikari.util.PropertyBeanSetter;

/**
 * This is the primary connection pool class that provides the basic
 * pooling behavior for HikariCP.
 *
 * @author Brett Wooldridge
 */
public final class HikariPool implements HikariPoolMBean
{
    private static final Logger LOGGER = LoggerFactory.getLogger(HikariPool.class);

    private final HikariConfig configuration;
    private final LinkedTransferQueue<IHikariConnectionProxy> idleConnections;

    private final AtomicInteger totalConnections;
    private final AtomicInteger idleConnectionCount;
    private final DataSource dataSource;
    private final long leakDetectionThreshold;
    private final boolean jdbc4ConnectionTest;
    private final boolean delegationProxies;

    private final Timer houseKeepingTimer;

    /**
     * Construct a HikariPool with the specified configuration.
     *
     * @param configuration a HikariConfig instance
     */
    HikariPool(HikariConfig configuration)
    {
        configuration.validate();

        this.configuration = configuration;
        this.totalConnections = new AtomicInteger();
        this.idleConnectionCount = new AtomicInteger();
        this.idleConnections = new LinkedTransferQueue<IHikariConnectionProxy>();

        this.jdbc4ConnectionTest = configuration.isJdbc4ConnectionTest();
        this.leakDetectionThreshold = configuration.getLeakDetectionThreshold();

        try
        {
            Class<?> clazz = ClassLoaderUtils.loadClass(configuration.getDataSourceClassName());
            this.dataSource = (DataSource) clazz.newInstance();
            PropertyBeanSetter.setTargetFromProperties(dataSource, configuration.getDataSourceProperties());

            HikariInstrumentationAgent instrumentationAgent = new HikariInstrumentationAgent(dataSource);
            delegationProxies = !instrumentationAgent.loadTransformerAgent(); 
            if (delegationProxies)
            {
                LOGGER.info("Falling back to Javassist delegate-based proxies.");
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException("Could not create datasource class: " + configuration.getDataSourceClassName(), e);
        }

        registerMBean();

        houseKeepingTimer = new Timer("Hikari Housekeeping Timer", true);

        long idleTimeout = configuration.getIdleTimeout();
        if (idleTimeout > 0 || configuration.getMaxLifetime() > 0)
        {
            houseKeepingTimer.scheduleAtFixedRate(new HouseKeeper(), TimeUnit.SECONDS.toMillis(30), TimeUnit.SECONDS.toMillis(30));
        }

        fillPool();            
    }

    /**
     * Get a connection from the pool, or timeout trying.
     *
     * @return a java.sql.Connection instance
     * @throws SQLException thrown if a timeout occurs trying to obtain a connection
     */
    Connection getConnection() throws SQLException
    {
        try
        {
            long timeout = configuration.getConnectionTimeout();
            final long start = System.currentTimeMillis();
            do
            {
                if (idleConnectionCount.get() == 0)
                {
                    addConnections();
                }
    
                IHikariConnectionProxy connectionProxy = idleConnections.poll(timeout, TimeUnit.MILLISECONDS);
                if (connectionProxy == null)
                {
                    LOGGER.error("Timeout of {}ms encountered waiting for connection", configuration.getConnectionTimeout());
                    throw new SQLException("Timeout of encountered waiting for connection");
                }

                idleConnectionCount.decrementAndGet();

                final long maxLifetime = configuration.getMaxLifetime();
                if (maxLifetime > 0 && start - connectionProxy.getCreationTime() > maxLifetime)
                {
                    // Throw away the connection that has passed its lifetime, try again
                    closeConnection(connectionProxy);
                    timeout -= (System.currentTimeMillis() - start);
                    continue;
                }

                connectionProxy.unclose();

                Connection connection = (Connection) connectionProxy; 
                if (!isConnectionAlive(connection, timeout))
                {
                    // Throw away the dead connection, try again
                    closeConnection(connectionProxy);
                    timeout -= (System.currentTimeMillis() - start);
                    continue;
                }
    
                if (leakDetectionThreshold > 0)
                {
                    connectionProxy.captureStack(leakDetectionThreshold, houseKeepingTimer);
                }

                return connection;

            } while (timeout > 0);

            throw new SQLException("Timeout of encountered waiting for connection");            
        }
        catch (InterruptedException e)
        {
            return null;
        }
    }

    /**
     * Release a connection back to the pool, or permanently close it if it
     * is broken.
     *
     * @param connectionProxy the connection to release back to the pool
     */
    public void releaseConnection(IHikariConnectionProxy connectionProxy)
    {
        if (!connectionProxy.isBrokenConnection())
        {
            connectionProxy.markLastAccess();
            idleConnectionCount.incrementAndGet();
            idleConnections.put(connectionProxy);
        }
        else
        {
            closeConnection(connectionProxy);
        }
    }

    // ***********************************************************************
    //                        HikariPoolMBean methods
    // ***********************************************************************
    
    /** {@inheritDoc} */
    public int getActiveConnections()
    {
        return Math.min(configuration.getMaximumPoolSize(), totalConnections.get() - idleConnectionCount.get());
    }
    
    /** {@inheritDoc} */
    public int getIdleConnections()
    {
        return idleConnectionCount.get();
    }

    /** {@inheritDoc} */
    public int getTotalConnections()
    {
        return totalConnections.get();
    }

    /** {@inheritDoc} */
    public int getThreadsAwaitingConnection()
    {
        return idleConnections.getWaitingConsumerCount();
    }

    /** {@inheritDoc} */
    public void closeIdleConnections()
    {
        final int idleCount = idleConnectionCount.get();
        for (int i = 0; i < idleCount; i++)
        {
            IHikariConnectionProxy connectionProxy = idleConnections.poll();
            if (connectionProxy == null)
            {
                break;
            }

            idleConnectionCount.decrementAndGet();
            
            closeConnection(connectionProxy);
        }
    }

    // ***********************************************************************
    //                           Private methods
    // ***********************************************************************
    
    /**
     * Fill the pool up to the minimum size.
     */
    private void fillPool()
    {
        int maxIters = (configuration.getMinimumPoolSize() / configuration.getAcquireIncrement()) + 1;
        while (totalConnections.get() < configuration.getMinimumPoolSize() && maxIters-- > 0)
        {
            addConnections();
        }
    }

    /**
     * Add connections to the pool, not exceeding the maximum allowed.
     */
    private synchronized void addConnections()
    {
        final int max = configuration.getMaximumPoolSize();
        final int increment = configuration.getAcquireIncrement();
        for (int i = 0; totalConnections.get() < max && i < increment; i++)
        {
            addConnection();
        }
    }

    /**
     * Create and add a single connection to the pool.
     */
    private void addConnection()
    {
        int retries = 0;
        while (true)
        {
            try
            {
                Connection connection = dataSource.getConnection();
                IHikariConnectionProxy proxyConnection;
                if (delegationProxies)
                {
                    proxyConnection = (IHikariConnectionProxy) JavassistProxyFactoryFactory.getProxyFactory().getProxyConnection(connection);
                }
                else
                {
                    proxyConnection = (IHikariConnectionProxy) connection;
                }

                proxyConnection.setParentPool(this);

                boolean alive = isConnectionAlive((Connection) proxyConnection, configuration.getConnectionTimeout());
                if (alive)
                {
                    connection.setAutoCommit(configuration.isAutoCommit());
                    idleConnectionCount.incrementAndGet();
                    totalConnections.incrementAndGet();
                    idleConnections.add(proxyConnection);
                    break;
                }
                else
                {
                    Thread.sleep(configuration.getAcquireRetryDelay());
                }
            }
            catch (Exception e)
            {
                if (retries++ > configuration.getAcquireRetries())
                {
                    LOGGER.error("Maximum connection creation retries exceeded", e);
                    break;
                }

                try
                {
                    Thread.sleep(configuration.getAcquireRetryDelay());
                }
                catch (InterruptedException e1)
                {
                    break;
                }
            }
        }
    }

    /**
     * Check whether the connection is alive or not.
     *
     * @param connection the connection to test
     * @param timeoutMs the timeout before we consider the test a failure
     * @return true if the connection is alive, false if it is not alive or we timed out
     */
    private boolean isConnectionAlive(final Connection connection, long timeoutMs)
    {
        // Set a realistic minimum timeout
        if (timeoutMs < 500)
        {
            timeoutMs = 500;
        }

        try
        {
            if (jdbc4ConnectionTest)
            {
                return connection.isValid((int) timeoutMs * 1000);
            }

            Statement statement = connection.createStatement();
            try
            {
                statement.executeQuery(configuration.getConnectionTestQuery());
            }
            finally
            {
                statement.close();
            }

            return true;
        }
        catch (SQLException e)
        {
            LOGGER.error("Exception during keep alive check.  Connection must be dead.");
            return false;
        }
    }

    /**
     * Permanently close a connection.
     *
     * @param connectionProxy the connection to actually close
     */
    private void closeConnection(IHikariConnectionProxy connectionProxy)
    {
        try
        {
            totalConnections.decrementAndGet();
            connectionProxy.__close();
        }
        catch (SQLException e)
        {
            return;
        }
    }

    /**
     * Register the pool and pool configuration objects with the MBean server.
     */
    private void registerMBean()
    {
        try
        {
            MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
            
            ObjectName poolConfigName = new ObjectName("com.zaxxer.hikari:type=PoolConfig (" + configuration.getPoolName() + ")");
            ObjectName poolName = new ObjectName("com.zaxxer.hikari:type=Pool (" + configuration.getPoolName() + ")");
            if (!mBeanServer.isRegistered(poolConfigName))
            {
                mBeanServer.registerMBean(configuration, poolConfigName);
                mBeanServer.registerMBean(this, poolName);
            }
            else
            {
                LOGGER.error("You cannot use the same HikariConfig for separate pool instances.");
            }
        }
        catch (Exception e)
        {
            LOGGER.warn("Unable to register management beans.", e);
        }
    }

    /**
     * The house keeping task to retire idle and maxAge connections.
     */
    private class HouseKeeper extends TimerTask
    {
        public void run()
        {
            houseKeepingTimer.purge();

            final long now = System.currentTimeMillis();
            final long idleTimeout = configuration.getIdleTimeout();
            final long maxLifetime = configuration.getMaxLifetime();
            final int idleCount = idleConnectionCount.get();

            for (int i = 0; i < idleCount; i++)
            {
                IHikariConnectionProxy connectionProxy = idleConnections.poll();
                if (connectionProxy == null)
                {
                    break;
                }

                idleConnectionCount.decrementAndGet();

                if ((idleTimeout > 0 && now > connectionProxy.getLastAccess() + idleTimeout)
                    ||
                    (maxLifetime > 0 && now > connectionProxy.getCreationTime() + maxLifetime))
                {
                    closeConnection(connectionProxy);
                }
                else
                {
                    idleConnectionCount.incrementAndGet();
                    idleConnections.add(connectionProxy);
                }
            }

            addConnections();
        }
    }
}
