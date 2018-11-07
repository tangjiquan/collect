package com.panther.study.threadandrunnable03.chapter21.example01;

/**
 * @author: Kevin
 * @date: created in 下午8:49 2018-11-07
 * @version: V1.0
 */
public class ApplicationContext {

	// context中保存了configuration
	private ApplicationConfiguration applicationConfiguration;
	private RuntimeInfo runtimeInfo;

	private static class Holder{
		private static ApplicationContext instance = new ApplicationContext();
	}

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
