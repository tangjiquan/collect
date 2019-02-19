package com.panther.study.jpo.jvm.lesson04;

/**
 *
 * 调整虚拟机栈大小
 * 虚拟机栈是线程独有的空间， 没一个线程有一份虚拟机栈空间，jdk1.8默认分配的虚拟机空间为1M，下的例子运行会发生异常
 *  方法会调用22536次，然后出现异常，如果改变栈的大小，-Xss3m， 则栈调用76118次然后再出现异常
 *
 *
 * @author: Kevin
 * @date: created in 下午11:07 2019-02-19
 * @version: V1.0
 */
public class Test03 {

	static int count = 0;

	static void fun(){
		count++;
		fun();
	}

	public static void main(String[] args){

		try{
			fun();
		}catch (Exception e){
			System.out.println(count);
			e.printStackTrace();
		}

	}

}
