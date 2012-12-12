<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>查看月统计信息</title>
	<link rel="stylesheet" href="./css/commons.css" type="text/css"></link>
  </head>
  
  <body>
  	<center>
  	  	<div id="form">
	  	  <form:form modelAttribute="monthRecord">
	      	<input type="hidden" name="backUrl" value="${backUrl }" />
	      	<p style="color: red;">${message }</p>
	        <table class="form_table">
	          <caption style = "font-size: 18px; color: #BB3700; font-weight: bold; text-align: left;">
	         	 查看月统计信息
	          </caption>
	          <tr><td>&nbsp;</td></tr>
	          <tr>
	            <th align="right"><span>*</span>年月：</th>
	            <td>
	            	<fmt:formatDate value="${monthRecord.monthTime}" pattern="yyyy-MM"/>
	            </td>
	          </tr>
	          <tr>
	            <th align="right"><span>*</span>月支出：</th>
	            <td>
	            	${monthRecord.monthPayCount}
	            </td>
	          </tr>
	          <tr>
	            <th align="right"><span>*</span>月收入：</th>
	            <td>
	            	${monthRecord.monthEarnCount}
	            </td>
	          </tr>
	          <tr>
	            <th align="right"><span>*</span>月统计：</th>
	            <td>
	            	${monthRecord.monthCount}
	            </td>
	          </tr>
	          <tr>
	            <th align="right">备注：</th>
	            <td>
	            	<textarea rows="3" cols="50" name="remark" id="remark" readonly="readonly">${monthRecord.remark }</textarea>
	            </td>
	          </tr>
	          <tr>
	          	<th align="right">添加人：</th>
	          	<td>
	            	${monthRecord.creator.name }
	          	</td>
	          </tr>
	          <tr>
	          	<th align="right">添加时间：</th>
	          	<td>
	            	${monthRecord.createdTime }
	            </td>
	          </tr>
	          <tr>
	          	<th align="right">修改人：</th>
	          	<td>
	          		${monthRecord.modifier.name }
	            </td>
	          </tr>
	          <tr>
	          	<th align="right">修改时间：</th>
	          	<td>
	            	${monthRecord.modifiedTime }
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
