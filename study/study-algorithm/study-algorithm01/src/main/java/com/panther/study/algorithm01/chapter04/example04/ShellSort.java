package com.panther.study.algorithm01.chapter04.example04;

/**
 * shell排序
 *
 * @author: Kevin
 * @date: created in 上午8:45 2018-11-14
 * @version: V1.0
 */
public class ShellSort {

	private static int SIZE = 10;
	public static void shellSort(int[] a){
		int i, j, h;
		int r, temp;
		int x;


	}

	public static void main(String[] args){
		int[] arr = new int[SIZE];


		for(int i=0; i<SIZE; i++){
			arr[i] = (int) (100+Math.random()*(100+1));
		}
		System.out.println("排序前的数组为：\n");
		for(int i=0; i<SIZE; i++){
			System.out.print(arr[i] + " ");
		}
		System.out.println("\n");
		shellSort(arr);

		System.out.println("排序后的数组为：\n");
		for(int i=0; i<SIZE; i++){
			System.out.print(arr[i] + " ");
		}
		System.out.println("\n");
	}

}
