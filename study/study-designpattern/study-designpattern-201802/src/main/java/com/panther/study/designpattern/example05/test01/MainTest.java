package com.panther.study.designpattern.example05.test01;

/**
 * @author: Kevin
 * @date: created in 下午3:39 2018-10-31
 * @version: V1.0
 */
public class MainTest {

	public static void main(String[] args){
		AppConfig appConfig = new AppConfig();
		String paramA = appConfig.getParameterA();
		String paramB = appConfig.getParameterB();
		System.out.println("paramA:" + paramA + ", paramB:" +paramB );

	}
}
