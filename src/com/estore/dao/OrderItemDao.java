package com.estore.dao;

import java.util.List;

import com.estore.domain.Order;
import com.estore.domain.OrderItem;

public interface OrderItemDao extends BaseDao<OrderItem> {
	// 添加订单项
	void addOrderItem(List<OrderItem> item);
	
	// 根据订单id查找订单项
	List<OrderItem> findOrderItemByOrderId(String order_id);
	
	
	//根据id删除订单项
	void delOrderItem(String id);
}
