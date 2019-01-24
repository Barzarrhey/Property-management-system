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

	// 表示定义数据库的用户名
    private static String USERNAME ;
    // 定义数据库的密码
    private static String PASSWORD ;
    // 定义数据库的驱动信息
    private static String DRIVER ;
    // 定义访问数据库的地址
    private static String URL ;

    private static DBUtils per = null;
    // 定义数据库的链接
    private Connection con = null;
    // 定义sql语句的执行对象
    private PreparedStatement pstmt = null;
    // 定义查询返回的结果集合
    private ResultSet resultSet = null;
    
    
	
    

    private DBUtils() {
    	try {
			// 获取配置文件中的输入流
			InputStream is = DBUtils.class.getClassLoader().getResourceAsStream("db.properties");
			// 根据键值对，解析文件中的字段
			// 加载输入流
			Properties properties = new Properties();
			properties.load(is);
			DRIVER = properties.getProperty("driver");// 获取驱动地址
			URL = properties.getProperty("url");
			USERNAME = properties.getProperty("username");
			PASSWORD = properties.getProperty("password");
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }

    /**
     * 单例模式，获得工具类的一个对象
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
     * 获得数据库的连接
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
     * 完成对数据库的表的添加删除和修改的操作
     * 
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public int executeUpdate(String sql, List<Object> params)
            throws SQLException {

    	getConnection();
        int result = -1;  // 表示当用户执行添加删除和修改的时候所影响数据库的行数
        
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
     * 从数据库中查询数据
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
     * jdbc的封装可以用反射机制来封装,把从数据库中获取的数据封装到一个类的对象里
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
            T resultObject = cls.newInstance();  // 通过反射机制创建实例
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
                
                
                field.setAccessible(true); // 打开javabean的访问private权限
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