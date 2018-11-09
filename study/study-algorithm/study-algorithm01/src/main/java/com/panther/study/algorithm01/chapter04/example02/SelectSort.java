package com.panther.study.algorithm01.chapter04.example02;

/**
 * 选择排序
 *
 * 选择排序的算法通过选择和交换实现排序，　其排序流程如下：
 * 1. 首先从原始数组中选择最小的１个数据，　将其和位置第１个位置的数据交换
 * 2. 接着从剩下的n-1个数据中选择次小的１个元素，　将其和第２个位置的数据交换
 * 3. 然后，　这样不断的重复，　直到最后两个数据完成交换，　至此变完成了对原始数组的从小到大的排序
 *
 * @author: Kevin
 * @date: created in 上午8:54 2018-11-09
 * @version: V1.0
 */
public class SelectSort {

	private static final int SIZE = 10;

	public static void selectSort(int[] a){
		int index, temp;
		for(int i=0; i<a.length; i++){
			index = i;
			for(int j=i+1; j<a.length-1; j++){
				if(a[j]<a[index]){
					index = j;
				}
			}
			if(index != i){
				temp = a[i];
				a[i] = a[index];
				a[index] = temp;
			}
			System.out.println("第" + i + "步排序结果:");
			for(int k=0; k<a.length; k++){
				System.out.print(a[k] + " ");
			}
			System.out.println("\n");
		}
	}

	public static void main(String[] args){
		int [] arr = new int[SIZE];
		for(int i=0; i<SIZE; i++){
			arr[i] = (int) (100 + Math.random()*(100+1));
		}
		System.out.println("排序前数组结果:");

		for(int i=0; i<SIZE; i++){
			System.out.print(arr[i] + " ");
		}
		System.out.println("\n");
		selectSort(arr);
		System.out.println("排序后数组结果:");

		for(int i=0; i<SIZE; i++){
			System.out.print(arr[i] + " ");
		}

	}

}
