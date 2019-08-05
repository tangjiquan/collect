package org.panther.study.motan.example01;

/**
 * @author: Kevin
 * @date: created in 下午11:11 2019-03-09
 * @version: V1.0
 */
public class FooServiceImpl implements FooService{
	@Override
	public String hello(String name) {
		System.out.println(name + " invoked rpc service");
		return "hello " + name;
	}


}
