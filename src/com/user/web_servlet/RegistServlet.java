package com.user.web_servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.UserBean.User;
import com.user.service.UserException;
import com.user.service.UserService;

/**
 * 封装表单数据，调用UserService 的regist（），若注册成功则输出“注册成功”，否则输出错误信息
 *
 */
public class RegistServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//请求编码
		response.setContentType("text/html;charset=UTF-8");//响应编码

		
		//1.依赖service层
		UserService userService=new UserService();
		
		//2.封装表单数据
		User user=new User();
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setVcode(request.getParameter("vcode"));
		
		/**
		 * 注册页面的表单校验
		 * 用map来封装错误源和错误信息,保存到request域中
		 */
		 
		Map<String, String> errmessa=new HashMap<String, String>();
		//校验用户名
		if(user.getUsername()==null||user.getUsername().trim().length()==0){
			errmessa.put("username", "用户名不能为空");
		}
		else if(user.getPassword().length()<3||user.getPassword().length()>15){
			errmessa.put("password", "密码在3~15位之间");
		}
		String sessionVcode=(String) request.getSession().getAttribute("vcode");
		 if (!user.getVcode().equals(sessionVcode)) {
			errmessa.put("vcode", "验证码错误");
		}
		 
		if(errmessa!=null&&errmessa.size()>0){
			request.setAttribute("error", errmessa);
			request.setAttribute("user", user);
			request.getRequestDispatcher("/user/Regist.jsp").forward(request, response);
			return;
		}
		
		/**
		 *	判断是否存在此用户
		 */
		try {
			userService.regist(user);
			response.getWriter().print("注册成功！<a href='"+request.getContextPath()+"/user/Login.jsp"+"'>点击此处登录</a>");
//			request.getRequestDispatcher("/user/login.jsp").forward(request, response);
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("user", user);
			request.getRequestDispatcher("/user/Regist.jsp").forward(request, response);
			return;
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
