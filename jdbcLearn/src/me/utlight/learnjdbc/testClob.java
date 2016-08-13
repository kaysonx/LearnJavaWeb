package me.utlight.learnjdbc;

import java.io.*;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import me.utlight.utils.JdbcUtils;

/**
 * @author liusha
 * 使用JDBC操作MySql的大文本
 */
public class testClob {
	
	private Connection conn = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	private Reader reader = null;
	
	
	/**
	 * 向mysql中插入大文本数据
	 */
	public void add() {
		
		try {
			conn = JdbcUtils.getConnection();
			String sql = "insert into testClob(resume) values(?)";
			pst = conn.prepareStatement(sql);
			//此方式获取路径 中间的空格会被  "%20"代替
			String path = testClob.class.getClassLoader().getResource("text.txt").getPath();
			path = path.replace("%20", " ");
			
			File file = new File(path);
			reader = new FileReader(file);
			
			pst.setCharacterStream(1, reader,file.length());
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
	 * 从mysql中读取大文本内容
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
				//两种方式获得文本数据的内容
				content = rs.getString("resume");
				//文件较大时推荐这种方法
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
