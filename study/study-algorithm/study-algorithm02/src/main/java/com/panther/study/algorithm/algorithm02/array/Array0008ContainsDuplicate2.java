package com.panther.study.algorithm.algorithm02.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 给出一个int类型的数组，如果其中有重复数据，并且两个数组下标的值相减小于指定的k值，则返回true， 否则返回false
 * 
 * @author: Kevin
 * @date: created in 上午8:39 2019-02-26
 * @version: V1.0
 */
public class Array0008ContainsDuplicate2 {

	public boolean contaionsDuplicate(int[] nums, int k){
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i=0; i<nums.length; i++){
			if(map.containsKey(nums[i])){
				if((i-map.get(nums[i]))<k){
					return true;
				}

			}
			map.put(nums[i], i);
		}
		return false;
	}

	public static void main(String[] args){
		Array0008ContainsDuplicate2 instance = new Array0008ContainsDuplicate2();
		int[] array = new int[]{4, 5, 6, 9, 8, 9};
		boolean flag = instance.contaionsDuplicate(array, 2);
		System.out.println(flag);

	}
}
