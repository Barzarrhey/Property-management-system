package com.pms.service;

import java.util.List;

import com.pms.pojo.Repair;

public interface RepairService {
	int addRepair(Repair repair);
	public List<Repair> getRepairByPage(Repair repair, int pageSize,int currentPageNo);
	public int getRepairCountByPage(Repair repair);
	public Repair getRepairById(int id);
}
