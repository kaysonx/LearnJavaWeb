package me.utlight.learnjdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import me.utlight.utils.JdbcUtils;

public class crudByStatement {
	
	private Connection conn = null;
	private Statement st = null;
	private ResultSet rs = null;

	/*
	 * ����ʱ�׳��쳣��java.lang.NoClassDefFoundError: Could not initialize class me.utlighy.utils.JdbcUtils
		�����������mysql�������ŵ�tomcat�µ�lib��
	 * */
	
	public void insert(){		
		try {
			conn = JdbcUtils.getConnection();
			st = conn.createStatement();
			
			String sql = "insert into Users(id,name,password,email,birthday) values(1,'test','123','123@112.com','1995-8-22')";
			
			int num = st.executeUpdate(sql);
			
			if (num > 0) {
				System.out.println("����ɹ�����");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.release(conn, st, rs);
		}
				
	}
	
	
	public void delete(){
		
		try {
			conn = JdbcUtils.getConnection();
			String sql = "delete from Users where id = 1";
			st = conn.createStatement();
			
			int num = st.executeUpdate(sql);
			
			if (num > 0) {
				System.out.println("ɾ���ɹ�����");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.release(conn, st, rs);
		}
	}
	
	
	public void update(){
		try {
			conn = JdbcUtils.getConnection();
			String sql = "update Users set name='wocale',email='db@doubi.com' where id = 1";
			st = conn.createStatement();
			
			int num = st.executeUpdate(sql);
			
			if (num > 0) {
				System.out.println("���³ɹ�����");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.release(conn, st, rs);
		}
	}
	
	
	
	public void findAll(){
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select * from Users";
			st = conn.createStatement();
			
			rs = st.executeQuery(sql);
			
			while (rs.next()) {
				System.out.println("id="+rs.getObject("id"));
				System.out.println("name="+rs.getObject("name"));
				System.out.println("password="+rs.getObject("password"));
				System.out.println("email="+rs.getObject("email"));
				System.out.println("birthday="+rs.getObject("birthday"));
				System.out.println("----------------------------");
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.release(conn, st, rs);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
