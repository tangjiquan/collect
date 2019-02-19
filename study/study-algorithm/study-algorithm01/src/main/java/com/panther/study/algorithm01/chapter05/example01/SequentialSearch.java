package com.panther.study.algorithm01.chapter05.example01;

import java.util.Scanner;

/**
 * 顺序查找
 *
 * @author: Kevin
 * @date: created in 上午8:36 2018-12-14
 * @version: V1.0
 */
public class SequentialSearch {

	static final int N = 15;

	static int searchFun(int a[], int n, int x){
		int i, f = -1;
		for(i=0; i<n; i++){
			if(x == a[i]){
				f = i;
				break;
			}
		}
		return f;
	}

	public static void main(String[] args){
		int x, n;
		int[] arr = new int[N];
		for(int i=0; i<N; i++){
			arr[i] = (int)(Math.random()*(100+1));

		}
		System.out.println("顺序查找算法演示");
		System.out.println("数据顺序");
		for(int i=0; i<N; i++){
			System.out.println(" " + arr[i]);
		}

		System.out.println("要查找的数据为：");
		Scanner input = new Scanner(System.in);
		x = input.nextInt();// 要查找的数据
		n = searchFun(arr, N, x);// 返回数组下标
		if(n<0){
			System.out.println("没找到数据" + n);
		}else{
			System.out.println("数据" + x + "位于" + (n+1) + "的位置");
		}


	}

}
