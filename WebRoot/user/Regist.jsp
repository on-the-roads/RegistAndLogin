<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>注册页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <script type="text/javascript">
  function _change(){
  	var vcode=document.getElementById("vCode");
  	vcode.src="${pageContext.request.contextPath }/VerifyCodeServlet?xxx="+new Date().getTime();
  	
  }
  
  </script>
  <body>
  <h1 >注册</h1>
  <h2 style="color:red;font-weight:500">${msg }</h2>
  <form action="${pageContext.request.contextPath }/RegistServlet" method="post">
  用户名 <input type="text" name="username" value="${user.username }">&nbsp; ${error.username }<br/>  
  密&nbsp;&nbsp; &nbsp;码 <input type="text" name="password" value="${user.password }" >&nbsp; ${error.password }<br/>
  验证码 <input type="text" name="vcode" size="6" value="${user.vcode }">
  <img id="vCode" src="${pageContext.request.contextPath }/VerifyCodeServlet">
  <a href="javascript:_change()">看不清，换一张</a>&nbsp; ${error.vcode }
  <br/>
  <input type="submit" value="注册">
  </form>
  </body>
</html>
