package com.panther.study.threadandrunnable03.chapter24.example03;

import java.io.*;
import java.net.Socket;

/**
 * @author: Kevin
 * @date: created in 下午8:26 2018-11-11
 * @version: V1.0
 */
public class ClientHandler implements Runnable {
	private final Socket socket;

	private final String clientID;

	public ClientHandler(Socket socket){
		this.socket = socket;
		this.clientID = socket.getInetAddress().getHostAddress() + ":" + socket.getPort();
	}

	@Override
	public void run() {
		try {
			this.chat();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void chat() throws  Exception{
		BufferedReader bufferedReader = wrap2Reader(socket.getInputStream());
		PrintStream printStream = wrap2Print(socket.getOutputStream());
		String received;
		while((received=bufferedReader.readLine())!=null){
			System.out.printf("clent:%s-message:%s\n", clientID, received);
			if(received.equals("quit")){
				write2Client(printStream, "client will close");
				socket.close();
				break;
			}
			write2Client(printStream, "Server: " + received);
		}
	}

	/**
	 * 将输入的字节流封装成BufferedReader缓冲字符流
	 *
	 * @param inputStream
	 * @return
	 */
	private BufferedReader wrap2Reader(InputStream inputStream){
		return new BufferedReader(new InputStreamReader(inputStream));
	}

	private PrintStream wrap2Print(OutputStream outputStream){
		return new PrintStream(outputStream);
	}

	private void write2Client(PrintStream print, String message){
		print.print(message);
		print.flush();
	}
}
