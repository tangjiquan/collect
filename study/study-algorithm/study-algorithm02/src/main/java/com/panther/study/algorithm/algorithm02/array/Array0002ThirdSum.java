package com.panther.study.algorithm.algorithm02.array;

import java.util.*;

/**
 * 给出一个数组，其中包含了a+b+c=0，找出该3个数
 * {-1 0 1 2 -1 -4},
 * 找出如下的3个数据：(-1, 0, 1), (-1, -2, 2)
 *
 * @author: Kevin
 * @date: created in 上午8:42 2019-02-18
 * @version: V1.0
 */
public class Array0002ThirdSum {


	public List<List<Integer>> threeSum(int[] nums){
		if(nums == null || nums.length < 3){
			return new ArrayList<List<Integer>>();
		}

		Set<List<Integer>> set = new HashSet<List<Integer>>();

		Arrays.sort(nums);

		for(int start=0; start<nums.length; start++){
			if(start!=0 && nums[start-1] == nums[start]){
				continue;
			}

			int mid = start + 1, end = nums.length - 1;
			while(mid < end){
				int sum = nums[start] + nums[mid] + nums[end];
				if(sum == 0){
					List<Integer> tmp = new ArrayList<Integer>();
					tmp.add(nums[start]);
					tmp.add(nums[mid]);
					tmp.add(nums[end]);
					set.add(tmp);

					while(++mid<end && nums[mid-1] == nums[mid])
						;
					while(--end>end && nums[end+1] == nums[mid])
						;

				}else if(sum < 0){
					mid++;
				}else{
					end--;
				}
			}
		}
		 return new ArrayList<List<Integer>>(set);

	}


	public static void main(String[] args){
		List<List<Integer>> lists = new Array0002ThirdSum().threeSum(new int[]{-1,0,1,2,-1,-4});
		for(List<Integer> list : lists){
			for(Integer i : list){
				System.out.println(i + "  ");
			}
			System.out.println();
		}
	}
}
