package me.utlight.mybatis.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import me.utlight.entity.Classes;
import me.utlight.util.MyBatisUtil;

//����һ��һ������ѯ
public class testMybatisAssociation01 {

	@Test
	public void testGetClass(){
		SqlSession sqlsesion = MyBatisUtil.getSqlSession();
		
		String statement = "me.utlight.mapping.classMapper.getClass";
		Classes clazz = sqlsesion.selectOne(statement,1);
		sqlsesion.close();
		System.out.println("��ѯ���1+"+clazz);
	}
	
	@Test
	public void testGetClass2(){
		SqlSession sqlsesion = MyBatisUtil.getSqlSession();
		
		String statement = "me.utlight.mapping.classMapper.getClass2";
		Classes clazz = sqlsesion.selectOne(statement,1);
		sqlsesion.close();
		System.out.println("��ѯ���2+"+clazz);
	}
}
