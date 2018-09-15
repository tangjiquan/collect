package org.panther.study.springboot.example01;

import java.io.*;

/**
 * 自定义java类加载器实现java热加载器
 *
 * @author: Kevin
 * @date: created in 上午10:39 2018-07-08
 * @version: V1.0
 */
public class MyClassLoader extends ClassLoader {

	// 要加载的java类加载器的classpat路径
	private String classpath;

	public MyClassLoader(String classpath){
		super(ClassLoader.getSystemClassLoader());
		this.classpath = classpath;
	}
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] data = this.loadClassData(name);
		return this.defineClass(name, data, 0, data.length);
	}

	/**
	 * 加载class文件中的内容
	 * @param name
	 * @return
	 */
	private byte[] loadClassData(String name) {
		name = name.replace(".", "//");
		try {
			FileInputStream fis = new FileInputStream(new File(classpath + name + ".class"));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int b = 0;
			while((b = fis.read()) != -1){
				baos.write(b);
			}
			fis.close();
			return baos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
