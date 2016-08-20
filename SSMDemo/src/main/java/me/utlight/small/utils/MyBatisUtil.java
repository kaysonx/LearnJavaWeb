package me.utlight.small.utils;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	
	/**
	 * ��ȡsqlsessionfactory
	 * @return
	 */
	public static SqlSessionFactory getSqlSessionFactory(){
		
		InputStream config = MyBatisUtil.class.getClassLoader().getResourceAsStream("MyBatisConfig.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(config);
		
		return factory;
	}
	
	/**
	 * ��ȡsqlsession
	 * @return
	 */
	public static SqlSession getSession(){
		return getSqlSessionFactory().openSession(true);
	}
	
	
	/**
	 * ��ȡsqlsession
	 * @param isAutoCommit �Ƿ��Զ��ύ����
	 * @return
	 */
	public static SqlSession getSession(boolean isAutoCommit){
		
		return getSqlSessionFactory().openSession(isAutoCommit);
	}
}
