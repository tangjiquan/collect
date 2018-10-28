package com.panther.mybatis.test.example01;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * @author: Kevin
 * @date: created in 下午9:30 2018-10-28
 * @version: V1.0
 */
public class MybatisHelloWorld {

	public static void main(String[] args){
		String resource = "com/panther/mybatis/test/example01/Configuration.xml";
		Reader reader ;
		try {
			reader = Resources.getResourceAsReader(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			SqlSession session = sqlSessionFactory.openSession();
			User user = session.selectOne("com.panther.mybatis.test.example01.UserDao.getUser", "1");
			System.out.println(user);
			session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
