package org.panther.study.threadandrunnable.example01;

/**
 * @author: Kevin
 * @date: created in 下午3:36 2018-07-08
 * @version: V1.0
 */
public class MyThread01 implements Runnable {
	@Override
	public void run() {
		System.out.println("mythread01");
	}

	public static void main(String[] args){
		MyThread01 myThread01 = new MyThread01();
		Thread td = new Thread(myThread01);
		td.start();
	}

}
