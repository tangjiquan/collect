package com.panther.study.jpo.jvm.lesson02;

/**
 * 在栈上分配内存
 * -server -Xmx100m -Xms100m -XX:+DoEscapeAnalysis -XX:+PrintGC
 * 在堆上分配内存容易出发GC
 * -server -Xmx100m -Xms100m -XX:-DoEscapeAnalysis -XX:+PrintGC
 *
 * @author: Kevin
 * @date: created in 下午1:31 2019-02-12
 * @version: V1.0
 */
public class OnStackTest {

	public static void alloc(){
		byte[] b = new byte[2];
		b[0] = 1;
	}

	public static void main(String[] args){
		long b = System.currentTimeMillis();
		for(int i=0; i<10000000; i++){
			alloc();
		}
		long e = System.currentTimeMillis();
		System.out.println(e-b);
	}

}
