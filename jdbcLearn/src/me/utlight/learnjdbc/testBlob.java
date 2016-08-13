package me.utlight.learnjdbc;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import me.utlight.utils.JdbcUtils;

/**
 * @author liusha
 * ʹ��JDBC����MySql�Ķ���������
 */
public class testBlob {
	
	private Connection conn = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;

	
	
	/**
	 * ��mysql�в������������
	 */
	public void add() {
		
		try {
			conn = JdbcUtils.getConnection();
			String sql = "insert into testBlob(image) values(?)";
			pst = conn.prepareStatement(sql);
			//�˷�ʽ��ȡ·�� �м�Ŀո�ᱻ  "%20"����
			String path = testBlob.class.getClassLoader().getResource("dx.jpg").getPath();
			path = path.replace("%20", " ");
			
			File file = new File(path);
			FileInputStream fis = new FileInputStream(file);
			pst.setBinaryStream(1, fis,(int)file.length());
			
	
			int num = pst.executeUpdate();
			
			if (num > 0) {
				System.out.println("����ɹ�!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.release(conn, pst, rs);
		}
	}
	
	
	/**
	 * ��mysql�ж�ȡ����������
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
