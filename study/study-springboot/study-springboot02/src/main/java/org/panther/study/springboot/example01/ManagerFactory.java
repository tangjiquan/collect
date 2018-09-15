package org.panther.study.springboot.example01;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * 加载manager的工厂
 *
 * @author: Kevin
 * @date: created in 上午10:58 2018-07-08
 * @version: V1.0
 */
public class ManagerFactory {

	// 记录热加载类的加载信息
	private  static final Map<String, LoadInfo> loadTimeMap = new HashMap<String, LoadInfo>();
	// 要加载的类的classpath
	private static final String CLASS_PATH = "/home/tangjiquan/Study/projects/panther/source/collect/study/study-springboot/study-springboot02/target/classes/";
	// 实现热加载的列的全名称
	public static  final String MY_MANAGER = "org.panther.study.springboot.example01.MyManager";

	public static BaseManager getManager(String className) throws IOException {
		File loaderFile = new File(CLASS_PATH + className.replaceAll("\\.", "/") + ".class");
		long lastModified = loaderFile.lastModified();
		// loadTimeMap不包含className为key的LoadInfo信息， 证明这个类没有被加载， 那么需要加载这个类到jvm中
		if(loadTimeMap.get(className) == null){
			load(className, lastModified);
		}else if(loadTimeMap.get(className).getLoadTime() !=  lastModified){
			load(className, lastModified);
		}
		return loadTimeMap.get(className).getManager();
	}

	public static void load(String className, long lastModified){
		MyClassLoader myClassLoader = new MyClassLoader(CLASS_PATH);
		Class<?> loadClass = null;
		try {
			loadClass = myClassLoader.loadClass(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		BaseManager manager = newInstance(loadClass);
		LoadInfo loadInfo = new LoadInfo(myClassLoader, lastModified);
		loadInfo.setManager(manager);
		loadTimeMap.put(className, loadInfo);
	}

	/**
	 * 以反射的方式创建BaseManager子类对象
	 *
	 * @param loadClass
	 * @return
	 */
	private static BaseManager newInstance(Class<?> loadClass) {

		try {
			return (BaseManager) loadClass.getConstructor(new Class[]{}).newInstance(new Object[]{});
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

		return null;
	}


}
