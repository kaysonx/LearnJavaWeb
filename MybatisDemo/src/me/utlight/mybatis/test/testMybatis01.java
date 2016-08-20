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
		//mybatis的配置文件
		String resource = "conf.xml";
		//使用类加载器加载mybatis的配置文件（也加载关联的映射文件）
		InputStream is = testMybatis01.class.getClassLoader().getResourceAsStream(resource);
		//也可使用MyBatis提供的Resources类加载
		//Reader reader = Resources.getResourceAsReader(resource);
		
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = sessionFactory.openSession();
		//映射sql的标识字符串
		String statement = "me.utlight.mapping.userMapper.getUser";
		
		User user = session.selectOne(statement,1);
		System.out.println(user);
				
		
	}

}
