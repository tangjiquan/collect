package com.panther.study.threadandrunnable03.chapter14.example01;

/**
 * 枚举方式 + Holder
 * 增加懒汉式的特性，类似Holder
 *
 * @author: Kevin
 * @date: created in 下午8:46 2018-10-28
 * @version: V1.0
 */
public class Singleton08 {

	private Singleton08(){}

	private enum EnumHolder{
		INSTANCE;
		private Singleton08 instace;

		EnumHolder(){
			this.instace = new Singleton08();
		}

		private Singleton08 getInstace(){
			return instace;
		}
	}

	public static Singleton08 getInstance(){
		return EnumHolder.INSTANCE.getInstace();
	}

}
