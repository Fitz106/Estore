package com.estore.dao;

import java.util.List;

import com.estore.domain.Order;
import com.estore.domain.User;

public interface OrderDao extends BaseDao<Order> {
	// 添加订单
	void createOrder(Order order);
	
	// 根据用户查询订单表
	List<Order> findOrder(String user_id);
	
	//根据订单号删除订单
	void delOrder(String id);
	
	//根据id更新支付状态
	void updateState(String id);
}
