package com.pms.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.pms.pojo.Admin;
import com.pms.pojo.Repair;
import com.pms.pojo.User;
import com.pms.service.RepairService;
import com.pms.util.Constants;
import com.pms.util.PageModel;

/**
 * 
 * @author barzarrhey
 * @date 2018-01-20
 */
@SuppressWarnings("unused")
@Controller
public class LeftControl{
	@Autowired
	RepairService repairService;
	Repair repair = new Repair();
	
	@RequestMapping("repairpost.html")
	public String repairPost(){
		return "repairpost";
	}
	@RequestMapping("repairview.html")
	public String repairView(HttpSession session,Model model) {
		// 这里模拟用户登录时传递session，当代码合并时删除 Begin
		// 管理员登录
		// Admin admin1 = new Admin();
		// session.setAttribute(Constants.USER_SESSION, admin1);
		// 用户登录
		// User user1 = new User();
		// user1.setUserId(1);
		// session.setAttribute(Constants.USER_SESSION, user1);
		// 这里模拟用户登录时传递session，当代码合并时删除 End
		// 获取session
		try {
			Admin admin = (Admin) session.getAttribute("admin");
			System.out.println("管理员查看报修");
			repairQueryAdmin(repair,model);
			return "repairview_admin";
		} catch (Exception e) {
			User user = (User) session.getAttribute("admin");
			System.out.println("用户查看报修");
			repairQueryUser(repair,user,model);
			return "repairview_user";
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
	public String repairQueryUser(
			Repair repair,User user,
			Model model){
		List<Repair> repairlist=repairService.getRepairByPage(repair, Constants.pageSize,0);
		int totalCount= repairService.getRepairCountByPage(repair);
		model=PageModel.setPageModel(1, totalCount, model);
		model.addAttribute("repairList",repairlist);
		repair.setUserId(user.getUserId());
		model.addAttribute("userId",repair.getUserId());
		return "repairview_user";
	}
}
