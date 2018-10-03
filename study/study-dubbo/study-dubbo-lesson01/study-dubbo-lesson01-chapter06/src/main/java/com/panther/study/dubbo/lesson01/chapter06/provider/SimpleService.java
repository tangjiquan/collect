package com.panther.study.dubbo.lesson01.chapter06.provider;


import com.panther.study.dubbo.lesson01.chapter06.entity.Simple;

public interface SimpleService {
	
	public String sayHello(String name);

	public Simple getSimple();
}
