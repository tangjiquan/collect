package com.panther.study.threadandrunnable03.chapter03.example03;

import java.util.concurrent.TimeUnit;

/**
 * 通过检查线程interrupt 的标识来决定是否退出，如果在线程中执行某个可以中断的方法，则可以捕获中断信号来决定是否退出
 * @author: Kevin
 * @date: created in 上午9:14 2018-09-19
 * @version: V1.0
 */
public class InterruptThreadExit01 {

	public static void main(String[] args){
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("I will start work");
				while(!Thread.interrupted()){
					// working
				}
				System.out.println("I will be exiting");
			}
		});

		t.start();
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("System will be shutdown");
		t.interrupt();;
	}
}
