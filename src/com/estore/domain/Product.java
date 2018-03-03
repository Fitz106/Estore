package com.estore.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
@Entity
@Table(name="products")
public class Product implements Serializable{

	private static final long serialVersionUID = -4001481578985704384L;
	@Id @Column(name="id",length=100)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id; 
	@Column(name="name" , length=40)
	private String name; 
	@Column(name="price")
	private double price; 
	@Column(name="category" , length=40)
	private String category; 
	@Column(name="pnum")
	private int pnum; 
	@Column(name="imgurl" , length=100)
	private String imgurl; 
	@Column(name="description" , length=255)
	private String description; 
	//@OneToMany(mappedBy="product",cascade=CascadeType.ALL)
//	private Set<OrderItem> orderitem = new HashSet<OrderItem>();
//	public Set<OrderItem> getOrderitem() {
//		return orderitem;
//	}
//	public void setOrderitem(Set<OrderItem> orderitem) {
//		this.orderitem = orderitem;
//	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
