package me.utlight.jdbcup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import me.utlight.utils.JdbcUtils_DBCP;

//ע��Ҫ��������jar�� 
public class testDBCP {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			conn = JdbcUtils_DBCP.getConnection();
			String sql = "insert into account(name,money) values(?,?)";
			//�߰汾��mysql������ҪStatement.RETURN_GENERATED_KEYS ���ܻ���Զ����ɵ�����
			pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, "woq");
			pst.setObject(2, 666);
			
			pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			
			if (rs.next()) {
				System.out.println(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JdbcUtils_DBCP.release(conn, pst, rs);
		}
	}
}
