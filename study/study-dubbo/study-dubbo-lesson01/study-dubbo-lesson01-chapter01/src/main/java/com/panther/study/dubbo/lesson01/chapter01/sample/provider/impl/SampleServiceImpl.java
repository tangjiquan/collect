package com.panther.study.dubbo.lesson01.chapter01.sample.provider.impl;

import com.panther.study.dubbo.lesson01.chapter01.sample.provider.SampleService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Kevin
 * @date: created in 下午2:13 2018-10-02
 * @version: V1.0
 */
public class SampleServiceImpl implements SampleService {

	@Override
	public String sayHello(String name){
		return "hello " + name;
	}
	@Override
	public List getUsers(){
		List list = new ArrayList();
		User u1 = new User();
		u1.setName("zhangsan");
		u1.setAge(11);
		u1.setSex("F");
		list.add(u1);
		return list;
	}
}
