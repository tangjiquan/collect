package com.panther.study.designpattern.example04.test01;

/**
 * @author: Kevin
 * @date: created in 下午6:07 2018-09-19
 * @version: V1.0
 */
public class MainTest {

	public static void main(String[] args){
		// 创建需要被适配的对象
		Adaptee adaptee = new Adaptee();
		// 创建客户端需要被调用的接口对象
		Target target = new Adapter(adaptee);
		// 请求处理
		target.request();

	}
}
