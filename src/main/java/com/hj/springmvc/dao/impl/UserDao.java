package com.hj.springmvc.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.hj.springmvc.dao.IUserDao;
import com.hj.springmvc.model.User;

@Repository("userDao")
public class UserDao extends BaseDao<User> implements IUserDao{

	@Override
	public User getUserByNameAndPassword(Object[] args) {
		// TODO Auto-generated method stub
		String hql = "select user from User user where user.username = ? and user.password = ?";
		Query query = this.currentSession().createQuery(hql);
		if(args!=null){
			for(int i = 0;i<args.length;i++){
				query.setParameter(i, args[i]);
			}
		}
		return (User) query.uniqueResult();
	}

}
