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
 *	UserServlet层
 *
 */
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");//请求编码
			response.setContentType("text/html;charset=utf-8");//响应编码
			
			//1.依赖service层
			UserService userService=new UserService();
			//2.封装表单数据
			User user=new User();
			user.setUsername(request.getParameter("username"));
			user.setPassword(request.getParameter("password"));
			user.setVcode(request.getParameter("vcode"));
			
			
			/**
			 * 登录页面的表单校验
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
			 
			if(errmessa!=null&&errmessa.size()>0){
				request.setAttribute("error", errmessa);
				request.setAttribute("user", user);
				request.getRequestDispatcher("/user/Login.jsp").forward(request, response);
				return;
			}
			
			try {
				userService.login(user);
				request.getSession().setAttribute("CurrentUser", user);
//				request.getRequestDispatcher("/user/Main.jsp").forward(request, response);
				response.sendRedirect(request.getContextPath()+"/user/Main.jsp");
			} catch (UserException e) {
				request.setAttribute("msg", e.getMessage());
				request.setAttribute("user", user);
				request.getRequestDispatcher("/user/Login.jsp").forward(request, response);
				return;
			}
			
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
