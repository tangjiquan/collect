package com.panther.study.threadandrunnable03.chapter01.example03;

/**
 * jvm永远不会退出， 即使main结束了，但是jvm还没有退出， 原因是在jvm进程中还有一个非守护
 * 线程正在运行
 *
 * @author: Kevin
 * @date: created in 下午11:51 2018-09-15
 * @version: V1.0
 */
public class DaemonThread01 {

	public static void main(String[] args){
		Thread t = new Thread(){
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		t.start();
		try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Main Thread finished lifecycle!");
	}


}
