package com.panther.study.threadandrunnable03.chapter19.example02;

import java.util.concurrent.TimeUnit;

/**
 * Future设计模式
 *
 * @author: Kevin
 * @date: created in 下午9:49 2018-11-06
 * @version: V1.0
 */
public class MainTest {

	public  static void test1() throws InterruptedException {
		FutureService<Void, Void> service = FutureServiceImpl.getInstance();
		Future<?> future = service.submit(new Runnable() {
			@Override
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("I am Finish done.");
			}
		});

		// get方法会使当前线程进入阻塞
		future.get();
	}

	/**
	 * 增加回调方法
	 *
	 * @throws InterruptedException
	 */
	public static void test2() throws InterruptedException {
		FutureService<String, Integer> service = FutureServiceImpl.getInstance();
		Future<Integer> future = service.submit(new Task<String, Integer>() {
			@Override
			public Integer get(String input) {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return input.length();
			}
		}, "hello", new CallBack<Integer>() {
			@Override
			public void call(Integer result) {
				System.out.println("hellllll" + result);
			}
		});


		System.out.println(future.get());
	}

	public static void main(String[] args) throws InterruptedException {
		//test1();
		test2();
	}
}
