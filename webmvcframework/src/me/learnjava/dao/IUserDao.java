package me.learnjava.dao;

import me.learnjava.domain.User;

public interface IUserDao {
	
	/**
	 * 根据用户名和密码来查找用户
	 * @param userName
	 * @param userPwd
	 * @return 查找到的用户
	 */
	User Find(String userName, String userPwd);
	
	/**
	 * 添加一个用户
	 * @param user
	 */
	void Add(User user);
	
	/**
	 * 根据用户名来查找用户
	 * @param username
	 * @return 查找到的用户
	 */
	User Find(String username);
}
