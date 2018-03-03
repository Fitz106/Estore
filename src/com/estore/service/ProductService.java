package com.estore.service;

import java.util.List;

import com.estore.domain.Product;

public interface ProductService {
	public String isprivilege(String ticket);
	
	public List<Product> findAll();
	
	public Product findById(String id);
	
	public Product findByName(String name);
	public void save(Product product);
}
