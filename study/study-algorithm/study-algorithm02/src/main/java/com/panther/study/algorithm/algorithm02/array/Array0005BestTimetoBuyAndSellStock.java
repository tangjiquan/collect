package com.panther.study.algorithm.algorithm02.array;

/**
 * 问题：
 * 	给定一个数组，数组的第i个元素是第i天的股票价格。如果你只允许进行一次买卖操作（买和买当做一次交易），设计一个算法寻找最大额收益
 * 解决思路：
 * 	只允许一次交易，思路就是去找到在前一段区间内最小的数，和后一段区间内最大的数，二者之差即为最大收益。
 	怎么去寻找最小值？用迭代的方式，通过遍历数组，我们可以设置一个min变量来保存当前时刻的最小值，最大收益值则是不断用当前值减去最小值，不断的更新min值以及最大收益值。
 *
 * @author: Kevin
 * @date: created in 上午8:41 2019-02-19
 * @version: V1.0
 */
public class Array0005BestTimetoBuyAndSellStock {

	public int maxProfit(int[] prices) {
		if (prices.length <= 1) {
			return 0;
		}
		int maxProfit = 0;
		int lowest = Integer.MAX_VALUE;
		for (int v : prices) {
			lowest = Math.min(v, lowest);
			maxProfit = Math.max(maxProfit, v - lowest);
		}
		return maxProfit;
	}

	public static void main(String[] args){
		Array0005BestTimetoBuyAndSellStock instance = new Array0005BestTimetoBuyAndSellStock();
		int result = instance.maxProfit(new int[]{7, 2, 3, 6, 1, 9});
		System.out.println(result);
	}
}
