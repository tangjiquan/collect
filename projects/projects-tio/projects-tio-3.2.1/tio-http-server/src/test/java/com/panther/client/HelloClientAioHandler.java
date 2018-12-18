package com.panther.client;

import com.panther.common.HelloPacket;
import org.tio.client.intf.ClientAioHandler;
import org.tio.core.ChannelContext;
import org.tio.core.GroupContext;
import org.tio.core.exception.AioDecodeException;
import org.tio.core.intf.Packet;

import java.nio.ByteBuffer;

/**
 * @author: Kevin
 * @date: created in 下午10:54 2018-11-21
 * @version: V1.0
 */
public class HelloClientAioHandler implements ClientAioHandler {
	private static HelloPacket heartbeatPacket = new HelloPacket();

	/**
	 * 此方法如果返回null, 框架层面则不会发心跳； 如果返回非null， 框架层面会定时发本方法返回的消息包
	 * @return
	 */
	@Override
	public Packet heartbeatPacket() {
		return heartbeatPacket;
	}

	/**
	 * 编码：把接收到的Bytebuffer, 解码成应用可以识别到的业务消息包
	 * 总的消息结构：消息头 + 消息体
	 * 消息头结构：4字节， 存储消息长度
	 * 消息体结构：对象的json串bytep[]
	 *
	 * @param byteBuffer
	 * @param i
	 * @param i1
	 * @param i2
	 * @param channelContext
	 * @return
	 * @throws AioDecodeException
	 */
	@Override
	public Packet decode(ByteBuffer byteBuffer, int i, int i1, int i2, ChannelContext channelContext) throws AioDecodeException {
		//收到的数据组不了业务包，则返回null以告诉框架数据不够
		if (i2 < HelloPacket.HEADER_LENGHT) {
			return null;
		}

		//读取消息体的长度
		int bodyLength = byteBuffer.getInt();

		//数据不正确，则抛出AioDecodeException异常
		if (bodyLength < 0) {
			throw new AioDecodeException("bodyLength [" + bodyLength + "] is not right, remote:" + channelContext.getClientNode());
		}

		//计算本次需要的数据长度
		int neededLength = HelloPacket.HEADER_LENGHT + bodyLength;
		//收到的数据是否足够组包
		int isDataEnough = i2 - neededLength;
		// 不够消息体长度(剩下的buffe组不了消息体)
		if (isDataEnough < 0) {
			return null;
		} else //组包成功
		{
			HelloPacket imPacket = new HelloPacket();
			if (bodyLength > 0) {
				byte[] dst = new byte[bodyLength];
				byteBuffer.get(dst);
				imPacket.setBody(dst);
			}
			return imPacket;
		}
	}

	/**
	 * 编码：把业务消息包编码为可以发送的ByteBuffer
	 * 总的消息结构：消息头 + 消息体
	 * 消息头结构：4字节， 存储消息长度
	 * 消息体结构：对象的json串bytep[]
	 *
	 * @param packet
	 * @param groupContext
	 * @param channelContext
	 * @return
	 */
	@Override
	public ByteBuffer encode(Packet packet, GroupContext groupContext, ChannelContext channelContext) {
		HelloPacket helloPacket = (HelloPacket) packet;
		byte[] body = helloPacket.getBody();
		int bodylen = 0;
		if(body != null){
			bodylen = body.length;
		}
		// bytebuffer的总长度是 = 消息头的长度 + 消息体的长度
		int allLen = HelloPacket.HEADER_LENGHT + bodylen;
		//创建一个新的bytebuffer
		ByteBuffer buffer = ByteBuffer.allocate(allLen);
		//设置字节序
		buffer.order(groupContext.getByteOrder());
		//写入消息头----消息头的内容就是消息体的长度
		buffer.putInt(bodylen);
		//写入消息体
		if(body != null){
			buffer.put(body);
		}
		return buffer;
	}

	/**
	 * 处理消息
	 *
	 * @param packet
	 * @param channelContext
	 * @throws Exception
	 */
	@Override
	public void handler(Packet packet, ChannelContext channelContext) throws Exception {
		HelloPacket helloPacket = (HelloPacket) packet;
		byte[] body = helloPacket.getBody();
		if(body != null){
			String str = new String(body, HelloPacket.CHARSET);
			System.out.println("收到的消息：" + str);
		}
		return ;
	}
}
