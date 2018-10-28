package com.panther.study.threadandrunnable03.chapter01.example03;

/**
 * 当将线程设置为守护线程， main线程执行结束后， jvm进程就退出了
 *
 * @author: Kevin
 * @date: created in 下午11:51 2018-09-15
 * @version: V1.0
 */
public class DaemonThread02 {

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
		t.setDaemon(true);
		t.start();
		try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Main Thread finished lifecycle!");
	}


}
