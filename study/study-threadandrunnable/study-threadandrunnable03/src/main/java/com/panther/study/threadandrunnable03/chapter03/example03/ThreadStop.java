package com.panther.study.threadandrunnable03.chapter03.example03;

/**
 * 使用stop关闭一一个线程,stop已经被废弃， 不建议使用
 *
 *
 * @author: Kevin
 * @date: created in 上午9:04 2018-09-19
 * @version: V1.0
 */
public class ThreadStop {

	public  static void main(String[] args){
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("线程开始睡眠5秒");
				try {
					Thread.sleep(9000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t1.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t1.stop();

	}
}
