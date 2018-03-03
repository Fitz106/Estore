package com.estore.action;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.estore.service.UserService;
import com.estore.service.UserServiceImpl;
import com.estore.util.EstoreUtil;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public String regist() throws IOException{
//	// 判断验证码程序:
//			// 接收验证码:
//			String checkcode;
//			checkcode = ServletActionContext.getRequest().getParameter("checkcode");
//			// 从session中获得验证码的随机值:
//			String checkcode1 = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode_session");
//			if(!checkcode.equalsIgnoreCase(checkcode1)){
//				ServletActionContext.getResponse().getWriter().write("验证码错误");
//				return "registfailure";
//			}

			try
			{
				String username = ServletActionContext.getRequest().getParameter("username");
				String password = ServletActionContext.getRequest().getParameter("password");
				System.out.println(username+password);
				Map<String, Object> map = userService.register(username, password);
				if (map.containsKey("msgregsuccess")) 
				{
		            return "registsuccess";
		        } 
				else 
				{
					String msgregerror = (String) map.get("msgreg");
					ServletActionContext.getRequest().setAttribute("regist.message", msgregerror);
		            return "registfailure";
		        } 
			}
			catch (Exception e) 
			{
		       // logger.error("注册异常" + e.getMessage());
				ServletActionContext.getRequest().setAttribute("regist.message",e.getMessage());
				
		        return "registfailure";
		    }
	}
	public String login() {
		String username = ServletActionContext.getRequest().getParameter("username");
		String password = ServletActionContext.getRequest().getParameter("password");
		String remeberme = ServletActionContext.getRequest().getParameter("remember");
		Map<String, Object> map = userService.login(username, password);
		if (map.containsKey("ticket")) 
		{
		    Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
		    cookie.setPath("/");
		    if ("on".equals(remeberme)) {
		        cookie.setMaxAge(3600 * 24 * 5);
		    }
		    ServletActionContext.getResponse().addCookie(cookie);

		    
		    return "loginsuccess";
		} 
		else 
		{
			String loginmsg = (String) map.get("msglogin");
			ServletActionContext.getRequest().setAttribute("login.message",loginmsg);
		    return "loginfailure";
		}
	}
    public String logout() {
    	Cookie[] cookie = ServletActionContext.getRequest().getCookies();
    	String ticket = null;
    	for(int i=0;i<cookie.length;i++)
    	{
    		Cookie cook = cookie[i];
    		if(cook.getName().equalsIgnoreCase("ticket"))
    		{
    			ticket = cook.getValue();
    			break;
    		}
    	}
        return "logout";
    }
    public String activecode() {
		// TODO Auto-generated method stub
		String activecode = ServletActionContext.getRequest().getParameter("activecode");
		System.out.println(activecode);
    	Map<String,Object> map = userService.activeUser(activecode);
    	if(map.containsKey("msgfailure"))
    	{
    		return "activefailure";
    	}
    	else
    	{
    		return "activesuccess";
    	}
	}

	@Override
	public String execute() throws Exception {
		// 得到请求参数method,判断当前是什么操作
		String method = ServletActionContext.getRequest().getParameter("method");
		if ("login".equals(method)) { // 登录操作台
			return login();
		} else if ("regist".equals(method)) { // 注册操作
			return regist();
		} else if ("logout".equals(method)) {
			// 注销操作
			return logout();
		} else if ("activeuser".equals(method)) {
			return activecode();
		}else if ("registpage".equals(method))
		{
			return "registpage";
		}
		return null;
	}
	
	
}
