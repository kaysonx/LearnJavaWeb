package me.utlight.util;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	
	
	/**
	 * @return
	 * ��ȡSqlSessionFactory
	 */
	public static SqlSessionFactory getSqlSeesionFactory(){
		
		String resource = "conf.xml";
		InputStream is = MyBatisUtil.class.getClassLoader().getResourceAsStream(resource);
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		return factory;
	}
	
	
	/**
	 * @return
	 *  ��ȡSqlSession
	 */
	public static SqlSession getSqlSession(){
		return getSqlSeesionFactory().openSession();
	}
	
	
	/**
	 * @param isAutoCommit �Ƿ��Զ��ύ����
	 * @return
	 */
	public static SqlSession getSqlSession(boolean isAutoCommit){
		return getSqlSeesionFactory().openSession(isAutoCommit);
	}
	
}
