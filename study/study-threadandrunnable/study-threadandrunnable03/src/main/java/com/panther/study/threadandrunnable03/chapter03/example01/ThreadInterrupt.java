package com.panther.study.threadandrunnable03.chapter03.example01;

import java.util.concurrent.TimeUnit;

/**
 * 创建的子线程准备睡眠1分钟， 但是在2毫秒的时候被主线程调用interrupt方法打断
 *
 * @author: Kevin
 * @date: created in 上午12:33 2018-09-16
 * @version: V1.0
 */
public class ThreadInterrupt {

	public static void main(String[] args){
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					TimeUnit.MINUTES.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		t.start();
		try {
			TimeUnit.MILLISECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t.interrupt();
	}
}
