package com.panther.study.threadandrunnable03.chapter15.example01;

/**
 * @author: Kevin
 * @date: created in 下午9:35 2018-10-30
 * @version: V1.0
 */
public interface TaskLifecycle<T> {

	// 任务启动的时候触发onStart方法
	void onStart(Thread thread);

	// 任务正在运行的时候触发onRunning
	void onRunning(Thread thread);

	// 任务运行结束触发onFinish方法，其中result是任务运行后的结果
	void onFinish(Thread thread, T result);

	// 任务执行报错时会触发onError方法
	void onError(Thread thread, Exception e);


	class EmptyLifecycle<T> implements TaskLifecycle<T>{

		@Override
		public void onStart(Thread thread) {

		}

		@Override
		public void onRunning(Thread thread) {

		}

		@Override
		public void onFinish(Thread thread, T result) {

		}

		@Override
		public void onError(Thread thread, Exception e) {

		}
	}
}
