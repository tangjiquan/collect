package com.panther.study.threadandrunnable04.chapter01.example03;

/**
 * 通过Thread创建线程
 *
 * @author: Kevin
 * @date: created in 下午10:03 2018-11-22
 * @version: V1.0
 */
public class ThreadCreate {

	public static void main(String[] args){
		Thread thread = new CustomThread();
		thread.start();
	}

	static class CustomThread extends Thread{
		@Override
		public void run() {
			System.out.println("Running...");
		}
	}
}
