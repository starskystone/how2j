package com.how2java.pojo;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category_")
public class Category {
 
	/*
	 * int id; String name; Set<Product> products;
	 * 
	 * public int getId() { return id; } public void setId(int id) { this.id = id; }
	 * public String getName() { return name; } public void setName(String name) {
	 * this.name = name; } public Set<Product> getProducts() { return products; }
	 * public void setProducts(Set<Product> products) { this.products = products; }
	 */
	int id;
    String name;
    Set<Product> products;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    //也可以达到映射的效果。 因为getName方法默认会被认为是字段映射。 除非加上了@Transient 才表示不进行映射
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "cid")
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
    
    
}