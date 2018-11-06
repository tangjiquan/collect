package com.panther.study.threadandrunnable03.chapter19.example01;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * FutureServiceImpl的主要作用在于当提交任务时创建一个新的线程来受理该任务， 进而达到任务异步执行的效果
 *
 * @author: Kevin
 * @date: created in 下午9:31 2018-11-06
 * @version: V1.0
 */
public class FutureServiceImpl<IN, OUT> implements FutureService<IN, OUT> {

	private final static String FUTURE_THREAD_PREFIX = "FUTURE-";
	private final AtomicInteger nextCounter = new AtomicInteger(0);
	private static FutureService instance = new FutureServiceImpl();

	private String getNextName() {
		return FUTURE_THREAD_PREFIX + nextCounter.getAndIncrement();
	}

	@Override
	public Future<?> submit(final Runnable runnable) {
		final FutureTask<Void> future = new FutureTask<>();
		new Thread(new Runnable() {
			@Override
			public void run() {
				runnable.run();
				// 任务执行完成后将null作为结果传递给future
				future.finish(null);
			}
		}, getNextName()).start();
		return future;
	}

	@Override
	public Future<OUT> submit(final Task<IN, OUT> task, final IN input) {
		final FutureTask<OUT> future = new FutureTask<>();
		new Thread(new Runnable() {
			@Override
			public void run() {
				OUT result = task.get(input);
				// 任务执行结束后， 将真实的结果通过finish方法传递到future
				future.finish(result);
			}
		}, getNextName()).start();
		return future;
	}

	public static FutureService getInstance() {
		return instance;
	}
}
