package com.panther.study.threadandrunnable03.chapter01.example02;

/**
 * 模拟公积金大厅4个窗口
 * 给index增加静态方法
 *
 * @author: Kevin
 * @date: created in 下午11:01 2018-09-15
 * @version: V1.0
 */
public class TicketWindow02 extends Thread{


	private  String name;
	private static final int MAX = 5000;

	private static int index = 1;

	@Override
	public void run() {
		while(index <= MAX){
			System.out.println("柜台" + name + "当前号码：" + (index++));
		}
	}

	public TicketWindow02(String name) {
		this.name = name;
	}



	public static void main(String[] args){
		TicketWindow02 t1 = new TicketWindow02("一号机器");
		t1.start();

		TicketWindow02 t2 = new TicketWindow02("二号机器");
		t2.start();

		TicketWindow02 t3 = new TicketWindow02("三号机器");
		t3.start();

		TicketWindow02 t4 = new TicketWindow02("四号机器");
		t4.start();


	}


}
