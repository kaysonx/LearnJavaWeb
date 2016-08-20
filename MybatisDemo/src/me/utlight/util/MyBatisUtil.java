package me.utlight.util;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	
	
	/**
	 * @return
	 * 获取SqlSessionFactory
	 */
	public static SqlSessionFactory getSqlSeesionFactory(){
		
		String resource = "conf.xml";
		InputStream is = MyBatisUtil.class.getClassLoader().getResourceAsStream(resource);
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		return factory;
	}
	
	
	/**
	 * @return
	 *  获取SqlSession
	 */
	public static SqlSession getSqlSession(){
		return getSqlSeesionFactory().openSession();
	}
	
	
	/**
	 * @param isAutoCommit 是否自动提交事务
	 * @return
	 */
	public static SqlSession getSqlSession(boolean isAutoCommit){
		return getSqlSeesionFactory().openSession(isAutoCommit);
	}
	
}
