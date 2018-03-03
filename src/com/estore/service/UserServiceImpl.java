package com.estore.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.estore.dao.UserDao;

import com.estore.domain.User;
import com.estore.exception.*;
import com.estore.util.EstoreUtil;
import com.estore.util.MailUtils;
@Transactional
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDAO;

	public void setUserDAO(UserDao userDAO) {
		this.userDAO = userDAO;
	}

	//注册
	public Map<String,Object> register(String username,String password)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		if(StringUtils.isBlank(username)){
            map.put("msgreg", "用户名不能为空");
            return map;
        }
		if (StringUtils.isBlank(password)) {
            map.put("msgreg", "密码不能为空");
            return map;
        }

        User user = userDAO.findUserByUserName(username);

        if (user != null) {
            map.put("msgreg", "用户名已经被注册");
            return map;
        }
        // 密码加强
        user = new User();
        try {
			BeanUtils.populate(user, ServletActionContext.getRequest().getParameterMap());//将用户请求参数封装到user对象中。
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
        Date updatetime = new Date();
        user.setSalt(UUID.randomUUID().toString().substring(0, 6));
        user.setRole("user");
        user.setUpdatetime(updatetime);
        user.setState(0);
        user.setId(UUID.randomUUID().toString().substring(0, 8));
//        String head = String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000));
//        user.setHeadUrl(head);
        user.setPassword(EstoreUtil.MD5(password+user.getSalt()));
     // 手动封装一个激活码
     	user.setActivecode(UUID.randomUUID().toString());
      //  System.out.println("ok");
        //userDAO.addUser(user);
       // System.out.println("ok1");
	// 发送邮件操作
        try{
		String emailMsg = "注册成功，请在12小时内<a href='http://106.14.145.56:80/estore/user?method=activeuser&activecode="
				+ user.getActivecode()
				+ "'>激活</a>，激活码是"
				+ user.getActivecode();
		// System.out.println("ok2");
		MailUtils.sendMail(user.getEmail(), emailMsg);
		//System.out.println("ok4");
		} catch (Exception e) {
			map.put("msgreg", "注册失败");
			return map;
		} 
        map.put("msgregsuccess", "注册成功");
        userDAO.addUser(user);
        return map;
	}
	
	
	public Map<String,Object> activeUser(String activecode)  {
		Map<String,Object> map = new HashMap<String,Object>();
		// 调用一个dao中的方法，根据activecode查找用户
		
		User user  = userDAO.findUserByActiveCode(activecode);
		if(user == null)
		{
			map.put("msgfailure", "根据激活码查找用户失败");
			System.out.println("根据激活码查找用户失败");
			return map;
		}
		else
		{
			long time = System.currentTimeMillis() - user.getUpdatetime().getTime();
			
			// 判断它是否超时 开发是12小时，测试 1分钟
			if (time > 12 * 60 * 60 * 1000) {
				map.put("msgfailure","激活码过期");
				return map;
			}
			else
			{
				// 进行激活操作
				userDAO.activeUserByActivecode(activecode);
				map.put("msgactive", "激活成功");
				return map;
			}
		}
	}
	
	//登录
	public Map<String, Object> login(String username, String password) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isBlank(username)) {
            map.put("msglogin", "用户名不能为空");
            return map;
        }

        if (StringUtils.isBlank(password)) {
            map.put("msglogin", "密码不能为空");
            return map;
        }

        User user = userDAO.findUserByUserName(username);

        if (user == null) {
            map.put("msglogin", "用户名不存在");
            return map;
        }
        System.out.println(user.getSalt());
        if (!EstoreUtil.MD5(password+user.getSalt()).equals(user.getPassword())) {
            map.put("msglogin", "密码不正确");
            return map;
        }
        // 判断用户的状态。
		if (user.getState() == 0) {
			// 用户状态未激活，不能进行登录操作
			map.put("msglogin","用户未激活");
		}
		user = addLoginTicket(user);
        map.put("ticket", user.getTicket());
        ServletActionContext.getRequest().getSession().setAttribute("user", user);
        userDAO.update(user);
        return map;
    }
	public User addLoginTicket(User user) {
        Date date = new Date();
        date.setTime(date.getTime() + 1000*3600*24);
        user.setExpired(date);
        user.setStatus(0);
        user.setTicket(UUID.randomUUID().toString().replaceAll("-", ""));
        return user;
    }


    public void logout(String ticket) {
        User user = userDAO.findUserByTicket(ticket);
        user.setStatus(1);
        userDAO.update(user);
        ServletActionContext.getRequest().getSession().setAttribute("user", null);
        
    }
}
