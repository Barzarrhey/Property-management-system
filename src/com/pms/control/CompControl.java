package com.pms.control;

import java.text.ParseException;
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

import com.mysql.jdbc.Statement;
import com.pms.pojo.Comp;
import com.pms.service.CompService;
import com.pms.util.Constants;
import com.pms.util.DBUtils;
import com.pms.util.PageController;
import com.pms.util.PageModel;
import com.pms.util.UploadUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

@Controller
public class CompControl {
	
	@Autowired
	private CompService compService;
	Comp comp;
	//CompService compService;
	//Comp comp;

	@RequestMapping("/compsubmit.do")
    public String compPostSubmit(String userId, String adminId, String reason, String subDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date subDatee = sdf.parse(subDate);
		if(StringUtils.isNumeric(userId)&&StringUtils.isNumeric(adminId)&&compPostStore(userId, adminId, subDatee,reason)) {
			
			return "comppostsuccess";
		}
		else
			return "comppostfail";
        
    }
	public boolean compPostStore(String userId, String adminId, Date subDate,String reason) {
		Comp comp = new Comp();
		comp.setUserId(Long.parseLong(userId));
		comp.setAdminId(Long.parseLong(adminId));
		comp.setSubDate(subDate);
		comp.setReason(reason);
		int flag1 = compService.compAdd(comp);
		boolean flag=(flag1==1)?true:false;
		return flag;
	}

	@RequestMapping("/pgCompList.do")
	public String compListByPage(Comp comp,HttpServletRequest req,Model model){
		//HttpServletRequest�������ɵĵ���jsp�����ö�����������
		int totalCount= compService.getCompCountByPage(comp);
		//pager.offset�ɲ���ṩ�Ĳ���
		String strPageNum = req.getParameter("pager.offset");
		int pageNum = 1; // �ѵ�ǰҳ�����ַ���ת��Ϊ���֣����ת��ʧ�ܣ������õ�ǰҳ��Ϊ 1 ������ҳ
		try
		{
			pageNum = Integer.parseInt(strPageNum)  ;
		} catch (Exception e)
		{
			pageNum = 1;
		}
		
		List<Comp> complist=compService.getCompByPage(comp, Constants.pageSize,pageNum);
		// ��õ�ǰҳ�����ַ���		
		model=PageModel.setPageModel(pageNum, totalCount, model);
		model.addAttribute("compList",complist);
		return "Comp3";
	}
//	
	@RequestMapping("compquery.do")
	public String compQuery(Comp comp,Model model){
		List<Comp> complist=compService.getCompByPage(comp, Constants.pageSize,0);
		int totalCount= compService.getCompCountByPage(comp);
		model=PageModel.setPageModel(1, totalCount, model);
		model.addAttribute("compList",complist);
		model.addAttribute("userId",comp.getUserId());
		model.addAttribute("adminId",comp.getAdminId());
		return "Comp3";
	}
	@RequestMapping("toCompAdd.html")
	public String tocompAdd()
	{
		return "Comp1";
	}
	@RequestMapping("compsolve")
	public String  compsolve(String  id,Model model)
	{
		DBUtils db = DBUtils.getInstance();
		StringBuffer sql = new StringBuffer("update pms_complaint set solDate =  ?  where id = ?  ");
		List<Object> param=new ArrayList<Object>();
		Date date = new Date();
		param.add(date);
		param.add(id);
		try {
			int i = db.executeUpdate(sql.toString(), param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//return "Comp3";
		}
		compQuery1(comp,model);
		return "Comp3";
	}
	public void compQuery1(Comp comp,Model model){
		List<Comp> complist=compService.getCompByPage(comp, Constants.pageSize,0);
		int totalCount= compService.getCompCountByPage(comp);
		model=PageModel.setPageModel(1, totalCount, model);
		model.addAttribute("compList",complist);
	}
	

	@RequestMapping("/Comp3.html")
	public String index2(Model model) {
		compQuery2(comp,model);
		return "Comp3";
	}
	@RequestMapping("/Comp1.html")
	public String index3() {
		return "Comp1";
	}
	
	public void compQuery2(Comp comp,Model model){
		List<Comp> complist=compService.getCompByPage(comp, Constants.pageSize,0);
		int totalCount= compService.getCompCountByPage(comp);
		model=PageModel.setPageModel(1, totalCount, model);
		model.addAttribute("compList",complist);
		//return "Comp3";
	}

	

//	
//	/**
//	 * �����Ӱ������ļ����
//	 * @param user
//	 * @param session
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping("userAdd.do")
//	public String userAdd(User user,HttpSession session,HttpServletRequest request,Model model,
//			@RequestParam(value ="attachs", required = false) MultipartFile[] picPaths){
//		
//		User currentUser=(User)session.getAttribute(Constants.USER_SESSION);
//		user.setCreatedBy(currentUser.getId());
//		user.setCreationDate(new Date());
//		user.setModifyBy(currentUser.getId());
//		user.setModifyDate(new Date());
//		userService.userAdd(user);
//		
//		//��ʵ·���ϴ�
//		String path = 
//				request.getSession().
//				getServletContext().
//				getRealPath("static"+File.separator+"uploadfiles");
//		//�ļ��������ع��̵��ļ�·��ȫ��
//		System.out.println("path="+path);
//		List<String> fileList=UploadUtil.fileUtil(path, request, picPaths);
//		//������ļ����Ƶ���ǰ�ļ��У���ǰ�ļ���ֻ��д��
//		String copyPath="D:\\workspace\\SMBMSMVC\\WebContent\\static\\uploadfiles";
//		System.out.println("listfile:"+fileList.get(0));
//		
//		try {
//			//�����ļ�����path����copypath
//			FileUtils.copyFileToDirectory(new File(fileList.get(0)), new File(copyPath));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		//�����ǻص���ҳҳ��������²�ѯ���������Ȥ����д�ɷ�����������
//		//��ȡ��ҳ����
//		List<User> userlist=userService.getUsersByPage(null, Constants.pageSize, 0);
//		//��ȡ��ҳ��������
//		int totalCount= userService.getUserCountByPage(null);
//		//setPageModel����ǰ̨�ĵ�ǰҳ��,ǰ̨��������,����Щ���ݷ�װ��model��ȥ
//		model=PageModel.setPageModel(1, totalCount, model);
//		model.addAttribute("userList",userlist);
//		
//		return "userlistbypage";
//	}
//	
//	@RequestMapping("/userview.html")
//	public String index3(Model model,@RequestParam("userid") String id){
//		model.addAttribute("userid",id);
//		int uid=Integer.parseInt(id);
//		User user=userService.getUserById(uid);
//		model.addAttribute("user");
//		return "userview";
//	}
//	@RequestMapping("/usermodify.html")
//	public String index4(Model model,@RequestParam("userid") String id){
//		model.addAttribute("userid",id);
//		int uid=Integer.parseInt(id);
//		userService.getUserById(uid);
//		return "usermodify";
//	}
//	@RequestMapping("/userdelete.html")
//	public String index5(Model model,@RequestParam("userid") String id){
//		model.addAttribute("userid",id);
//		//ɾ�������״̬,
//		return "userlistbypage";
//	}
//	
	
}
