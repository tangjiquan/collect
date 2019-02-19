package com.panther.study.jpo.jvm.lesson01;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 堆内存溢出
 *
 * # 打印堆内存的的快照，设置堆内存的大小
 * -XX:+HeapDumpOnOutOfMemoryError -Xms20m -Xmx20m -XX:HeapDumpPath=/java.dump -XX:+PrintGCDetails
 *
 * @author: Kevin
 * @date: created in 下午6:50 2019-02-04
 * @version: V1.0
 */
public class OutOfMemory {

	public static void main(String[] args){
		List<Person> list = new ArrayList<Person>();
		while(true){
			list.add(new Person());
		}

	}


	static class Person{

	}
}
