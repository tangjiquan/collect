package com.panther.study.threadandrunnable03.chapter03.example01;

/**
 *
 * 主线程和子线程都在睡眠
 * Thread.sleep只会影响当前线程
 *
 * @author: Kevin
 * @date: created in 上午12:02 2018-09-16
 * @version: V1.0
 */
public class ThreadSleep {

	public static void main(String[] args){
		new Thread(new Thread(){
				@Override
				public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		}).start();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
