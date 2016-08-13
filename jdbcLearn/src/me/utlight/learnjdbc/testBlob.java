package me.utlight.learnjdbc;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import me.utlight.utils.JdbcUtils;

/**
 * @author liusha
 * 使用JDBC操作MySql的二进制数据
 */
public class testBlob {
	
	private Connection conn = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;

	
	
	/**
	 * 向mysql中插入二进制数据
	 */
	public void add() {
		
		try {
			conn = JdbcUtils.getConnection();
			String sql = "insert into testBlob(image) values(?)";
			pst = conn.prepareStatement(sql);
			//此方式获取路径 中间的空格会被  "%20"代替
			String path = testBlob.class.getClassLoader().getResource("dx.jpg").getPath();
			path = path.replace("%20", " ");
			
			File file = new File(path);
			FileInputStream fis = new FileInputStream(file);
			pst.setBinaryStream(1, fis,(int)file.length());
			
	
			int num = pst.executeUpdate();
			
			if (num > 0) {
				System.out.println("插入成功!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.release(conn, pst, rs);
		}
	}
	
	
	/**
	 * 从mysql中读取二进制内容
	 */
	public void read(){
		
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select image from testBlob where id=1";
			pst = conn.prepareStatement(sql);			
			rs = pst.executeQuery();

			if (rs.next()) {
	
				InputStream in = rs.getBinaryStream("image");
				int len = 0;
				byte buffer[] = new byte[1024];
				
				FileOutputStream out = new FileOutputStream("D:\\1.jpg");
				while ((len = in.read(buffer)) > 0) {
					out.write(buffer,0,len);
				}
				
				in.close();
				out.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.release(conn, pst, rs);
		}
	}
	
}
