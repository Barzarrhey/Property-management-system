package com.pms.pojo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * @author barzarrhey
 * @date 2018-01-20
 * ±®–ﬁ¿‡
 */
public class Repair {
	private Long userId;
	private Long resId;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date subDate;
	private Date solDate;
	private String reason;
	private Long id;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getResId() {
		return resId;
	}
	public void setResId(Long resId) {
		this.resId = resId;
	}
	public Date getSubDate() {
		return subDate;
	}
	public void setSubDate(Date subDate) {
		this.subDate = subDate;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
