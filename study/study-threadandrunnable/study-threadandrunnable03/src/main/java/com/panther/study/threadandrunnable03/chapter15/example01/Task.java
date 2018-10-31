package com.panther.study.threadandrunnable03.chapter15.example01;

/**
 * @author: Kevin
 * @date: created in 下午9:39 2018-10-30
 * @version: V1.0
 */
public interface Task<T> {

	// 任务执行接口，该接口允许返回值
	T call();
}
