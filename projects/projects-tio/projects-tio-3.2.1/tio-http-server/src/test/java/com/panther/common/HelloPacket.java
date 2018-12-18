package com.panther.common;

import org.tio.core.intf.Packet;

/**
 * 发送包结构
 *
 * @author: Kevin
 * @date: created in 下午5:09 2018-11-21
 * @version: V1.0
 */
public class HelloPacket extends Packet {
	private static final long serialVersionUID = -172060606924066412L;
	public static final int HEADER_LENGHT = 4;
	public static final String CHARSET = "utf-8";
	private byte[] body;

	public byte[] getBody() {
		return body;
	}

	public void setBody(byte[] body) {
		this.body = body;
	}
}
