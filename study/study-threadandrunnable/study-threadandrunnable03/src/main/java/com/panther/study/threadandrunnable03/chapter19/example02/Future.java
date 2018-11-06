package com.panther.study.threadandrunnable03.chapter19.example02;

/**
 * Future提供了获取计算结果和判断任务是否完成的两个接口
 * @author: Kevin
 * @date: created in 下午9:24 2018-11-06
 * @version: V1.0
 */
public interface Future<T> {

	// 返回计算结果， 该方法会陷入阻塞状态
	T get() throws InterruptedException;

	// 判定任务是否已经被执行完成
	boolean done();
}
