package com.estore.service;

import java.util.Map;


import com.estore.domain.User;

public interface UserService {
	
	public Map<String,Object> register(String username,String password);
	
	public Map<String,Object> activeUser(String activecode);
	
	public Map<String, Object> login(String username, String password);
	
	public User addLoginTicket(User user);
	public void logout(String ticket);
}
