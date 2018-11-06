package com.panther.study.threadandrunnable03.chapter19.example01;

/**
 * @author: Kevin
 * @date: created in 下午9:28 2018-11-06
 * @version: V1.0
 */
public interface FutureService<IN, OUT> {

	// 提交不需要返回值的任务， Future.get方法返回的是null
	Future<?> submit(Runnable runnable);

	// 提交需要返回值的任务， 其中task接口代替了Runnable
	Future<OUT> submit(Task<IN, OUT> task, IN input);


}
