package com.panther.study.algorithm.algorithm02.array;

import java.util.HashSet;
import java.util.Set;

/**
 * 给出一个int类型的数组，如果其中有重复数据，则返回true， 否则返回false
 *
 * @author: Kevin
 * @date: created in 上午8:39 2019-02-26
 * @version: V1.0
 */
public class Array0008ContainsDuplicate {

	public boolean contaionsDuplicate(int[] nums){
		Set<Integer> set = new HashSet<Integer>();
		for(Integer i : nums){
			if(set.contains(i)){
				return true;
			}
			set.add(i);
		}
		return false;
	}

	public static void main(String[] args){
		Array0008ContainsDuplicate instance = new Array0008ContainsDuplicate();
		int[] array = new int[]{4, 5, 6, 7, 8, 9};
		boolean flag = instance.contaionsDuplicate(array);
		System.out.println(flag);

	}
}
