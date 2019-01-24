package com.pms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.dao.UserDao;
import com.pms.dao.impl.UserDaoImpl;
import com.pms.pojo.User;
import com.pms.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao ;
	public User login(String name, String password) {
		
		return userDao.login(name, password);
	}
	
	public List<User> getUsersByPage(User user, int pageSize, int currentPageNo) {
		// TODO Auto-generated method stub
		return userDao.getUsersByPage(user, pageSize, currentPageNo);
	}
	public int getUserCountByPage(User user) {
		// TODO Auto-generated method stub
		return userDao.getUserCountByPage(user);
	}
	public int userAdd(User user) {
		// TODO Auto-generated method stub
		return userDao.userAdd(user);
	}

	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return userDao.getUserById(id);
	}

	public int changeUserPassword(long id, String password) {
		// TODO Auto-generated method stub
		return userDao.changeUserPassword(id, password);
	}
	
	

}
