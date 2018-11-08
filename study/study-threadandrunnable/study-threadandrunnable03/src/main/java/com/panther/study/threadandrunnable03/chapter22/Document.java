package com.panther.study.threadandrunnable03.chapter22;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Kevin
 * @date: created in 下午8:46 2018-11-08
 * @version: V1.0
 */
public class Document {

	// 如果发生了变化， changed会被设置为true
	private boolean changed = false;

	// 一次需要保存的内容， 可以将其理解为内容缓存
	private List<String> context = new ArrayList<String>();

	private  FileWriter fileWriter = null;

	private static AutoSaveThread autoSaveThread;

	private Document(String documentPath, String documentName){
		try {
			this.fileWriter = new FileWriter(new File(documentPath, documentName), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 静态方法， 主要用于创建文档， 顺便自动保存文档
	public static Document create(String documentPath, String documentName){
		Document document = new Document(documentPath, documentName);
		autoSaveThread = new AutoSaveThread(document);
		autoSaveThread.start();
		return document;
	}

	public void edit(String content){
		synchronized (this){
			this.context.add(content);
			// 文档改变， changed会变为true
			this.changed = true;
		}
	}

	/**
	 * 文档关闭的时候中断自动保存线程， 然后关闭writer释放资源
	 */
	public void close(){
		autoSaveThread.interrupt();
		try {
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void save(){
		synchronized (this){
			if(!changed){
				return ;
			}
			System.out.println(Thread.currentThread() + "execute the save action");
			for(String cacheLine : context){
				try {
					this.fileWriter.write(cacheLine);
					this.fileWriter.write("\r\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				this.fileWriter.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.changed = false;
			this.context.clear();
		}
	}
}
