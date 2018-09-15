package org.panther.study.concurrency.example01.count;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Kevin
 * @date: created in 下午11:14 2018-07-07
 * @version: V1.0
 */
public class CountExample2 {

	public static int clientTotal = 5000;

	public static int threadTotal = 200;

	public static AtomicInteger count = new AtomicInteger(0);

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(threadTotal);
		final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
		for(int i=0; i<clientTotal; i++){
			executorService.execute(new Runnable() {
				@Override
				public void run() {
						try {
							semaphore.acquire();
							add();
							semaphore.release();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						countDownLatch.countDown();
				}
			});
		}

		countDownLatch.await();
		executorService.shutdown();
		System.out.println(count.get());
	}

	private static void add(){
		count.getAndIncrement();
	}




}
