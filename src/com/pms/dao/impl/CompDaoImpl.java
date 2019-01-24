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
//	/* ����������������ʼλ�ã����ݵ�ǰҳ��Ĵ�С�ҵ��������������ݵļ���
//	 * @see com.smbmsmvc.dao.UserDao#getUsersByPage(com.smbmsmvc.pojo.User, int, int)
//	 */
	public List<Comp> getCompByPage(Comp comp, int pageSize,int offset) {
		DBUtils db=DBUtils.getInstance();
		
		//StringBuffer ��string����ǿ��,���е�append�����ַ���׷�ӣ�Ч�ʸ���
				//where 1=1Ŀ����Ϊ���ú��������������ζ���where�ṹ
		StringBuffer sql=new StringBuffer("select * from pms_complaint u where 1=1 ");
		List<Object> param=new ArrayList<Object>();
		if(comp!=null){
			//User�����Ĳ����û����͵绰,���������User���󴫵ݣ��ô������ܷ�����ж��Ƿ�Ҫ��and����
			if(comp.getUserId()!=null){
			//StringUtils��mysql����������,Ŀ�����ж��ַ����Ƿ�Ϊnull��""
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
//		//limit��mysql�ķ�ҳ��䣬��Ҫ��������
//		//��һ�������Ƿ�ҳ����ʼ����
//		//�ڶ��������Ƿ�ҳ��ÿҳ����
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
//	 * ����������ѯ�����ж�������¼����Ҫ��
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
			//list.get(0)�õ���һ��
			//get("count")�õ���һ������������count��ֵ
			//Ĭ�����ֵ�ǳ�����
			Long count=(Long)list.get(0).get("count");
			//intValue()�����������ת��Ϊ����
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
	/* ����id��ȡUser����
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
