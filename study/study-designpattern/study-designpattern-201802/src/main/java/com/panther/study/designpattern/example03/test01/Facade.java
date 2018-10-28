package com.panther.study.designpattern.example03.test01;

/**
 * @author: Kevin
 * @date: created in 下午2:57 2018-09-16
 * @version: V1.0
 */
public class Facade {


	public void test(){
		AModuleApi a = new AModuleApiImple();
		a.testA();
		BModuleApi b = new BModuleApiImple();
		b.testB();

	}
}
