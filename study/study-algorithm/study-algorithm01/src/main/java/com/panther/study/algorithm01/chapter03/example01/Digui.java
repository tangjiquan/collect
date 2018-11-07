package com.panther.study.algorithm01.chapter03.example01;

/**
 * 递归算法
 * 递归算法是在不断的反复的调用自身达到求解问题的方法
 *
 * 方法递归有两种：
 * 	直接递归：方法中调用方法本身
 * 	简介递归：fun_a调用fun_b, fun_b又调用fun_a
 *
 * @author: Kevin
 * @date: created in 上午9:18 2018-11-07
 * @version: V1.0
 */
public class Digui {

	/**
	 * 递归算法计算阶乘
	 *
	 * @param n
	 * @return
	 */
	static long fact(int n){
		if(n<=1){
			return 1;
		}else{
			return n*fact(n-1);
		}
	}

	public static void main(String[] args){
		int n = 5;
		long result = fact(n);
		System.out.println(n + "的阶乘为" + result);
	}
}
