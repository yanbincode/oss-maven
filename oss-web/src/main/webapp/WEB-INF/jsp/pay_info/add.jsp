<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>支出信息添加</title>
	<link rel="stylesheet" href="./css/commons.css" type="text/css"></link>
  	<script type="text/javascript" src="./js/wpCalendar.js"></script>
  </head>
  <body>
    <center>
  	  	<div id="form">
		  	<form:form action="payInfo.do" modelAttribute="payInfo" method="post">
		  		<input type="hidden" name="method" value="add"/>
		  		<input type="hidden" name="backUrl" value="${backUrl}" />
		  		<input type="hidden" name="pageIndex" value="${pageIndex}" />
		  		<p style="color: red;">${message }</p>
		        <table class="form_table">
		          <caption style = "font-size: 18px; color: #BB3700; font-weight: bold; text-align: left;">
		         	 添加支出信息
		          </caption>
		          <tr><td>&nbsp;</td></tr>
		          <tr>
		            <th align="right">
		            	<span>*</span>支出金额：
		            </th>
		            <td>
		            	<form:input path="payNumber" cssClass="box"/>
		            	<form:errors path="payNumber"/>
		            </td>
		          </tr>
		          <tr>
		            <th align="right">
		            	<span>*</span>支出信息：
		            </th>
		            <td>
		            	<form:textarea path="payContent" cols="50" rows="3"/>
		            	<form:errors path="payContent"/>
		            </td>
		          </tr>
		          <tr>
		            <th align="right">
		            	<span>*</span>支出人：
		            </th>
		            <td>
		            	<select name="payer" id="payer" class="box"> 
		            	  <option value="">请选择</option>
		                  <c:forEach items="${ossUsers}" var="ossUser" varStatus="status">
		                  	<c:choose>
		                  		<c:when test="${payInfo.payer.userId eq ossUser.userId}">
		                  			<option value="${ossUser.userId}" selected="selected">${ossUser.name }</option>
		                  		</c:when>
		                  		<c:otherwise>
		                  			<option value="${ossUser.userId }">${ossUser.name }</option>
		                  		</c:otherwise>
		                  	</c:choose>
		            	  </c:forEach>
		            	</select> 
		            	<form:errors path="payer" />
		            </td>
		          </tr>
		          <tr>
		            <th align="right"><span>*</span>支出时间：</th>
		            <td>
		            	<form:input path="dayTime" onfocus="showCalendar(this)" readonly="readonly" cssClass="box"/>
		            	<form:errors path="dayTime" />
		            </td>
		          </tr>
		          <tr>
		          	<th align="right">
		          		<span>*</span>支出类型：
		          	</th>
		          	<td>
		          		<select name="accountType" id="accountType" class="box"> 
		          			<option value="">请选择</option>
		          			<c:forEach items="${accountTypes}" var="accountType" varStatus="status">
			                  	<c:choose>
			                  		<c:when test="${payInfo.accountType.recordId eq accountType.recordId}">
			                  			<option value="${accountType.recordId}" selected="selected">${accountType.typeName}</option>
			                  		</c:when>
			                  		<c:otherwise>
			                  			<option value="${accountType.recordId}">${accountType.typeName}</option>
			                  		</c:otherwise>
			                  	</c:choose>
		          			</c:forEach>
		          		</select>
		          		<form:errors path="accountType" />
		          	</td>
		          </tr>
		          <tr>
		          	<th align="right">价值等级：</th>
		          	<td>
		          		<input type="radio" name="payValue" value="0">值得
		          		<input type="radio" name="payValue" value="1" checked="checked">尚可
		          		<input type="radio" name="payValue" value="2">凑合
		          		<input type="radio" name="payValue" value="3">不值
		          	</td>
		          </tr>
		          <tr>
		            <th align="right">备注：</th>
		            <td>
		            	<form:textarea path="remark" rows="3" cols="50"/>
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
