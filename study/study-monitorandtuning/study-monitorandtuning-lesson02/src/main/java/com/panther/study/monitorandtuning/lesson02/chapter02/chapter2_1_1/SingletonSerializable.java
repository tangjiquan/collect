package com.panther.study.monitorandtuning.lesson02.chapter02.chapter2_1_1;

/**
 * @author: Kevin
 * @date: created in 下午9:47 2019-07-02
 * @version: V1.0
 */
public class SingletonSerializable {

	String name;

	private  SingletonSerializable(){
		System.out.println("SingletonSerializable is create");
		name = "SingletonSerializable";
	}

	private static SingletonSerializable instance = new SingletonSerializable();

	public static SingletonSerializable getInstance(){
		return instance;
	}

	public static void createString(){
		System.out.println("createString in Singleton");
	}

	private Object readResolve(){// 阻止生成新的实例，总是返回当前对象
		return instance;
	}

}

