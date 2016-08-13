package me.utlight.learnjdbc;

import java.io.*;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import me.utlight.utils.JdbcUtils;

/**
 * @author liusha
 * ʹ��JDBC����MySql�Ĵ��ı�
 */
public class testClob {
	
	private Connection conn = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	private Reader reader = null;
	
	
	/**
	 * ��mysql�в�����ı�����
	 */
	public void add() {
		
		try {
			conn = JdbcUtils.getConnection();
			String sql = "insert into testClob(resume) values(?)";
			pst = conn.prepareStatement(sql);
			//�˷�ʽ��ȡ·�� �м�Ŀո�ᱻ  "%20"����
			String path = testClob.class.getClassLoader().getResource("text.txt").getPath();
			path = path.replace("%20", " ");
			
			File file = new File(path);
			reader = new FileReader(file);
			
			pst.setCharacterStream(1, reader,file.length());
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
	 * ��mysql�ж�ȡ���ı�����
	 */
	public void read(){
		
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select resume from testClob where id=1";
			pst = conn.prepareStatement(sql);
			
			rs = pst.executeQuery();
			
			String contentStr = "";
			String content = "";
			
			if (rs.next()) {
				//���ַ�ʽ����ı����ݵ�����
				content = rs.getString("resume");
				//�ļ��ϴ�ʱ�Ƽ����ַ���
				reader = rs.getCharacterStream("resume");
				char buffer[] = new char[1024];
				int len = 0;
				FileWriter out = new FileWriter("D:\\1.txt");
				while ((len = reader.read(buffer)) > 0) {
					
					contentStr += new String(buffer);
					out.write(buffer,0,len);
				}
				
				out.close();
				reader.close();
			}
			
			System.out.println(content);
			System.out.println("-------------------------------");
			System.out.println(contentStr);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.release(conn, pst, rs);
		}
	}
	
}
