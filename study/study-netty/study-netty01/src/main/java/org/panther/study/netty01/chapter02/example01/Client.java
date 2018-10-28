package org.panther.study.netty01.chapter02.example01;

import java.io.IOException;
import java.net.Socket;

/**
 * @author: Kevin
 * @date: created in 下午3:42 2018-10-21
 * @version: V1.0
 */
public class Client {

	private static final String HOST = "127.0.0.1";
	private static final int PORT = 8000;
	private static final int SLEEP_TIME = 5000;

	public static void main(String[] args){
		try {
			final Socket socket = new Socket(HOST, PORT);
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("客户端启动");
					while(true){
						String message = "hello world";
						System.out.println("客户端发送数据："+ message);
						try {
							socket.getOutputStream().write(message.getBytes());
						} catch (IOException e) {
							e.printStackTrace();
						}
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
