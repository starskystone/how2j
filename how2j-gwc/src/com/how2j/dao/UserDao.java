package com.how2j.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.how2j.bean.User;
import com.how2j.idao.IUserDao;

public class UserDao implements IUserDao {

	public UserDao() {
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
	public User geUser(String name, String password) {
		User user = new User();
		try {
			Connection con = getConnecttion();
			Statement st = con.createStatement();
			String sql = "select * from user where name = ? and password = ?";
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt(1);
				String nameR = rs.getString(2);
				String passwordR = rs.getString(3);
				user.setId(id);
				user.setName(nameR);
				user.setPassword(passwordR);
;			}
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
}
