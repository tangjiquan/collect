package com.panther.client;

import com.panther.common.Const;
import com.panther.common.HelloPacket;
import org.tio.client.ClientChannelContext;
import org.tio.client.ClientGroupContext;
import org.tio.client.ReconnConf;
import org.tio.client.TioClient;
import org.tio.client.intf.ClientAioHandler;
import org.tio.client.intf.ClientAioListener;
import org.tio.core.Node;
import org.tio.core.Tio;

import java.io.IOException;

/**
 * @author: Kevin
 * @date: created in 下午10:42 2018-11-21
 * @version: V1.0
 */
public class HelloClientStarter {

	// 服务节点
	public static Node serverNode = new Node(Const.SERVER, Const.PROT);

	// 包括编码， 解码， 消息处理
	public static ClientAioHandler tioClientHandler = new HelloClientAioHandler();

	public static ClientAioListener aioListener = null;

	// 断链后自动连接的， 不想自动连接请设置null
	private static ReconnConf  reconnConf = new ReconnConf(5000L);

	// 一组连接共用的上下文对象
	public static ClientGroupContext clientGroupContext = new ClientGroupContext(tioClientHandler, aioListener, reconnConf);

	public static TioClient tioClient;

	public static ClientChannelContext clientChannelContext = null;

	public static void main(String[] args){
		clientGroupContext.setHeartbeatTimeout(Const.TIMEOUT);
		try {
			tioClient = new TioClient(clientGroupContext);
			clientChannelContext = tioClient.connect(serverNode);
			send();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void send(){
		HelloPacket packet = new HelloPacket();
		packet.setBody("hello world".getBytes());
		Tio.send(clientChannelContext, packet);
	}
}
