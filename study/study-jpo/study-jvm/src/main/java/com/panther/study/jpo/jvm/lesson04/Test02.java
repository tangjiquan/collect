package com.panther.study.jpo.jvm.lesson04;

/**
 * 观察有没有使用TLAB时， eden区内存的变化
 * 一开始关闭逃逸分析，和线程本地变量空间。打印出内存使用情况
 * 》 -XX:-DoEscapeAnalysis -XX:-EliminateAllocations -XX:-UseTLAB -XX:+PrintGCDetails
 * 然后在开启-XX:+UseTLAB
 * -XX:-DoEscapeAnalysis -XX:-EliminateAllocations -XX:+UseTLAB -XX:+PrintGCDetails
 * 会发现PSYoungGen的内存使用变多，eden区占用高了，开启TLAB后，线程默认从eden区申请1%作为线程私有内存，所以
 * 就显得eden内存占用多了
 *
 * @author: Kevin
 * @date: created in 下午10:58 2019-02-19
 * @version: V1.0
 */
public class Test02 {

	public static void main(String[] args) {
		byte[] b = new byte[1024];
	}

}
