package com.panther.study.threadandrunnable03.chapter24.example03;

import com.panther.study.threadandrunnable03.chapter08.example01.BasicThreadPool;
import com.panther.study.threadandrunnable03.chapter08.example01.ThreadPool;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * @author: Kevin
 * @date: created in 下午8:19 2018-11-11
 * @version: V1.0
 */
public class ChatServer {

	// 服务端端口
	private final int port;

	// 定义线程池
	private ThreadPool threadPool;

	private ServerSocket serverSocket;

	public ChatServer(int port){
		this.port = port;
	}

	public ChatServer(){
		this(13312);
	}

	public void startServer(){
		// 创建线程池，初始化一个线程，核心线程数量为2， 最大线程为4， 阻塞队列中最大可加入1000个任务
		this.threadPool = new BasicThreadPool(1,2,4,1000);
		try {
			this.serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			this.serverSocket.setReuseAddress(true);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		System.out.println("Chat Server is started and listen at port:" + port);
	}

	public void listen(){
		for(;;){
			Socket client = null;
			try {
				client = serverSocket.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.threadPool.execute(new ClientHandler(client));
		}
	}
}
