package com.panther.study.threadandrunnable03.chapter04.example03;

import java.util.concurrent.TimeUnit;

/**
 * 通过jconsole可以看出有4个线程处于BLOCKED有一个线程处于TIMED_WAITING（sleeping)状态
 *
 * @author: Kevin
 * @date: created in 下午8:36 2018-10-07
 * @version: V1.0
 */
public class Mutex  implements  Runnable{
	private final static Object MUTEX = new Object();
	@Override
	public void run(){
		synchronized (MUTEX){
			try {
				TimeUnit.SECONDS.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args){
		final Mutex mutex = new Mutex();
		for(int i=0; i<5; i++){
			new Thread(mutex, "测试"+i).start();
		}
	}
}
