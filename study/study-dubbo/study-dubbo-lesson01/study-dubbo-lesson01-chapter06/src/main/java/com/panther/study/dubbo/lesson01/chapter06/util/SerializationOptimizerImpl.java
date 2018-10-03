package com.panther.study.dubbo.lesson01.chapter06.util;

import com.alibaba.dubbo.common.serialize.support.SerializationOptimizer;
import com.panther.study.dubbo.lesson01.chapter06.entity.User;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: Kevin
 * @date: created in 下午9:38 2018-10-03
 * @version: V1.0
 */
public class SerializationOptimizerImpl implements SerializationOptimizer {
	@Override
	public Collection<Class> getSerializableClasses() {
		List<Class> classes = new LinkedList<Class>();
		//这里可以把所有需要进行序列化的类进行添加
		classes.add(User.class);
		return classes;
	}
}
