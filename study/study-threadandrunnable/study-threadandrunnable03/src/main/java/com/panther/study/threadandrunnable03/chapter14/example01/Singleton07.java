package com.panther.study.threadandrunnable03.chapter14.example01;

/**
 * 枚举方式
 *
 * @author: Kevin
 * @date: created in 下午8:46 2018-10-28
 * @version: V1.0
 */
public enum  Singleton07 {
	INSTACE;

	Singleton07(){
		System.out.println("instace will be init");
	}

	public static void method(){
		// 调用方法则会主动使用Singleton, INSTACE将会实例话
	}

	public static Singleton07 getInstace(){
		return INSTACE;
	}
}
