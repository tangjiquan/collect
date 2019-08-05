package com.panther.study.monitorandtuning.lesson02.chapter02.chapter2_1_1;

/**
 * @author: Kevin
 * @date: created in 下午9:30 2019-07-02
 * @version: V1.0
 */
public class SingletonV1 {

	private SingletonV1(){
		System.out.println("SingletonV1 is create");
	}

	public static SingletonV1 instance = new SingletonV1();

	public static SingletonV1 getInstance(){
		return instance;
	}
}
