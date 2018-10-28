package com.panther.study.threadandrunnable03.chapter07.example02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * 防止某个程序被重复启用，在进程启动时候会创建一个lock文件，进程在收到中断信号的时候会删除这个lock文件
 * 在kill -1 PID 命令之后jvm进程会收到中断信号， 启动hock线程， 删除lock
 * 如果使用kill -9 命令， 那么hook不会被执行
 * hook也可以执行一些资源释放的工作， 比如关闭文件句柄，socket连接， 数据库connection等
 *
 * @author: Kevin
 * @date: created in 下午10:05 2018-10-20
 * @version: V1.0
 */
public class PreventDuplicated {

	private final static String LOCK_PATH = "/home/tangjiquan/Study/temp/spark";
	private final static String LOCK_FILE = ".lock";
	private final static String PERMISSIONS = "rw-------";

	public static void main(String[] args){
		Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override
			public void run() {
				System.out.println("the program recevie will singal");
				getLockFile().toFile().delete();
			}
		});

		checkRunning();

		for(;;){
			try {
				TimeUnit.SECONDS.sleep(2);
				System.out.println("program is running");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private static void checkRunning() {
		Path path = getLockFile();
		if(path.toFile().exists()){
			throw new RuntimeException("the program already running");
		}

		Set<PosixFilePermission> perms = PosixFilePermissions.fromString(PERMISSIONS);
		try {
			Files.createFile(path, PosixFilePermissions.asFileAttribute(perms));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static Path getLockFile(){
		return Paths.get(LOCK_PATH, LOCK_FILE);
	}

}
