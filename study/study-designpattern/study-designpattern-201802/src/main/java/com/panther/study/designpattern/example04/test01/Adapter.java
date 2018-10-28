package com.panther.study.designpattern.example04.test01;

/**
 * @author: Kevin
 * @date: created in 下午6:05 2018-09-19
 * @version: V1.0
 */
public class Adapter implements Target {

	private Adaptee adaptee;

	public Adapter(Adaptee adaptee){
		this.adaptee = adaptee;
	}
	@Override
	public void request() {
		// 可能转调已经实现的方法， 进行适配
		adaptee.specificRequest();
	}
}
