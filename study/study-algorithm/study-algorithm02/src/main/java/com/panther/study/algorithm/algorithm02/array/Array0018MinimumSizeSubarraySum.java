package com.panther.study.algorithm.algorithm02.array;

/**
 * 问题：
 * 	给定一个正整数数组和一个正整数 s ，求连续子数组的和大于等于 s 的最小长度。
 *
 * 解决思路:
 *  采用滑动窗口算法(Slide Window Algorithm)。
 	设下标 l 和 r, 把左开右闭 [l, r) 想象成一个窗口。
 	当窗口内的和 sum 小于 s 时， 则 r 向右滑动，增加窗口区间。
 	当窗口内的和 sum 大于等于 s 时，表示已经满足原题目要求，是一个可行解，解 l 向右滑动，继续求解。
 *
 *
 * @author: Kevin
 * @date: created in 上午8:54 2019-06-11
 * @version: V1.0
 */
public class Array0018MinimumSizeSubarraySum {
}
