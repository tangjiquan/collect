package com.panther.study.algorithm.algorithm02.array;

import java.util.Arrays;

/**
 * 问题：
 * 		假设按照升序排序的数组在预先未知的某个点上进行了旋转
 * 	例如：数组[0,1,2,4,5,6,7]可能变为[4,5,6,7,0,1,2]
 * 	找出最小的那个值
 *
 * 思路：
 * 		数列基本有序，则使用二分查找。假设数列是n，s是起始下标，e是最后下标，m是中间元素下标。分为三种情况：
 *		1. n[s] < n[e]
 		2. n[m] > n[s] > n[e]
 		3. n[m] < n[e] < n[s]
 *	情况1：n[s] < n[e]
 		0 1 2 4 5 6 7
 		这种情况，直接返回n[s]。因为数列是有序的，或者只是旋转过一次。如果n[s] < n[e]，则表明没有旋转。最小元素是n[s]。

 	情况2：n[m] > n[s] > n[e]
 		4 5 6 7 0 1 2
 		只需要满足n[m] > n[s]即可，因为第一种情况排除后，n[s]就一定会 > n[e]。
 	在本例中：
		 n[s] = 4
		 n[m] = 7
		 n[e] = 2
		 则最小值肯定在7之后，到2之间，即 [m+1, e]。

 	情况3：n[m] < n[e] < n[s]
	 	6 7 0 1 2 4 5
 	n[m] < n[e]，在本例中：
		 n[s] = 6
		 n[m] = 1
		 n[e] = 5
 	则表明，从m到e，数组已经是上升的，所以最小值在[s, m]。


 *
 *
 * @author: Kevin
 * @date: created in 上午9:09 2019-02-28
 * @version: V1.0
 */
public class Array0009FindMinimuminRotatedSortedArray {



	public int findMin(int[] nums){
		if(nums.length == 1){
			return nums[0];
		}
		if(nums.length == 2){
			return Math.min(nums[0], nums[1]);
		}
		int s=0, e=nums.length - 1;
		int m=(s+e)/2;
		if(nums[s]<nums[e]){
			return nums[s];
		}
		if(nums[m]>nums[s]){
			return findMin(Arrays.copyOfRange(nums, m+1, e+1));
		}
		return findMin(Arrays.copyOfRange(nums, s, m+1));
	}

	public static void main(String[] args){
		Array0009FindMinimuminRotatedSortedArray instace = new Array0009FindMinimuminRotatedSortedArray();
		//int[] array = new int[]{0,1,2,4,5,6,7};
		//int[] array = new int[]{4,5,6,7,0,1,2};
		int[] array = new int[]{6,7,0,1,2,4,5};

		int flag = instace.findMin(array);
		System.out.println(flag);
	}

}
