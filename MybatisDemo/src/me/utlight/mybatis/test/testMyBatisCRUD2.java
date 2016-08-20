package me.utlight.mybatis.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import me.utlight.entity.User;
import me.utlight.mapping.UserMapperI;
import me.utlight.util.MyBatisUtil;

public class testMyBatisCRUD2 {
	
//	@Test
//	public void testAdd(){
//		
//		SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
//		//得到UserMapperI的实现对象
//		UserMapperI mapper = sqlSession.getMapper(UserMapperI.class);
//		User user = new User();
//		user.setName("cccc");
//		user.setAge(222);
//		
//		int add = mapper.add(user);
//		sqlSession.close();
//		System.out.println("增加结果+"+add);
//	}
	
//	@Test
//	public void testUpdate(){
//		
//		SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
//		//得到UserMapperI的实现对象
//		UserMapperI mapper = sqlSession.getMapper(UserMapperI.class);
//		User user = new User();
//		user.setId(4);
//		user.setName("updateccc");
//		user.setAge(22);
//		
//		int update = mapper.update(user);
//		sqlSession.close();
//		System.out.println("修改结果+"+update);
//	}
//	
//	@Test
//	public void testDelete(){
//		
//		SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
//		//得到UserMapperI的实现对象
//		UserMapperI mapper = sqlSession.getMapper(UserMapperI.class);
//	
//		int result = mapper.deleteById(5);
//		sqlSession.close();
//		System.out.println("删除结果+"+result);
//	}
//	
	@Test
	public void testGetAll(){
		
		SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
		//得到UserMapperI的实现对象
		UserMapperI mapper = sqlSession.getMapper(UserMapperI.class);
		List<User> users = mapper.getAll();
		
		sqlSession.close();
		System.out.println("查询结果+"+users);
	}
}
