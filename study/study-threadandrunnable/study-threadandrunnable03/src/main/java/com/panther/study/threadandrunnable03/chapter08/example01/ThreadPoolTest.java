package com.panther.study.threadandrunnable03.chapter08.example01;

import java.util.concurrent.TimeUnit;

/**
 * 线程池的任务提交， 线程池数量的动态扩展，线程池的销毁功能
 *
 * @author: Kevin
 * @date: created in 下午9:57 2018-10-21
 * @version: V1.0
 */
public class ThreadPoolTest {


	public static void main(String[] args){
		// 定义线程池， 初始化线程为2， 核心线程数为4， 最大线程数为6， 队列中最多运行1000个任务
		final ThreadPool threadpool = new BasicThreadPool(2, 6, 4, 1000);
		// 定义20个任务并且慢慢提交给线程池
		for(int i=0; i<20; i++){
			threadpool.execute(new Runnable() {
				@Override
				public void run() {
					try {
						TimeUnit.SECONDS.sleep(10);
						System.out.println(Thread.currentThread().getName() + " is running and done");

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}

		for(;;){
			// 不断输出线程池信息
			System.out.println("getActiveCount:" + threadpool.getActiveCount());
			System.out.println("getQueuSize:" + threadpool.getQueueSize());
			System.out.println("getCoreSize:" + threadpool.getQueueSize());
			System.out.println("getMaxSize:" + threadpool.getMaxSize());
			System.out.println("=============================");
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
