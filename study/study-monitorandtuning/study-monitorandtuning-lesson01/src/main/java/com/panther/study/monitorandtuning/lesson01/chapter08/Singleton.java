package com.panther.study.monitorandtuning.lesson01.chapter08;

public class Singleton {
	private Singleton() {}
	private static class SingletonHolder{
		private static Singleton instance = new Singleton();
	}
	public static Singleton getInstance() {
		return SingletonHolder.instance;
	}
}
