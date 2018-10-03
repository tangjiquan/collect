package com.panther.study.dubbo.lesson01.chapter01.sample.cumtomer;

import java.util.List;

/**
 * @author: Kevin
 * @date: created in 下午2:13 2018-10-02
 * @version: V1.0
 */
public interface SampleService {

	public String sayHello(String name);

	public List getUsers();
}
