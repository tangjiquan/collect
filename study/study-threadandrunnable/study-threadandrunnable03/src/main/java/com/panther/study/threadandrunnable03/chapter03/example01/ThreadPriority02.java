package com.panther.study.threadandrunnable03.chapter03.example01;

/**
 * 设置线程优先级别
 *
 * @author: Kevin
 * @date: created in 上午12:14 2018-09-16
 * @version: V1.0
 */
public class ThreadPriority02 {

	public static void main(String[] args){
		Thread t1  = new Thread(new Thread(){
			@Override
			public void run() {
			}
		});

		t1.setPriority(3);

		Thread t2  = new Thread(new Thread(){
			@Override
			public void run() {
			}
		});

		t1.start();
		t2.start();//默认优先级是5， 和main线程的优先级一致
		System.out.println("t1线程优先级：" + t1.getPriority());
		System.out.println("t2线程优先级：" + t2.getPriority());
		System.out.println("main线程优先级：" + Thread.currentThread().getPriority());
	}
}
