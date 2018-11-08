package com.panther.study.threadandrunnable03.chapter22;

/**
 * @author: Kevin
 * @date: created in 下午9:01 2018-11-08
 * @version: V1.0
 */
public class MainTest  {

	public static void main(String[] args){
		new DocumentEditThread("/home/tangjiquan/Study/temp", "aa.txt").start();
	}
}
