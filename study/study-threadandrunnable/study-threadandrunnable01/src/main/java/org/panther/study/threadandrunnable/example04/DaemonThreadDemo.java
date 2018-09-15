package org.panther.study.threadandrunnable.example04;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Scanner;

/**
 *
 * 文件读写 ，业务逻辑不能放到守护线程中
 * @author: Kevin
 * @date: created in 下午4:23 2018-07-08
 * @version: V1.0
 */

class DaemonThread implements Runnable{

	@Override
	public void run() {
		System.out.println("进入守护线程" + Thread.currentThread().getName());
		try {
			writeToFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("退出守护线程" + Thread.currentThread().getName());

	}

	private void writeToFile() throws Exception{
		File finename = new File("/home/tangjiquan/Temp/daemon.txt");
		OutputStream os = new FileOutputStream(finename, true);
		int count = 0;
		while(count<999){
			os.write(("\r\nword" + count).getBytes());
			System.out.println("守护线程" + Thread.currentThread().getName() + "向文件中写入word" + count ++);
			Thread.sleep(1000);

		}

	}
}
public class DaemonThreadDemo {

	public static void main(String[] args){
		System.out.println("退出主线程" + Thread.currentThread().getName());
		DaemonThread daemonThread = new DaemonThread();
		Thread thread = new Thread(daemonThread);
		thread.setDaemon(true);
		thread.start();
		Scanner scanner = new Scanner(System.in);
		scanner.next();

		System.out.println("退出主线程" + Thread.currentThread().getName());
	}


}
