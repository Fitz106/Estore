package com.estore.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.estore.dao.UserDao;
import com.estore.domain.User;
@Repository
public class UserDaoHibernate5 extends BaseDaoImpl<User> implements UserDao{

	@Override
	public void addUser(User user) {
		save(user);
	}

	@Override
	public User findUserByActiveCode(String activecode) {
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>)  getHibernateTemplate().find("from User u where u.activecode=?",activecode);
		if (users != null && users.size() > 0) {
			return users.get(0);
		}
		System.out.println("listchangdu"+users.size());
		return null;
	}

	@Override
	public void activeUserByActivecode(String activecode) {
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>)  getHibernateTemplate().find("from User u where u.activecode=?",activecode);
		if (users != null && users.size() > 0) {
			User user =  users.get(0);
			user.setState(1);
			update(user);
		}
		
		
		
		
	}

	@Override
	public User findUserByUserNameAndPassword(String username, String password) {
		User user =  findById(username);
		if(user == null | user.getPassword()!=password)
			return null;
		else
			return user;
				
	}

	@Override
	public boolean checkPrivilege(String role, String pname) {
		User user = findById(pname);
		if(role.equals(user.getRole()))
			return true;
		else
			return false;
	}

	@Override
	public User findUserByUserName(String username) {
		System.out.println(username);
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) getHibernateTemplate().find("from User u where u.username=?",username);
		if (users != null && users.size() > 0) {
			return users.get(0);
		}
		return null;
	}

	@Override
	public User findUserByTicket(String ticket) {
		System.out.println(ticket);
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) getHibernateTemplate().find("from User u where u.ticket=?",ticket);
		if (users != null && users.size() > 0) {
			User user = users.get(0);
			return user;
			
		}
		return null;
	}
}
