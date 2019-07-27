package com.how2j.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.how2j.bean.Hero;
import com.how2j.idao.IHeroDao;

public class HeroDao implements IHeroDao{

	public HeroDao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection("jdbc:mysql://120.78.173.180:3306/how2j?characterEncoding=UTF-8", "root","1");
	}

	@Override
	public Hero select() {
		return null;
	}

	@Override
	public int getTotal() {
		int total = 0;
		try {
			Connection connection = getConnection();
			Statement statment = connection.createStatement();
			String sql = "select count(1) from hero";
			ResultSet rs = statment.executeQuery(sql);
			while(rs.next()) {
				total = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}
	
	@Override
	public void add(Hero hero) {
		try {
			String sql = "insert into hero values(null,?,?,?)";
			Connection connection = getConnection();
			PreparedStatement statment = connection.prepareStatement(sql);
			statment.setString(1, hero.getName());
			statment.setInt(2, hero.getHp());
			statment.setInt(3, hero.getDamage());
			statment.execute();
			ResultSet rs = statment.getGeneratedKeys();
			
			if(rs.next()) {
				int id = rs.getInt(1);
				hero.setId(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Hero hero) {
		try {
			String sql = "update hero set name = ?,hp = ?,damage = ? where id = ?";
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(0, hero.getName());
			ps.setInt(1, hero.hp);
			ps.setInt(2, hero.getDamage());
			ps.setInt(3, hero.id);
			boolean b = ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Hero hero) {
		try {
			String sql = "delete hero where id = "+ hero.getId();
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.execute();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
