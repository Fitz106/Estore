package com.estore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estore.dao.ProductDao;
import com.estore.dao.UserDao;
import com.estore.domain.Product;
import com.estore.domain.User;





@Transactional
@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductDao productDAO;	
	@Autowired
	private UserDao userDAO;
	public UserDao getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDao userDAO) {
		this.userDAO = userDAO;
	}

	public ProductDao getProductDAO() {
		return productDAO;
	}

	public void setProductDAO(ProductDao productDAO) {
		this.productDAO = productDAO;
	}

	// 判断权限
	public String isprivilege(String ticket){
		User user = userDAO.findUserByTicket(ticket);
        if (user == null) {
            return "no";
        }else
        {
        	if("admin".equals(user.getRole()))
    		{
    			return "yes";
    		}
    		else
    		{
    			return "no";
    		}
        }
		
	}


	@Override
	public void save(Product product) {
		productDAO.addproduct(product);
	}

	// 查找所有商品
	public List<Product> findAll(){
		return productDAO.findAll();
	}
	// 根据id查找商品
	public Product findById(String id){
		return productDAO.findById(id);
	}

	@Override
	public Product findByName(String name) {
		// TODO Auto-generated method stub
		return productDAO.findByProductName(name);
	}

//	// 得到销售榜单信息
//	public List<Product> findSell(User user){
//		return productDAO.findSell();
//	}
	
}
