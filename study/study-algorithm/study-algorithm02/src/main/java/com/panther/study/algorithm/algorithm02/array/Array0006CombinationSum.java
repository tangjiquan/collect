package com.panther.study.algorithm.algorithm02.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 问题：
 * 	给定一个数组（元素无重复），和一个目标值，找到所有组合，加起来等于目标值。数组中的元素可以重复使用
 *
 * 解题思路：
 *	因为我们可以组合任意多个数，然后确认其和是否为目标数，并且返回所有可能的组合，所以必须遍历所有可能性才能求解。
 	如果遍历所有的可能，这必然会导致效率的问题，所以避免重复搜索，我们搜索的时候只搜索当前或之后的数，不搜索之前的元素。
 	这是非常典型的DFS并且返回路径的题目，我们采用DFS的方法，在搜索之前我们首先进行排序，因为数组有可能是乱序的。

 * @author: Kevin
 * @date: created in 上午8:45 2019-02-21
 * @version: V1.0
 */
public class Array0006CombinationSum {

	public List<List<Integer>> combinationSum(int[] candidates, int target){
		if(candidates == null || candidates.length ==0){
			return new ArrayList<List<Integer>>();
		}
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		ArrayList<Integer> cur = new ArrayList<Integer>();
		// 首先进行排序,避免重复搜索
		Arrays.sort(candidates);
		dfs(0, target, result, cur, candidates);
		return result;
	}

	private  void dfs(int start, int target, List<List<Integer>> result, List<Integer> cur, int[] cacandidates){
		// 等于表示这条是正确的路，将其加到result上
		if(target == 0){
			// 每次重新创建temp列表，避免与之前的列表重复
			result.add(new ArrayList<Integer>(cur));
			return ;
		}
		//目标和的差值大于0,继续进行深度优先搜索，
		// 选取之后的每个数字的可能性，其中index的作用是避免搜索之前搜索过的数组元素
		for(int i=start; i<cacandidates.length; i++){
			if(cacandidates[i]>target){
				return;
			}

			cur.add(cacandidates[i]);
			//先加入元素，然后进行搜索，这里进行DFS搜索，如果不满足，就把cur列表里的元素去除掉
			dfs(i, target-cacandidates[i], result, cur, cacandidates);
			//目标和不符合，所以将临时列表的最新值去除，然后尝试下一个元素
			cur.remove(cur.size()-1);
		}
	}


	public static void main(String[] args){
		int[] input = new int[]{2,3,6,7};
		int target = 7;
		List<List<Integer>> result = new Array0006CombinationSum().combinationSum(input, target);

		for(int i=0; i<result.size();i++){
			List<Integer> temp = result.get(i);
			for(int j=0; j<temp.size();j++){
				System.out.println(temp.get(j));
			}
			System.out.println();
		}
	}
}
