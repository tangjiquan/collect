package org.panther.study.threadandrunnable.example03;

/**
 * @author: Kevin
 * @date: created in 下午3:52 2018-07-08
 * @version: V1.0
 */

class MyThread implements Runnable{
	private  int ticketsCont = 5;//一共有5张票


	@Override
	public void run() {
		while(ticketsCont > 0){
			ticketsCont--;// 如果还有票就卖
			System.out.println(Thread.currentThread().getName() + "卖了一张票， 剩余票数：" + ticketsCont);
		}
	}
}
public class TicketsRunnable {

	public static void main(String[] args){
		MyThread mt = new MyThread();
		// 创建三个线程模拟卖票
		Thread thread1 = new Thread(mt, "窗口1");
		Thread thread2 = new Thread(mt, "窗口2");
		Thread thread3 = new Thread(mt, "窗口3");

		thread1.start();
		thread2.start();
		thread3.start();

	}

}
