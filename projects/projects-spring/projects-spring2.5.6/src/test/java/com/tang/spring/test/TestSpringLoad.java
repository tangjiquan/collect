package com.tang.spring.test;

import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class TestSpringLoad {
	
	@Test
	public void TestSpringByFileSystemXml(){
		// FileSystemXmlApplicationContext
		//1. Ĭ��Ϊ��Ŀ�Ĺ���·���� ����Ŀ�ĸ�Ŀ¼
		//ApplicationContext context = new FileSystemXmlApplicationContext("test/com/tang/spring/test/bean.xml");
		
		//2. ǰ׺classpath����ʾ������Ŀ��classpath�����·��
		//ApplicationContext context = new FileSystemXmlApplicationContext("classpath:bean.xml");
		
		//3. ʹ��ǰ׺file��ʾ�����ļ��ľ���·��
		//ApplicationContext context = new FileSystemXmlApplicationContext("file:/home/tangjiquan/Study/workspace/sources/java/spring/spring2.5.6/test/com/tang/spring/test/bean.xml");

		//4. ����ͬʱ���ض���ļ�
		//String[] xfg = new String[]{"test/com/tang/spring/test/bean.xml"};
		//ApplicationContext context = new FileSystemXmlApplicationContext(xfg);
		
		//5. ʹ��ͨ������ظ���Ҫ����ļ�
		//ApplicationContext context = new FileSystemXmlApplicationContext("classpath:*bean.xml");
		
		
		//6. Resource
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource res = resolver.getResource("classpath:bean.xml");
		XmlBeanFactory context = new XmlBeanFactory(res);
		
		Person person = (Person) context.getBean("person");
		System.out.println(person.getAge());
	}
	
	
	@Test
	public void testSpringByClassPathXml(){
		//1. û��ǰ׺�� Ĭ��Ϊ��Ŀ��classpath�����·��
		//ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
		
		
		//2. ǰ׺classpath , ��ʾ������Ŀ��classpath�����·��
		//ApplicationContext context = new ClassPathXmlApplicationContext("classpath:bean.xml");
		
		
		//3. ʹ��ǰ׺file��ʾ�����ļ��ľ���·��
		//ApplicationContext context = new ClassPathXmlApplicationContext("file:/home/tangjiquan/Study/workspace/sources/java/spring/spring2.5.6/test/bean.xml");
		
		//4. ͬʱ���ض���ļ�
		//String[] xfg = new String[]{"classpath:bean.xml"};
		//ApplicationContext context = new ClassPathXmlApplicationContext(xfg);

		
		//5. ʹ��ͨ������ظ���Ҫ����ļ�
		ApplicationContext context = new ClassPathXmlApplicationContext("*bean.xml");
		//ApplicationContext context = new ClassPathXmlApplicationContext("classpath:*bean.xml");

		
		Person person = (Person) context.getBean("person");
		System.out.println(person.getAge());
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
