package com.pms.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mysql.jdbc.StringUtils;
import com.pms.dao.AdminDao;
import com.pms.pojo.Admin;
import com.pms.util.DBUtils;

/**
 * @author Administrator
 *
 */
@Repository
public class AdminDaoImpl implements AdminDao {

	public Admin login(String name, String password) {
		DBUtils db=DBUtils.getInstance();
		String sql="select * from pms_admin where adminId=? and adminPassword=?";
		List<Object> params=new ArrayList<Object>();
		params.add(name);
		params.add(password);
		List<Admin> admins=null;
		try {
			admins=db.executeQueryByRef(sql, params, Admin.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(admins!=null && !admins.isEmpty()) return admins.get(0);
		return null;
	}

	

	/* ����������������ʼλ�ã����ݵ�ǰҳ��Ĵ�С�ҵ��������������ݵļ���
	 * @see com.smbmsmvc.dao.UserDao#getUsersByPage(com.smbmsmvc.pojo.User, int, int)
	 */
	public List<Admin> getAdminsByPage(Admin admin, int pageSize,int offset) {
		DBUtils db=DBUtils.getInstance();
		
		//StringBuffer ��string����ǿ��,���е�append�����ַ���׷�ӣ�Ч�ʸ���
				//where 1=1Ŀ����Ϊ���ú��������������ζ���where�ṹ
		StringBuffer sql=new StringBuffer("select * from pms_admin u where 1=1 ");
		List<Object> param=new ArrayList<Object>();
		if(admin!=null){
			//User�����Ĳ����û����͵绰,���������User���󴫵ݣ��ô������ܷ�����ж��Ƿ�Ҫ��and����
			if(!StringUtils.isNullOrEmpty(admin.getAdminName())){
			//StringUtils��mysql����������,Ŀ�����ж��ַ����Ƿ�Ϊnull��""
				sql.append(" and u.adminName like ?");
				param.add("%"+admin.getAdminName()+"%");
			}
			if(!StringUtils.isNullOrEmpty(admin.getAdminPhone())){
				sql.append(" and u.adminPhone like ?");
				param.add("%"+admin.getAdminPhone()+"%");
			}
		}
		//limit��mysql�ķ�ҳ��䣬��Ҫ��������
		//��һ�������Ƿ�ҳ����ʼ����
		//�ڶ��������Ƿ�ҳ��ÿҳ����
		sql.append(" limit ?,?");
		param.add(offset);
		param.add(pageSize);
		List<Admin> list=null;
		try {
			list = db.executeQueryByRef(sql.toString(), param,Admin.class);
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
	public int getAdminCountByPage(Admin admin) {
		DBUtils db = DBUtils.getInstance();
		
		StringBuffer sql = new StringBuffer("select count(*) as count from pms_admin u where 1=1 ");
		List<Object> param = new ArrayList<Object>();
		
		if(admin!= null) {
			
			if (!StringUtils.isNullOrEmpty(admin.getAdminName())) {
				sql.append(" and u.adminName like ?");
				param.add("%" + admin.getAdminName() + "%");
			}
			if (!StringUtils.isNullOrEmpty(admin.getAdminPhone())) {
				sql.append(" and u.adminPhone like ?");
				param.add("%" + admin.getAdminPhone() + "%");
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

	public int adminAdd(Admin admin) {
		DBUtils db=DBUtils.getInstance();
		String sql="insert into pms_admin (adminName,adminSex,adminTime,adminPassword,type,adminPhone) "
				+ " values (?,?,?,?,?,?)";

		List<Object> params = new ArrayList<Object>();
		params.add(admin.getAdminName());
		params.add(admin.getAdminSex());
		params.add(admin.getAdminTime());
		params.add(admin.getAdminPassword());
		params.add(admin.getType());
		params.add(admin.getAdminPhone());
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
	public Admin getAdminById(int id) {
		DBUtils db=DBUtils.getInstance();
		String sql="select * from pms_admin where id=?";
		List<Object> params=new ArrayList<Object>();
		params.add(id);
		
		List<Admin> users=null;
		try {
			users=db.executeQueryByRef(sql, params, Admin.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(users!=null && !users.isEmpty()) return users.get(0);
		return null;
		
	}



	public int adminDelete(int id) {
		DBUtils db=DBUtils.getInstance();
		String sql="delete from pms_admin where adminId=?";
		List<Object> params=new ArrayList<Object>();
		params.add(id);
		int users=-2;
		try {
			users=db.executeUpdate(sql, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}


	public Admin getAdmin(String name,String phone) {
		DBUtils db=DBUtils.getInstance();
		String sql=("select * from pms_admin u where u.adminName like ? and u.adminPhone like ?");
		List<Object> param=new ArrayList<Object>();
		
			//User�����Ĳ����û����͵绰,���������User���󴫵ݣ��ô������ܷ�����ж��Ƿ�Ҫ��and����
			
				param.add(name);
				List<Admin> admin=null;
				param.add(phone);
				try {
					
					admin=db.executeQueryByRef(sql, param, Admin.class);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return  admin.get(0);
			}



	public int changeAdminPassword(long id, String newpwd) {
		DBUtils db=DBUtils.getInstance();
		String sql="update pms_admin set adminPassword=  ?  where adminId = ?";
		List<Object> params=new ArrayList<Object>();
		params.add(newpwd);
		params.add(id);
		int users=-2;
		try {
			users=db.executeUpdate(sql, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
		
	
}
