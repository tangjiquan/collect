package com.panther.study.jpo.jvm.lesson03;

/**
 * StackOverflowError 异常
 * 每当java程序代码启动一个新的线程时，java虚拟机都会为他分配一个java栈。Java栈以帧为单位保存线程的运行状态。
 * 当线程调用java方法时，虚拟机压入一个新的栈帧到该线程的java栈中。只要这个方法还没有返回，它就一直存在。
 * 如果线程的方法嵌套调用层次太多(如递归调用)，随着java栈中帧的逐渐增多，最终会由于该线程java栈中所有栈帧大小总
 * 和大于-Xss设置的值，而产生StackOverflowError内存溢出异常。随着-Xss参数值的增大，可以嵌套的方法调用层次也相应增加
 *
 * -Xss128k
 *
 * @author: Kevin
 * @date: created in 下午10:30 2019-02-14
 * @version: V1.0
 */
public class StackOverflowError {

	private int count;

	public static void main(String[] args){
		new StackOverflowError().method();
	}
	public void method(){
		System.out.println(count++);
		method();
	}

}
