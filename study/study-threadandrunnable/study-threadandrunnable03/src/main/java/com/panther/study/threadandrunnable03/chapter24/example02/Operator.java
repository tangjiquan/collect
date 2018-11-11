package com.panther.study.threadandrunnable03.chapter24.example02;

import com.panther.study.threadandrunnable03.chapter08.example01.BasicThreadPool;
import com.panther.study.threadandrunnable03.chapter08.example01.ThreadPool;

/**
 * 使用线程池代替每一个请求创建线程
 *
 * @author: Kevin
 * @date: created in 下午8:11 2018-11-11
 * @version: V1.0
 */
public class Operator {

	private final ThreadPool threadPool = new BasicThreadPool(2,6,4,1000);



	public void call(String business){
		// 为每一个请求创建一个线程去处理
		TaskHandler taskHandler = new TaskHandler(new Request(business));
		threadPool.execute(taskHandler);

	}
}
