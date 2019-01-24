package com.pms.control;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileSystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.pms.pojo.User;
import com.pms.service.UserService;
import com.pms.util.Constants;
import com.pms.util.DBUtils;
import com.pms.util.PageController;
import com.pms.util.PageModel;
import com.pms.util.UploadUtil;

import org.apache.commons.io.FileUtils;

@Controller
public class UserControl {
	
	@Autowired
	private UserService userService;

	@RequestMapping("/pgUserList.do")
	public String userListByPage(User user,HttpServletRequest req,Model model){
		//HttpServletRequest可以自由的调用jsp的内置对象并重新命名
		int totalCount= userService.getUserCountByPage(user);
		//pager.offset由插件提供的参数
		String strPageNum = req.getParameter("pager.offset");
		int pageNum = 0; // 把当前页数的字符串转化为数字，如果转化失败，则设置当前页数为 1 ，即首页
		try
		{
			pageNum = Integer.parseInt(strPageNum)  ;
		} catch (Exception e)
		{
			pageNum = 0;
		}
		
		List<User> userlist=userService.getUsersByPage(user, Constants.pageSize,pageNum);
		// 获得当前页数的字符串		
		model=PageModel.setPageModel(pageNum, totalCount, model);
		model.addAttribute("userList",userlist);
		model.addAttribute("userName",user.getUserName());
		model.addAttribute("userPhone",user.getUserPhone());
		return "userlook1";
	}
	
	@RequestMapping("userquery.do")
	public String userQuery(User user,Model model){
		//SpringMVC中，如果参数是某个对象的属性，它可以自动封装到对应的对象去
		//属性名和列名完全一致
		List<User> userlist=userService.getUsersByPage(user, Constants.pageSize,0);
		int totalCount= userService.getUserCountByPage(user);
		model=PageModel.setPageModel(1, totalCount, model);
		model.addAttribute("userList",userlist);
		model.addAttribute("userName",user.getUserName());
		model.addAttribute("userPhone",user.getUserPhone());
		return "userlook1";
	}
	public void userQuery1(User user,Model model){
		//SpringMVC中，如果参数是某个对象的属性，它可以自动封装到对应的对象去
		//属性名和列名完全一致
		List<User> userlist=userService.getUsersByPage(user, Constants.pageSize,0);
		int totalCount= userService.getUserCountByPage(user);
		model=PageModel.setPageModel(1, totalCount, model);
		model.addAttribute("userList",userlist);
		model.addAttribute("userName",user.getUserName());
		model.addAttribute("userphone",user.getUserPhone());
		//return "userlook1";
	}
	@RequestMapping("/userlook1.do")
	public String index9(User user,Model model){
		 userQuery1( user,model);
		return "userlook1";
	}
	
	@RequestMapping("useradd1.do")
	public String useradd2(@RequestParam("name") String name,
			@RequestParam("sex") String sex,
			@RequestParam("area") String area,
			@RequestParam("password") String pwd,
			@RequestParam("phone") String phone, HttpServletRequest req, Model model) {
		int fl=-1;
		if(name.equals("")||pwd.equals("")||phone.equals("")) {
			String message="信息不能为空";
			fl=1;
			req.getSession().setAttribute("message", message);
			model.addAttribute("fl", fl);
			return "useradd";
		}else {
		User user=new User();
		user.setType(2);
		//user.setUserId(7);
		user.setUserHouseArea(area);
		user.setUserName(name);
		user.setUserPassword(pwd);
		user.setUserPhone(phone);
		user.setUserSex(sex);
		//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		//System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		user.setUserTime(new Date());
		int re=userService.userAdd(user);
		if(re==-1) {
			String message="增添失败";
			fl=2;
			 req.getSession().setAttribute("message", message);
			 model.addAttribute("fl", fl);
			return "useradd";
		}else {
			 
			 DBUtils db=DBUtils.getInstance();
			 String sql="select * from pms_user where userName=? and userPhone=?";
			 List<Object> params=new ArrayList<Object>();
			 params.add(name);
			 params.add(phone);
			 List<User> useradd1=null;
				try {
					useradd1=db.executeQueryByRef(sql, params, User.class);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				model.addAttribute("useradd1",useradd1.get(0));
			return "useraddsu";
		}
		}
		
	}
	
	@RequestMapping("/userview.do")
	public String index3(Model model,@RequestParam("userId") String id){
		//model.addAttribute("userid",id);
		int uid=Integer.parseInt(id);
		User user=userService.getUserById(uid);
	    System.out.print(uid);
		model.addAttribute("user",user);
		return "userview";
	}
	@RequestMapping("/usermodify.html")
	public String index4(Model model,@RequestParam("userid") String id){
		model.addAttribute("userid",id);
		int uid=Integer.parseInt(id);
		userService.getUserById(uid);
		return "usermodify";
	}
  
	@RequestMapping("/userchange.do")
	public String index12(@RequestParam("userId") String id, 
			Model model){
		int uid=Integer.parseInt(id);
		int fo=-1;
		User user=userService.getUserById(uid);
		if(user.getUserSex().equals("男")) {
			fo=1;
		}else {
			fo=2;
		}
		model.addAttribute("fo",fo);
		model.addAttribute("user",user);
		return "userchange";
	}
	@RequestMapping("/userchange1.do")
	public String index13(@RequestParam("userName") String userName, 
			@RequestParam("userSex") String userSex,
			@RequestParam("userHouseArea") String area,
			@RequestParam("userPhone") String userPhone,
			@RequestParam("userId") String userId,
			User user,Model model, HttpServletRequest req,
			User user1,Model model1){
		int fla=-1;
		if(userName.equals("")||userSex.equals("")||userPhone.equals("")) {
			String message="信息不全，修改失败";
            fla=1;
			req.getSession().setAttribute("message", message);
			model.addAttribute("fla", fla);
			return "userchange";
		}
		DBUtils db=DBUtils.getInstance();
		String sql="update pms_user set userName=?,userSex=?,userHouseArea=?,userPhone=? where userId=?";
		List<Object> params=new ArrayList<Object>();
		params.add(userName);
		params.add(userSex);
		params.add(area);
		params.add(userPhone);
		params.add(userId);
		try {
			int delete=db.executeUpdate(sql, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int uid=Integer.parseInt(userId);
		User user2=userService.getUserById(uid);
		model.addAttribute("user",user2);
		return "userchangesu";
	}
	@RequestMapping("/userdelete.do")
	public String index5(Model model,@RequestParam("userId") String id,
			HttpServletRequest req,User user){
		//model.addAttribute("userid",id);
		//删除或更新状态,
		int uid=Integer.parseInt(id);
		DBUtils db=DBUtils.getInstance();
		String sql="delete from pms_user where userId=?";
		List<Object> params=new ArrayList<Object>();
		params.add(uid);
		try {
			int delete=db.executeUpdate(sql, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userQuery1(user,model);
		return "userlook1";
	}
	
	@RequestMapping("changeUserPassword.html")
	public String index20(@RequestParam("oldpwd") String oldpwd, @RequestParam("newpwd") String newpwd,
			@RequestParam("newpwdag") String newpwdag, Model model, HttpSession session) {
		int i = -1;
		long id = -1;
		User User = null;
		String pwd = null;
		if ((!oldpwd.equals("")) && (!newpwd.equals("")) && newpwd.equals(newpwdag)) {
			try {
				User = (User) session.getAttribute("admin");
				pwd = User.getUserPassword();
				id = User.getUserId();
			} catch (Exception e) {

			}
			if (pwd.equals(oldpwd)) {
				
				int j =userService.changeUserPassword(id, newpwdag);
				if(j==1) {
					i=4;
					model.addAttribute("bys", i);
					User.setUserPassword(newpwd);
					session.setAttribute("admin", User);
				return "userlook";
				}
				else {
					i = 3;
					model.addAttribute("bys", i);
					return "changeuserform";
				}
			} else {
				i = 2;
				model.addAttribute("bys", i);
				return "changeuserform";
			}
		} else {
			i = 1;
			model.addAttribute("bys", i);
			return "changeuserform";
		}

	}
	
	@RequestMapping("/userlook.do")
	public String index8(){
		return "userlook";
	}
	@RequestMapping("/useradd.do")
	public String index10(){
		return "useradd";
	}
	@RequestMapping("/userPasswordChange.do")
	public String index18(){
		return "changeuserform";
	}

	
}
