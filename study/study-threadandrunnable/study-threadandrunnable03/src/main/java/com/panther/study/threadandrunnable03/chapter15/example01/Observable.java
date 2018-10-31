package com.panther.study.threadandrunnable03.chapter15.example01;

/**
 * @author: Kevin
 * @date: created in 下午9:32 2018-10-30
 * @version: V1.0
 */
public interface Observable {

	// 任务生命周期的枚举类型
	enum Cycle{
		STARTED, RUNNING, DONE, ERROR
	}

	Cycle getCycle();

	// 定义启动线程的方法， 主要作用是为了屏蔽Thread的其他方法
	void start();

	// 定义线程的打断方法
	void interrupt();

}
