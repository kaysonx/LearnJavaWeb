package me.utlight.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



/**
 * @author liusha
 * 通过配置tomcat数据源来实现连接池
 */
public class JdbcUtils_JNDI {

	private static DataSource ds = null;
	
	static{
		try {
			//初始化JDNI
			Context initCtx = new InitialContext();
			//得到JDBI容器
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			
			ds = (DataSource)envCtx.lookup("jdbc/datasource");
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static Connection getConnection() throws SQLException{
		 return ds.getConnection();
	}
	
	public static void release(Connection conn, Statement st, ResultSet rs){
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
		}
		
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
