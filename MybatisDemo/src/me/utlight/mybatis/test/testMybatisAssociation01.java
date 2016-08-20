package me.utlight.mybatis.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import me.utlight.entity.Classes;
import me.utlight.util.MyBatisUtil;

//测试一对一关联查询
public class testMybatisAssociation01 {

	@Test
	public void testGetClass(){
		SqlSession sqlsesion = MyBatisUtil.getSqlSession();
		
		String statement = "me.utlight.mapping.classMapper.getClass";
		Classes clazz = sqlsesion.selectOne(statement,1);
		sqlsesion.close();
		System.out.println("查询结果1+"+clazz);
	}
	
	@Test
	public void testGetClass2(){
		SqlSession sqlsesion = MyBatisUtil.getSqlSession();
		
		String statement = "me.utlight.mapping.classMapper.getClass2";
		Classes clazz = sqlsesion.selectOne(statement,1);
		sqlsesion.close();
		System.out.println("查询结果2+"+clazz);
	}
}
