package com.hj.springmvc.dao;

import com.hj.springmvc.model.User;


public interface IUserDao extends IBaseDao<User>{
	public User getUserByNameAndPassword(Object[] args);
}
