package org.panther.study.threadandrunnable02.example03;

/**
 * @author: Kevin
 * @date: created in 下午2:13 2018-08-26
 * @version: V1.0
 */
public class MyObject02 {

	public synchronized void m1(){
		try {
			System.out.println(Thread.currentThread().getName());
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public  synchronized void m2(){
		System.out.println(Thread.currentThread().getName());
	}

	public static void main(String[] args){
		final MyObject02 m =new MyObject02();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				m.m1();
			}
		}, "t1");
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				m.m2();
			}
		}, "t2");

		t1.start();
		t2.start();

	}
}
