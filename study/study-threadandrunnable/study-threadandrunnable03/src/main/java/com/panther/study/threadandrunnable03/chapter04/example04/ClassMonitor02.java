package com.panther.study.threadandrunnable03.chapter04.example04;

import java.util.concurrent.TimeUnit;

/**
 * * synchronized同步类的不同静态方法， 争抢的是同一个锁
 *
 * @author: Kevin
 * @date: created in 下午9:36 2018-10-07
 * @version: V1.0
 */
public class ClassMonitor02 {

	public static synchronized void method1(){
		System.out.println(Thread.currentThread().getId() + " enter to method1");
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public  void method2(){
		synchronized (ClassMonitor02.class){
			System.out.println(Thread.currentThread().getName() + " enter to method2");
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args){
		ClassMonitor02 thisMonitor01 = new ClassMonitor02();
		//new Thread(thisMonitor01::method1, "T1").start();
		//new Thread(thisMonitor01::method2, "T2").start();
	}
}
