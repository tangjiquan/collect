package org.panther.study.springboot.example01;

/**
 * 测试java类的热加载
 *
 * @author: Kevin
 * @date: created in 上午11:23 2018-07-08
 * @version: V1.0
 */
public class ClassLoaderTest {

	public static void main(String[] args){
		new Thread(new MsgHandler()).start();
	}
}
