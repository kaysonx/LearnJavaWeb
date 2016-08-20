package me.utlight.mybatis.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import me.utlight.entity.User;

public class testMybatis01 {

	@Test
	public void test() throws IOException {
		//mybatis�������ļ�
		String resource = "conf.xml";
		//ʹ�������������mybatis�������ļ���Ҳ���ع�����ӳ���ļ���
		InputStream is = testMybatis01.class.getClassLoader().getResourceAsStream(resource);
		//Ҳ��ʹ��MyBatis�ṩ��Resources�����
		//Reader reader = Resources.getResourceAsReader(resource);
		
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = sessionFactory.openSession();
		//ӳ��sql�ı�ʶ�ַ���
		String statement = "me.utlight.mapping.userMapper.getUser";
		
		User user = session.selectOne(statement,1);
		System.out.println(user);
				
		
	}

}
