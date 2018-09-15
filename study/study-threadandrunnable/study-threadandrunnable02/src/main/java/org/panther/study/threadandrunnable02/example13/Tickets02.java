package org.panther.study.threadandrunnable02.example13;

import java.util.Vector;

/**
 * 同步类容器， 比如Vector底层使用大量的synochion， 不能应对高并发场景
 * @author: Kevin
 * @date: created in 上午9:44 2018-09-10
 * @version: V1.0
 */
public class Tickets02 {
	public static void main(String[] args){
		// 初始化火车票并添加火车票，线程同步使用Vector代替ArrayList，HashTable代替HashMap
		final Vector<String> tickes = new Vector<String>();
		for(int i=0; i<1000; i++){
			tickes.add("火车票" + i);
		}


		for(int i=1; i<10; i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					while(true){
						if(tickes.isEmpty()) {break;}
						System.out.println(Thread.currentThread().getName() + "---" + tickes.remove(0));
					}
				}
			}).start();
		}
	}

}
