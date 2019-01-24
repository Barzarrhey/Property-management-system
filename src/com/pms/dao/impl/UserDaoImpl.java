package com.pms.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mysql.jdbc.StringUtils;
import com.pms.dao.UserDao;
import com.pms.pojo.User;
import com.pms.util.DBUtils;

/**
 * @author Administrator
 *
 */
@Repository
public class UserDaoImpl implements UserDao {

	public User login(String name, String password) {
		DBUtils db=DBUtils.getInstance();
		String sql="select * from pms_user where userId=? and userPassword=?";
		List<Object> params=new ArrayList<Object>();
		params.add(name);
		params.add(password);
		List<User> users=null;
		try {
			users=db.executeQueryByRef(sql, params, User.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(users!=null && !users.isEmpty()) return users.get(0);
		return null;
	}

	

	/* 根据条件，根据起始位置，根据当前页面的大小找到符合条件的数据的集合
	 * @see com.smbmsmvc.dao.UserDao#getUsersByPage(com.smbmsmvc.pojo.User, int, int)
	 */
	public List<User> getUsersByPage(User user, int pageSize,int offset) {
		DBUtils db=DBUtils.getInstance();
		
		//StringBuffer 是string的增强版,其中的append用于字符串追加，效率更高
				//where 1=1目的是为了让后面的条件不管如何都有where结构
		StringBuffer sql=new StringBuffer("select * from pms_user u where 1=1 ");
		List<Object> param=new ArrayList<Object>();
		if(user!=null){
			//User传来的参数用户名和电话,这个参数用User对象传递，好处在于能方便的判断是否要加and条件
			if(!StringUtils.isNullOrEmpty(user.getUserName())){
			//StringUtils是mysql驱动包的类,目的是判断字符串是否为null或""
				sql.append(" and u.userName like ?");
				param.add("%"+user.getUserName()+"%");
			}
			if(!StringUtils.isNullOrEmpty(user.getUserPhone())){
				sql.append(" and u.userPhone like ?");
				param.add("%"+user.getUserPhone()+"%");
			}
		}
		//limit是mysql的分页语句，需要两个参数
		//第一个参数是分页的起始索引
		//第二个参数是分页的每页行数
		sql.append("order by userId DESC");
		sql.append(" limit ?,?");
		param.add(offset);
		param.add(pageSize);
		List<User> list=null;
		try {
			list = db.executeQueryByRef(sql.toString(), param, User.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	
	/* 
	 * 根据条件查询到底有多少条记录符合要求
	 * @see com.smbmsmvc.dao.UserDao#getUserCountByPage(com.smbmsmvc.pojo.User)
	 */
	public int getUserCountByPage(User user) {
		DBUtils db = DBUtils.getInstance();
		
		StringBuffer sql = new StringBuffer("select count(*) as count from pms_user u where 1=1 ");
		List<Object> param = new ArrayList<Object>();
		
		if (user != null) {
			
			if (!StringUtils.isNullOrEmpty(user.getUserName())) {
				sql.append(" and u.userName like ?");
				param.add("%" + user.getUserName() + "%");
			}
			if (!StringUtils.isNullOrEmpty(user.getUserPhone())) {
				sql.append(" and u.userPhone like ?");
				param.add("%" + user.getUserPhone() + "%");
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

	public int userAdd(User user) {
		DBUtils db=DBUtils.getInstance();
		String sql="insert into pms_user (userName,userSex,userTime,userHouseArea,userPassword,Type,userPhone) "
				+ " values (?,?,?,?,?,?,?)";

		List<Object> params = new ArrayList<Object>();
		params.add(user.getUserName());
		params.add(user.getUserSex());
		//params.add(user.getUserId());
		params.add(user.getUserTime());
		params.add(user.getUserHouseArea());
		params.add(user.getUserPassword());
		params.add(user.getType());
		params.add(user.getUserPhone());
		int res=-1;
		try {
			res=db.executeUpdate(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}



	/* 根据id获取User对象
	 * @see com.smbmsmvc.dao.UserDao#getUserById(int)
	 */
	public User getUserById(int id) {
		DBUtils db=DBUtils.getInstance();
		String sql="select * from pms_user where userId=?";
		List<Object> params=new ArrayList<Object>();
		params.add(id);
		
		List<User> users=null;
		try {
			users=db.executeQueryByRef(sql, params, User.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(users!=null && !users.isEmpty()) {
			return users.get(0);
		}else {
			return null;
		}
		
	}
	public int changeUserPassword(long id, String password) {
		DBUtils db=DBUtils.getInstance();
		String sql="update pms_user set userPassword=  ?  where userId = ?";
		List<Object> params=new ArrayList<Object>();
		params.add(password);
		params.add(id);
		int userr=-2;
		try {
			userr=db.executeUpdate(sql, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userr;
	}
	
}
