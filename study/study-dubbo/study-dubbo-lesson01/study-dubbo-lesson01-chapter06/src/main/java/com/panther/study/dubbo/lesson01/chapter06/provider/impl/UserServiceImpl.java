package com.panther.study.dubbo.lesson01.chapter06.provider.impl;

import com.panther.study.dubbo.lesson01.chapter06.entity.User;
import com.panther.study.dubbo.lesson01.chapter06.provider.UserService;
import org.springframework.stereotype.Service;

import javax.ws.rs.PathParam;

/**
 * @author: Kevin
 * @date: created in 下午9:33 2018-10-03
 * @version: V1.0
 */
@Service("userService")
@com.alibaba.dubbo.config.annotation.Service(interfaceClass = com.panther.study.dubbo.lesson01.chapter06.provider.UserService.class, protocol = {"rest", "dubbo"}, retries = 0)
public class UserServiceImpl implements UserService {
	@Override
	public void testget() {
		//http://localhost:8888/provider/userService/testget
		System.out.println("测试...get");
	}

	@Override
	public User getUser() {
		User user = new User();
		user.setId("1001");
		user.setName("张三");
		return user;
	}

	@Override
	public User getUser(@PathParam(value = "id") Integer id) {
		System.out.println("测试传入int类型的id: " + id);
		User user = new User();
		user.setId("1001");
		user.setName("张三");
		return user;
	}

	@Override
	public User getUser(@PathParam(value = "id") Integer id, @PathParam(value = "name") String name) {
		System.out.println("测试俩个参数：");
		System.out.println("id: " + id);
		System.out.println("name: " + name);
		User user = new User();
		user.setId("1001");
		user.setName("张三");
		return user;
	}

	@Override
	public void testpost() {
		System.out.println("测试...post");
	}

	@Override
	public User postUser(User user) {
		System.out.println(user.getName());
		System.out.println("测试...postUser");
		User user1 = new User();
		user1.setId("1001");
		user1.setName("张三");
		return user1;
	}

	@Override
	public User postUser(@PathParam(value = "id") String id) {
		System.out.println(id);
		System.out.println("测试...get");
		User user = new User();
		user.setId("1001");
		user.setName("张三");
		return user;
	}
}
