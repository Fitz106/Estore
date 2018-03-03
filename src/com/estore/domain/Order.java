package com.estore.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
@Entity
@Table(name="orders")
public class Order implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7554845798251950991L;
	@Id @Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
	@Column(name="money")
	private double money;
	@Column(name="receiverinfo" , length=255)
	private String receiverinfo;
	@Column(name="paystate")
	private int paystate;

	@Column(name="ordertime")
	private Date ordertime;
	
	private String user_id;
	
public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	//	@ManyToOne
//	@JoinColumn(name="user_id")
//	private User user;
//	
//	
//	@OneToMany(mappedBy="order",cascade=CascadeType.ALL)
//	private List<OrderItem> orderitem = new ArrayList<OrderItem>();
//
//	public List<OrderItem> getOrderitem() {
//		return orderitem;
//	}
//	public void setOrderitem(List<OrderItem> orderitem) {
//		this.orderitem = orderitem;
//	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getReceiverinfo() {
		return receiverinfo;
	}
	public void setReceiverinfo(String receiverinfo) {
		this.receiverinfo = receiverinfo;
	}
	public int getPaystate() {
		return paystate;
	}
	public void setPaystate(int paystate) {
		this.paystate = paystate;
	}

//	public User getUser() {
//		return user;
//	}
//	public void setUser(User user) {
//		this.user = user;
//	}

	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
}
