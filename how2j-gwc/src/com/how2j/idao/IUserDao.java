package com.how2j.idao;

import com.how2j.bean.User;

public interface IUserDao {
	public User geUser(String name, String password);
}
