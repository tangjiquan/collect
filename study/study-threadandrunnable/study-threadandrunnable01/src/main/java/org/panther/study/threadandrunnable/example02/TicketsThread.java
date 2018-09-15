package org.panther.study.threadandrunnable.example02;

/**
 * @author: Kevin
 * @date: created in 下午3:44 2018-07-08
 * @version: V1.0
 */

class MyThread extends Thread{
	private  int ticketsCont = 5;//一共有5张票

	private String name;

	public MyThread(String name){
		this.name = name;
	}

	@Override
	public void run() {
		while(ticketsCont > 0){
			ticketsCont--;// 如果还有票就卖
			System.out.println(name + "卖了一张票， 剩余票数：" + ticketsCont);
		}
	}
}
public class TicketsThread {

	public static void main(String[] args){
		// 创建三个线程模拟卖票
		MyThread mt1 = new MyThread("窗口1");
		MyThread mt2 = new MyThread("窗口2");
		MyThread mt3 = new MyThread("窗口3");

		mt1.start();
		mt2.start();
		mt3.start();
	}
}
