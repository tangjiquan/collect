package com.panther.study.threadandrunnable03.chapter08.example01;

/**
 * @author: Kevin
 * @date: created in 下午8:38 2018-10-21
 * @version: V1.0
 */
public interface ThreadPool {

	/**
	 * 提交到线程池
	 * @param runnable
	 */
	void execute(Runnable runnable);

	/**
	 * 关闭线程池
	 */
	void shutdown();

	/**
	 * 获取线程池的初始化大小
	 * @return
	 */
	int getInitSize();

	/**
	 * 获取线程池最大线程数
	 * @return
	 */
	int getMaxSize();

	/**
	 * 获取线程池核心线程数
	 * @return
	 */
	int getCoreSize();

	/**
	 * 获取线程池中用于缓存任务队列的大小
	 * @return
	 */
	int getQueueSize();

	/**
	 * 获取线程池中活跃的线程数量
	 * @return
	 */
	int getActiveCount();

	/**
	 * 查看线程池是否已经被shutdown
	 * @return
	 */
	boolean isShutdown();
}
