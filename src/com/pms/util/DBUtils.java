package com.pms.util;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DBUtils {

	// ��ʾ�������ݿ���û���
    private static String USERNAME ;
    // �������ݿ������
    private static String PASSWORD ;
    // �������ݿ��������Ϣ
    private static String DRIVER ;
    // ����������ݿ�ĵ�ַ
    private static String URL ;

    private static DBUtils per = null;
    // �������ݿ������
    private Connection con = null;
    // ����sql����ִ�ж���
    private PreparedStatement pstmt = null;
    // �����ѯ���صĽ������
    private ResultSet resultSet = null;
    
    
	
    

    private DBUtils() {
    	try {
			// ��ȡ�����ļ��е�������
			InputStream is = DBUtils.class.getClassLoader().getResourceAsStream("db.properties");
			// ���ݼ�ֵ�ԣ������ļ��е��ֶ�
			// ����������
			Properties properties = new Properties();
			properties.load(is);
			DRIVER = properties.getProperty("driver");// ��ȡ������ַ
			URL = properties.getProperty("url");
			USERNAME = properties.getProperty("username");
			PASSWORD = properties.getProperty("password");
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }

    /**
     * ����ģʽ����ù������һ������
     * 
     * @return
     */
    public static DBUtils getInstance() {
        if (per == null) {
            per = new DBUtils();
            per.registeredDriver();
        }
        return per;
    }

    private void registeredDriver() {
        // TODO Auto-generated method stub
        try {
            Class.forName(DRIVER);
           
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * ������ݿ������
     * 
     * @return
     */
    public Connection getConnection() {
        try {
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return con;
    }

    /**
     * ��ɶ����ݿ�ı�����ɾ�����޸ĵĲ���
     * 
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public int executeUpdate(String sql, List<Object> params)
            throws SQLException {

    	getConnection();
        int result = -1;  // ��ʾ���û�ִ�����ɾ�����޸ĵ�ʱ����Ӱ�����ݿ������
        
        pstmt = con.prepareStatement(sql);

        if (params != null && !params.isEmpty()) {
            int index = 1;
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }

        result = pstmt.executeUpdate();
        
        closeDB();
        return result;
    }
    

    /**
     * �����ݿ��в�ѯ����
     * 
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public List<Map<String, Object>> executeQuery(String sql,
            List<Object> params) throws SQLException {
    	 getConnection();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        int index = 1;
        pstmt = con.prepareStatement(sql);
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }
        resultSet = pstmt.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int cols_len = metaData.getColumnCount();
        while (resultSet.next()) {
            Map<String, Object> map = new HashMap<String, Object>();
            for (int i = 0; i < cols_len; i++) {
                String cols_name = metaData.getColumnName(i + 1);
                Object cols_value = resultSet.getObject(cols_name);
                if (cols_value == null) {
                    cols_value = "";
                }
                map.put(cols_name, cols_value);
            }
            list.add(map);
        }
        closeDB();
        return list;

    }

    /**
     * jdbc�ķ�װ�����÷����������װ,�Ѵ����ݿ��л�ȡ�����ݷ�װ��һ����Ķ�����
     * 
     * @param sql
     * @param params
     * @param cls
     * @return
     * @throws Exception
     */
    public <T> List<T> executeQueryByRef(String sql, List<Object> params,
            Class<T> cls) throws Exception {
    	getConnection();
        List<T> list = new ArrayList<T>();
        int index = 1;
        pstmt = con.prepareStatement(sql);
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }
        resultSet = pstmt.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int cols_len = metaData.getColumnCount();
        while (resultSet.next()) {
            T resultObject = cls.newInstance();  // ͨ��������ƴ���ʵ��
            for (int i = 0; i < cols_len; i++) {
                String cols_name = metaData.getColumnName(i + 1);
                Object cols_value = resultSet.getObject(cols_name);
                Field field = cls.getDeclaredField(cols_name);
                if (cols_value == null) {
                    cols_value = "";
                    if(field.getType().getName()=="long"
                    		|| field.getType().getName()=="int"
                    		|| field.getType().getName()=="short"
                    		|| field.getType().getName()=="byte"){
                    	cols_value = 0;
                    }
                    if(field.getType().getName()=="java.util.Date"){
                    	cols_value = null;
                    }
                }
                
                
                field.setAccessible(true); // ��javabean�ķ���privateȨ��
                field.set(resultObject, cols_value);
            }
            list.add(resultObject);
        }
        
        closeDB();
        
        return list;

    }

    public void closeDB() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    

}