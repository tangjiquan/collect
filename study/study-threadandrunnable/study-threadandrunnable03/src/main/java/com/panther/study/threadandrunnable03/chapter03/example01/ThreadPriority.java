package com.panther.study.threadandrunnable03.chapter03.example01;

/**
 * 设置线程优先级别
 *
 * @author: Kevin
 * @date: created in 上午12:14 2018-09-16
 * @version: V1.0
 */
public class ThreadPriority {

	public static void main(String[] args){
		Thread t1  = new Thread(new Thread(){
			@Override
			public void run() {
				while(true){
					System.out.println("t1");
				}
			}
		});

		t1.setPriority(3);

		Thread t2  = new Thread(new Thread(){
			@Override
			public void run() {
				while(true){
					System.out.println("t2");
				}
			}
		});

		t2.setPriority(2);

		t1.start();
		t2.start();
	}
}
