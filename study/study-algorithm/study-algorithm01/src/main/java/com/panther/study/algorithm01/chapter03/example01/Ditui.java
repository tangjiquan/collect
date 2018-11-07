package com.panther.study.algorithm01.chapter03.example01;

/**
 * 递推算法
 *
 * 有着明确的计算公式可以遵循， 往往需要用户知道答案和问题间的逻辑关系
 *
 *
 * @author: Kevin
 * @date: created in 上午9:09 2018-11-07
 * @version: V1.0
 */
public class Ditui {

	public static int fibonacci(int n){
		int t1, t2;
		if(n==1 || n==2){
			return 1;
		}else{
			t1 = fibonacci(n-1);
			t2 = fibonacci(n-2);
			return t1 + t2;
		}
	}

	public static void main(String[] args){
		int month = 10;
		int num = fibonacci(month);
		System.out.println("经过" + month + "个月，共繁殖" + num + "对兔子");
	}
}
