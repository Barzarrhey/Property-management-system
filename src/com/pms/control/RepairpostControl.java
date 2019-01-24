package com.pms.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

import com.pms.pojo.Repair;
import com.pms.service.impl.RepairServiceImpl;

/**
 * 
 * @author barzarrhey
 * @date 2019-01-20
 */
@Controller

public class RepairpostControl {
	private RepairServiceImpl repairService = new RepairServiceImpl();
	
	@RequestMapping("/submit.do")
    public String repairPostSubmit(String userId, String resId, String reason, String subDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date subDatee = sdf.parse(subDate);
		if(StringUtils.isNumeric(userId)&&StringUtils.isNumeric(resId)&&repairPostStore(userId, resId, reason, subDatee)) {
			
			return "repairpostsuccess";
		}
		else
			return "repairpostfail";
        
    }
	
	public boolean repairPostStore(String userId, String resId, String reason, Date subDate) {
		Repair repair = new Repair();
		repair.setUserId(Long.parseLong(userId));
		repair.setResId(Long.parseLong(resId));
		repair.setReason(reason);
		repair.setSubDate(subDate);
		System.out.println("用户ID:" + repair.getUserId());
		System.out.println("物品ID:" + repair.getResId());
		System.out.println("报修时间:" + repair.getSubDate());
		System.out.println("具体说明:" + repair.getReason());
		int flag1 = repairService.addRepair(repair);
		boolean flag=(flag1==1)?true:false;
		return flag;
	}
}
