package org.panther.study.threadandrunnable02.example01;

import java.util.ArrayList;

/**
 * 线程不安全， 没有按5,4,3,2,1打印
 * @author: Kevin
 * @date: created in 下午1:05 2018-08-26
 * @version: V1.0
 */
public class MyThread01 extends Thread {

	private int count = 5;
	@Override
	public void run(){
		count--;
		System.out.println(this.currentThread().getName() + "count = " + count);
	}

	public static void main(String[] args){
		MyThread01 myThread = new MyThread01();
		/*Thread t1 = new Thread(myThread, "t1");
		Thread t2 = new Thread(myThread, "t2");
		Thread t3 = new Thread(myThread, "t3");
		Thread t4 = new Thread(myThread, "t4");
		Thread t5 = new Thread(myThread, "t5");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		*/
		ArrayList<Thread> list = new ArrayList<Thread>();
		for(int i=0; i<100; i++){
			Thread t = new Thread(myThread, "thread" + i);
			list.add(t);

		}
		for(Thread t : list){
			t.start();
		}



	}

}
