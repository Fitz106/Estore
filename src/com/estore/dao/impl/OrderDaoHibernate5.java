package com.estore.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.estore.dao.OrderDao;
import com.estore.domain.Order;
import com.estore.domain.User;
@Repository
public class OrderDaoHibernate5 extends BaseDaoImpl<Order> implements OrderDao {

	@Override
	public void createOrder(Order order) {
		save(order);
	}

	@Override
	public List<Order> findOrder(String user_id) {
		@SuppressWarnings("unchecked")
		List<Order> orders = (List<Order>) getHibernateTemplate().find("from Order ord where ord.user_id =?", user_id);
		return orders;
	}

	@Override
	public void delOrder(String id) {
		deleteById(id);
	}

	@Override
	public void updateState(String id) {
		Order ord = findById(id);
		ord.setPaystate(1);
		update(ord);
		
	}

}
