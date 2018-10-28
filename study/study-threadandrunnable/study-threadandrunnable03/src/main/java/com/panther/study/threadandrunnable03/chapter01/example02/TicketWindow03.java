package com.panther.study.threadandrunnable03.chapter01.example02;

/**
 * 模拟公积金大厅4个窗口
 * 将Thread中的run方法的代码逻辑抽象出到runnable中
 * Thread的run方法不能共享， Runnable的run方法能共享
 *
 *
 * @author: Kevin
 * @date: created in 下午11:01 2018-09-15
 * @version: V1.0
 */
public class TicketWindow03 implements Runnable{


	private  String name;
	private static final int MAX = 50;

	private static int index = 1;

	@Override
	public void run() {
		while(index <= MAX){
			System.out.println("柜台" + Thread.currentThread().getName() + "当前号码：" + (index++));
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}



	public static void main(String[] args){
		TicketWindow03 t = new TicketWindow03();
		Thread t1 = new Thread(t, "一号机器");
		t1.start();

		Thread t2 = new Thread(t, "二号机器");
		t2.start();

		Thread t3 = new Thread(t, "三号机器");
		t3.start();

		Thread t4 = new Thread(t, "四号机器");
		t4.start();


	}


}
