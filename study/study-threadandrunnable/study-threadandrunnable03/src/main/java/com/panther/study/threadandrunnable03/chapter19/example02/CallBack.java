package com.panther.study.threadandrunnable03.chapter19.example02;

/**
 * @author: Kevin
 * @date: created in 下午10:43 2018-11-06
 * @version: V1.0
 */
public interface CallBack<T> {
	/**
	 * 当任务完成时候调用该方法， 其中T为任务执行后的结果
	 *
	 * @param t
	 */
	void call(T t);
}
