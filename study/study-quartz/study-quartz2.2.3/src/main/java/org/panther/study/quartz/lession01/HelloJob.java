package org.panther.study.quartz.lession01;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author: Kevin
 * @date: created in 下午11:40 2019-05-11
 * @version: V1.0
 */
public class HelloJob implements Job {
	public HelloJob() {
	}
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println("quartz...test");
	}
}
