package com.estore.action;

import java.util.ResourceBundle;

import org.apache.struts2.ServletActionContext;


import com.opensymphony.xwork2.ActionSupport;



public class PayAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String paypage()
	{
		return "success";
	}
	public String onlinepay()
	{
		// 1.将要提交的数据得到
		// 获得 支付必须基本数据
		String orderid = ServletActionContext.getRequest().getParameter("orderid");
		String money = ServletActionContext.getRequest().getParameter("money");
		// 银行
		String pd_FrpId = ServletActionContext.getRequest().getParameter("pd_FrpId");

		// 发给支付公司需要哪些数据
		String p0_Cmd = "Buy";
		String p2_Order = orderid;
		String p3_Amt = money;
		String p4_Cur = "CNY";

		
		// 2.将数据提交到指定的路径

		// response.sendRedirect("https://www.yeepay.com/app-merchant-proxy/node?p0_Cmd="+p0_Cmd+"&p1_MerId="+p1_MerId);

		ServletActionContext.getRequest().setAttribute("pd_FrpId", pd_FrpId);
		ServletActionContext.getRequest().setAttribute("p0_Cmd", p0_Cmd);
		ServletActionContext.getRequest().setAttribute("p2_Order", p2_Order);
		ServletActionContext.getRequest().setAttribute("p3_Amt", p3_Amt);
		ServletActionContext.getRequest().setAttribute("p4_Cur", p4_Cur);


		return "successpay";

	}
	
	@Override
	public String execute() throws Exception {
		String method = ServletActionContext.getRequest().getParameter("method");
		if ("paypage".equals(method)) {
			// 根据id查找商品
			return paypage();
		} else if("onlinepay".equals(method))
		{
			return onlinepay();
		}
		return null;
	}
	

}
