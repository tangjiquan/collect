package com.panther.study.monitorandtuning.lesson02.chapter02.chapter2_1_1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author: Kevin
 * @date: created in 下午9:59 2019-07-02
 * @version: V1.0
 */
class TestSingletonSerializable{

	public static void main(String[] args) throws Exception {
		SingletonSerializable s1 = null;
		SingletonSerializable s = SingletonSerializable.getInstance();

		FileOutputStream fos = new FileOutputStream("TestSingletonSerializable.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(s);
		oos.flush();
		oos.close();

		FileInputStream fis = new FileInputStream("TestSingletonSerializable.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);

		s1 = (SingletonSerializable)ois.readObject();

		System.out.println(s == s1);
	}
}