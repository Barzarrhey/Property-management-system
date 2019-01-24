package com.pms.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class User {
	
	private String userName;
	private String userSex;
	private long userId;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date userTime;
	private String userHouseArea;
	private String userPassword;
	private int type;
	private String userPhone;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public Date getUserTime() {
		return userTime;
	}
	public void setUserTime(Date userTime) {
		this.userTime = userTime;
	}
	public String getUserHouseArea() {
		return userHouseArea;
	}
	public void setUserHouseArea(String userHouseArea) {
		this.userHouseArea = userHouseArea;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	
	
	
	
} 
 