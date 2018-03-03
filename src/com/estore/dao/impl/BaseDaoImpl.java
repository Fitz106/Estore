package com.estore.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.estore.dao.BaseDao;

public abstract class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	private Class<T> clazz;

	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		setSessionFactory(sessionFactory);
	}
	
	
	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {

		ParameterizedType pt = (ParameterizedType)this.getClass().getGenericSuperclass();//BaseDaoImpl<User> 
		clazz = (Class<T>)pt.getActualTypeArguments()[0];
	}

	public Session getCurrentSession(){
		Session session = null;
		try {
			session = getSessionFactory().getCurrentSession();
		} catch (HibernateException e) {
			throw new RuntimeException("getCurrentSession error", e);
			//session = getSessionFactory().openSession();
		}
		return session;
	}
	
	@Override
	public void save(T entity) {
		getHibernateTemplate().save(entity);
//		getHibernateTemplate().execute(new HibernateCallback<T>() {
//			@Override
//			public T doInHibernate(Session session) throws HibernateException {
//				session.save(entity);
//				return null;
//			}
//	});
	}
	
	@Override
	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}
	
	@Override
	public void deleteById(Serializable id) {
		getHibernateTemplate().delete(findById(id));
	}
	
	@Override
	public T findById(Serializable id) {
		return getHibernateTemplate().get(clazz, id);
	}
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<T> findAll() {	
		return (List<T>) getHibernateTemplate().find("FROM "+ clazz.getSimpleName());
	}
}
