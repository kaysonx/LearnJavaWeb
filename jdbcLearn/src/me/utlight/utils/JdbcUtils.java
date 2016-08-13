package me.utlight.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class JdbcUtils {
	
	private static String driver = null;
	private static String url = null;
	private static String username = null;
	private static String password = null;
	
	static{
		try {
			//表示从当前类的同级目录下加载配置文件
			InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
			Properties prop = new Properties();
			prop.load(in);
			
			driver = prop.getProperty("driver");
			url = prop.getProperty("url");
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			
			//加载数据库驱动
			Class.forName(driver);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	}
	
	//获取数据库连接
	public static java.sql.Connection getConnection() throws SQLException {
		
		 return DriverManager.getConnection(url,username,password);
	}
	
	
	/**
	 * release
	 * @param conn 数据库连接对象
	 * @param st   执行SQL的对象
	 * @param rs   查询结果对象
	 */
	public static void release(java.sql.Connection conn, Statement st, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			rs = null;
		}
		
		if (st != null) {
			try {
				st.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			st = null;
		}
		
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}
	
	/**
	 * update
	 * @param sql
	 * @param params
	 * @throws SQLException
	 */
	public static void update(String sql, Object[] params) throws SQLException{
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pst = conn.prepareStatement(sql);
			
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					pst.setObject(i+1, params[i]);
				}
			}
		
			
			pst.executeUpdate();
		} finally{
			release(conn, pst, rs);
		}
	}
	
	
	
	
	
	
	public static Object query(String sql, Object[] params, ResultSetHander rsh) throws SQLException{
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pst = conn.prepareStatement(sql);
			
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					pst.setObject(i+1, params[i]);
				}
			}
			
			rs = pst.executeQuery();
			//相当于传一个委托  作为处理结果的方法
			return rsh.handler(rs);
			
		} finally {
			release(conn, pst, rs);
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
