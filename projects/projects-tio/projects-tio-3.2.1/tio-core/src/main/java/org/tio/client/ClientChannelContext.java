package org.tio.client;

import org.tio.core.ChannelContext;
import org.tio.core.GroupContext;
import org.tio.core.Node;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousSocketChannel;

/**
 *
 * @author tanyaowu
 * 2017年4月1日 上午9:31:16
 */
public class ClientChannelContext extends ChannelContext {

	private String bindIp;

	private Integer bindPort;

	/**
	 * @param groupContext
	 * @param asynchronousSocketChannel
	 *
	 * @author tanyaowu
	 *
	 */
	public ClientChannelContext(GroupContext groupContext, AsynchronousSocketChannel asynchronousSocketChannel) {
		super(groupContext, asynchronousSocketChannel);
	}
	
	/**
	 * 创建一个虚拟ChannelContext，主要用来模拟一些操作，真实场景中用得少
	 * @param groupContext
	 */
	public ClientChannelContext(GroupContext groupContext) {
		super(groupContext);
	}

	/**
	 * @see org.tio.core.ChannelContext#createClientNode(AsynchronousSocketChannel)
	 *
	 * @param asynchronousSocketChannel
	 * @return
	 * @throws IOException
	 * @author tanyaowu
	 * 2016年12月6日 下午12:18:08
	 *
	 */
	@Override
	public Node createClientNode(AsynchronousSocketChannel asynchronousSocketChannel) throws IOException {
		InetSocketAddress inetSocketAddress = (InetSocketAddress) asynchronousSocketChannel.getLocalAddress();
		Node clientNode = new Node(inetSocketAddress.getHostString(), inetSocketAddress.getPort());
		return clientNode;
	}

	/**
	 * @return the bindIp
	 */
	public String getBindIp() {
		return bindIp;
	}

	/**
	 * @return the bindPort
	 */
	public Integer getBindPort() {
		return bindPort;
	}

	/**
	 * @param bindIp the bindIp to set
	 */
	public void setBindIp(String bindIp) {
		this.bindIp = bindIp;
	}

	/**
	 * @param bindPort the bindPort to set
	 */
	public void setBindPort(Integer bindPort) {
		this.bindPort = bindPort;
	}

	/** 
	 * @return
	 * @author tanyaowu
	 */
	@Override
	public boolean isServer() {
		return false;
	}

}
