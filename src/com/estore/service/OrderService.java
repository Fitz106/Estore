package com.estore.service;

import java.util.List;

import com.estore.domain.Order;
import com.estore.domain.OrderItem;
import com.estore.domain.User;

public interface OrderService {
	public void add(User user, Order order,List<OrderItem> item);
	
	public List<Order> find(String user_id);
	
	public void delete(String id);
	
	public void updateState(String id);
}
