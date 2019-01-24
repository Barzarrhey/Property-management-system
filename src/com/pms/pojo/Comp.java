package com.pms.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Comp {
	private long id;
	private Long userId;
	private Long adminId;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date subDate;
	
	private Date solDate;
	private String reason;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getSolDate() {
		return solDate;
	}
	public void setSolDate(Date solDate) {
		this.solDate = solDate;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getAdminId() {
		return adminId;
	}
	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}
	public Date getSubDate() {
		// TODO Auto-generated method stub
		return subDate;
	}
	public void setSubDate(Date subDate) {
		this.subDate = subDate;
	}

	
	
	
	
} 
 