package com.how2j.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.how2j.bean.Product;
import com.how2j.idao.IProductDao;

public class ProductDao implements IProductDao {

	public static void main(String[] args) {
		System.out.println(new ProductDao().list().size());
	}
	
	public ProductDao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnecttion() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://120.78.173.180:3306/cart?characterEncoding=UTF-8", "root",
				"1");
	}

	@Override
	public List<Product> list() {
		List<Product> list = new ArrayList<Product>();
		try {
			Connection con = getConnecttion();
			Statement st = con.createStatement();
			String sql = "select * from product order by id asc";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				Product product = new Product();
				int id = rs.getInt(1);
				String name = rs.getString(2);
				int price = rs.getInt(3);
				product.setId(id);
				product.setName(name);
				product.setPrice(price);
				list.add(product);
			}
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
