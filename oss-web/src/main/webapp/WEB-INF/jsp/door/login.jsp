<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<title>欢迎登录个人理财管理系统</title>
	<link rel="stylesheet" href="./css/commons.css" type="text/css"></link>
	<script type="text/javascript" src="./js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="./js/commons.js"></script>
</head>
<body>
<!-- 用来灰化主窗口的层 -->
<div id="full_div" >
</div>
<center>
  <div id="super" style="background-color: #FFFFFF;">
    <div id="top">
      	<p>欢迎财务管理管理系统！</p>
    </div>
    <div id="loger">
    	<form id="login_form" action="ossUser.do" method="post">
    		<input type="hidden" name="method" value="login">
    		<span style="color: red;">&nbsp;${message}</span>
	        <table>
	          <tr>
	            <td>
	            	<input type="text" name="userName" value="userName" onfocus="setNull(this)" onblur="showBack(this)"
	            		style="border: 1px #004E98 solid; width: 205px; height: 26px; font-size: 16px; color: gray;" />
	            </td>
	          </tr>
	          <tr>
	            <td>
	            	<input name="passWord" type="password" value="******" onfocus="setNull(this)" onblur="showBack(this)"
	            	     style="border: 1px #004E98 solid; width: 205px; height: 26px; font-size: 16px; color: gray;" />
	            </td>
	          </tr>
	          <!-- 补充验证码 -->
	          <tr>
	            <td>
	            	<input type="checkbox" id="save_login" name="saveFlag" value="1">
	              	<font style="color: #004E98;">记住账号，下次自动登录</font>
	            </td>
	          </tr>
	          <tr>
	            <td>
	           		<img src = "./images/btn_login.gif" onclick = "login()" style = "cursor: pointer;">
	            	<img src = "./images/btn_reg.gif"  onclick = "signIn()" style = "cursor: pointer;">
	            </td>
	          </tr>
	        </table>
    	</form>
    </div>
  </div>
</center>
<div id="register">
	<jsp:include page="./register.jsp" />
</div>
</body>
</html>
