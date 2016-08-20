package me.utlight.test;

import org.junit.Test;

import junit.framework.Assert;
import me.utlight.entity.Users;
import me.utlight.service.UsersDAO;
import me.utlight.serviceimpl.UsersDAOImpl;

public class testUsersImpl {

	@Test
	public void testUsersLogin(){
		Users u = new Users(1,"test","test");
		UsersDAO udao = new UsersDAOImpl();
		Assert.assertEquals(true, udao.usersLogin(u));
	}
}
