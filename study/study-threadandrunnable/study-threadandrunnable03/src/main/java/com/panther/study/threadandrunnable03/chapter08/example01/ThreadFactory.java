package com.panther.study.threadandrunnable03.chapter08.example01;

/**
 * 创建线程工厂
 *
 * @author: Kevin
 * @date: created in 下午8:56 2018-10-21
 * @version: V1.0
 */
public interface ThreadFactory {
	Thread createThread(Runnable runnable);
}
