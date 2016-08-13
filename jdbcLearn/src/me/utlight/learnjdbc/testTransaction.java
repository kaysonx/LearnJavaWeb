package me.utlight.learnjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;

import me.utlight.utils.JdbcUtils;

/**
 * @author liusha
 * ��������
 */
public class testTransaction {
	 
	private Connection conn = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	
	//ģ��ת�� �ɹ�
	public void testTransaction01(){
		
		try {
			conn = JdbcUtils.getConnection();
			conn.setAutoCommit(false); //��������
			
			String sql = "update account set money=money-100 where name='A'";
			pst = conn.prepareStatement(sql);
			pst.executeUpdate();
			
			String sql2 = "update account set money=money+100 where name='B'";
			pst = conn.prepareStatement(sql2);
			pst.executeUpdate();
			
			conn.commit();
			System.out.println("ת�˳ɹ�....");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.release(conn, pst, rs);
		}
	}
	
	//ģ��ת��ʧ�ܺ�  �Զ��ع�����
	public void testTransaction02(){
		
		try {
			conn = JdbcUtils.getConnection();
			conn.setAutoCommit(false); //��������
			
			String sql = "update account set money=money-100 where name='A'";
			pst = conn.prepareStatement(sql);
			pst.executeUpdate();
			
			int x = 1/0;//�����쳣 ģ��ִ��ʧ��
			
			String sql2 = "update account set money=money+100 where name='B'";
			pst = conn.prepareStatement(sql2);
			pst.executeUpdate();
			
			conn.commit();
			System.out.println("ת�˳ɹ�....");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.release(conn, pst, rs);
		
		}
	}
	
	//ģ��ת��ʧ�ܺ�  �ֶ�֪ͨ���ݿ�ع�����
	public void testTransaction03(){
		
		try {
			conn = JdbcUtils.getConnection();
			conn.setAutoCommit(false); //��������
			
			String sql = "update account set money=money-100 where name='A'";
			pst = conn.prepareStatement(sql);
			pst.executeUpdate();
			
			int x = 1/0;//�����쳣 ģ��ִ��ʧ��
			
			String sql2 = "update account set money=money+100 where name='B'";
			pst = conn.prepareStatement(sql2);
			pst.executeUpdate();
			
			conn.commit();
			System.out.println("ת�˳ɹ�....");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			JdbcUtils.release(conn, pst, rs);
		
		}
	}
	
	//ģ��ת��ʧ�ܺ�  ���ûع��� �ֶ��ع���
	public void testTransaction04(){
			
			Savepoint sp = null;
			try {
				conn = JdbcUtils.getConnection();
				conn.setAutoCommit(false); //��������
				
				String sql = "update account set money=money-100 where name='A'";
				pst = conn.prepareStatement(sql);
				pst.executeUpdate();
				
				sp = conn.setSavepoint();//��������ع���

				String sql1 = "update account set money=money+100 where name='C'";
				pst = conn.prepareStatement(sql);
				pst.executeUpdate();
				
				int x = 1/0;//�����쳣 ģ��ִ��ʧ��
				
				String sql2 = "update account set money=money+100 where name='B'";
				pst = conn.prepareStatement(sql2);
				pst.executeUpdate();
				
				conn.commit();
				System.out.println("ת�˳ɹ�....");
			} catch (Exception e) {
				try {
					conn.rollback(sp);
					conn.commit();//һ�����������ύ����
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				e.printStackTrace();
			} finally {
				JdbcUtils.release(conn, pst, rs);
			
			}
		}
		
	public void findAll() {
			try {
				conn = JdbcUtils.getConnection();
				String sql = "select * from account";
				pst = conn.prepareStatement(sql);
				
				rs = pst.executeQuery(sql);
				
				while (rs.next()) {
					System.out.print("id="+rs.getObject("id") + " ");
					System.out.print("name="+rs.getObject("name") + " ");
					System.out.print("money="+rs.getObject("money") + " ");
					System.out.println("----------------------------");
				}
						
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				JdbcUtils.release(conn, pst, rs);
			}
		}
}
