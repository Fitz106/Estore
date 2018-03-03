package com.estore.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.estore.dao.OrderItemDao;
import com.estore.domain.Order;
import com.estore.domain.OrderItem;
@Repository
public class OrderItemDaoHibernate5 extends BaseDaoImpl<OrderItem> implements OrderItemDao {

	@Override
	public void addOrderItem(List<OrderItem> items) {

//		Object[][] params = new Object[items.size()][3];
		
		for (int i = 0; i < items.size(); i++) {
			
			OrderItem item = items.get(i);

			save(item);
		}
	}

	@Override
	public List<OrderItem> findOrderItemByOrderId(String order_id) {
		@SuppressWarnings("unchecked")
		List<OrderItem> orderitems = (List<OrderItem>) getHibernateTemplate().find("select orditem from OrderItem orditem where orditem.order_id =?", order_id);
		return orderitems;
	}


	@Override
	public void delOrderItem(String id) {
		@SuppressWarnings("unchecked")
		List<OrderItem> orderitems = (List<OrderItem>) getHibernateTemplate().find("select orditem from OrderItem orditem where orditem.order_id =?", id);
		getHibernateTemplate().deleteAll(orderitems);
		
	}


}
