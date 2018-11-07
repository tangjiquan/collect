package com.panther.study.threadandrunnable03.chapter21.example04;

/**
 * 使用ThreadLoacl设置线程的上下文
 *
 * @author: Kevin
 * @date: created in 下午9:30 2018-11-07
 * @version: V1.0
 */
public class ActionContextV1 {

	private static final ThreadLocal<Context> context = new ThreadLocal<Context>();

	public static Context getContext(){
		return context.get();
	}

	class Context{
		private ApplicationConfiguration applicationConfiguration;
		private RuntimeInfo runtimeInfo;

		public ApplicationConfiguration getApplicationConfiguration() {
			return applicationConfiguration;
		}

		public void setApplicationConfiguration(ApplicationConfiguration applicationConfiguration) {
			this.applicationConfiguration = applicationConfiguration;
		}

		public RuntimeInfo getRuntimeInfo() {
			return runtimeInfo;
		}

		public void setRuntimeInfo(RuntimeInfo runtimeInfo) {
			this.runtimeInfo = runtimeInfo;
		}
	}
}
