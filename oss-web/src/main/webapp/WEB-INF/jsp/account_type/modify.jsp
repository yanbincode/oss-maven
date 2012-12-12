<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>统计类型查看</title>
	<link rel="stylesheet" href="./css/commons.css" type="text/css"></link>
  </head>
  
  <body>
  	<center>
  	  	<div id="form">
	  	  <form:form modelAttribute="accountType" action="accountType.do" method="post">
	      	<input type="hidden" name="method" id="method" value="modify" />
	      	<input type="hidden" name="recordId" id="recordId" value="${accountType.recordId }" />
	      	<input type="hidden" name="backUrl" value="${backUrl }" />
	      	<input type="hidden" name="pageIndex" value="${pageIndex}" />
	      	<p style="color: red;">${message }</p>
	        <table class="form_table">
	          <caption style = "font-size: 18px; color: #BB3700; font-weight: bold; text-align: left;">
	         	 修改统计类型
	          </caption>
	          <tr><td>&nbsp;</td></tr>
	          <tr>
	            <th align="right"><span>*</span>类型代号：</th>
	            <td>
	            	<form:input path="typeId" cssClass="box"/>
	            	<form:errors path="typeId"/>
	            </td>
	          </tr>
	          <tr>
	            <th align="right"><span>*</span>类型名称：</th>
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
	            	<c:choose>
	            		<c:when test="${accountType.usePlace == '1' }">
	            			<input type="radio" name="usePlace" value="1" checked="checked">支出信息
	            			<input type="radio" name="usePlace" value="2">收入信息
	            		</c:when>
	            		<c:when test="${accountType.usePlace == '2' }">
	            			<input type="radio" name="usePlace" value="1">支出信息
	            			<input type="radio" name="usePlace" value="2" checked="checked">收入信息
	            		</c:when>
	            	</c:choose>
	            	<form:errors path="usePlace"/>
	             </td>
	          </tr>
	          <tr>
	            <th align="right">
	            	<span>*</span>状态：
	            </th>
	            <td>
	            	<c:choose>
	            		<c:when test="${accountType.active == '0' }">
	            			<input type="radio" name="active" value="0" checked="checked">无效
	            			<input type="radio" name="active" value="1">有效
	            		</c:when>
	            		<c:when test="${accountType.active == '1' }">
	            			<input type="radio" name="active" value="0">无效
	            			<input type="radio" name="active" value="1" checked="checked">有效
	            		</c:when>
	            	</c:choose>
	            	<form:errors path="active"/>
	            </td>
	          </tr>
	          <tr>
	            <th align="right">类型描述：</th>
	            <td>
	            	<textarea name="description" cols="50" rows="3">${accountType.description}</textarea>
	            </td>
	          </tr>
	          <tr>
	          	<th align="right">添加人：</th>
	          	<td>
	            	${accountType.creator.name }
	          	</td>
	          </tr>
	          <tr>
	          	<th align="right">添加时间：</th>
	          	<td>
	            	${accountType.createdTime }
	            </td>
	          </tr>
	          <tr>
	          	<th align="right">修改人：</th>
	          	<td>
	          		${accountType.modifier.name }
	            </td>
	          </tr>
	          <tr>
	          	<th align="right">修改时间：</th>
	          	<td>
	            	${accountType.modifiedTime }
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
