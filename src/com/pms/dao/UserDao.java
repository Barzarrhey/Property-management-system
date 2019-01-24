package com.pms.dao;

import java.util.List;

import com.pms.pojo.User;

public interface UserDao {
	public User login(String name,String password);
	
	public List<User> getUsersByPage(User user, int pageSize,int currentPageNo);
	public int getUserCountByPage(User user);
	public int userAdd(User user);
	public User getUserById(int id);
	public int changeUserPassword(long id, String password);
}
