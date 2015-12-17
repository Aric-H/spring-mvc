package com.hj.springmvc.service;

import java.util.List;

import com.hj.springmvc.model.Pager;
import com.hj.springmvc.model.User;

public interface IUserService {
	public void add(User user);
	public void delete(int id);
	public void update(User user);
	public User load(int id);
	public User getUserByNameAndPassword(Object[] args);
	public List<User> list();
	public Pager<User> findByPage();
}
