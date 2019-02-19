package com.panther.study.threadandrunnable06.chapter01.example01;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 
 * Futuretask方式可以拿到返回结果
 *
 * @author: Kevin
 * @date: created in 下午10:54 2018-12-21
 * @version: V1.0
 */
public class CallerTaskTest extends Thread {

	public static class MyCallerTask implements Callable<String>{

		@Override
		public String call() {
			return "a thread task";
		}
	}

	public static void main(String[] args){
		// 创建异步任务
		FutureTask<String> futureTask = new FutureTask<String>(new MyCallerTask());
		// 启动线程
		new Thread(futureTask).start();
		String result = null;
		try {
			// 等待任务执行完毕， 等待返回结果
			result = futureTask.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println(result);
	}
}
