package me.learnjava.service;

import me.learnjava.domain.User;
import me.learnjava.exception.UserExistException;

/**
 * @author liusha
 *
 */
public interface IUserInfoService {
	
	/**
	 * 注册功能
	 * @param user
	 * @throws UserExistException
	 */
	void registUser(User user) throws UserExistException;
	
	/**
	 * 登录功能
	 * @param userName
	 * @param userPwd
	 * @return
	 */
	User loginUser(String userName, String userPwd);
}
