package com.panther.study.threadandrunnable03.chapter08.example01;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Kevin
 * @date: created in 下午9:24 2018-10-21
 * @version: V1.0
 */
public class BasicThreadPool extends Thread implements ThreadPool {
	// 初始化线程数量
	private final int initSize;

	// 线程池核心线程数量
	private final int coreSize;

	// 线程池最大线程数量
	private final int maxSize;

	// 当前活跃的线程数量
	private int activeCount;

	// 创建线程需要的工程
	private final ThreadFactory threadFactory;

	// 任务队列
	private final RunnableQueue runnableQueue;

	// 线程池是否被shutdown
	private volatile boolean isShutdown = false;

	// 工作线程的队列
	private final Queue<ThreadTask> threadQueue = new ArrayDeque();

	private final static DenyPolicy DENY_POLICY = new DenyPolicy.DiscardDenyPolicy();

	private final static ThreadFactory DEFAULT_FACTORY = new DefaultThreadFactory();

	private final long keepAliveTime;

	private final TimeUnit timeUnit;

	public BasicThreadPool(int initSize, int maxsize, int coreSize, int queueSize){
		this(initSize, maxsize, coreSize, queueSize, DEFAULT_FACTORY, DENY_POLICY, 10, TimeUnit.SECONDS);
	}

	public BasicThreadPool(int initSize, int maxSize, int coreSize, int queueSize,
						   ThreadFactory threadFactory, DenyPolicy denyPolicy, long keepAliveTime,
						   TimeUnit timeUnit){
		this.initSize = initSize;
		this.maxSize = maxSize;
		this.coreSize = coreSize;
		this.threadFactory = threadFactory;
		this.runnableQueue = new LinkedRunnableQueue(queueSize, denyPolicy, this);
		this.keepAliveTime = keepAliveTime;
		this.timeUnit = timeUnit;
		this.init();
	}

	public void init(){
		start();
		for(int i=0; i<initSize; i++){
			newThread();
		}
	}

	private void newThread(){
		InternalTask internalTask = new InternalTask(runnableQueue);
		Thread thread = this.threadFactory.createThread(internalTask);
		ThreadTask threadTask = new ThreadTask(thread, internalTask);
		threadQueue.offer(threadTask);
		this.activeCount++;
		thread.start();
	}

	@Override
	public void execute(Runnable runnable) {
		if(this.isShutdown()){
			throw new IllegalStateException("the thread pool is destory");
		}
		this.runnableQueue.offer(runnable);
	}

	private void removeThread(){
		ThreadTask threadTask = threadQueue.remove();
		threadTask.internalTask.stop();
		this.activeCount--;
	}


	@Override
	public void shutdown() {
		synchronized (this){
			if(isShutdown){
				return;
			}
			isShutdown = true;
			for(ThreadTask threadTask : threadQueue){
				threadTask.internalTask.stop();
				threadTask.thread.interrupt();
			}
			this.interrupt();
		}
	}

	@Override
	public int getInitSize() {
		if (isShutdown) {
			throw new IllegalStateException("the thread pool is destory");
		}
		return this.initSize;
	}

	@Override
	public int getMaxSize() {
		if (isShutdown) {
			throw new IllegalStateException("the thread pool is destory");
		}
		return this.maxSize;
	}

	@Override
	public int getCoreSize() {
		if (isShutdown) {
			throw new IllegalStateException("the thread pool is destory");
		}
		return this.coreSize;
	}

	@Override
	public int getQueueSize() {
		if (isShutdown) {
			throw new IllegalStateException("the thread pool is destory");
		}
		return this.runnableQueue.size();
	}

	@Override
	public int getActiveCount() {
		synchronized (this){
			return this.activeCount;
		}
	}

	@Override
	public boolean isShutdown() {
		return this.isShutdown;
	}

	@Override
	public void run() {
		while(!isShutdown && !isInterrupted()){
			try {
				timeUnit.sleep(keepAliveTime);
			} catch (InterruptedException e) {
				isShutdown = true;
				break;
			}
			synchronized (this){
				if(isShutdown){
					break;
				}

				// 当前队列中有尚未处理， 并且activeCount< coreSize 则扩容
				if(runnableQueue.size()>0 && activeCount <coreSize){
					for(int i=initSize; i<coreSize; i++){
						newThread();
					}
					continue;
				}

				// 当前队列中有尚未处理的，并且activeCount<maxSize则继续扩容
				if(runnableQueue.size() > 0 && activeCount < maxSize){
					for(int i=coreSize; i<maxSize; i++){
						newThread();
					}
				}

				// 如果任务队列中没有任务， 则需要收回， 回收至coreSize为止
				if(runnableQueue.size() == 0 && activeCount>coreSize){
					for(int i = coreSize; i<activeCount; i++){
						removeThread();
					}
				}
			}
		}
	}

	public static class ThreadTask {
		Thread thread;
		InternalTask internalTask;
		public ThreadTask(Thread thread, InternalTask internalTask){
			this.thread = thread;
			this.internalTask = internalTask;
		}
	}

	private static class DefaultThreadFactory implements  ThreadFactory{
		private static final AtomicInteger GROUP_COUNTER = new AtomicInteger(1);
		private static final ThreadGroup group = new ThreadGroup("MyThreadPool-" + GROUP_COUNTER.getAndIncrement());
		private static final AtomicInteger COUNTER = new AtomicInteger(0);

		@Override
		public Thread createThread(Runnable runnable) {
			return new Thread(group, runnable, "Thread-pool-" + COUNTER.getAndIncrement());
		}
	}
}
