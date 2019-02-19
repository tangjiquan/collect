package com.panther.study.jpo.jvm.lesson04;

/**
 *
 * 对象是否分配到栈空间与i线程本地变量的效果
 *
  -XX:-DoEscapeAnalysis -XX:-EliminateAllocations -XX:-UseTLAB -XX:+PrintGC
 关闭逃逸分析，关闭标量替换，关闭线程本地内存，打印gc信息
 默认是打开逃逸分析的，如果关闭了逃逸分析， 就不会往栈上分配（逃逸分析:函数里定义的变量在外部还存在引用）

 -XX:-DoEscapeAnalysis -XX:-EliminateAllocations 不会在栈上分配内存
 -XX:-UseTLAB 不使用线程本地变量， 默认是在本地分配

	》 1. 虚拟机已经进行了调优，使用了栈上分配和线程本地变量， 所以发现时间很少, 栈上不要GC
 	》 2. 当是使用了-XX:-DoEscapeAnalysis -XX:-EliminateAllocations -XX:-UseTLAB -XX:+PrintGC会导致
 		不使用了栈上分配和线程本地变量， 所以发现时间变多，应为需要堆上分配内存， 然后发生GC
 	》 3. 是使用了-XX:+DoEscapeAnalysis -XX:+EliminateAllocations -XX:-+UseTLAB -XX:+PrintGC会
 		使用了栈上分配和线程本地变量， 所以发现时间变少，应为不需要堆上分配内存， 然后不发生GC

 * @author: Kevin
 * @date: created in 下午10:04 2019-02-19
 * @version: V1.0
 */
public class Test01 {
	class User {
		int id;
		String name;

		public User(int id, String name) {
			super();
			this.id = id;
			this.name = name;
		}
	}

	public void newObject(int i) {
		User u = new User(i, "name" + i);
	}

	public static void main(String[] args) {
		Test01 t = new Test01();
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++) {
			t.newObject(i);
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
}
