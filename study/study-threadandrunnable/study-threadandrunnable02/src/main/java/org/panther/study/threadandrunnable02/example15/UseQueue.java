package org.panther.study.threadandrunnable02.example15;

import java.util.concurrent.SynchronousQueue;

/**
 * @author: Kevin
 * @date: created in 上午10:33 2018-09-10
 * @version: V1.0
 */
public class UseQueue {
	public static void main(String [] args) throws InterruptedException {
		/*ConcurrentLinkedQueue<String> q = new ConcurrentLinkedQueue<>();
		q.offer("a");
		q.offer("b");
		q.offer("c");
		q.offer("d");
		System.out.println(q.poll());//从头部取出元素， 并删除
		System.out.println(q.size());
		System.out.println(q.peek());
		System.out.println(q.size());*/

		///////////////////
		/*ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<String>(5);
		arrayBlockingQueue.put("a");
		arrayBlockingQueue.put("b");
		arrayBlockingQueue.add("c");
		arrayBlockingQueue.add("d");
		arrayBlockingQueue.add("e");
		arrayBlockingQueue.add("f");// 只允许增加5个
		//System.out.println(arrayBlockingQueue.offer("a", 3, TimeUnit.SECONDS));// 3秒后就不能条件就返回false
*/



		// 阻塞队列
		// 5表示初始化长度
		/*LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>(5);
		queue.offer("a");
		queue.offer("b");
		queue.offer("c");
		queue.offer("d");
		queue.offer("e");
		System.out.println(queue.size());

		List<String> list = new ArrayList<>();
		System.out.println(queue.drainTo(list, 3));//从队列中取三个元素放到list中
		System.out.println(list.size());
		for(String s : list){
			System.out.println(s);

		}*/

		// SynchronousQueue不允许加元素
		// SynchronousQueue来一个处理一个，没有等待延迟
		SynchronousQueue<String> queue = new SynchronousQueue<String>();
		queue.add("ddd");

	}
}
