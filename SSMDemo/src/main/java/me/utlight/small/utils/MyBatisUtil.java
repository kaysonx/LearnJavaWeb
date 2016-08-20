package me.utlight.small.utils;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	
	/**
	 * 获取sqlsessionfactory
	 * @return
	 */
	public static SqlSessionFactory getSqlSessionFactory(){
		
		InputStream config = MyBatisUtil.class.getClassLoader().getResourceAsStream("MyBatisConfig.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(config);
		
		return factory;
	}
	
	/**
	 * 获取sqlsession
	 * @return
	 */
	public static SqlSession getSession(){
		return getSqlSessionFactory().openSession(true);
	}
	
	
	/**
	 * 获取sqlsession
	 * @param isAutoCommit 是否自动提交事务
	 * @return
	 */
	public static SqlSession getSession(boolean isAutoCommit){
		
		return getSqlSessionFactory().openSession(isAutoCommit);
	}
}
