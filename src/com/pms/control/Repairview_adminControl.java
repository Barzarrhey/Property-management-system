package com.pms.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pms.pojo.Repair;
import com.pms.service.RepairService;
import com.pms.util.Constants;
import com.pms.util.DBUtils;
import com.pms.util.PageModel;

@Controller
public class Repairview_adminControl {
	@Autowired
	private RepairService repairService;
	@RequestMapping("/pgRepairList_admin.do")
	public String repairListByPage(Repair repair,HttpServletRequest req,Model model){
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
		return "repairview_admin";
	}
	@RequestMapping("repairquery_admin.do")
	public String repairQuery(
			Repair repair,
			Model model){
		List<Repair> repairlist=repairService.getRepairByPage(repair, Constants.pageSize,0);
		int totalCount= repairService.getRepairCountByPage(repair);
		model=PageModel.setPageModel(1, totalCount, model);
		model.addAttribute("repairList",repairlist);
		model.addAttribute("userId",repair.getUserId());
		model.addAttribute("resId",repair.getResId());
		return "repairview_admin";
	}
	@RequestMapping("solveRepair.html")
	public String solveRepair(Repair repair,Model model, String id) {
		repairQuery(repair,model);
		//return "repairview_admin";
		DBUtils db = DBUtils.getInstance();
		StringBuffer sql = new StringBuffer("update pms_repair set solDate =  ?  where id = ?  ");
		List<Object> param=new ArrayList<Object>();
		Date date = new Date();
	
		param.add(date);
		param.add(id);
		try {
			@SuppressWarnings("unused")
			int i = db.executeUpdate(sql.toString(), param);
			repairQueryAdmin(repair,model);
			return "repairview_admin";
		} catch (Exception e) {
			e.printStackTrace();
			repairQueryAdmin(repair,model);
			return "repairview_admin";
		}
	}
	public String repairQueryAdmin(
			Repair repair,
			Model model){
		List<Repair> repairlist=repairService.getRepairByPage(repair, Constants.pageSize,0);
		int totalCount= repairService.getRepairCountByPage(repair);
		model=PageModel.setPageModel(1, totalCount, model);
		model.addAttribute("repairList",repairlist);
		return "repairview_admin";
	}
	
}
