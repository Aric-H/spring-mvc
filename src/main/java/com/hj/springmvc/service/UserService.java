package com.hj.springmvc.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hj.springmvc.dao.IUserDao;
import com.hj.springmvc.model.Pager;
import com.hj.springmvc.model.User;

@Service("userService")
public class UserService implements IUserService {

	@Resource
	private IUserDao userDao;
	@Override
	public void add(User user) {
		// TODO Auto-generated method stub
		userDao.add(user);

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		userDao.delete(id);

	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		userDao.update(user);

	}

	@Override
	public User load(int id) {
		// TODO Auto-generated method stub
		return userDao.load(id);

	}

	@Override
	public List<User> list() {
		// TODO Auto-generated method stub
		String hql = "from User";
		return userDao.list(hql);

	}

	@Override
	public Pager<User> findByPage() {
		// TODO Auto-generated method stub
		String hql = "from User";
		return userDao.find(hql);
	}

	@Override
	public User getUserByNameAndPassword(Object[] args) {
		// TODO Auto-generated method stub
		return userDao.getUserByNameAndPassword(args);
	}

}
