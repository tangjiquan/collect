package com.panther.study.threadandrunnable03.chapter22;

import java.util.Scanner;

/**
 * @author: Kevin
 * @date: created in 下午8:57 2018-11-08
 * @version: V1.0
 */
public class DocumentEditThread extends  Thread{

	private final String documentPath;
	private final String documentName;

	private final Scanner scanner = new Scanner(System.in);

	public DocumentEditThread(String documentPath, String documentName){
		this.documentName = documentName;
		this.documentPath = documentPath;
	}


	@Override
	public void run() {
		int times = 0;
		Document document = Document.create(documentPath, documentName);
		while(true){
			String text = scanner.next();
			if("quit".equals(text)){

				document.close();
				break;
			}
			document.edit(text);
			if(times == 5){
				document.save();
				times = 0;
			}
			times++;
		}

	}
}
