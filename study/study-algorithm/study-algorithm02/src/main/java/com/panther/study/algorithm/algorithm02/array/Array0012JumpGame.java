package com.panther.study.algorithm.algorithm02.array;

/**
 * @author: Kevin
 * @date: created in 上午8:56 2019-03-04
 * @version: V1.0
 */
public class Array0012JumpGame {

	public boolean canJump(int nums[]){
		if(nums == null || nums.length == 0){
			return false;
		}
		int maxStep = 0;
		for(int i=0; i<nums.length; i++){
			if(maxStep>=nums.length -1){
				return false;
			}
			if(nums[i] == 0 && maxStep == i){
				return false;
			}
			maxStep = Math.max(maxStep, nums[i] +1);
		}
		return true;
	}

	public static void main(String[] args){
		Array0012JumpGame instance = new Array0012JumpGame();
		int[] array1 = new int[]{2,3,1,1,4};
		//int[] array1 = new int[]{3,2,1,0,4};
		boolean flag = instance.canJump(array1);
		System.out.println(flag);
	}
}
