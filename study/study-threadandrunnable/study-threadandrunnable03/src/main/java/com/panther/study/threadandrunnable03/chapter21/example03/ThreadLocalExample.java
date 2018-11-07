package com.panther.study.threadandrunnable03.chapter21.example03;

import java.util.concurrent.TimeUnit;

/**
 * 定义一个全局的唯一的ThreadLocal<Integer>, 然后启动10个线程对threadLocal进行
 * set和get操作， 10个线程彼此不影响
 *
 * @author: Kevin
 * @date: created in 下午9:01 2018-11-07
 * @version: V1.0
 */
public class ThreadLocalExample {

	public static void main(String[] args){
		final ThreadLocal<Integer> tlocal = new ThreadLocal<>();

		// 创建10个线程使用tlocal
		for(int i=0; i<10; i++){

			final int finalI = i;
			new Thread(new Runnable() {
				@Override
				public void run() {
					// 每个线程都会设置tlocal, 但是彼此的数据都是独立的
					tlocal.set(finalI);
					System.out.println(Thread.currentThread() + "set i " + tlocal.get());
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread() + "set i " + tlocal.get());
				}
			}).start();
		}
	}
}
