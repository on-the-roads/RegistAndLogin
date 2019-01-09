package com.user.UserDao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


import com.mysql.jdbc.Connection;

public class JdbcUtils {
	private static final String dbconfig ="dbconfig.properties";
	private static Properties props = new Properties();
	static{
		// 1.加载数据库的配置文件
		InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream(
				dbconfig );
		
//		Properties props = new Properties();
		try {
			props.load(in);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		// 2.加载驱动类
		try {
			Class.forName(props.getProperty("driverclass"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	public static Connection getConnection() throws IOException, SQLException,
			ClassNotFoundException {


		// 3.连接数据库
		Connection conn = (Connection) DriverManager.getConnection(
				props.getProperty("url"), 
				props.getProperty("username"), 
				props.getProperty("password"));

		return conn;
	}
}
