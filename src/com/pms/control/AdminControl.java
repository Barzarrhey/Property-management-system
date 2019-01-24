package com.pms.control;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.pms.pojo.Admin;
import com.pms.pojo.User;
import com.pms.service.AdminService;
import com.pms.service.UserService;

import com.pms.util.Constants;

import com.pms.util.PageModel;



@Controller
public class AdminControl {

	@Autowired
	private AdminService adminService;
	@Autowired
	private UserService userService;

	@RequestMapping("/login.do")
	public String login(@RequestParam("name") String name, @RequestParam("password") String pwd,
			@RequestParam("type") String type, Model model, HttpSession session) {
		if (type.equals("1")) {
			Admin admin = adminService.login(name, pwd);
			if (admin == null) {
				session.setAttribute("error", "登录失败！用户名或密码有误");
				return "fail";
			} else {
				session.setAttribute("admin", admin);

				// 获取分页数据
//				List<Admin> adminlist = adminService.getAdminsByPage(null, Constants.pageSize, 0);
//				// 获取分页的总条数
//				int totalCount = adminService.getAdminCountByPage(null);
//				// setPageModel设置前台的当前页面,前台的总条数,将这些内容封装到model中去
//				model = PageModel.setPageModel(0, totalCount, model);
//				model.addAttribute("adminList", adminlist);
				return "mainpage";
			}
		} else {
			User user = userService.login(name, pwd);
			if (user == null) {
				session.setAttribute("error", "登录失败！用户名或密码有误");
				return "fail";
			} else {
				session.setAttribute("admin", user);
				return "mainpage";
			}
		}

	}

	@RequestMapping("/pgAdminListbyadmin.html")
	public String adminListByadmin(Admin admin,HttpServletRequest req, Model model) {
		// HttpServletRequest可以自由的调用jsp的内置对象并重新命名
		int totalCount = adminService.getAdminCountByPage(admin);
		// pager.offset由插件提供的参数
		String strPageNum = req.getParameter("pager.offset");
		int pageNum = 0; // 把当前页数的字符串转化为数字，如果转化失败，则设置当前页数为 1 ，即首页
		try {
			pageNum = Integer.parseInt(strPageNum);
		} catch (Exception e) {
			pageNum = 0;
		}

		List<Admin> adminlist = adminService.getAdminsByPage(admin, Constants.pageSize, pageNum);
		// 获得当前页数的字符串
		model = PageModel.setPageModel(pageNum, totalCount, model);
		model.addAttribute("adminList", adminlist);
		model.addAttribute("adminName", admin.getAdminName());
		model.addAttribute("adminPhone", admin.getAdminPhone());
		return "adminlistbyadmin";
	}
	@RequestMapping("/pgAdminListbyuser.html")
	public String adminListByuser(Admin admin,HttpServletRequest req, Model model) {
		// HttpServletRequest可以自由的调用jsp的内置对象并重新命名
		int totalCount = adminService.getAdminCountByPage(admin);
		// pager.offset由插件提供的参数
		String strPageNum = req.getParameter("pager.offset");
		int pageNum = 0; // 把当前页数的字符串转化为数字，如果转化失败，则设置当前页数为 1 ，即首页
		try {
			pageNum = Integer.parseInt(strPageNum);
		} catch (Exception e) {
			pageNum = 0;
		}

		List<Admin> adminlist = adminService.getAdminsByPage(admin, Constants.pageSize, pageNum);
		// 获得当前页数的字符串
		model = PageModel.setPageModel(pageNum, totalCount, model);
		model.addAttribute("adminList", adminlist);
		model.addAttribute("adminName", admin.getAdminName());
		model.addAttribute("adminPhone", admin.getAdminPhone());
		return "adminlistbyuser";
	}

	@RequestMapping("/adminquerybyadmin.html")
	public String adminQuerybyadmin(Admin admin, Model model) {
		// SpringMVC中，如果参数是某个对象的属性，它可以自动封装到对应的对象去
		// 属性名和列名完全一致
		List<Admin> adminlist = adminService.getAdminsByPage(admin, Constants.pageSize, 0);
		int totalCount = adminService.getAdminCountByPage(admin);
		model = PageModel.setPageModel(1, totalCount, model);
		model.addAttribute("adminList", adminlist);
		model.addAttribute("adminName", admin.getAdminName());
		model.addAttribute("adminPhone", admin.getAdminPhone());
		return "adminlistbyadmin";
	}
	@RequestMapping("/adminquerybyuser.html")
	public String adminQuerybyuser(Admin admin, Model model) {
		// SpringMVC中，如果参数是某个对象的属性，它可以自动封装到对应的对象去
		// 属性名和列名完全一致
		List<Admin> adminlist = adminService.getAdminsByPage(admin, Constants.pageSize, 0);
		int totalCount = adminService.getAdminCountByPage(admin);
		model = PageModel.setPageModel(1, totalCount, model);
		model.addAttribute("adminList", adminlist);
		model.addAttribute("adminName", admin.getAdminName());
		model.addAttribute("adminPhone", admin.getAdminPhone());
		return "adminlistbyuser";
	}

	/**
	 * 这个添加包括了文件添加
	 * 
	 * @param user
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("adminAdd.html")
	public String adminAdd(Admin admin, @RequestParam("adminName") String name,
			@RequestParam("adminPassword") String pwd, @RequestParam("adminSex") String sex,
			@RequestParam("adminPhone") String phone, HttpSession session, Model model) {
		int j =-2;
		if ((!name.equals(""))&&(!pwd.equals(""))&&(!phone.equals(""))) {
			admin.setAdminName(name);
			admin.setAdminPassword(pwd);
			admin.setAdminSex(sex);
			admin.setAdminTime(new Date());
			admin.setAdminPhone(phone);
			admin.setType(1);
			int i = adminService.adminAdd(admin);
			if (i == 1) {
				admin = adminService.getAdmin(name, phone);
				session.setAttribute("success", admin.getAdminId());
				 j=3;
					model.addAttribute("pe", j);
				return "adminadd";
			} else {
				j=2;
				model.addAttribute("pe", j);
				return "adminadd";
			}
		} else {
			 j=1;
			model.addAttribute("pe", j);
			return "adminadd";
		}
	}

	
	

	

	@Autowired
	private AdminService adminDelect;

	@RequestMapping("/admindelete.html")
	public String index5(Model model, @RequestParam("adminId") String id) {
		int uid = Integer.parseInt(id);
		int res = -2;
		res = adminDelect.adminDelete(uid);
		if (res != -1) {
			List<Admin> adminlist = adminService.getAdminsByPage(null, Constants.pageSize, 0);
			// 获取分页的总条数
			int totalCount = adminService.getAdminCountByPage(null);
			// setPageModel设置前台的当前页面,前台的总条数,将这些内容封装到model中去
			model = PageModel.setPageModel(1, totalCount, model);
			model.addAttribute("adminList", adminlist);
			return "adminlistbyadmin";
		} else {
			int i = 1;
			model.addAttribute("te", i);
			return "adminlistbyadmin";
		}
	}


	@RequestMapping("/changeAdminPassword.html")
	public String index1(@RequestParam("oldpwd") String oldpwd, @RequestParam("newpwd") String newpwd,
			@RequestParam("newpwdag") String newpwdag, Model model, HttpSession session) {
		int i = -1;
		long id = -1;
		Admin Admin = null;
		String pwd = null;
		if ((!oldpwd.equals("")) && (!newpwd.equals("")) && newpwd.equals(newpwdag)) {
			try {
				Admin = (Admin) session.getAttribute("admin");
				pwd = Admin.getAdminPassword();
				id = Admin.getAdminId();
			} catch (Exception e) {

			}
			if (pwd.equals(oldpwd)) {
				
				int j =adminService.changeAdminPassword(id, newpwdag);
				if(j==1) {
					i = 4;
					model.addAttribute("by", i);
					Admin.setAdminPassword(newpwd);
					session.setAttribute("admin", Admin);
				return "admininformation";
				}
				else {
					i = 3;
					model.addAttribute("by", i);
					return "changeadminform";
				}
			} else {
				i = 2;
				model.addAttribute("by", i);
				return "changeadminform";
			}
		} else {
			i = 1;
			model.addAttribute("by", i);
			return "changeadminform";
		}

	}

	@RequestMapping("/changeadminform.html")
	public String index12() {
		return "changeadminform";
	}
	@RequestMapping("/welcome.html")
	public String index7() {
		return "welcome";
	}

	@RequestMapping("/adminpage.html")
	public String index8() {
		return "right";
	}

	@RequestMapping("/admininformation.html")
	public String index9() {
		return "admininformation";
	}

	@RequestMapping("/adminadd.html")
	public String index10() {
		return "adminadd";
	}
}
