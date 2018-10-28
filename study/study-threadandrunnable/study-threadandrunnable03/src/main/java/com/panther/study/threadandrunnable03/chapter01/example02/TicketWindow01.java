package com.panther.study.threadandrunnable03.chapter01.example02;

/**
 * 模拟公积金大厅4个窗口
 *
 * @author: Kevin
 * @date: created in 下午11:01 2018-09-15
 * @version: V1.0
 */
public class TicketWindow01 extends Thread{


	private  String name;
	private static final int MAX = 50;

	private int index = 1;

	@Override
	public void run() {
		while(index <= MAX){
			System.out.println("柜台" + name + "当前号码：" + (index++));
		}
	}

	public TicketWindow01(String name) {
		this.name = name;
	}



	public static void main(String[] args){
		TicketWindow01 t1 = new TicketWindow01("一号机器");
		t1.start();

		TicketWindow01 t2 = new TicketWindow01("二号机器");
		t2.start();

		TicketWindow01 t3 = new TicketWindow01("三号机器");
		t3.start();

		TicketWindow01 t4 = new TicketWindow01("四号机器");
		t4.start();
	}


}
