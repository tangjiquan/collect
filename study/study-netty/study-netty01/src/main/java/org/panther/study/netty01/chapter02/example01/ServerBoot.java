package org.panther.study.netty01.chapter02.example01;

/**
 * @author: Kevin
 * @date: created in 下午3:28 2018-10-21
 * @version: V1.0
 */
public class ServerBoot {
	private static final int PORT = 8000;
	public static void main(String[] args){
		Server server = new Server(PORT);
		server.start();

	}
}
