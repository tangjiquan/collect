package com.panther.study.dubbo.lesson01.chapter02.dependency.provider.impl;


import com.panther.study.dubbo.lesson01.chapter02.dependency.provider.DependencyService;

/**
 * @author: Kevin
 * @date: created in 下午2:13 2018-10-02
 * @version: V1.0
 */
public class DependencyServiceImpl implements DependencyService {

	@Override
	public String dependency(String value){
		return "hello world " + value;
	}
}
