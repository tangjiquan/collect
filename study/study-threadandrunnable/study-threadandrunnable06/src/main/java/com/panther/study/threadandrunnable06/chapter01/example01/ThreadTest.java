package com.panther.study.threadandrunnable06.chapter01.example01;

/**
 * 可以方便参数传入， 在子类中添加成员变量，通过set的方式或者构造器的方式传入参数
 *
 *
 * @author: Kevin
 * @date: created in 下午10:54 2018-12-21
 * @version: V1.0
 */
public class ThreadTest extends Thread {

	public static class MyThread extends Thread{

		@Override
		public void run() {
			System.out.println("a thread");
		}
	}

	public static void main(String[] args){
		// 创建线程
		MyThread thread = new MyThread();
		// 启动线程
		thread.start();
	}
}
