package com.pms.service.impl;

import com.pms.dao.impl.RepairDaoImpl;
import com.pms.pojo.Repair;
import java.util.List;
import com.pms.service.RepairService;
import org.springframework.stereotype.Service;

/**
 * 
 * @author barzarrhey
 * @date 2019-01-21
 */
@Service
public class RepairServiceImpl implements RepairService {

	private RepairDaoImpl repairDao = new RepairDaoImpl();
	
	public int addRepair(Repair repair) {
		return repairDao.addRepair(repair);
	}
	public List<Repair> getRepairByPage(Repair repair, int pageSize, int currentPageNo) {
		return repairDao.getRepairByPage(repair, pageSize, currentPageNo);
	}
	public int getRepairCountByPage(Repair repair) {
		return repairDao.getRepairCountByPage(repair);
	}
	public Repair getRepairById(int id) {
		return repairDao.getRepairById(id);
	}
	
}
