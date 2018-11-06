package com.panther.study.threadandrunnable03.chapter18.example01;

import java.util.Arrays;
import java.util.List;

/**
 * 非线程安全可变对象被不可变加以处理后， 也具备不可变性
 * ArrayList生成的stream在多线程的情况下也是线程安全的， 也具备不可变的结果
 * @author: Kevin
 * @date: created in 下午8:31 2018-11-05
 * @version: V1.0
 */
public class ArrayListStream {

	public static void main(String [] args){
		List<String> list = Arrays.asList("java", "Thread", "current", "scala", "clojure");
		//list.parallelStream().map(String::toUpperCase).forEach(System.out::println);
		//list.forEach(System.out::println);
	}

}
