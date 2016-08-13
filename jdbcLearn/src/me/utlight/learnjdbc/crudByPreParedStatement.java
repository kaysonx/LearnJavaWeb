package me.utlight.learnjdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import me.utlight.utils.JdbcUtils;

public class crudByPreParedStatement {
	
	private Connection conn = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	
	public void insert() {
		
		try {
			conn = JdbcUtils.getConnection();
			//通过占位符的方式 拼接SQL语句 可以防止sql注入  简化sql语句的书写
			String sql = "insert into Users(id,name,password,email,birthday) values(?,?,?,?,?)";
			
			pst = conn.prepareStatement(sql);
			//注意 索引从1开始
			pst.setInt(1, 2);
			pst.setString(2, "wocele");
			pst.setString(3, "666666");
			pst.setString(4, "joker_e@163.com");
			//一个是java里的date  一个是sql里的date  注意区分
			pst.setDate(5, new Date(new java.util.Date().getTime()));
			
			int num = pst.executeUpdate();
			if (num > 0) {
				System.out.println("插入成功....");
			}
			
		} catch (Exception e) {
			 e.printStackTrace();
		} finally {
			JdbcUtils.release(conn, pst, rs);
		}
	}
	
	
	public void delete() {
		
		try {
			conn = JdbcUtils.getConnection();
			//通过占位符的方式 拼接SQL语句 可以防止sql注入  简化sql语句的书写
			String sql = "delete from Users where id=?";
			
			pst = conn.prepareStatement(sql);
			//注意 索引从1开始
			pst.setInt(1, 2);
			
			
			int num = pst.executeUpdate();
			if (num > 0) {
				System.out.println("删除成功....");
			}
			
		} catch (Exception e) {
			 e.printStackTrace();
		} finally {
			JdbcUtils.release(conn, pst, rs);
		}
	}
	
	public void update() {
		
		try {
			conn = JdbcUtils.getConnection();
			//通过占位符的方式 拼接SQL语句 可以防止sql注入  简化sql语句的书写
			String sql = "update Users set name=?,email=? where id=?";
			
			pst = conn.prepareStatement(sql);
			//注意 索引从1开始
			pst.setString(1, "test");
			pst.setString(2, "666@666.com");
			pst.setInt(3, 2);
			
			
			int num = pst.executeUpdate();
			if (num > 0) {
				System.out.println("更新成功....");
			}
			
		} catch (Exception e) {
			 e.printStackTrace();
		} finally {
			JdbcUtils.release(conn, pst, rs);
		}
	}
	
	public void findAll() {
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select * from Users";
			pst = conn.prepareStatement(sql);
			
			rs = pst.executeQuery(sql);
			
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
			JdbcUtils.release(conn, pst, rs);
		}
	}
	
}
