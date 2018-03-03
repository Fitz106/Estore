package com.estore.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.estore.domain.Product;
import com.estore.service.ProductService;
import com.opensymphony.xwork2.ActionSupport;



public class CartAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ProductService getProductService() {
		return productService;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	@Autowired
	private ProductService productService;
	public String add()
	{
		// 1.得到要添加到购物车中的商品的id
		String id = ServletActionContext.getRequest().getParameter("id");

		Product p = productService.findById(id);

		// 3.得到购物车
		@SuppressWarnings("unchecked")
		Map<Product, Integer> cart = (Map<Product, Integer>) ServletActionContext.getRequest().getSession().getAttribute("cart");


		if (cart == null) {// 如果cart不存在，说明是第一次购物.
			cart = new HashMap<Product, Integer>();
		}
		// 判断购物车中是否有要添加商品。

		Integer count = cart.put(p, 1);
		if (count != null) {
			// 说明有吗
			cart.put(p, count + 1);
		}

		ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		return "index";
	}
	public String remove()
	{
		String id = ServletActionContext.getRequest().getParameter("id");

		Product p = new Product();
		p.setId(id);

		// 得到购物车
		@SuppressWarnings("unchecked")
		Map<Product, Integer> cart = (Map<Product, Integer>) ServletActionContext.getRequest().getSession().getAttribute("cart");

		cart.remove(p);

		if (cart.size() == 0) {
			ServletActionContext.getRequest().getSession().removeAttribute("cart");
		}

		return "showcart";
	}
	public String update()
	{
		String id = ServletActionContext.getRequest().getParameter("id");
		int count = Integer.parseInt(ServletActionContext.getRequest().getParameter("count"));

		// 这是要修改的商品
		Product p = new Product();
		p.setId(id);

		// 得到购物车
		@SuppressWarnings("unchecked")
		Map<Product, Integer> cart = (Map<Product, Integer>) ServletActionContext.getRequest().getSession().getAttribute("cart");

		// 修改商品的数量
		if (count == 0) {
			cart.remove(p); // 将商品从购物车中移除
		} else {
			cart.put(p, count);
		}
		ServletActionContext.getRequest().getSession().setAttribute("cart", cart);

		return "showcart";
	}
	@Override
	public String execute() throws Exception {
		String method = ServletActionContext.getRequest().getParameter("method");

		if ("add".equals(method)) {// 添加商品到购物车
			return add();
		} else if ("remove".equals(method)) { // 从购物车删除商品
			return remove();
		} else if ("update".equals(method)) {// 修改购物车商品
			return update();
		}else if("showcart".equals(method))
		{
			return "cartindex";
		}
			return null;
	}

}
