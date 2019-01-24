package com.pms.util;

import org.springframework.ui.Model;

public class PageModel {
	public static Model setPageModel(int pageNum,int totalCount,Model model){

		int currentPage = 0;
		int totalRecord = 0;
		int totalPage = 0;
		
		

		PageController pageContoller = new PageController();// ʵ����PageController��ĵ�pageContoller����
		
		
		
		
		pageContoller.setTotalRecord(totalCount);// �����ܼ�¼
		pageContoller.setTotalPage();// ������ҳ��
		totalPage = pageContoller.getTotalPage();// �����ҳ��

		// �����ǰҳ��С��0�������ҳ������ѵ�ǰҳ������Ϊ1������ҳ
		if (pageNum <= 0 || pageNum > totalPage)
		{
			pageNum = 1;
		}
		pageContoller.setCurrentPage(pageNum);// ���õ�ǰҳ
		currentPage = pageContoller.getCurrentPage();// ��õ�ǰҳ
		totalRecord = pageContoller.getTotalRecord();
		// ��ȡ�ܼ�¼
		
		model.addAttribute("totalRecord", totalRecord);
		model.addAttribute("totalPages", totalPage);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("currentPage", currentPage);
		return model;
	}
}
