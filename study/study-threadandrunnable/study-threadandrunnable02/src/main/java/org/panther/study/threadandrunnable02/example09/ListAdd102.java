package org.panther.study.threadandrunnable02.example09;

import java.util.ArrayList;
import java.util.List;


/**
 * t1线程一直在运行， t2一直监听t1线程， 当size为5的时候t2线程关闭
 *
 * @author: Kevin
 * @date: created in 上午11:23 2018-09-09
 * @version: V1.0
 */
public class ListAdd101 {
	private volatile static List list = new ArrayList();

	public void add(){
		list.add("bjsxt");
	}

	public int size(){
		return list.size();
	}

	public static void main(String[] args){
		final ListAdd101 listAdd1 = new ListAdd101();

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i<10; i++){
					listAdd1.add();
					System.out.println("当前线程"+ Thread.currentThread().getName() + "添加了一个元素");
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "t1");

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true){
					if(listAdd1.size() == 5){
						System.out.println("当前线程接收到通知" + Thread.currentThread().getName() + "list.size = 5线程停止");
						throw  new RuntimeException();
					}
				}
			}
		}, "t2");

		t1.start();
		t2.start();

	}
}
