package com.panther.study.jpo.jvm.lesson03;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * JVM Heap(堆内存溢出）：java.lang.OutOfMemoryError:Java heap space
 * JVM在启动的时候会自动设置Heap的值，可以通过-Xms -Xmx设置堆的大小,在JVM中如果98%的时间是用于GC,
 * 且可用的Heap size 不足2%的时候将抛出此异常信息。
 *
 * Java堆用于储存对象实例。当需要为对象实例分配内存，而堆的内存占用又已经达到-Xmx设置的最大值。将会抛出OutOfMemoryError异常
 *
 * -Xms5m -Xmx5m
 * 服务器默配置-Xms5m -Xmx5m
 *
 *
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
		 at java.util.Arrays.copyOf(Arrays.java:3210)
		 at java.util.Arrays.copyOf(Arrays.java:3181)
		 at java.util.ArrayList.grow(ArrayList.java:261)
		 at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:235)
		 at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:227)
		 at java.util.ArrayList.add(ArrayList.java:458)
		 at com.panther.study.jpo.jvm.lesson03.HeapSpaceOutOfMemoryError.main(HeapSpaceOutOfMemoryError.java:31)
		 at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
		 at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
		 at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
		 at java.lang.reflect.Method.invoke(Method.java:498)
		 at com.intellij.rt.execution.application.AppMain.main(AppMain.java:147)

		 Process finished with exit code 1
 *
 *
 * @author: Kevin
 * @date: created in 下午10:00 2019-02-14
 * @version: V1.0
 *
 *
 */
public class HeapSpaceOutOfMemoryError {

	public static void main(String[] args){
		int count = 0;
		List list = new ArrayList();

		while(true){
			list.add(new Object());
			System.out.println(count++);
		}
	}
}
