package com.panther.study.threadandrunnable03.chapter04.example01;

/**
 * @author: Kevin
 * @date: created in 上午8:59 2018-09-21
 * @version: V1.0
 */
public class TicketWindowRunnable implements Runnable{

	private int index = 1;
	private final static int MAX = 500;

	@Override
	public void run() {
		while(index<=MAX){
			System.out.println(Thread.currentThread() + "号码是：" + (index++));
		}
	}

	public static void main(String[] args){
		TicketWindowRunnable task = new TicketWindowRunnable();
		Thread t1 = new Thread(task, "一号窗口");
		Thread t2 = new Thread(task, "二号窗口");
		Thread t3 = new Thread(task, "三号窗口");

		t1.start();
		t2.start();
		t3.start();

	}
}
