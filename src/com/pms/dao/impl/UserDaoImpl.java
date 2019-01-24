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

	

	/* ����������������ʼλ�ã����ݵ�ǰҳ��Ĵ�С�ҵ��������������ݵļ���
	 * @see com.smbmsmvc.dao.UserDao#getUsersByPage(com.smbmsmvc.pojo.User, int, int)
	 */
	public List<User> getUsersByPage(User user, int pageSize,int offset) {
		DBUtils db=DBUtils.getInstance();
		
		//StringBuffer ��string����ǿ��,���е�append�����ַ���׷�ӣ�Ч�ʸ���
				//where 1=1Ŀ����Ϊ���ú��������������ζ���where�ṹ
		StringBuffer sql=new StringBuffer("select * from pms_user u where 1=1 ");
		List<Object> param=new ArrayList<Object>();
		if(user!=null){
			//User�����Ĳ����û����͵绰,���������User���󴫵ݣ��ô������ܷ�����ж��Ƿ�Ҫ��and����
			if(!StringUtils.isNullOrEmpty(user.getUserName())){
			//StringUtils��mysql����������,Ŀ�����ж��ַ����Ƿ�Ϊnull��""
				sql.append(" and u.userName like ?");
				param.add("%"+user.getUserName()+"%");
			}
			if(!StringUtils.isNullOrEmpty(user.getUserPhone())){
				sql.append(" and u.userPhone like ?");
				param.add("%"+user.getUserPhone()+"%");
			}
		}
		//limit��mysql�ķ�ҳ��䣬��Ҫ��������
		//��һ�������Ƿ�ҳ����ʼ����
		//�ڶ��������Ƿ�ҳ��ÿҳ����
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
	 * ����������ѯ�����ж�������¼����Ҫ��
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
			//list.get(0)�õ���һ��
			//get("count")�õ���һ������������count��ֵ
			//Ĭ�����ֵ�ǳ�����
			Long count=(Long)list.get(0).get("count");
			//intValue()�����������ת��Ϊ����
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



	/* ����id��ȡUser����
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
