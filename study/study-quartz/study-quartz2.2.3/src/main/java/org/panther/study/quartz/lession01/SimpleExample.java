package org.panther.study.quartz.lession01;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author: Kevin
 * @date: created in 下午11:37 2019-05-11
 * @version: V1.0
 */
public class SimpleExample {
	public void run() throws Exception {


		// 1. 创建Scheduler工厂
		SchedulerFactory sf = new StdSchedulerFactory();
		// 2. 从工厂中获取调度器
		Scheduler sched = sf.getScheduler();


		// 3. 创建JobDetail
		JobDetail job = newJob(HelloJob.class).withIdentity("job1", "group1").build();

		// 任务运行时间
		long time=  System.currentTimeMillis() + 3*1000L; //3秒后启动任务
		Date runTime = new Date(time);

		// 4. 创建Trigger
		Trigger trigger = newTrigger().withIdentity("trigger1", "group1")
				.startAt(runTime)//默认启动时间
				.withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))// 两秒执行一次
				.build();

		// 5. 注册任务和定时器
		sched.scheduleJob(job, trigger);

		// 6. 启动调度器
		sched.start();

		try {
			// wait 65 seconds to show job
			Thread.sleep(65L * 1000L);
			// executing...
		} catch (Exception e) {
			//
		}

		// 7. 关闭调度器
		sched.shutdown(true);
	}

	public static void main(String[] args) throws Exception {
		SimpleExample example = new SimpleExample();
		example.run();

	}

}

