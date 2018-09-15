package org.panther.study.concurrency.example01.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: Kevin
 * @date: created in 上午12:58 2018-07-08
 * @version: V1.0
 */
public class SynchronizedExample1 {

	// 修饰代码块
	public void test1(){
		synchronized (this){
			for(int i=0; i<10; i++){
				System.out.println(i);
			}
		}
	}

	// 修饰方法
	public synchronized void test2(){
			for(int i=0; i<10; i++){
				System.out.println(i);
			}
	}

	public  static void main(String[] args){
		/*SynchronizedExample1 synchronizedExample1 =  new SynchronizedExample1();
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				synchronizedExample1.test1();
			}
		});
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				synchronizedExample1.test1();
			}
		});*/
		/*SynchronizedExample1 synchronizedExample1 =  new SynchronizedExample1();
		synchronizedExample1.test1();
		synchronizedExample1.test1();*/


		final SynchronizedExample1 synchronizedExample1 =  new SynchronizedExample1();
		final SynchronizedExample1 synchronizedExample2 =  new SynchronizedExample1();
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				synchronizedExample1.test1();
			}
		});
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				synchronizedExample2.test1();
			}
		});
	}
}
