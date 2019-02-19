package com.panther.study.threadandrunnable06.chapter01.example01;

/**
 *
 * 缺点：没有返回值
 * @author: Kevin
 * @date: created in 下午10:54 2018-12-21
 * @version: V1.0
 */
public class RunableTest extends Thread {

	public static class MyRunnable implements Runnable{

		@Override
		public void run() {
			System.out.println("a thread");
		}
	}

	public static void main(String[] args){
		// 创建线程
		MyRunnable task = new MyRunnable();
		new Thread(task).start();
		new Thread(task).start();
	}
}
