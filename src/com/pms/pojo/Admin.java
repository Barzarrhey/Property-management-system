package com.pms.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Admin {
	private String adminName;
	private String adminSex;
	private long adminId;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date adminTime;
	private String adminPassword;
	private int type;
	private String adminPhone;
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminSex() {
		return adminSex;
	}
	public void setAdminSex(String adminSex) {
		this.adminSex = adminSex;
	}
	public long getAdminId() {
		return adminId;
	}
	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}
	public Date getAdminTime() {
		return adminTime;
	}
	public void setAdminTime(Date adminTime) {
		this.adminTime = adminTime;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	public long getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getAdminPhone() {
		return adminPhone;
	}
	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone;
	}
}
