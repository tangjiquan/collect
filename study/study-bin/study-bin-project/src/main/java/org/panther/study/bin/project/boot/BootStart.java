package org.panther.study.bin.project.boot;

/**
 * @author: Kevin
 * @date: created in 下午10:09 2019-05-06
 * @version: V1.0
 */
public class BootStart {

	public static void main(String[] args){
		System.out.println("test...");
		int i =0;
		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(i++);
		}
	}
}
