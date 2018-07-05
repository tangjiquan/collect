/*
 * Copyright 2002-2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.context.support;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

/**
 * Standalone XML application context, taking the context definition files
 * from the file system or from URLs, interpreting plain paths as relative
 * file system locations (e.g. "mydir/myfile.txt"). Useful for test harnesses
 * as well as for standalone environments.
 *
 * <p><b>NOTE:</b> Plain paths will always be interpreted as relative
 * to the current VM working directory, even if they start with a slash.
 * (This is consistent with the semantics in a Servlet container.)
 * <b>Use an explicit "file:" prefix to enforce an absolute file path.</b>
 *
 * <p>The config location defaults can be overridden via {@link #getConfigLocations},
 * Config locations can either denote concrete files like "/myfiles/context.xml"
 * or Ant-style patterns like "/myfiles/*-context.xml" (see the
 * {@link org.springframework.util.AntPathMatcher} javadoc for pattern details).
 *
 * <p>Note: In case of multiple config locations, later bean definitions will
 * override ones defined in earlier loaded files. This can be leveraged to
 * deliberately override certain bean definitions via an extra XML file.
 *
 * <p><b>This is a simple, one-stop shop convenience ApplicationContext.
 * Consider using the {@link GenericApplicationContext} class in combination
 * with an {@link org.springframework.beans.factory.xml.XmlBeanDefinitionReader}
 * for more flexible context setup.</b>
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @see #getResource
 * @see #getResourceByPath
 * @see GenericApplicationContext
 */
public class FileSystemXmlApplicationContext extends AbstractXmlApplicationContext {

	/**
	 * Create a new FileSystemXmlApplicationContext for bean-style configuration.
	 * @see #setConfigLocation
	 * @see #setConfigLocations
	 * @see #afterPropertiesSet()
	 */
	public FileSystemXmlApplicationContext() {
	}

	/**
	 * Create a new FileSystemXmlApplicationContext for bean-style configuration.
	 * @param parent the parent context
	 * @see #setConfigLocation
	 * @see #setConfigLocations
	 * @see #afterPropertiesSet()
	 */
	public FileSystemXmlApplicationContext(ApplicationContext parent) {
		super(parent);
	}

	/**
	 * Create a new FileSystemXmlApplicationContext, loading the definitions
	 * from the given XML file and automatically refreshing the context.
	 * @param configLocation file path
	 * @throws BeansException if context creation failed
	 */
	//这个构造函数configLocation包含的是BeanDefinition所在的文件路径
	public FileSystemXmlApplicationContext(String configLocation) throws BeansException {
		this(new String[] {configLocation}, true, null);
	}

	/**
	 * Create a new FileSystemXmlApplicationContext, loading the definitions
	 * from the given XML files and automatically refreshing the context.
	 * @param configLocations array of file paths
	 * @throws BeansException if context creation failed
	 */
	//允许configLocations包含多个BeanDefinition的文件路径
	public FileSystemXmlApplicationContext(String[] configLocations) throws BeansException {
		this(configLocations, true, null);
	}

	/**
	 * Create a new FileSystemXmlApplicationContext with the given parent,
	 * loading the definitions from the given XML files and automatically
	 * refreshing the context.
	 * @param configLocations array of file paths
	 * @param parent the parent context
	 * @throws BeansException if context creation failed
	 */
	//允许configLocations包含多个BeanDefinition的文件路径，还指定了自己双亲IOC容器
	public FileSystemXmlApplicationContext(String[] configLocations, ApplicationContext parent) throws BeansException {
		this(configLocations, true, parent);
	}

	/**
	 * Create a new FileSystemXmlApplicationContext, loading the definitions
	 * from the given XML files.
	 * @param configLocations array of file paths
	 * @param refresh whether to automatically refresh the context,
	 * loading all bean definitions and creating all singletons.
	 * Alternatively, call refresh manually after further configuring the context.
	 * @throws BeansException if context creation failed
	 * @see #refresh()
	 */
	public FileSystemXmlApplicationContext(String[] configLocations, boolean refresh) throws BeansException {
		this(configLocations, refresh, null);
	}

	/**
	 * Create a new FileSystemXmlApplicationContext with the given parent,
	 * loading the definitions from the given XML files.
	 * @param configLocations array of file paths
	 * @param refresh whether to automatically refresh the context,
	 * loading all bean definitions and creating all singletons.
	 * Alternatively, call refresh manually after further configuring the context.
	 * @param parent the parent context
	 * @throws BeansException if context creation failed
	 * @see #refresh()
	 */
	//在对象的初始化过程中，调用refresh函数载入BeanDefinition,这个refresh启动了BeanDefinition的载入过程，
	public FileSystemXmlApplicationContext(String[] configLocations, boolean refresh, ApplicationContext parent)
			throws BeansException {

		/*
		 * 这个this.parent是一个ApplicationContext类型的，比如创建一个FileSystemXmlApplicationContext
		 * 		String str = "E:\\java\\eclipsework\\eclipse_j2ee\\source\\spring\\Spring2.5.6\\test\\beans.xml";
		 *      FileSystemXmlApplicationContext factory = new FileSystemXmlApplicationContext(str);
		 * 在创建的时候给构造函数传递了一个参数，这个参数是配置文件的文件系统 的路径，但是这个构造函数会调用其他的构造函数，最终调用到这个构造函数：
		 * 
		 * public FileSystemXmlApplicationContext(String[] configLocations, boolean refresh, ApplicationContext parent)
			throws BeansException {

				super(parent);
				//这个方法是AbstractRefreshableConfigApplication类中的，主要讲configLocations变量设置到configLocations变量中
				setConfigLocations(configLocations);
				if (refresh) {
				//这里是IoC容器的初始化过程，其初始化过程的大致步骤由AbstractApplicationContext来定义
				refresh();
			}
		 }
		  其中，它会调用父类的super(parent),调用
		  public AbstractXmlApplicationContext(ApplicationContext parent) {
				super(parent);
		  }，  然后一层的调用到最上层的的AbstractApplicationContext的构造函数，也就是这个构造函数，将parent的值传递给全局的parent
		 */
		super(parent);
		//这个方法是AbstractRefreshableConfigApplication类中的，主要讲configLocations变量设置到configLocations变量中
		setConfigLocations(configLocations);
		if (refresh) {
			//这里是IoC容器的初始化过程，其初始化过程的大致步骤由AbstractApplicationContext来定义
			refresh();
		}
	}


	/**
	 * Resolve resource paths as file system paths.
	 * <p>Note: Even if a given path starts with a slash, it will get
	 * interpreted as relative to the current VM working directory.
	 * This is consistent with the semantics in a Servlet container.
	 * @param path path to the resource
	 * @return Resource handle
	 * @see org.springframework.web.context.support.XmlWebApplicationContext#getResourceByPath
	 */
	//这个应用于文件系统中Resource的实现，通过构造一个FileSystemResource来得到一个在文件系统中定位的BeanDefinition
	//这个getResourceByPath是在BeanDefinitionReader 的loadBeandefinition中调用的，
	//loadBeandefinition采用了模板模式，具体定位实际上是由各个子类完成的
	protected Resource getResourceByPath(String path) {
		if (path != null && path.startsWith("/")) {
			path = path.substring(1);
		}
		return new FileSystemResource(path);
	}

}
