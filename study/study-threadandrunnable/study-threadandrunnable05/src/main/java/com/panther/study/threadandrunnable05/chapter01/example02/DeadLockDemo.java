package com.panther.study.threadandrunnable05.chapter01.example02;

/**
 * 死锁案例
 *
 * @author: Kevin
 * @date: created in 下午9:27 2018-12-18
 * @version: V1.0
 */
public class DeadLockDemo {

	private static String A = "A";
	private static String B = "B";

	public static void main(String[] args){
		new DeadLockDemo().deadLock();
	}

	/**
	 *
	 * 两个线程的状态是BLOCKED，两个线程死锁

	 "Thread-1" #11 prio=5 os_prio=0 tid=0x00007f734013f800 nid=0x1b3f waiting for monitor entry [0x00007f730eabb000]
	 java.lang.Thread.State: BLOCKED (on object monitor)
	 at com.panther.study.threadandrunnable05.chapter01.example02.DeadLockDemo$2.run(DeadLockDemo.java:43)
	 - waiting to lock <0x00000000d7149fc0> (a java.lang.String)
	 - locked <0x00000000d7149ff0> (a java.lang.String)
	 at java.lang.Thread.run(Thread.java:745)

	 "Thread-0" #10 prio=5 os_prio=0 tid=0x00007f7340137000 nid=0x1b3d waiting for monitor entry [0x00007f730ebbc000]
	 java.lang.Thread.State: BLOCKED (on object monitor)
	 at com.panther.study.threadandrunnable05.chapter01.example02.DeadLockDemo$1.run(DeadLockDemo.java:32)
	 - waiting to lock <0x00000000d7149ff0> (a java.lang.String)
	 - locked <0x00000000d7149fc0> (a java.lang.String)
	 at java.lang.Thread.run(Thread.java:745)






	 */
	public void deadLock(){


		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (A){
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (B){
						System.out.println("1");
					}
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (B){
					synchronized (A){
						System.out.println("2");
					}
				}
			}
		});

		t1.start();
		t2.start();
	}
}
