package com.panther.study.jpo.jvm.lesson03;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Kevin
 * @date: created in 下午10:08 2019-02-14
 * @version: V1.0
 */
public class PermGenSpaceOutOfMemoryError {

	public static void main(String[] args) throws Exception {
		List<ClassLoader> classLoaders = new ArrayList<ClassLoader>();
		while(true){
			ClassLoader loader = new URLClassLoader(new URL[]{new URL("/home/tangjiquan/Study/projects/panther/source/collect/study/study-jpo/study-jvm/target/classes").toURI().toURL()});
			classLoaders.add(loader);
			try {
				loader.loadClass("com.panther.study.jpo.jvm.lesson03.PermGenSpaceOutOfMemoryError");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
