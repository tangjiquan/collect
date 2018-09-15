package org.panther.study.springboot.example01;

/**
 * 封装加载类的信息
 *
 * @author: Kevin
 * @date: created in 上午10:54 2018-07-08
 * @version: V1.0
 */
public class LoadInfo {

	// 自定义的类加载器
	private MyClassLoader myClassLoader;
	// 记录要加载的类的时间戳 --> 加载器时间
	private long loadTime;
	private BaseManager manager;

	public LoadInfo(MyClassLoader myClassLoader, long loadTime) {
		super();
		this.myClassLoader = myClassLoader;
		this.loadTime = loadTime;
	}

	public MyClassLoader getMyClassLoader() {
		return myClassLoader;
	}

	public void setMyClassLoader(MyClassLoader myClassLoader) {
		this.myClassLoader = myClassLoader;
	}

	public long getLoadTime() {
		return loadTime;
	}

	public void setLoadTime(long loadTime) {
		this.loadTime = loadTime;
	}

	public BaseManager getManager() {
		return manager;
	}

	public void setManager(BaseManager manager) {
		this.manager = manager;
	}
}
