package com.hj.springmvc.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hj.springmvc.dao.IUserDao;
import com.hj.springmvc.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/springDispatcherServlet-servlet.xml")
public class TestUser {
	@Resource
	private IUserDao userDao;
	@Test
	public void testAdd(){
		User user = new User("玉皇大帝", "yudi123", "玉帝", "yudi@163.com");
		userDao.add(user);
	}
	@Test
	public void testDelete(){
		userDao.delete(4);
	}
	@Test
	public void testLoad(){
		System.out.println(userDao.load(1));
	}
	@Test
	public void testUpdate(){
		User user = userDao.load(1);
		user.setUsername("单策");
		user.setNickname("单车");
		user.setPassword("dance123");
		user.setEmail("dance@163.com");
		userDao.update(user);
	}

}
