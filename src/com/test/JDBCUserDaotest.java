package com.test;

import org.junit.Test;

import com.user.UserBean.User;
import com.user.UserDao.JDBCUserDaoImp;

public class JDBCUserDaotest {

	
	@Test
	public void testadd() {
		User user =new User();
		user.setPassword("12345");
		user.setUsername("Newton");
		new JDBCUserDaoImp().add(user);
	}
	
	@Test
	public void testfind() {
		User user=	new JDBCUserDaoImp().UserFindByName("Newton");
		System.out.println(user.getUsername()+":"+user.getPassword());
	}
}
