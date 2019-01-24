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
		
		//StringBuffer ��string����ǿ��,���е�append�����ַ���׷�ӣ�Ч�ʸ���
				//where 1=1Ŀ����Ϊ���ú��������������ζ���where�ṹ
		// select * from pms_repair  where 1=1  ORDER BY soldate,subDate desc
		StringBuffer sql=new StringBuffer("select * from pms_repair u where 1=1 ");
		List<Object> param=new ArrayList<Object>();
		if(repair!=null){
			//User�����Ĳ����û����͵绰,���������User���󴫵ݣ��ô������ܷ�����ж��Ƿ�Ҫ��and����
			if(repair.getUserId()!=null){
			//StringUtils��mysql����������,Ŀ�����ж��ַ����Ƿ�Ϊnull��""
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
//	 * ����������ѯ�����ж�������¼����Ҫ��
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
			//list.get(0)�õ���һ��
			//get("count")�õ���һ������������count��ֵ
			//Ĭ�����ֵ�ǳ�����
			Long count=(Long)list.get(0).get("count");
			//intValue()�����������ת��Ϊ����
			return count.intValue();
		}
		return -1;
	}
	
	/* ����id��ȡUser����
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
