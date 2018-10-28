package com.panther.study.designpattern.example02.test01;

/**
 * @author: Kevin
 * @date: created in 上午10:59 2018-09-16
 * @version: V1.0
 */
public class MainTest {

	public static void main(String[] args){
		Api api = new ApiImpl();
		api.test1("hello!");
	}
}
