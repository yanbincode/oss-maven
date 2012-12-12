<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>欢迎注册</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" href="./css/commons.css" type="text/css"></link>
	<script type="text/javascript" src="./js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="./js/commons.js"></script>
	<script type="text/javascript" src="./js/ajax_base.js"></script>
	
  </head>
  <body>
    <center>
    	<div align="right">
    		<img src="./images/close.bmp" style="cursor: pointer;" onclick="closeSign()"></img>
    	</div>
    	<span id="message" style="color: red;"></span>
    	<div style="background-color: #FFFFFF;">
    		<fieldset style = "width:500px">
    			<legend style="color: blue; font-weight: bold;">新用户注册</legend>
    			<form name = "register_Form" id = "register_Form" action = "/finance/memberInfoController?method=register" method = "post">
    				<table class="form_table">
    					<tr>
    						<td align="right"><span>*</span>用户名：</td>
    						<td>
    							<input type="text" id="userName" name="userName" value="" class="register_box"/>
    							<span id="userNameError"></span>
    							<div style="font-size: 12px; color: red;">填写4－16位大小写英文字母、数字及下划线</div>
    						</td>
    					</tr>
    					<tr>
    						<td align="right"><span>*</span>密码：</td>
    						<td>
    							<input type="password" id="passWord" name="passWord" value="" class="register_box"/>
    							<span id="passWordError"></span>
    							<div style="font-size: 12px; color: red;">填写6－20位任意字符</div>
    						</td>
    					</tr>
    					<tr>
    						<td align="right"><span>*</span>确认密码：</td>
    						<td>
    							<input type="password" id="passWords" name="passWords" value="" class="register_box" />
    							<span id="passWordsError"></span> 
    							<div style="font-size: 12px; color: red;">填写与密码一致的6－20位任意字符</div>
    						</td>
    					</tr>
    					<tr>
    						<td align="right"><span>*</span>姓名：</td>
    						<td>
    							<input type="text" id="name" name="name" value="" class="register_box"/>
    							<span id="nameError"></span>  
    							<div style="font-size: 12px; color: red;">填写中文名字</div>
    						</td>
    					</tr>
    					<tr>
    						<td align="right"><span>*</span>年龄：</td>
    						<td>
    							<input type="text" id="age" name="age" value="" class="register_box"/>
    							<span id="ageError"></span>  
    							<div style="font-size: 12px; color: red;">填写数字</div>
    						</td>
    					</tr>
    					<tr>
    						<td align="right"><span>*</span>性别：</td>
    						<td>
    							<input type="radio" id="male" name="gender" value="0" checked="checked"/>男
    							<input type="radio" id="female" name="gender" value="1" />女 
    						</td>
    					</tr>
    					<tr>
    						<td align="right"><span>*</span>电话：</td>
    						<td>
    							<input type="text" id="phone" name="phone" value="" class="register_box">
    							<span id="phoneError"></span> 
    							<div style="font-size: 12px; color: red;">填写11为数字手机号</div>
    						</td>
    					</tr>
    					<tr>
    						<td align="right"><span>*</span>身份证：</td>
    						<td>
    							<input type="text" id="idCard" name="idCard" value="" class="register_box">
    							<span id="idCardError"></span> 
    							<div style="font-size: 12px; color: red;">填写标准身份证格式</div>
    						</td>
    					</tr>
    				</table>
    				<br/>
    				<div align="center">
    					<input type="button" class = "button" name="sure" id="sure" value=" 提 交 " onclick="register()">
    					<input type="reset" class = "button" name="reset" id="reset" value=" 重 置 ">
    				</div>
    			</form>
    		</fieldset>
    	</div>
    </center>
  </body>
</html>
