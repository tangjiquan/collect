package com.panther.study.threadandrunnable05.chapter01.example01;

/**
 * 并行和串行操作比较耗时时间
 *
 * @author: Kevin
 * @date: created in 下午9:00 2018-12-18
 * @version: V1.0
 */
public class ConcurrentTest {

	private static final long count = 1000000000;

	public  static void main(String[] args){
		concurrency();
		serial();
	}

	public static void concurrency(){
		long start = System.currentTimeMillis();
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				int a = 0;
				for(long i = 0; i<count; i++){
					a += 5;
				}
			}
		});
		thread.start();
		int b = 0;
		for(long i=0; i<count;i++){
			b--;
		}
		long time = System.currentTimeMillis() - start;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("concurrent:" + time + "ms, b=" + b);
	}


	public static void serial(){
		long start = System.currentTimeMillis();
		int a = 0;
		for(long i=0; i<count; i++){
			a+=5;
		}
		int b = 0;
		for(long i=0; i<count; i++){
			b--;
		}

		long time = System.currentTimeMillis() - start;
		System.out.println("serial:" + time + "ms, b = "+ b);

	}
}
