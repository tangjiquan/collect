package com.panther.study.threadandrunnable03.chapter03.example03;

import java.util.concurrent.TimeUnit;

/**
 *
 * 由于线程的interrupt标识可能被擦除， 或者逻辑单元中不会调用任何可以中断方法， 所以使用volatile修饰
 * 的开关flag关闭线程也是一种常见的的做饭
 *
 * @author: Kevin
 * @date: created in 上午9:41 2018-09-19
 * @version: V1.0
 */
public class FlagThreadStop {

	static class MyTask extends Thread{
		private volatile boolean closed = false;

		@Override
		public void run() {
			System.out.println("I will start work");
			while(!closed && !Thread.interrupted()){
				// working
			}
			System.out.println("I will be exiting");

		}

		public void close(){
			this.closed = true;
			this.interrupt();
		}
	}

	public static void main(String[] args){
		MyTask myTask = new MyTask();
		myTask.start();
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("System will be shutdown ");
		myTask.close();
	}
}
