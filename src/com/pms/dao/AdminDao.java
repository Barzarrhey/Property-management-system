package com.pms.dao;

import java.util.List;

import com.pms.pojo.Admin;

public interface AdminDao {
	public Admin login(String name,String password);
	public List<Admin> getAdminsByPage(Admin admin, int pageSize,int currentPageNo);
	public int getAdminCountByPage(Admin admin);
	public int adminAdd(Admin admin);
	public Admin getAdminById(int id);
	public int adminDelete(int id);
	public Admin getAdmin(String name,String phone);
	public int changeAdminPassword(long id,String newpwd);
}
