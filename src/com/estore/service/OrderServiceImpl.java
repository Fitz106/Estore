package com.estore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estore.dao.OrderDao;
import com.estore.dao.OrderItemDao;
import com.estore.dao.ProductDao;
import com.estore.dao.impl.OrderDaoHibernate5;
import com.estore.dao.impl.OrderItemDaoHibernate5;
import com.estore.dao.impl.ProductDaoHibernate5;
import com.estore.domain.Order;
import com.estore.domain.OrderItem;
import com.estore.domain.User;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao orderDAO;


	@Autowired
	private OrderItemDao orderitemDAO;
	@Autowired
	private ProductDao productDAO;
	public void setOrderDAO(OrderDao orderDAO) {
		this.orderDAO = orderDAO;
	}

	public void setOrderitemDAO(OrderItemDao orderitemDAO) {
		this.orderitemDAO = orderitemDAO;
	}

	public void setProductDAO(ProductDao productDAO) {
		this.productDAO = productDAO;
	}
	// 添加订单
	public void add(User user, Order order,List<OrderItem> item){
		// 1.向orders表中添加数据
		System.out.println(orderDAO==null);
		orderDAO.createOrder(order);
		System.out.println("#############");
		// 2.向orderitem表中添加数据
		orderitemDAO.addOrderItem(item);
		// 3.修改products表中数据
		productDAO.updatejianProductCount(item);
	}

	// 根据用户查找订单
	public List<Order> find(String user_id){

		List<Order> orders = orderDAO.findOrder(user_id); // 查询订单信息,不包含订单中的商品信息
		return orders;
	}

	// 根据id删除订单
	public void delete(String id){
		// 1.修改商品表中商品数量
		// 1.1 得到商品的数量
		List<OrderItem> items = orderitemDAO.findOrderItemByOrderId(id);
		// 1.2修改商品的数量
		productDAO.updateaddProductCount(items);
		// 2.删除订单项
		orderitemDAO.delOrderItem(id);
		// 3.删除订单
		orderDAO.delOrder(id);
	}

	// 根据订单号修改订单状态
	public void updateState(String id){
		orderDAO.updateState(id);
	}
}
