package com.pms.dao.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mysql.jdbc.StringUtils;
import com.pms.dao.CompDao;
import com.pms.pojo.Comp;
import com.pms.util.DBUtils;

/**
 * @author Administrator
 *
 */
@Repository
public class CompDaoImpl implements CompDao {
	
//
//	/* 根据条件，根据起始位置，根据当前页面的大小找到符合条件的数据的集合
//	 * @see com.smbmsmvc.dao.UserDao#getUsersByPage(com.smbmsmvc.pojo.User, int, int)
//	 */
	public List<Comp> getCompByPage(Comp comp, int pageSize,int offset) {
		DBUtils db=DBUtils.getInstance();
		
		//StringBuffer 是string的增强版,其中的append用于字符串追加，效率更高
				//where 1=1目的是为了让后面的条件不管如何都有where结构
		StringBuffer sql=new StringBuffer("select * from pms_complaint u where 1=1 ");
		List<Object> param=new ArrayList<Object>();
		if(comp!=null){
			//User传来的参数用户名和电话,这个参数用User对象传递，好处在于能方便的判断是否要加and条件
			if(comp.getUserId()!=null){
			//StringUtils是mysql驱动包的类,目的是判断字符串是否为null或""
				sql.append(" and u.userID like ?");
				param.add(comp.getUserId());
				//param.add("%"+comp.getUserId()+"%");
			}
			if(comp.getAdminId()!=null){
				sql.append(" and u.adminId like ?");
				param.add(comp.getAdminId());
				//param.add("%"+comp.getAdminId()+"%");
				
			}
		}
//		//limit是mysql的分页语句，需要两个参数
//		//第一个参数是分页的起始索引
//		//第二个参数是分页的每页行数
		// ORDER BY soldate,subDate desc
		sql.append("  ORDER BY soldate,subDate desc ");
		sql.append(" limit ?,?");
		param.add(offset);
		param.add(pageSize);
		List<Comp> list=null;
		try {
			list = db.executeQueryByRef(sql.toString(), param, Comp.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
      }
//	public int compsolve(Long id)
//	{
//		DBUtils db = DBUtils.getInstance();
//		StringBuffer sql = new StringBuffer("UPDATE pms_complaint set solDate = ? where id = ? ");
//		List<Object> param=new ArrayList<Object>();
//		Date date = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        //Date subDatee = sdf.parse(date);
//		param.add(sdf);
//		List<Comp> list = null;
//		try {
//			list = db.executeQueryByRef(sql.toString(), param, Comp.class);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//return list;
//		return 1;
//	}
//
//	
//	/* 
//	 * 根据条件查询到底有多少条记录符合要求
//	 * @see com.smbmsmvc.dao.UserDao#getUserCountByPage(com.smbmsmvc.pojo.User)
//	 */
	public int getCompCountByPage(Comp comp) {
		DBUtils db = DBUtils.getInstance();
		
		StringBuffer sql = new StringBuffer("select count(*) as count from pms_complaint u where 1=1 ");
		List<Object> param = new ArrayList<Object>();
		
		if (comp != null) {
			
			if (comp.getUserId()!=null) {
				sql.append(" and u.userId like ?");
				param.add( comp.getUserId() );
				//param.add("%"+comp.getUserId()+"%");
			}
			if (comp.getAdminId()!=null) {
				sql.append(" and u.adminId like ?");
				param.add(comp.getAdminId());
				//param.add("%"+comp.getAdminId()+"%");
		}
		}
		List<Map<String, Object>> list = null;
		try {
			
			list = db.executeQuery(sql.toString(), param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
//
	public int compAdd(Comp comp) {
		DBUtils db=DBUtils.getInstance();
		String sql="insert into pms_complaint (userId,adminId,subDate,reason) "
				+ " values (?,?,?,?)";

		List<Object> params = new ArrayList<Object>();
		params.add(comp.getUserId());
		params.add(comp.getAdminId());
		params.add(comp.getSubDate());
		//params.add(comp.getSolDate());
		params.add(comp.getReason());

		int res=-1;
		try {
			res=db.executeUpdate(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}

//
//
	/* 根据id获取User对象
	 * @see com.smbmsmvc.dao.UserDao#getUserById(int)
	 */
	public Comp getCompById(int id) {
		DBUtils db=DBUtils.getInstance();
		String sql="select * from pms_complaint where id=?";
		//sql.append(" order by DESC ");
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		
		List<Comp> comp=null;
		try {
			comp=db.executeQueryByRef(sql, params, Comp.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(comp!=null && !comp.isEmpty()) return comp.get(0);
		return null;
		
	}
	
}
