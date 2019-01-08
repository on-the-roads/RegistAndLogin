package com.test;

import org.junit.Test;

import com.user.UserBean.User;
import com.user.UserDao.UserDao;

public class Daotest {
	@Test
	public void testadd()  {
		User user=new User();
		user.setPassword("4787584758");
		user.setUsername("Jlinss");
		 new UserDao().add(user);
	}
	
	@Test
	public void testfind(){
		User user=  new UserDao().UserFindByName("Jlinss");
		System.out.println(user.getUsername()+" : "+user.getPassword());
	}

}
