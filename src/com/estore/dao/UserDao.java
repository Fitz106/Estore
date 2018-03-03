package com.estore.dao;

import com.estore.domain.User;

public interface UserDao extends BaseDao<User> {
	//添加用户
	void addUser(User user);
		
	//根据激活码查找用户
	User findUserByActiveCode(String activecode);
	
	//根据激活码激活用户
	void activeUserByActivecode(String activecode);
	
	//根据用户名和密码查找用户
	User findUserByUserNameAndPassword(String username, String password);
	
	//检查权限
	boolean checkPrivilege(String role, String pname);
	
	//根据用户名查找用户
	User findUserByUserName(String username);
	User findUserByTicket(String ticket);
}
