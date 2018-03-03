package com.estore.domain;

import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name="orderitem")
public class OrderItem implements Serializable{
	private static final long serialVersionUID = -3999116854744070038L;
	@Id
    @Column(name = "id", nullable = false)  
    @GeneratedValue(strategy = GenerationType.AUTO) 
	private int id;

	private int buynum; //
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
	private String name;
	private double price;
//	@ManyToOne(cascade=CascadeType.ALL)
//	@JoinColumn(name="order_id",unique=true)
//	private Order order; // 
//	@ManyToOne(cascade=CascadeType.ALL)
//	@JoinColumn(name="product_id",unique=true)
//	private Product product;
	private String order_id;
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	private String product_id;
	
	public int getBuynum() {
		return buynum;
	}
	public void setBuynum(int buynum) {
		this.buynum = buynum;
	}
//	public Order getOrder() {
//		return order;
//	}
//	public void setOrder(Order order) {
//		this.order = order;
//	}
//	public Product getProduct() {
//		return product;
//	}
//	public void setProduct(Product product) {
//		this.product = product;
//	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
