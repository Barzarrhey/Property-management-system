package com.pms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.dao.CompDao;
import com.pms.dao.impl.CompDaoImpl;
import com.pms.pojo.Comp;
import com.pms.service.CompService;

@Service
public class CompServiceImpl implements CompService {

	@Autowired
	CompDao compDao ;
//	public Comp login(int type) {
//		
//		return compDao.login(type);
//	}
	
	public List<Comp> getCompByPage(Comp comp, int pageSize, int currentPageNo) {
		// TODO Auto-generated method stub
		return compDao.getCompByPage(comp, pageSize, currentPageNo);
	}
	public int getCompCountByPage(Comp comp) {
		// TODO Auto-generated method stub
		return compDao.getCompCountByPage(comp);
	}
	public int compAdd(Comp comp) {
		// TODO Auto-generated method stub
		return compDao.compAdd(comp);
	}

	public Comp getCompById(int id) {
		// TODO Auto-generated method stub
		return compDao.getCompById(id);
	}

}
