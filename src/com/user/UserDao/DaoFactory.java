package com.user.UserDao;

import java.io.InputStream;
import java.util.Properties;


public class DaoFactory {
/**
 * 创建一个properties文件
 * 在文件中给出实现类的名称，通过反射完成创建对象。
 */
	private static UserDao userDao=null;
	private static Properties prop=null;
	static {
		try{
			InputStream in=DaoFactory.class.getClassLoader().getResourceAsStream("dao.properties");
			 prop=new Properties();
			prop.load(in);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public  static UserDao getUserDao(){
		try {
			Class clazz = Class.forName(prop.getProperty("com.user.UserDao.UserDao"));
			userDao = (UserDao) clazz.newInstance();
			return userDao;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
//		return null;
		
	}
	
}
