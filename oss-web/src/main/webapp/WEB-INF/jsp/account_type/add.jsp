<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>统计类型添加</title>
	<link rel="stylesheet" href="./css/commons.css" type="text/css"></link>
  </head>
  <body>
    <center>
  	  	<div id="form">
		  	<form:form action="accountType.do" modelAttribute="accountType" method="post">
		  		<input type="hidden" name="method" value="add"/>
		  		<input type="hidden" name="backUrl" value="${backUrl}" />
		  		<input type="hidden" name="pageIndex" value="${pageIndex}" />
		  		<!-- 添加时 ，状态默认有效 -->
		  		<input type="hidden" name="active" value="1"/>
		  		<p style="color: red;">${message }</p>
		        <table class="form_table">
		          <caption style = "font-size: 18px; color: #BB3700; font-weight: bold; text-align: left;">
		         	 添加统计类型
		          </caption>
		          <tr><td>&nbsp;</td></tr>
		          <tr>
		            <th align="right">
		            	<span>*</span>类型代号：
		            </th>
		            <td>
		            	<form:input path="typeId" cssClass="box"/>
		            	<form:errors path="typeId"/>
		            </td>
		          </tr>
		          <tr>
		            <th align="right">
		            	<span>*</span>类型名称：
		            </th>
		            <td>
		            	<form:input path="typeName" cssClass="box"/>
		            	<form:errors path="typeName"/>
		            </td>
		          </tr>
		          <tr>
		            <th align="right">
		            	<span>*</span>使用情况：
		            </th>
		            <td>
		            	<input type="radio" name="usePlace" value="1">支出信息
		            	<input type="radio" name="usePlace" value="2">收入信息
		            	<form:errors path="usePlace"/>
		            </td>
		          </tr>
		          <tr>
		            <th align="right">类型描述：</th>
		            <td>
		            	<form:textarea path="description" rows="3" cols="50"/>
		            </td>
		          </tr>
		          <tr>
		            <td>&nbsp;</td>
		            <td>
		            	<input class = "button" type = "submit" name = "sure" id="sure" value = " 添 加 ">
		              	<input class = "button" type = "button" name = "return" id="return" 
		              		value = " 返 回 " onclick="javascript:location.href = '${backUrl}'">
		            </td>
		          </tr>
		          <tr><td>&nbsp;</td></tr>
		        </table>
		  	</form:form>
  	  	</div>
  	</center>
  </body>
</html>
