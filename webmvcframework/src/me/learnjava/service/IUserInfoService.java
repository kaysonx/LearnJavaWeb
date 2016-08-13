package me.learnjava.service;

import me.learnjava.domain.User;
import me.learnjava.exception.UserExistException;

/**
 * @author liusha
 *
 */
public interface IUserInfoService {
	
	/**
	 * ע�Ṧ��
	 * @param user
	 * @throws UserExistException
	 */
	void registUser(User user) throws UserExistException;
	
	/**
	 * ��¼����
	 * @param userName
	 * @param userPwd
	 * @return
	 */
	User loginUser(String userName, String userPwd);
}
