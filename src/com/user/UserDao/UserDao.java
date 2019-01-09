package com.user.UserDao;

import com.user.UserBean.User;

public interface UserDao {
	/**
	 * 数据处理接口
	 * 功能：
	 *  1.查询
		2.添加
	 */
	public  User UserFindByName(String username);
	public  void add(User user);		
}
