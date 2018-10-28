package com.panther.study.threadandrunnable03.chapter14.example01;

/**
 * Holder方式
 *
 *
 * @author: Kevin
 * @date: created in 下午8:41 2018-10-28
 * @version: V1.0
 */
public final class Singleton06 {

	private byte[] data = new byte[1024];

	private Singleton06(){}

	// 在静态内部类中持有Singleton的实例，并且可被直接初始化
	private static class Holder{
		private static Singleton06 instace = new Singleton06();
	}

	// 调用getInstance方法， 事实上获取Holder的instace静态内部属性
	public static Singleton06 getInstace(){
		return Holder.instace;
	}
}
