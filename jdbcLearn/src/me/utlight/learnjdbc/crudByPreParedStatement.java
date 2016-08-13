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
			//ͨ��ռλ���ķ�ʽ ƴ��SQL��� ���Է�ֹsqlע��  ��sql������д
			String sql = "insert into Users(id,name,password,email,birthday) values(?,?,?,?,?)";
			
			pst = conn.prepareStatement(sql);
			//ע�� ������1��ʼ
			pst.setInt(1, 2);
			pst.setString(2, "wocele");
			pst.setString(3, "666666");
			pst.setString(4, "joker_e@163.com");
			//һ����java���date  һ����sql���date  ע������
			pst.setDate(5, new Date(new java.util.Date().getTime()));
			
			int num = pst.executeUpdate();
			if (num > 0) {
				System.out.println("����ɹ�....");
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
			//ͨ��ռλ���ķ�ʽ ƴ��SQL��� ���Է�ֹsqlע��  ��sql������д
			String sql = "delete from Users where id=?";
			
			pst = conn.prepareStatement(sql);
			//ע�� ������1��ʼ
			pst.setInt(1, 2);
			
			
			int num = pst.executeUpdate();
			if (num > 0) {
				System.out.println("ɾ���ɹ�....");
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
			//ͨ��ռλ���ķ�ʽ ƴ��SQL��� ���Է�ֹsqlע��  ��sql������д
			String sql = "update Users set name=?,email=? where id=?";
			
			pst = conn.prepareStatement(sql);
			//ע�� ������1��ʼ
			pst.setString(1, "test");
			pst.setString(2, "666@666.com");
			pst.setInt(3, 2);
			
			
			int num = pst.executeUpdate();
			if (num > 0) {
				System.out.println("���³ɹ�....");
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
