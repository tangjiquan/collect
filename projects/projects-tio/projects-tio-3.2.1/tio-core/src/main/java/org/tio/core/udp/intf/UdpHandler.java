package org.tio.core.udp.intf;

import org.tio.core.udp.UdpPacket;

import java.net.DatagramSocket;

/**
 * @author tanyaowu
 * 2017年7月5日 下午2:46:47
 */
public interface UdpHandler {

	/**
	 *
	 * @param udpPacket
	 * @param datagramSocket
	 * @author tanyaowu
	 */
	public void handler(UdpPacket udpPacket, DatagramSocket datagramSocket);
}
