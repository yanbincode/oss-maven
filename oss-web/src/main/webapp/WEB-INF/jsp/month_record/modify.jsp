<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>修改月统计信息</title>
	<link rel="stylesheet" href="./css/commons.css" type="text/css"></link>
	<script type="text/javascript" src="./js/jquery-1.7.2.min.js"></script>
  	<script type="text/javascript" src="./js/wpCalendar.js"></script>
  	<script type="text/javascript" src="./js/commons.js"></script>
  </head>
  
  <body>
  	<center>
  	  	<div id="form">
	  	  <form:form modelAttribute="monthRecord" action="monthRecord.do" method="post">
	      	<input type="hidden" name="method" id="method" value="modify" />
	      	<input type="hidden" name="recordId" id="recordId" value="${monthRecord.recordId }" />
	      	<input type="hidden" name="backUrl" value="${backUrl }" />
	      	<input type="hidden" name="pageIndex" value="${pageIndex}" />
	      	<p style="color: red;">${message }</p>
	        <table class="form_table">
	          <caption style = "font-size: 18px; color: #BB3700; font-weight: bold; text-align: left;">
	         	 修改月统计信息
	          </caption>
	          <tr><td>&nbsp;</td></tr>
	          <tr>
	            <th align="right"><span>*</span>年月：</th>
	            <td>
	            	<form:input path="monthTime" cssClass="box" onfocus="showCalendar(this)" readonly="readonly"/>
	            	<form:errors path="monthTime"/>
	            </td>
	          </tr>
	          <tr>
	            <th align="right"><span>*</span>月支出：</th>
	            <td>
	            	<form:input path="monthPayCount" cssClass="box"/>
	            	<form:errors path="monthPayCount"/>
	            </td>
	          </tr>
	          <tr>
	            <th align="right"><span>*</span>月收入：</th>
	            <td>
	            	<form:input path="monthEarnCount" cssClass="box"/>
	            	<form:errors path="monthEarnCount"/>
	            </td>
	          </tr>
	          <tr>
	            <th align="right"><span>*</span>月统计：</th>
	            <td>
	            	<form:input path="monthCount" cssClass="box" cssStyle="width:285px" readonly="readonly"/>
	            	<input type="button" class="button" style="width: 60px" value=" 计算 " 
	            					onclick="countTotal('monthEarnCount','monthPayCount','monthCount')">
	            	<form:errors path="monthCount"/>
	            </td>
	          </tr>
	          <tr>
	            <th align="right">备注：</th>
	            <td>
	            	<textarea rows="3" cols="50" name="remark" id="remark">${monthRecord.remark }</textarea>
	            	<form:errors path="remark"/>
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
