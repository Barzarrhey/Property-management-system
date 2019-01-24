package com.pms.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pms.pojo.Repair;
import com.pms.util.DBUtils;

public class RepairDaoImpl {
	public int addRepair(Repair repair) {
		DBUtils db=DBUtils.getInstance();
		String sql="insert into pms_repair (userId, resId,subDate,reason) "
				+ " values (?,?,?,?)";

		List<Object> params = new ArrayList<Object>();
		params.add(repair.getUserId());
		params.add(repair.getResId());
		params.add(repair.getSubDate());
		params.add(repair.getReason());
		int res=-1;
		try {
			res=db.executeUpdate(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}

	public List<Repair> getRepairByPage(Repair repair, int pageSize,int offset) {
		DBUtils db=DBUtils.getInstance();
		
		//StringBuffer 是string的增强版,其中的append用于字符串追加，效率更高
				//where 1=1目的是为了让后面的条件不管如何都有where结构
		// select * from pms_repair  where 1=1  ORDER BY soldate,subDate desc
		StringBuffer sql=new StringBuffer("select * from pms_repair u where 1=1 ");
		List<Object> param=new ArrayList<Object>();
		if(repair!=null){
			//User传来的参数用户名和电话,这个参数用User对象传递，好处在于能方便的判断是否要加and条件
			if(repair.getUserId()!=null){
			//StringUtils是mysql驱动包的类,目的是判断字符串是否为null或""
				sql.append(" and u.userID like ?");
				param.add(repair.getUserId());
				//param.add("%"+repair.getUserId()+"%");
			} 
			if(repair.getResId()!=null){
				sql.append(" and u.resId like ?");
				param.add(repair.getResId());
				//param.add("%"+repair.getAdminId()+"%");
			}
		}
		sql.append(" ORDER BY soldate,subDate Desc");
		sql.append(" limit ?,?");
		param.add(offset);
		param.add(pageSize);
		List<Repair> list=null;
		try {
			list = db.executeQueryByRef(sql.toString(), param, Repair.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
      }
//
//	
//	/* 
//	 * 根据条件查询到底有多少条记录符合要求
//	 * @see com.smbmsmvc.dao.UserDao#getUserCountByPage(com.smbmsmvc.pojo.User)
//	 */
	public int getRepairCountByPage(Repair repair) {
		DBUtils db = DBUtils.getInstance();
		
		StringBuffer sql = new StringBuffer("select count(*) as count from pms_repair u where 1=1 ");
		List<Object> param = new ArrayList<Object>();
		//System.out.println(repair.getUserId());
		if (repair != null) {
			
			if (repair.getUserId()!=null) {
				// System.out.println(repair.getUserId());
				sql.append(" and u.userId like ?");
				param.add( repair.getUserId() );
				//param.add("%"+repair.getUserId()+"%");
			}
			if (repair.getResId()!=null) {
				// System.out.println(repair.getResId());
				sql.append(" and u.resId like ?");
				param.add(repair.getResId());
				//param.add("%"+repair.getAdminId()+"%");
		}
		}
		List<Map<String, Object>> list = null;
		try {
			
			list = db.executeQuery(sql.toString(), param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list != null && !list.isEmpty()) {
			//list.get(0)得到第一行
			//get("count")得到第一行里面列名叫count的值
			//默认这个值是长整型
			Long count=(Long)list.get(0).get("count");
			//intValue()将这个长整型转换为整型
			return count.intValue();
		}
		return -1;
	}
	
	/* 根据id获取User对象
	 * @see com.smbmsmvc.dao.UserDao#getUserById(int)
	 */
	public Repair getRepairById(int id) {
		DBUtils db=DBUtils.getInstance();
		String sql="select * from pms_repair where id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		
		List<Repair> repair=null;
		try {
			repair=db.executeQueryByRef(sql, params, Repair.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(repair!=null && !repair.isEmpty()) return repair.get(0);
		return null;
		
	}
	
}
