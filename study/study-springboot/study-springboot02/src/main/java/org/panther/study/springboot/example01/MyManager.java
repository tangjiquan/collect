package org.panther.study.springboot.example01;

/**
 *
 * 	BaseManager的子类， 此类需要实现java类的热加载功能
 * @author: Kevin
 * @date: created in 上午10:50 2018-07-08
 * @version: V1.0
 */
public class MyManager implements BaseManager{
	@Override
	public void logic() {
		System.out.println("ewwweee");

	}
}
