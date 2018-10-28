package org.panther.study.netty01.chapter02.example01;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: Kevin
 * @date: created in 下午3:29 2018-10-21
 * @version: V1.0
 */
public class Server {

	private ServerSocket serverSocket;

	public Server(int port){
		try {
			this.serverSocket = new ServerSocket(port);
			System.out.println("服务端启动成功， 端口："+port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void start(){
		new Thread(new Runnable() {
			@Override
			public void run() {
				doStart();
			}
		}).start();
	}

	private void doStart(){
		while(true){
			try {
				Socket client = serverSocket.accept();
				new ClientHandler(client).start();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

}
