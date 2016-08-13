package me.learnjava.service.impl;

import me.learnjava.dao.IUserDao;
import me.learnjava.dao.impl.UserInfoDao;
import me.learnjava.domain.User;
import me.learnjava.exception.UserExistException;
import me.learnjava.service.IUserInfoService;

public class UserServiceImpl implements IUserInfoService {

	private IUserDao userDao = new UserInfoDao();
	@Override
	public void registUser(User user) throws UserExistException {
		
		if (userDao.Find(user.getUserName()) != null) {
			
			throw new UserExistException("您输入的用户名已存在!!!");
		}	
		
		userDao.Add(user);
	}

	@Override
	public User loginUser(String userName, String userPwd) {
		
		return userDao.Find(userName, userPwd);		
	}
	
	
}
