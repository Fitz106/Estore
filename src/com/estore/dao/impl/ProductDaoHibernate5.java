package com.estore.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.estore.dao.ProductDao;
import com.estore.domain.Order;
import com.estore.domain.OrderItem;
import com.estore.domain.Product;
import com.estore.domain.User;
@Repository
public class ProductDaoHibernate5 extends BaseDaoImpl<Product> implements ProductDao {



	public Product findByProductId(String id) {
		return findById(id);
	}

	@Override
	public void updatejianProductCount(List<OrderItem> items) {
		Integer buyn = 0;
		Product pro;
		for (int i = 0; i < items.size(); i++) {

			OrderItem orderitem = items.get(i);
			buyn = orderitem.getBuynum();
			pro = findByProductId(orderitem.getProduct_id());
			pro.setPnum(pro.getPnum()-buyn);
			update(pro);
		}
	}

	@Override
	public void updateaddProductCount(List<OrderItem> items) {
		Integer buyn = 0;
		Product pro;
		for (int i = 0; i < items.size(); i++) {

			OrderItem orderitem = items.get(i);
			buyn = orderitem.getBuynum();
			pro = findByProductId(orderitem.getProduct_id());
			pro.setPnum(pro.getPnum()+buyn);
			update(pro);
		}
	}
	public void addproduct(Product product)
	{
		save(product);
	}

	@Override
	public Product findByProductName(String name) {
		@SuppressWarnings("unchecked")
		List<Product> product = (List<Product>)  getHibernateTemplate().find("from Product p where p.name=?",name);
		if (product != null && product.size() > 0) {
			return product.get(0);
		}
		System.out.println("listchangdu"+product.size());
		return null;
	}


	
}
