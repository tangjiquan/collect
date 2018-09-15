package org.panther.study.threadandrunnable.example01;

/**
 * @author: Kevin
 * @date: created in 下午3:37 2018-07-08
 * @version: V1.0
 */
public class MyThread02 extends Thread {

	@Override
	public void run() {
		System.out.println("mythread01");
	}

	public static void main(String[] args){
		MyThread02 myThread02 = new MyThread02();
		myThread02.start();
	}
}
