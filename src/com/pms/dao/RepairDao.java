package com.pms.dao;

import java.util.List;

import com.pms.pojo.Repair;

/**
 * 
 * @author barzarrhey
 * @date 2019-01-21
 */
public interface RepairDao {
	public int addRepair(Repair repair);
	public List<Repair> getrepairByPage(Repair repair, int pageSize,int currentPageNo);
	public int getrepairCountByPage(Repair repair);
	public int repairAdd(Repair repair);
}
