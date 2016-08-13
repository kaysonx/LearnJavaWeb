package me.utlight.learnjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import me.utlight.utils.JdbcUtils;

/**
 * @author liusha
 * ���� JDBC����������ƣ�������ִ��Ч��
 */
public class testBatch {
	
	
	private Connection conn = null;
	private ResultSet rs = null;
	
	
	
	
	public void testJdbcBatchHandByStatement(){
		
	    Statement st = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql1 = "insert into testBatch(name) values('aaa')";
			String sql2 = "insert into testBatch(name) values('bbb')";
			String sql3 = "insert into testBatch(name) values('ccc')";
			String sql4 = "insert into testBatch(name) values('ddd')";
			String sql5 = "insert into testBatch(name) values('eee')";
			
			st = conn.createStatement();
			st.addBatch(sql5);
			st.addBatch(sql4);
			st.addBatch(sql3);
			st.addBatch(sql2);
			st.addBatch(sql1);
			//ִ��������SQL���
			st.executeBatch();
			//����ѱ����SQL
			st.clearBatch();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.release(conn, st, rs);
		}
	}
	
	public void testJdbcBatchHandByPreparedStatement(){
		
		PreparedStatement pst = null;
		long startTime = System.currentTimeMillis();
		try {
			conn = JdbcUtils.getConnection();
			String sql = "insert into testBatch(id,name) values(?,?)";
			pst = conn.prepareStatement(sql);
			
			for (int i = 1; i < 10000; i++) {
				pst.setInt(1, i);
				pst.setString(2, "test"+i);
				pst.addBatch();
				
				if (i % 1000 == 0) {
					pst.executeBatch();
					pst.clearBatch();
				}
			}
			pst.executeBatch();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.release(conn, pst, rs);
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("��������ʱ��Ϊ�� "+(endTime-startTime)/1000 + "�롣����");
	}
}
