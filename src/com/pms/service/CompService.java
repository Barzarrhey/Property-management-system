package com.pms.service;

import java.util.List;

import com.pms.pojo.Comp;

public interface CompService {
//	public Comp login(int type);
	public List<Comp> getCompByPage(Comp comp, int pageSize,int currentPageNo);
	public int getCompCountByPage(Comp comp);
	public int compAdd(Comp comp);
	public Comp getCompById(int id);
}
