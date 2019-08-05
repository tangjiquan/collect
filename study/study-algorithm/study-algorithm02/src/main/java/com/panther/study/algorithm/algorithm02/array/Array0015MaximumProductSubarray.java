package com.panther.study.algorithm.algorithm02.array;

/**
 * 问题：
 * 	连续乘积最大的子数组
 * 思路：
 * 	在乘积中，因为负数*负数=正数，所以连续乘积为负数时，并不能舍弃这个数，因为如果数组的元素是负数，它可能成为乘积最大的数。
 * 	要定义两个变量：positive和negative，分别记录当前乘积最大值和最小值。
 * @author: Kevin
 * @date: created in 上午8:46 2019-03-07
 * @version: V1.0
 */
public class Array0015MaximumProductSubarray {

	public int maxProduct(int[] nums) {
		int max = nums[0];
		int positive = nums[0];
		int negative = nums[0];
		for (int i = 1; i < nums.length; i++) {
			positive *= nums[i];
			negative *= nums[i];
			if (positive < negative) {
				int t = positive;
				positive = negative;
				negative = t;
			}
			positive = Math.max(positive, nums[i]);
			negative = Math.min(negative, nums[i]);
			max = Math.max(max, positive);
		}
		return max;
	}

	public static void main(String[] args){
		Array0015MaximumProductSubarray instance = new Array0015MaximumProductSubarray();
		int[] array = new int[]{1,-2,-3,4,-5};
		int result = instance.maxProduct(array);
		System.out.println(result);
	}
}
