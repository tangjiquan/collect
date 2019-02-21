package com.panther.study.algorithm.algorithm02.array;

/**
 *
 * 问题：
 *	买卖股票的最大利益的值
 * 解决思路：
 *  要求是可以进行多次交易，利用贪心就可以了，每次股价比昨天贵就加上这个差值,不停的买入和
 *
 *
 * @author: Kevin
 * @date: created in 上午8:41 2019-02-19
 * @version: V1.0
 */
public class Array0005BestTimetoBuyAndSellStock2 {

	public int maxProfit(int[] prices) {
		if (prices.length <= 2) {
			return 0;
		}

		int profit = 0;
		for(int i=1; i< prices.length; i++){
			if(prices[i-1] < prices[i]){
				profit += prices[i] - prices[i-1];
			}
		}
		return profit;
	}

	public static void main(String[] args){
		Array0005BestTimetoBuyAndSellStock2 instance = new Array0005BestTimetoBuyAndSellStock2();
		int result = instance.maxProfit(new int[]{7, 2, 3, 6, 1, 9});
		System.out.println(result);
	}
}
