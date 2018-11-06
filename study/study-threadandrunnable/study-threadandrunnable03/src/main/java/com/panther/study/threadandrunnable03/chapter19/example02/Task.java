package com.panther.study.threadandrunnable03.chapter19.example02;

/**
 * 提供给调用者实现逻辑， 接受一个参数并且返回最终的计算结果
 * @author: Kevin
 * @date: created in 下午9:31 2018-11-06
 * @version: V1.0
 */
public interface Task<IN, OUT> {

	// 给定一个参数， 经过计算返回结果
	OUT get(IN input);
}
