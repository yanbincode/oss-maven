<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>修改收入信息</title>
	<link rel="stylesheet" href="./css/commons.css" type="text/css"></link>
  	<script type="text/javascript" src="./js/wpCalendar.js"></script>
  </head>
  
  <body>
  	<center>
  	  	<div id="form">
	  	  <form:form modelAttribute="earnInfo" action="earnInfo.do" method="post">
	      	<input type="hidden" name="method" id="method" value="modify" />
	      	<input type="hidden" name="recordId" id="recordId" value="${earnInfo.recordId }" />
	      	<input type="hidden" name="backUrl" value="${backUrl }" />
	      	<input type="hidden" name="pageIndex" value="${pageIndex}" />
	      	<p style="color: red;">${message }</p>
	        <table class="form_table">
	          <caption style = "font-size: 18px; color: #BB3700; font-weight: bold; text-align: left;">
	         	 修改收入信息
	          </caption>
	          <tr><td>&nbsp;</td></tr>
	          <tr>
	            <th align="right"><span>*</span>支出金额：</th>
	            <td>
	            	<form:input path="earnNumber" cssClass="box"/>
	            	<form:errors path="earnNumber"/>
	            </td>
	          </tr>
	          <tr>
	            <th align="right"><span>*</span>支出信息：</th>
	            <td>
	            	<textarea name="earnContent" id="earnContent" cols="50" rows="3">${earnInfo.earnContent}</textarea>
	            	<form:errors path="earnContent"/>
	            </td>
	          </tr>
	          <tr>
	            <th align="right"><span>*</span>支出人：</th>
	            <td>
	            	<select name="earner" id="earner" class="box""> 
	            	  <option value="">请选择</option>
	                  <c:forEach items="${ossUsers}" var="ossUser" varStatus="status">
	                  	<c:choose>
	                  		<c:when test="${earnInfo.earner.userId eq ossUser.userId}">
	                  			<option value="${ossUser.userId}" selected="selected">${ossUser.name }</option>
	                  		</c:when>
	                  		<c:otherwise>
	                  			<option value="${ossUser.userId }">${ossUser.name }</option>
	                  		</c:otherwise>
	                  	</c:choose>
	            	  </c:forEach>
	            	</select> 
	            	<form:errors path="earner" />
	            </td>
	          </tr>
	          <tr>
	            <th align="right"><span>*</span>收入时间：</th>
	            <td>
	            	<form:input path="dayTime" cssClass="box" onfocus="showCalendar(this)" readonly="readonly"/>
	            	<form:errors path="dayTime"/>
	            </td>
	          </tr>
	          <tr>
	          	<th align="right"><span>*</span>收入类型：</th>
	          	<td>
	          		<select name="accountType" id="accountType" class="box"> 
	          			<option value="">请选择</option>
	          			<c:forEach items="${accountTypes}" var="accountType" varStatus="status">
		                  	<c:choose>
		                  		<c:when test="${earnInfo.accountType.recordId eq accountType.recordId}">
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
	            <th align="right">备注：</th>
	            <td>
	            	<textarea rows="3" cols="50" name="remark" id="remark">${earnInfo.remark }</textarea>
	            	<form:errors path="remark"/>
	            </td>
	          </tr>
	          <tr>
	          	<th align="right">添加人：</th>
	          	<td>
	            	${earnInfo.creator.name }
	          	</td>
	          </tr>
	          <tr>
	          	<th align="right">添加时间：</th>
	          	<td>
	            	${earnInfo.createdTime }
	            </td>
	          </tr>
	          <tr>
	          	<th align="right">修改人：</th>
	          	<td>
	          		${earnInfo.modifier.name }
	            </td>
	          </tr>
	          <tr>
	          	<th align="right">修改时间：</th>
	          	<td>
	            	${earnInfo.modifiedTime }
	            </td>
	          </tr>
	          <tr>
	            <td>&nbsp;</td>
	            <td>
	            	<input class = "button" type = "submit" name = "sure" id="sure" value = " 修 改 ">
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
