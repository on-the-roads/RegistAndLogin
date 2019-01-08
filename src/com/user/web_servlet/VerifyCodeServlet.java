package com.user.web_servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.vcode.utils.VerifyCode;

public class VerifyCodeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//1.创建验证码类
			VerifyCode verifyCode=new VerifyCode();
		//2.得到验证码图片
			BufferedImage image=verifyCode.getImage();
		//3.将图片响应给客户端
			VerifyCode.output(image, response.getOutputStream());
		//4.将图片上的文本保存到session域
			request.getSession().setAttribute("vcode", verifyCode.getText());
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
