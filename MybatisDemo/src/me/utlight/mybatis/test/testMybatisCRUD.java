package me.utlight.mybatis.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import me.utlight.entity.User;
import me.utlight.util.MyBatisUtil;

public class testMybatisCRUD {
	
//	@Test
//	public void testAdd(){
//		
//		SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
//		
//		String statement = "me.utlight.mapping.userMapper.addUser";
//		User user = new User();
//		user.setName("add");
//		user.setAge(21);
//		
//		int result = sqlSession.insert(statement,user);
//		sqlSession.close();
//		System.out.println("添加用户+"+result);
//	}
//	
//	@Test
//	public void testUpdate(){
//		
//		SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
//		
//		String statement = "me.utlight.mapping.userMapper.updateUser";
//		User user = new User();
//		user.setId(3);
//		user.setName("changed  name");
//		user.setAge(1);
//		
//		int result = sqlSession.update(statement,user);
//		sqlSession.close();
//		System.out.println("修改用户+"+result);
//	}
//	
//	@Test
//	public void testDelete(){
//		
//		SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
//		
//		String statement = "me.utlight.mapping.userMapper.deleteUser";
//		
//		int result = sqlSession.delete(statement,3);
//		sqlSession.close();
//		System.out.println("删除用户+"+result);
//	}
//	
	@Test
	public void testGetAll(){
		
		SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
		
		String statement = "me.utlight.mapping.userMapper.getAllUsers";
		
		List<User> lstUsers = sqlSession.selectList(statement);
		sqlSession.close();
		System.out.println("所有用户+"+lstUsers);
	}
}
