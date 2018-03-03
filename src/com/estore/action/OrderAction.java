package com.estore.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.estore.domain.Order;
import com.estore.domain.OrderItem;
import com.estore.domain.Product;
import com.estore.domain.User;
import com.estore.service.OrderService;
import com.estore.service.OrderServiceImpl;
import com.estore.service.ProductService;
import com.opensymphony.xwork2.ActionSupport;

public class OrderAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductService productService;
	public ProductService getProductService() {
		return productService;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public OrderService getOrderService() {
		return orderService;
	}
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	public String add()
	{
		// 1.将请求参数封装到Order对象中.
		Order order = new Order();
		// 1.1 表单数据
		try {
			BeanUtils.populate(order, ServletActionContext.getRequest().getParameterMap()); // 只封装了表单数据到javaBean，简单说，只有receiverinfo
																	// money两项
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
//		String receiverinfo = (String) ServletActionContext.getRequest().getAttribute("receiverinfo");
//		String money  = (String) ServletActionContext.getRequest().getAttribute("money");
//		order.setMoney(Double.parseDouble(money));
//		order.setReceiverinfo(receiverinfo);
		// 1.2 订单编号 当前用户id
		order.setId(UUID.randomUUID().toString());
		order.setPaystate(0);
		Date ordertime = new Date();
		order.setOrdertime(ordertime);
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
		if(user==null){
			return "error";
		}
		order.setUser_id(user.getId());

		// 1.3 将订单项封装到订单中.
		@SuppressWarnings("unchecked")
		Map<Product, Integer> cart = (Map<Product, Integer>) ServletActionContext.getRequest().getSession().getAttribute("cart"); // 得到购物车
		List<OrderItem> items = new ArrayList<OrderItem>();
		for (Product p : cart.keySet()) {

			OrderItem item = new OrderItem();
			item.setOrder_id(order.getId());
			Product p1 = productService.findById(p.getId());
			item.setProduct_id(p.getId());
			item.setBuynum(cart.get(p));
			item.setName(p.getName());
			item.setPrice(p.getPrice());
			items.add(item);
		}
		// 2.调用OrderService中方法，创建订单
		try {
			orderService.add(user, order,items);
//			ServletActionContext.getResponse().getWriter().write(
//					"下单成功,<a href='" + ServletActionContext.getRequest().getContextPath()
//							+ "/index.jsp'>继续购物</a>，<a href='"
//							+ ServletActionContext.getRequest().getContextPath()
//							+ "/order?method=search'>查看订单</a>");
			ServletActionContext.getRequest().setAttribute("orders",order);
			ServletActionContext.getRequest().setAttribute("orderitem",items);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	public String del()
	{
		String id = ServletActionContext.getRequest().getParameter("id"); // 得到要删除的订单的id。
		// 调用OrderService中删除订单操作
		try {
			orderService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			return "delfailure";
		}
		return "delsuccess";
	}
	public String search()
	{
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");

		if (user == null) {
			return "error";
		}
		// 2.调用orderService中查询订单操作
		try {
			List<Order> orders = orderService.find(user.getId());

			ServletActionContext.getRequest().setAttribute("orders", orders);
			return "searchsuccess";
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
	}
	@Override
	public String execute() throws Exception {
		// 得到请求参数method,判断当前是什么操作
		String method = ServletActionContext.getRequest().getParameter("method");
		if ("add".equals(method)) {
			return add();
		} else if ("del".equals(method)) {
			return del();
		} else if ("search".equals(method)) {
			return search();
		}else if("showorder".equals(method)){
			return "successshow";
		}else
		return null;
	}
	
}
