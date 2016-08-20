package me.utlight.mybatis.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import me.utlight.util.MyBatisUtil;

//测试存储过程
public class testProcedure {

	@Test
	public void testGetUserCount(){
		
		SqlSession sqlsession = MyBatisUtil.getSqlSession();
		String statement = "me.utlight.mapping.userMapper2.getUserCount";
		
		//存储过程参数
		Map<String, Integer> parameterMap = new HashMap<String, Integer>();
		parameterMap.put("sexid", 1);
		parameterMap.put("usercount", -1);
		
		sqlsession.selectOne(statement, parameterMap);
		
		Integer result = parameterMap.get("usercount");
		sqlsession.close();
		
		System.out.println("用户数+"+result);
	}
}
