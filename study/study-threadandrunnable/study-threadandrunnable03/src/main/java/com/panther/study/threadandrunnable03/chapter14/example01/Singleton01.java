package com.panther.study.threadandrunnable03.chapter14.example01;

/**
 * 饿汉式单例设计模式
 *
 *
 * @author: Kevin
 * @date: created in 下午8:11 2018-10-28
 * @version: V1.0
 */
public final class Singleton01 {

	private byte[] data = new byte[1024];

	// 定义实例对象的时候直接初始化
	private static Singleton01 instance = new Singleton01();

	private Singleton01(){}

	public static Singleton01 getInstance(){
		return instance;
	}
}
