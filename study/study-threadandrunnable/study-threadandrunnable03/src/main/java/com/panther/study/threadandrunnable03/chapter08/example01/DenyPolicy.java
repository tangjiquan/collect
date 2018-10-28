package com.panther.study.threadandrunnable03.chapter08.example01;

/**
 * @author: Kevin
 * @date: created in 下午8:57 2018-10-21
 * @version: V1.0
 */
public interface DenyPolicy {

	void reject(Runnable runnable, ThreadPool threadPool);

	/**
	 * 该拒绝策略会直接将任务丢弃
	 */
	class DiscardDenyPolicy implements DenyPolicy{

		@Override
		public void reject(Runnable runnable, ThreadPool threadPool) {
			// todo
		}
	}

	/**
	 * 该任务会向任务提交者抛出异常
	 */
	class AbortDenyPolicy implements DenyPolicy{

		@Override
		public void reject(Runnable runnable, ThreadPool threadPool) {
			throw new RuntimeException("the runnable "+ runnable + "will be abort.");
		}
	}

	/**
	 * 该拒绝策略会使任务提交者所在的线程中执行
	 */
	class RunnerDenyPolicy implements DenyPolicy{

		@Override
		public void reject(Runnable runnable, ThreadPool threadPool) {
			if(!threadPool.isShutdown()){
				runnable.run();
			}
		}
	}
}
