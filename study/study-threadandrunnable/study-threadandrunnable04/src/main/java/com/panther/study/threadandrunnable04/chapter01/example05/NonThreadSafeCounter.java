package com.panther.study.threadandrunnable04.chapter01.example05;

/**
 * 非线程安全的计数器
 *
 * @author: Kevin
 * @date: created in 下午10:40 2018-11-22
 * @version: V1.0
 */
public class NonThreadSafeCounter {
	private int count = 0;

	public void increment(){
		count++;
	}

	public int get(){
		return count;
	}
}
