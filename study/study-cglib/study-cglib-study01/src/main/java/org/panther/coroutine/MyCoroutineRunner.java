package org.panther.coroutine;

import com.offbynull.coroutines.user.Coroutine;
import com.offbynull.coroutines.user.CoroutineRunner;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: Kevin
 * @date: created in 下午5:00 2018-08-13
 * @version: V1.0
 */
public class MyCoroutineRunner {
	Queue<CoroutineRunner> coroutineQueue = new LinkedList<CoroutineRunner>();
	public void join(Coroutine c) {
		// 往就绪队列塞Task
		coroutineQueue.add(new CoroutineRunner(c));
	}
	public void execute() {
		while (!coroutineQueue.isEmpty()) {
			System.out.printf("[MAIN] Current number of tasks: %d\n", coroutineQueue.size());
			CoroutineRunner coroutineRunner = coroutineQueue.remove();
			boolean notFinish = coroutineRunner.execute(); // 执行协程
			if (notFinish) {
				// 若协程没有完成，继续丢到队尾
				coroutineQueue.add(coroutineRunner);
			}
		}
	}
}
