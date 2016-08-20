package me.utlight.mybatis.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import me.utlight.entity.Classes2;
import me.utlight.util.MyBatisUtil;

//测试一对多关联查询
public class testMybatisAssociation02 {

	@Test
	public void testGetClass3(){
		SqlSession sqlsesion = MyBatisUtil.getSqlSession();
		
		String statement = "me.utlight.mapping.classMapper2.getClass3";
		Classes2 clazz = sqlsesion.selectOne(statement,1);
		sqlsesion.close();
		System.out.println("查询结果1+"+clazz);
	}
	
	@Test
	public void testGetClass4(){
		SqlSession sqlsesion = MyBatisUtil.getSqlSession();
		
		String statement = "me.utlight.mapping.classMapper2.getClass4";
		Classes2 clazz = sqlsesion.selectOne(statement,1);
		sqlsesion.close();
		System.out.println("查询结果2+"+clazz);
	}
}
