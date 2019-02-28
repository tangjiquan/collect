package com.panther.study.algorithm.algorithm02.array;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * 给出一个int类型的数组，两个数组下标的值相减小于指定的k值，并且两个数组的值相减小于t
 * 
 * @author: Kevin
 * @date: created in 上午8:39 2019-02-27
 * @version: V1.0
 */
public class Array0008ContainsDuplicate3 {

	public boolean containsNearByAlmostDuplicate(int[] nums, int k, int t){
		if(k<1 || t<1 || nums==null || nums.length<2){
			return false;
		}

		SortedSet<Long> set = new TreeSet<Long>();

		for(int j=0; j<nums.length; j++) {
			SortedSet<Long> subSet = set.subSet((long)nums[j]-t, (long)nums[j]+t+1);
			if(!subSet.isEmpty()){
				// 打印set
				for(Long l : subSet){
					System.out.print(l + " ");
				}
				return true;
			}
			if(j>=k){
				set.remove(nums[j-k]);
			}
			set.add((long)nums[j]);
		}
		return false;
	}

	public static void main(String[] args){
		Array0008ContainsDuplicate3 instance = new Array0008ContainsDuplicate3();
		boolean flag = instance.containsNearByAlmostDuplicate(new int[]{3,4,5,6}, 3, 2);
		System.out.println(flag);
	}
}
