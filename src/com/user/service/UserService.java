package com.user.service;

import com.user.UserBean.User;
import com.user.UserDao.UserDao;

/**
 * 
 * User的业务逻辑层
 *  1.注册失败抛出自定义异常，带有错误信息并保存request域中；
	2.检查到已有用户存在，抛出错误信息“该用户已被注册”
	3. 上述无问题则添加用户
 *
 */
public class UserService {
	private UserDao dao=new UserDao();
	
	//注册功能
	public   void regist(User user)throws UserException{
		String username=user.getUsername();
		if(dao.UserFindByName(username)!=null){
			throw new UserException(username+" 已存在! 请重新注册");
		}
		dao.add(user);
	}
	
	//登录功能
	public User login(User form)throws UserException{
		String username=form.getUsername();
		User user=dao.UserFindByName(username);
		if(user==null){
			throw new UserException("该用户不存在");
		}
		if(!user.getPassword().equals(form.getPassword())){
			throw new UserException("用户名或者密码错误");
		}
		return user;
	}
	
	
}
