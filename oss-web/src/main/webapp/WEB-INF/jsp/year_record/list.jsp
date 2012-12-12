<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<title>年统计信息</title>
	<link rel="stylesheet" href="./css/commons.css" type="text/css"></link>
	<script type="text/javascript" src="./js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="./js/deal_string.js"></script>
	<script type="text/javascript" src="./js/commons.js"></script>
	<script type="text/javascript" src="./js/wpCalendar.js"></script>
</head>
<body style="font-family: 微软雅黑;">
<center>
	<!-- ===========支出=============== -->
	<div id="year_record_list" class="list">
		<p style="color: red;">${message}</p>
		<input type="hidden" id="pageIndex" value="${pageIndex}">
		<table class="list_table" cellspacing="0">
			<tr>
				<th colspan="12" bgcolor="#698CC3" style = "background-color: #698CC3; color: #ffffff; text-align: left; font-weight: bold; font-size: 12px">
					年统计信息<font style="color: red; padding-left: 100px;">共（${count}）条</font>
				</th>
			</tr>
			<tr style="height: 25px;" >
			  <th>序号</th>
			  <th>年号</th>
			  <th>年收入</th>
			  <th>年支出</th>
			  <th>年合计</th>
			  <!-- 表单用form ，列表用链接就行了 -->
			  <th style="width: 20%;">
			  	<input type="text" class="stat_box" id="dateTime" onfocus="showCalendar(this)" readonly="readonly">
			  	<a href="javascript:void(0)" onclick="goCount('yearRecord',${pageIndex})">手动统计</a>
			  </th>
			</tr>
			<c:forEach items="${yearRecords}" var="yearRecord" varStatus="status">
			<tr>
			  <td>${status.count }</td> <!-- 打印出循环次数，可用来做序号 -->
			  <td>
			  	<fmt:formatDate value="${yearRecord.yearTime}" pattern="yyyy"/>
			  </td>
			  <td>${yearRecord.yearEarnCount}</td>
			  <td>${yearRecord.yearPayCount}</td>
			  <td>${yearRecord.yearCount}</td>
			  <th style="font-size: 12px; background-color: FFFFFF;">
			  	<a href="yearRecord.do?method=reCount&recordId=${yearRecord.recordId }&pageIndex=${pageIndex}" >重新统计</a>
			  	<a href="yearRecord.do?method=modify&recordId=${yearRecord.recordId }&pageIndex=${pageIndex}">修改</a>
			    <a href="yearRecord.do?method=details&recordId=${yearRecord.recordId }">详情</a>
			    <!-- js控制a标签跳转。 -->
			    <a href="javascript:void(0)" onclick="confim(${yearRecord.recordId }, 'yearRecord', ${pageIndex}); return false;">删除</a>
			  </th>
			</tr>
			</c:forEach>
		</table>
		<br/>
		<a href="yearRecord.do?method=show&pageIndex=1">首页</a>
		<a href="yearRecord.do?method=show&pageIndex=${pageIndex - 1}">上一页</a>
		<input type="text" size="3" id="page_index" name="page_index" value="${pageIndex }">
		<a href="yearRecord.do?method=show&pageIndex=${pageIndex + 1}">下一页</a>
		<a href="yearRecord.do?method=show&pageIndex=${lastIndex}">尾页</a>
	</div>
</center>
</body>
</html>