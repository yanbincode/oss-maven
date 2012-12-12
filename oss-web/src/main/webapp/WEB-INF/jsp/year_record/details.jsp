<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>查看年统计信息</title>
	<link rel="stylesheet" href="./css/commons.css" type="text/css"></link>
  </head>
  
  <body>
  	<center>
  	  	<div id="form">
	  	  <form:form modelAttribute="yearRecord">
	      	<input type="hidden" name="backUrl" value="${backUrl }" />
	      	<p style="color: red;">${message}</p>
	        <table class="form_table">
	          <caption style = "font-size: 18px; color: #BB3700; font-weight: bold; text-align: left;">
	         	 查看年统计信息
	          </caption>
	          <tr><td>&nbsp;</td></tr>
	          <tr>
	            <th align="right"><span>*</span>年：</th>
	            <td>
	            	<fmt:formatDate value="${yearRecord.yearTime}" pattern="yyyy-MM"/>
	            </td>
	          </tr>
	          <tr>
	            <th align="right"><span>*</span>年支出：</th>
	            <td>
	            	${yearRecord.yearPayCount}
	            </td>
	          </tr>
	          <tr>
	            <th align="right"><span>*</span>年收入：</th>
	            <td>
	            	${yearRecord.yearEarnCount}
	            </td>
	          </tr>
	          <tr>
	            <th align="right"><span>*</span>年统计：</th>
	            <td>
	            	${yearRecord.yearCount}
	            </td>
	          </tr>
	          <tr>
	            <th align="right">备注：</th>
	            <td>
	            	<textarea rows="3" cols="50" name="remark" id="remark" readonly="readonly">${yearRecord.remark }</textarea>
	            </td>
	          </tr>
	          <tr>
	          	<th align="right">添加人：</th>
	          	<td>
	            	${yearRecord.creator.name }
	          	</td>
	          </tr>
	          <tr>
	          	<th align="right">添加时间：</th>
	          	<td>
	            	${yearRecord.createdTime }
	            </td>
	          </tr>
	          <tr>
	          	<th align="right">修改人：</th>
	          	<td>
	          		${yearRecord.modifier.name}
	            </td>
	          </tr>
	          <tr>
	          	<th align="right">修改时间：</th>
	          	<td>
	            	${yearRecord.modifiedTime}
	            </td>
	          </tr>
	          <tr>
	            <td>&nbsp;</td>
	            <td>
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
