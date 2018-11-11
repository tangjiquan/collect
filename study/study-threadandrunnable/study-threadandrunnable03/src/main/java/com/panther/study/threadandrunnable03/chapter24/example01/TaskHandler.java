package com.panther.study.threadandrunnable03.chapter24.example01;

import java.util.concurrent.TimeUnit;

/**
 * TaskHandler处理每一个提交的request请求
 *
 *
 * @author: Kevin
 * @date: created in 下午8:08 2018-11-11
 * @version: V1.0
 */
public class TaskHandler implements Runnable{

	private final Request request;

	public TaskHandler(Request request){
		this.request = request;
	}
	@Override
	public void run() {
		System.out.println("begin handler " + request);
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("end handler " + request);
	}



}
