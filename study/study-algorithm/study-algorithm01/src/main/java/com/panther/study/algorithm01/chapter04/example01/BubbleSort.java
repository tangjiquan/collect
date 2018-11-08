package com.panther.study.algorithm01.chapter04.example01;

/**
 * 冒泡排序算法
 *
 * @author: Kevin
 * @date: created in 上午9:16 2018-11-08
 * @version: V1.0
 */
public class BubbleSort {

	static final int SIZE = 10;

	public static void bubbleSort(int[] a) {
		int temp;
		for (int i = 1; i < a.length; i++) {
			for (int j = 0; j < a.length - 1; j++) {
				if (a[j] > a[j + 1]) {
					temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
			System.out.println("第" + i + "步排序结果");
			for (int k = 0; k < a.length; k++) {
				System.out.println("" + a[k]);
			}
			System.out.println("\n");
		}
	}


	public static void main(String[] args) {
		int[] arr = new int[SIZE];
		int i;
		for (i = 0; i < SIZE; i++) {
			arr[i] = (int) (100 + Math.random() * (100 + 1));// 初始化数组
		}
		System.out.println("排序前的数组为\n");
		for (i = 0; i < SIZE; i++) {
			System.out.print(arr[i] + " ");
		}
		bubbleSort(arr);
		System.out.println("排序后的数组为\n");
		for (i = 0; i < SIZE; i++) {
			System.out.print(arr[i] + " ");
		}
	}
}
