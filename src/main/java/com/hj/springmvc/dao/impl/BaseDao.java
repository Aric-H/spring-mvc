package com.hj.springmvc.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.hj.springmvc.dao.IBaseDao;
import com.hj.springmvc.model.Pager;
import com.hj.springmvc.model.SystemContext;

public class BaseDao<T> extends HibernateDaoSupport implements IBaseDao<T>{
	
	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory){
		this.setSessionFactory(sessionFactory);
	}
	//获取泛型类型
	private Class<T> clazz;
	private Class<T> getClazz(){
		ParameterizedType type = (ParameterizedType)(this.getClass().getGenericSuperclass());
		Type[] types = type.getActualTypeArguments();
		clazz = (Class<T>)types[0];
		return clazz;
	}
 
	@Override
	public void add(T t) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(t);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(load(id));
		
	}

	@Override
	public void update(T t) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(t);
		
	}

	@Override
	public List<T> list(String hql) {
		// TODO Auto-generated method stub
		return this.currentSession().createQuery(hql).list();
	}

	@Override
	public List<T> list(String hql,Object[] args) {
		// TODO Auto-generated method stub
		Query query = this.currentSession().createQuery(hql);
		if(args!=null){
			for(int i = 0;i<args.length;i++){
				query.setParameter(i, args[i]);
			}
		}
		return query.list();
	}

	@Override
	public T load(int id) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(getClazz(), id);
		
	}

	@Override
	public List<T> list(String hql,Object arg) {
		// TODO Auto-generated method stub
		return this.list(hql,new Object[]{arg});
	}

	@Override
	public Pager<T> find(String hql,Object[] args) {
		// TODO Auto-generated method stub
		Pager<T> page = new Pager<T>();
		Query query = this.currentSession().createQuery(hql);
		if(args!=null){
			for(int i = 0;i<args.length;i++){
				query.setParameter(i, args[i]);
			}
		}
		int currentPage = SystemContext.getCurrentPage();
		int pageSize = SystemContext.getPageSize();
		//获取查询的记录数
		long totalRecord = query.list().size();
		query.setFirstResult((currentPage-1)*pageSize).setMaxResults(pageSize);
		page.setDatas(query.list());
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		page.setTotalRecord(totalRecord);
		return page;
	}
	private String getCountHql(String hql){
		//1、获取hql中from前面的字符串
		String f = hql.substring(0, hql.indexOf("from"));
		//2、将from前面的字符串替换为select count(*)
		if(f.equals("")){
			hql="select count(*) "+hql;
		}else{
			hql.replace(f, "select count(*) ");
		}
		return hql;
	}

	@Override
	public Pager<T> find(String hql,Object arg) {
		// TODO Auto-generated method stub
		return this.find(hql,new Object[]{arg});
	}

	@Override
	public Pager<T> find(String hql) {
		// TODO Auto-generated method stub
		return this.find(hql,null);
	}

}
