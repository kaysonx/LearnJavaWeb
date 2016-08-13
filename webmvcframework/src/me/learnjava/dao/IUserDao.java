package me.learnjava.dao;

import me.learnjava.domain.User;

public interface IUserDao {
	
	/**
	 * �����û����������������û�
	 * @param userName
	 * @param userPwd
	 * @return ���ҵ����û�
	 */
	User Find(String userName, String userPwd);
	
	/**
	 * ���һ���û�
	 * @param user
	 */
	void Add(User user);
	
	/**
	 * �����û����������û�
	 * @param username
	 * @return ���ҵ����û�
	 */
	User Find(String username);
}
