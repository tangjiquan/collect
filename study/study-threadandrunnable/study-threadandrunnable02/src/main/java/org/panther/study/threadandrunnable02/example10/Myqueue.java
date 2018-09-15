package org.panther.study.threadandrunnable02.example10;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义一个队列， 增加元素和取走元素
 *
 * @author: Kevin
 * @date: created in 下午4:09 2018-09-09
 * @version: V1.0
 */
public class Myqueue {

	// 1. 需要一个承载元素的集合
	private final LinkedList<Object> list = new LinkedList<Object>();

	// 2. 需要一个计数器
	private AtomicInteger count = new AtomicInteger(0);

	// 3. 定制上线和下线
	private final int minSize = 0;
	private final int maxSize;

	// 4. 构造方法
	public Myqueue(int size){
		this.maxSize = size;
	}

	// 5. 初始化一个对象用于加锁
	private final Object lock = new Object();

	// 放入一个元素
	public void put(Object object){
		synchronized (lock){
			while(count.get() == this.maxSize){
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			list.add(object);
			System.out.println("新加入元素：" + object);
			count.incrementAndGet();

			// 容器为空的时候
			lock.notify();//
		}
	}

	// 获取一个元素
	public Object take(){
		Object ret = null;
		synchronized (lock){
			while(count.get() == this.minSize){
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
			// 1. 移除元素
			ret = list.removeFirst();
			// 2. 计数器递减
			count.decrementAndGet();
			// 3. 唤醒另外一个线程
			lock.notify();
		}
		return ret;
	}

	public int getSize(){
		return this.count.get();
	}
	public static void main(String[] args){
		final Myqueue myqueue = new Myqueue(5);
		myqueue.put("A");
		myqueue.put("B");
		myqueue.put("C");
		myqueue.put("D");
		myqueue.put("E");
		System.out.println("当容器的长度" + myqueue.getSize());


		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				myqueue.put("G");
				myqueue.put("K");
			}
		}, "t1");
		t1.start();

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				Object object1 = myqueue.take();
				System.out.println("移除的元素" + object1);
				Object object2 = myqueue.take();
				System.out.println("移除的元素" + object2);

			}
		},"t2");

		t2.start();
	}
}