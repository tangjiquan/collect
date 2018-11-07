package com.panther.study.threadandrunnable03.chapter21.example02;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 自定义上下文
 *
 * 这种方式容易造成内存泄露， 线程的生命周期结束，但是contexts中的Thread不会释放，对应的
 * 值也不会被释放
 *
 * @author: Kevin
 * @date: created in 下午8:49 2018-11-07
 * @version: V1.0
 */
public class ApplicationContext {

	private ConcurrentHashMap<Thread, ActionContext> contexts = new ConcurrentHashMap<Thread, ActionContext>();

	public ActionContext getActionContext(){
		ActionContext actionContext = contexts.get(Thread.currentThread());
		if(actionContext == null){
			actionContext = new ActionContext();
			contexts.put(Thread.currentThread(), actionContext);
		}
		return actionContext;
	}
}
