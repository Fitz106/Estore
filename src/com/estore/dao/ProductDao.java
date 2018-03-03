package com.estore.dao;

import java.util.List;

import com.estore.domain.Order;
import com.estore.domain.OrderItem;
import com.estore.domain.Product;

public interface ProductDao extends BaseDao<Product> {
	//添加商品
	void addproduct(Product product);
	
	//查询所有商品
	//List<Product> findAll();
	
	//通过id查询商品
	Product findByProductId(String id);
	
	//根据订单更新商品数量
	void updatejianProductCount(List<OrderItem> items);
	
	Product findByProductName(String name);
	
	//删除订单项更新商品数量
	void updateaddProductCount(List<OrderItem> items);
	
//	//销售榜单
//	List<Product> findSell();
}
