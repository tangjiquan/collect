package com.panther.study.algorithm.algorithm02.array;

import java.util.HashMap;

/**
 *
 * 给出两个int类型的数组，找出两个数据， 该两个数据的和是另一个给定的数据
 * 例如：
 * 		给出：numbers={6,7,8} target=13
 * 		输出：index=1, index=2
 *
 * @author: Kevin
 * @date: created in 上午8:45 2019-02-15
 * @version: V1.0
 */
public class Array0001TwoSum {

	public int[] twoSum(int[] nums, int target){
		if(nums == null || nums.length <= 1){
			return new int[2];
		}

		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i=0; i<nums.length; i++){
			map.put(target-nums[i], i);
		}

		// 判断减去的值是否在数组中
		for(int i=0; i<nums.length; i++){
			Integer v = map.get(nums[i]);
			if(v!=null && v!=i){
				return new int[]{v+1, i+1};
			}
		}
		return null;
	}

	public static void main(String[] args){
		Array0001TwoSum array0001TwoSum = new Array0001TwoSum();
		int[] result = array0001TwoSum.twoSum(new int[]{6, 8, 7}, 13);
		System.out.println(result[0] + "   " + result[1]);
	}

}
