package me.utlight.mybatis.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import me.utlight.entity.Order;
import me.utlight.util.MyBatisUtil;

public class testMyBatisCRUD3 {
	
	@Test
	public void testGetOrderById(){
		SqlSession sqlsession = MyBatisUtil.getSqlSession();
		String statement = "me.utlight.mapping.orderMapper.getOrderById";
		
		Order order = sqlsession.selectOne(statement, 1);
		sqlsession.close();
		System.out.println("查询结果1：+"+order);
	}
	
	@Test
	public void testGetOrderById2(){
		SqlSession sqlsession = MyBatisUtil.getSqlSession();
		String statement = "me.utlight.mapping.orderMapper.selectOrder";
		
		Order order = sqlsession.selectOne(statement, 1);
		sqlsession.close();
		System.out.println("查询结果2：+"+order);
	}
	
	@Test
	public void testGetOrderById3(){
		SqlSession sqlsession = MyBatisUtil.getSqlSession();
		String statement = "me.utlight.mapping.orderMapper.selectOrderResultMap";
		
		Order order = sqlsession.selectOne(statement, 1);
		sqlsession.close();
		System.out.println("查询结果3：+"+order);
	}
}
