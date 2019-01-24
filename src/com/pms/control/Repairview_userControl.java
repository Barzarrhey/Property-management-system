package com.pms.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pms.pojo.Repair;
import com.pms.pojo.User;
import com.pms.service.RepairService;
import com.pms.util.Constants;
import com.pms.util.PageModel;
import javax.servlet.http.HttpSession;


@Controller
public class Repairview_userControl {
	Repair repair = new Repair();
	@Autowired
	private RepairService repairService;
	@RequestMapping("/pgRepairList_user.do")
	public String repairListByPage(HttpSession session,Repair repair, HttpServletRequest req,Model model){
		User user = (User) session.getAttribute("admin");
		repair.setUserId(user.getUserId());
		int totalCount= repairService.getRepairCountByPage(repair);
		String strPageNum = req.getParameter("pager.offset");
		int pageNum = 1;
		try
		{
			pageNum = Integer.parseInt(strPageNum)  ;
		} catch (Exception e)
		{
			pageNum = 1;
		}
		
		List<Repair> repairlist=repairService.getRepairByPage(repair, Constants.pageSize,pageNum);
		model=PageModel.setPageModel(pageNum, totalCount, model);
		model.addAttribute("repairList",repairlist);
		return "repairview_user";
	}
}
