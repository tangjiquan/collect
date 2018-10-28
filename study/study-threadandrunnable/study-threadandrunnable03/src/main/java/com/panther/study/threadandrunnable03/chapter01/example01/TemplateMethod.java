package com.panther.study.threadandrunnable03.chapter01.example01;

/**
 * 模板方法设计模式
 *
 * @author: Kevin
 * @date: created in 下午10:46 2018-09-15
 * @version: V1.0
 */
public class TemplateMethod {

	public final void print(String msg){
		System.out.println("######");
		wrapPring(msg);
		System.out.println("#######");
	}

	public void wrapPring(String msg){

	}

	public static void main(String[] args){
		TemplateMethod m1 = new TemplateMethod(){

			@Override
			public void wrapPring(String msg) {
				System.out.println(msg);
			}
		};

		TemplateMethod m2 = new TemplateMethod(){

			@Override
			public void wrapPring(String msg) {
				System.out.println(msg);
			}
		};

		m1.wrapPring("m1");
		m2.wrapPring("m2");

	}
}
