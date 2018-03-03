package com.estore.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.estore.domain.Product;
import com.estore.service.ProductService;
import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private ProductService productService;
	public ProductService getProductService() {
		return productService;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public String findAll()
	{
		// 1.查询所有商品
		List<Product> ps = productService.findAll();

		// 2.跳转到page.jsp页面
		Product p = ps.get(0);
		ServletActionContext.getRequest().setAttribute("p", p);
		System.out.println(ps.get(0).getName());
		System.out.println(ps.get(0).getImgurl());
		return "success";
	}
	/**
	 * 执行的访问首页的方法:
	 */
	public String execute(){
		return findAll();
	}
}
