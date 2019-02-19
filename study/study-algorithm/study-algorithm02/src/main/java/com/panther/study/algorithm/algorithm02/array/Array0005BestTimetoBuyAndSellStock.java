package com.panther.study.algorithm.algorithm02.array;

/**
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
