package me.utlight.learnjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;

import me.utlight.utils.JdbcUtils;

/**
 * @author liusha
 * 测试事务
 */
public class testTransaction {
	 
	private Connection conn = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	
	//模拟转帐 成功
	public void testTransaction01(){
		
		try {
			conn = JdbcUtils.getConnection();
			conn.setAutoCommit(false); //开启事务
			
			String sql = "update account set money=money-100 where name='A'";
			pst = conn.prepareStatement(sql);
			pst.executeUpdate();
			
			String sql2 = "update account set money=money+100 where name='B'";
			pst = conn.prepareStatement(sql2);
			pst.executeUpdate();
			
			conn.commit();
			System.out.println("转账成功....");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.release(conn, pst, rs);
		}
	}
	
	//模拟转账失败后  自动回滚事务
	public void testTransaction02(){
		
		try {
			conn = JdbcUtils.getConnection();
			conn.setAutoCommit(false); //开启事务
			
			String sql = "update account set money=money-100 where name='A'";
			pst = conn.prepareStatement(sql);
			pst.executeUpdate();
			
			int x = 1/0;//制造异常 模拟执行失败
			
			String sql2 = "update account set money=money+100 where name='B'";
			pst = conn.prepareStatement(sql2);
			pst.executeUpdate();
			
			conn.commit();
			System.out.println("转账成功....");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.release(conn, pst, rs);
		
		}
	}
	
	//模拟转账失败后  手动通知数据库回滚事务
	public void testTransaction03(){
		
		try {
			conn = JdbcUtils.getConnection();
			conn.setAutoCommit(false); //开启事务
			
			String sql = "update account set money=money-100 where name='A'";
			pst = conn.prepareStatement(sql);
			pst.executeUpdate();
			
			int x = 1/0;//制造异常 模拟执行失败
			
			String sql2 = "update account set money=money+100 where name='B'";
			pst = conn.prepareStatement(sql2);
			pst.executeUpdate();
			
			conn.commit();
			System.out.println("转账成功....");
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
	
	//模拟转账失败后  设置回滚点 手动回滚。
	public void testTransaction04(){
			
			Savepoint sp = null;
			try {
				conn = JdbcUtils.getConnection();
				conn.setAutoCommit(false); //开启事务
				
				String sql = "update account set money=money-100 where name='A'";
				pst = conn.prepareStatement(sql);
				pst.executeUpdate();
				
				sp = conn.setSavepoint();//设置事务回滚点

				String sql1 = "update account set money=money+100 where name='C'";
				pst = conn.prepareStatement(sql);
				pst.executeUpdate();
				
				int x = 1/0;//制造异常 模拟执行失败
				
				String sql2 = "update account set money=money+100 where name='B'";
				pst = conn.prepareStatement(sql2);
				pst.executeUpdate();
				
				conn.commit();
				System.out.println("转账成功....");
			} catch (Exception e) {
				try {
					conn.rollback(sp);
					conn.commit();//一定不能忘记提交事务
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
