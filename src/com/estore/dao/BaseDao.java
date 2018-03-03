package com.estore.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;




public interface BaseDao<T> {

	public void save(T entity);

	public void update(T entity);

	public void deleteById(Serializable id);
	

	public T findById(Serializable id);
	

	public List<T> findAll();
}
