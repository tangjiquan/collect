package com.panther.study.threadandrunnable03.chapter24.example01;

/**
 * @author: Kevin
 * @date: created in 下午8:11 2018-11-11
 * @version: V1.0
 */
public class Operator {

	public void call(String business){
		// 为每一个请求创建一个线程去处理
		TaskHandler taskHandler = new TaskHandler(new Request(business));
		new Thread(taskHandler).start();

	}
}
