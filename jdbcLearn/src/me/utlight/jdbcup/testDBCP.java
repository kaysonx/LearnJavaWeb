package me.utlight.jdbcup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import me.utlight.utils.JdbcUtils_DBCP;

//注意要引用三个jar包 
public class testDBCP {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			conn = JdbcUtils_DBCP.getConnection();
			String sql = "insert into account(name,money) values(?,?)";
			//高版本的mysql驱动得要Statement.RETURN_GENERATED_KEYS 才能获得自动生成的主键
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
