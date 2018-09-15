package org.panther.coroutine;

/**
 * @author: Kevin
 * @date: created in 下午5:00 2018-08-13
 * @version: V1.0
 */
public class TestRunner {
	public static void main(String args[]) {
		MyCoroutineRunner myCoroutineRunner = new MyCoroutineRunner();
		myCoroutineRunner.join(new MyCoroutine1());
		myCoroutineRunner.join(new MyCoroutine2());
		myCoroutineRunner.join(new MyCoroutine3());
		System.out.println("=== Start ===");
		myCoroutineRunner.execute();
		System.out.println("=== All tasks finished ! ===");
	}
}
