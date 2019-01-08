<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <h1 >登录</h1>
  <h2 style="color:red;font-weight:500">${msg }</h2>
  <form action="${pageContext.request.contextPath }/LoginServlet" method="post">
  用户名 <input type="text" name="username" value="${user.username }">&nbsp; ${error.username }<br/>  
  密&nbsp;&nbsp; &nbsp;码 <input type="password" name="password" value="${user.password }" >&nbsp; ${error.password }<br/>
  <input type="submit" value="登录"><br/>
  </form>
  <a href="${pageContext.request.contextPath }/user/Regist.jsp">点我注册</a>
  </body>
</html>
