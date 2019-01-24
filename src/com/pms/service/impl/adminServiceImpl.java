package com.pms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.dao.AdminDao;
import com.pms.pojo.Admin;
import com.pms.service.AdminService;

@Service
public class adminServiceImpl implements AdminService {

	@Autowired
	AdminDao AdminDao ;
	public Admin login(String name, String password) {
		
		return AdminDao.login(name, password);
	}
	
	public List<Admin> getAdminsByPage(Admin admin, int pageSize, int currentPageNo) {
		// TODO Auto-generated method stub
		return AdminDao.getAdminsByPage(admin, pageSize, currentPageNo);
	}
	public int getAdminCountByPage(Admin admin) {
		// TODO Auto-generated method stub
		return AdminDao.getAdminCountByPage(admin);
	}
	public int adminAdd(Admin admin) {
		// TODO Auto-generated method stub
		return AdminDao.adminAdd(admin);
	}

	public Admin getAdminById(int id) {
		// TODO Auto-generated method stub
		return AdminDao.getAdminById(id);
	}

	public int adminDelete(int id) {
		// TODO Auto-generated method stub
		return AdminDao.adminDelete(id);
	}

	public Admin getAdmin(String name,String phone)
	{
		// TODO Auto-generated method stub
		return AdminDao.getAdmin(name, phone);
	}

	public int changeAdminPassword(long id, String newpwd) {

		return AdminDao.changeAdminPassword(id, newpwd);
	}

}
