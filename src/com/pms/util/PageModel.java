package com.pms.util;

import org.springframework.ui.Model;

public class PageModel {
	public static Model setPageModel(int pageNum,int totalCount,Model model){

		int currentPage = 0;
		int totalRecord = 0;
		int totalPage = 0;
		
		

		PageController pageContoller = new PageController();// 实例化PageController类的到pageContoller对象
		
		
		
		
		pageContoller.setTotalRecord(totalCount);// 设置总记录
		pageContoller.setTotalPage();// 设置总页数
		totalPage = pageContoller.getTotalPage();// 获得总页数

		// 如果当前页数小于0或大于总页数，则把当前页重新设为1，即首页
		if (pageNum <= 0 || pageNum > totalPage)
		{
			pageNum = 1;
		}
		pageContoller.setCurrentPage(pageNum);// 设置当前页
		currentPage = pageContoller.getCurrentPage();// 获得当前页
		totalRecord = pageContoller.getTotalRecord();
		// 获取总记录
		
		model.addAttribute("totalRecord", totalRecord);
		model.addAttribute("totalPages", totalPage);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("currentPage", currentPage);
		return model;
	}
}
