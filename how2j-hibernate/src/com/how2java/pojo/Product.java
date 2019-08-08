package com.how2java.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product_")
public class Product {
	/*注解前product
	 * int id; String name; float price; Category category; int version; public int
	 * getId() { return id; } public void setId(int id) { this.id = id; } public
	 * String getName() { return name; } public void setName(String name) {
	 * this.name = name; } public float getPrice() { return price; } public void
	 * setPrice(float price) { this.price = price; } public Category getCategory() {
	 * return category; } public void setCategory(Category category) { this.category
	 * = category; } public int getVersion() { return version; } public void
	 * setVersion(int version) { this.version = version; }
	 */
	int id;
	String name;
	float price;
	Category category;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "price")
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	@ManyToOne
	@JoinColumn(name = "cid")
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
}