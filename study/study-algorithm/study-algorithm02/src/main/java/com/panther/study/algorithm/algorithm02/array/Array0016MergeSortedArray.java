package com.panther.study.algorithm.algorithm02.array;

/**
 * 问题：
 * 	给定两个排序整数数组A和B，将b合并为一个排序数组。
 		注意：您可以假设a有足够的空间（大小大于或等于m + n）来保存来自B的附加元素。

 * @author: Kevin
 * @date: created in 上午8:42 2019-03-08
 * @version: V1.0
 */
public class Array0016MergeSortedArray {

	public int help(int[] nums, int k){
		if (k < 0) {
			return Integer.MIN_VALUE;
		}
		return nums[k];
	}

	public int[] merge(int[] nums1, int m, int[] nums2, int n){
		if (nums1 == null || nums2 == null) {
			return null;
		}
		int t = m + n - 1;
		while (t >= 0) {
			int n1 = help(nums1, m - 1);
			int n2 = help(nums2, n - 1);
			if (n1 > n2) {
				nums1[t] = n1;
				m--;
			} else {
				nums1[t] = n2;
				n--;
			}
			t--;
		}
		return nums1;
	}

	public static void main(String[] args){
		Array0016MergeSortedArray instace = new Array0016MergeSortedArray();
		int[] nums1 = new int[]{1,3};
		int[] nums2 = new int[]{2};
		int[] result = instace.merge(nums1,25, nums2, 11);

		for(int i=0; i<result.length; i++){
			System.out.println(result[i]);
		}

	}
}
