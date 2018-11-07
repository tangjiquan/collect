package com.panther.study.algorithm01.chapter03.example01;

/**
 * 穷举算法
 * <p>
 * 鸡兔同笼问题
 *
 * @author: Kevin
 * @date: created in 上午8:56 2018-11-07
 * @version: V1.0
 */
public class Qiongju {

	private static int chichen;
	private static int rabbit;

	public static String qiongju(int head, int foot) {
		String flag = "0";
		int i, j;
		for (i = 0; i < head; i++) {
			j = head - i;
			if (i*2 + j*4 == foot) {
				flag = "1";
				chichen = i;
				rabbit = j;
			}
		}
		return flag;
	}

	public static void main(String[] args) {
		String flag = qiongju(35, 94);
		if("1".equals(flag)){
			System.out.println("鸡:" + chichen + "只, " + "兔:" + rabbit + "只");
		}
	}
}
