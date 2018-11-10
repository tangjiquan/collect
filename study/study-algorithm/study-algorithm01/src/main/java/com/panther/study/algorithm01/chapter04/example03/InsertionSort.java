package com.panther.study.algorithm01.chapter04.example03;

/**
 * @author: Kevin
 * @date: created in 下午10:24 2018-11-10
 * @version: V1.0
 */
public class InsertionSort {

	static final int SIZE = 10;
	static void insertionSort(int[] a){
		int i, j, t, h;
		for(i=1; i<a.length; i++){
			t=a[i];
			j=i-1;
			while(j>=0 && t<a[j]){
				a[j+1]=a[j];
				j--;
			}
			a[j+1]=t;
			System.out.println("第"+i+"步排序结果");
			for(int k=0; k<a.length; k++){
				System.out.print(" "+a[k]);
			}
			System.out.println("\n");
		}
	}

	public static void main(String[] args){
		int[] arr = new int[SIZE];
		int i;
		for(i=0; i<SIZE; i++){
			arr[i] = (int)(100+Math.random()*(100+1));
		}
		System.out.println("排序前的数组为：");
		for(int k=0; k<arr.length; k++){
			System.out.print(" "+arr[k]);
		}
		System.out.println("\n");
		insertionSort(arr);
		System.out.println("排序后的数组为：");
		for(int k=0; k<arr.length; k++){
			System.out.print(" "+arr[k]);
		}

	}
}
