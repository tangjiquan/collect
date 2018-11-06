package com.panther.study.threadandrunnable03.chapter18.example03;

import java.util.List;

/**
 * 设计一个不可变类的共享资源需要具备不可破坏性， 比如使用final修饰， 共享资源的操作方法是不可被重写的
 * 以防止继承带来的安全性问题， 但是单凭这两点也不足以保证一个类是不可变的。
 *
 * 返回的list可以被其他线程修改， 需要在返回list的时候增加不可修饰的约束Collections.unmodifiableList(this.list)
 * 或者克隆一个全新的list返回
 *
 *
 * @author: Kevin
 * @date: created in 下午9:04 2018-11-05
 * @version: V1.0
 */
public final class Immutable {

	private final List<String> list;

	public Immutable(List<String> list){
		this.list = list;
	}

	public List<String> getList(){
		return this.list;
	}
}
