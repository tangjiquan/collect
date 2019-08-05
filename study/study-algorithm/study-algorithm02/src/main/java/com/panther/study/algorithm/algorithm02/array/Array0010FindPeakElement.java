package com.panther.study.algorithm.algorithm02.array;

/**
 * 问题：给定一个相邻元素不相等的数组，找出其中一个局部最大值，返回对应的下表
 * 思路：peak element是一个比左边大，比右边也大的数据。一个数组中可能有多个peak element元素， 只需要随机返回一个
 * 举例：一个数组[1, 2, 3, 1],那么3就是peak element，他的下标就是2
 *
 *
 *
 * @author: Kevin
 * @date: created in 上午9:11 2019-03-01
 * @version: V1.0
 */
public class Array0010FindPeakElement {

	public int findPeakElement(int[] array){
		int low = 0;
		int high = array.length - 1;

		while(low<high){
			int mid = low + (high - low)/2;
			if(array[mid]>array[mid+1]){
				high = mid;
			}else{
				low = mid+1;
			}
		}
		return low;
	}

	public static void main(String[] args){
		Array0010FindPeakElement instace = new Array0010FindPeakElement();
		int[] array = new int[]{1, 2, 3, 4,5,1,6, 7, 9};
		int flag = instace.findPeakElement(array);
		System.out.println(flag);
	}
}
