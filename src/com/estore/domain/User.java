package com.estore.domain;




import java.util.Date;
import java.util.HashMap;

import java.util.Map;

import javax.persistence.*;
@Entity
@Table(name="users")
public class User {
	public User() {
		super();
	}
	@Id @Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
	@Column(name="username" , length=40)
	private String username;
	@Column(name="password" , length=100)
	private String password;
	@Column(name="nickname" , length=40)
	private String nickname;
	@Column(name="email" , length=100)
	private String email;
	@Column(name="salt" , length=100)
	private String Salt;

	@Column(name="ticket" , length=100)
	private String ticket;
	@Column(name="status" , length=100)
	private Integer status;
	@Column(name="expired" , length=100)
	private Date expired;
	
	@Column(name="role" ,length=100)
	private String role;
	@Column(name="state")
	private Integer state;
	@Column(name="activecode" , length=100)
	private String activecode;
	@Column(name="updatetime")
	private Date updatetime;
//	@OneToMany
//	private List<Order> order = new ArrayList<Order>();
//	
//	public List<Order> getOrder() {
//		return order;
//	}
//	public void setOrder(List<Order> order) {
//		this.order = order;
//	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getActivecode() {
		return activecode;
	}
	public void setActivecode(String activecode) {
		this.activecode = activecode;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime( Date updatetime) {
		this.updatetime = updatetime;
		
	}
	public String getSalt() {
		return Salt;
	}
	public void setSalt(String salt) {
		Salt = salt;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getExpired() {
		return expired;
	}
	public void setExpired(Date expired) {
		this.expired = expired;
	}
	public Map<String, String> validateRegist() {
		Map<String, String> map = new HashMap<String, String>();

		if (username == null || username.trim().isEmpty()) {
			map.put("username.message", "用户名不能为空");
		}

		if (password == null || password.trim().isEmpty()) {
			map.put("password.message", "密码不能为空");
		}

		if (nickname == null || nickname.trim().isEmpty()) {
			map.put("nickname.message", "昵称不能为空");
		}

		if (email == null || email.trim().isEmpty()) {
			map.put("email.message", "邮箱不能为空");
		}

		return map;
	}

	
	
}
