<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>收入信息</title>
	<link rel="stylesheet" href="./css/commons.css" type="text/css"></link>
	<script type="text/javascript" src="./js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="./js/deal_string.js"></script>
	<script type="text/javascript" src="./js/commons.js"></script>
</head>
<body style="font-family: 微软雅黑;">
<center>
	<!-- ===========收入=============== -->
	<div id="earn_info_list" class="list">
		<p style="color: red;">${message }</p>
		<table class="list_table" cellspacing="0">
			<tr>
				<th colspan="12" bgcolor="#698CC3" style = "background-color: #698CC3; color: #ffffff; text-align: left; font-weight: bold; font-size: 12px">
					收入信息<font style="color: red; padding-left: 100px;">共（${count}）条</font>
				</th>
			</tr>
			<tr style="height: 25px;" >
			  <th>序号</th>
			  <th>收入金额</th>
			  <th>收入人</th>
			  <th>收入时间</th>
			  <th>收入类型</th>
			  <th>创建人</th>
			  <th>创建时间</th>
			  <!-- 表单用form ，列表用链接就行了 -->
			  <th><a href="earnInfo.do?method=add&pageIndex=${pageIndex}">添加</a></th>
			</tr>
			<c:forEach items="${earnInfos}" var="earnInfo" varStatus="status">
			<tr>
			  <td>${status.count }</td> <!-- 打印出循环次数，可用来做序号 -->
			  <td>${earnInfo.earnNumber }</td>
			  <td>${earnInfo.earner.name }</td>
			  <td>
			  	<fmt:formatDate value="${earnInfo.dayTime}" pattern="yyyy-MM-dd"/>
			  </td>
			  <td>${earnInfo.accountType.typeName}</td>
			  <td>${earnInfo.creator.name }</td>
			  <td>
			  	<fmt:formatDate value="${earnInfo.createdTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
			  </td>
			  <th style="font-size: 12px; background-color: FFFFFF;">
			  	<a href="earnInfo.do?method=modify&recordId=${earnInfo.recordId }&pageIndex=${pageIndex}">修改</a>
			    <a href="earnInfo.do?method=details&recordId=${earnInfo.recordId }">详情</a>
			    <!-- js控制a标签跳转。 -->
			    <a href="javascript:void(0)" onclick="confim(${earnInfo.recordId }, 'earnInfo', ${pageIndex}); return false;">删除</a>
			  </th>
			</tr>
			</c:forEach>
		</table>
		<br/>
		<a href="earnInfo.do?method=show&pageIndex=1">首页</a>
		<a href="earnInfo.do?method=show&pageIndex=${pageIndex - 1}">上一页</a>
		<input type="text" size="3" id="page_index" name="page_index" value="${pageIndex }">
		<a href="earnInfo.do?method=show&pageIndex=${pageIndex + 1}">下一页</a>
		<a href="earnInfo.do?method=show&pageIndex=${lastIndex}">尾页</a>
	</div>
</center>
</body>
</html>