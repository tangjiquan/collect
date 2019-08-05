package com.panther.study.algorithm.algorithm02.array;

/**
 * https://www.cnblogs.com/271934Liao/p/7053406.html
 *
 *
 * 根据题目要求，数组里的每个元素表示从该位置可以跳出的最远距离，
 * 要求问从第一个元素（index=0）开始，能否达到数组的最后一个元素，这里认为最后一个元素为终点。
 * 这里是到达，说明超过也行
 *
 * @author: Kevin
 * @date: created in 上午8:43 2019-03-05
 * @version: V1.0
 */
public class Array0013MajorityElement {


	public int majorityElement(int[] nums) {
		int m = nums[0];
		int c = 1;
		for (int i = 1; i < nums.length; i++) {
			if (m == nums[i]) {
				c++;
			} else if (c > 1) {
				c--;
			} else {
				m = nums[i];
			}
		}
		return m;
	}


	public static void main(String[] args){
		Array0013MajorityElement instance = new Array0013MajorityElement();
		int[] array = new int[]{2,3,5,5,5,5,2};
		int result = instance.majorityElement(array);
		System.out.println(result);
	}
}
