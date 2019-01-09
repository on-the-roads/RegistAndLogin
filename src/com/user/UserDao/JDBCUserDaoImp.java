package com.user.UserDao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.user.UserBean.User;

public class JDBCUserDaoImp implements UserDao{
	
	private static Connection conn=null;
	public  User UserFindByName(String username){
		
		PreparedStatement pst = null;
		//发送查询指令
		String sql = "select * from t_user where username=?";
		try {
			//1.利用JdbcUtils工具获取数据库连接
		 conn=	JdbcUtils.getConnection();
		 //2.获得stateme
		pst = (PreparedStatement) conn.prepareStatement(sql);
		pst.setString(1, username);
		// 4.执行查询操作
		ResultSet set=pst.executeQuery();//executeUpdateִ�и��²���
		if(set==null)
			return null;
		
		if(set.next()){
			//结果转化为User对象，然后返回
			User user=new User();
			user.setUsername(set.getString("username"));
			user.setPassword(set.getString("password"));
			return user;
		}
		else
			return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		finally{
			try{
			if (pst!=null)
				pst.close();
			if(conn!=null)
				conn.close();
			}catch (SQLException e){
			throw new RuntimeException(e);
			}
	}
	}
	
	public  void  add(User user) {
		PreparedStatement pst = null;
		String sql = "insert into t_user (username,password) values(?,?)";
		try {
			//1.连接数据库
		 conn=	JdbcUtils.getConnection();
		pst = (PreparedStatement) conn.prepareStatement(sql);
		pst.setString(1, user.getUsername());
		pst.setString(2, user.getPassword());
		// 4.提交
		pst.executeUpdate();//executeUpdate提交指令
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		finally{
			//关闭
		}
			try{
			if (pst!=null)
				pst.close();
			if(conn!=null)
				conn.close();
			}catch (SQLException e){
			throw new RuntimeException(e);
			}
		}
		
	}

