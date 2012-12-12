<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>支出信息</title>
	<link rel="stylesheet" href="./css/commons.css" type="text/css"></link>
	<script type="text/javascript" src="./js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="./js/deal_string.js"></script>
	<script type="text/javascript" src="./js/commons.js"></script>
</head>
<body style="font-family: 微软雅黑;">
<center>
	<!-- ===========支出=============== -->
	<div id="pay_info_list" class="list">
		<p style="color: red;">${message }</p>
		<table class="list_table" cellspacing="0">
			<tr>
				<th colspan="12" bgcolor="#698CC3" style = "background-color: #698CC3; color: #ffffff; text-align: left; font-weight: bold; font-size: 12px">
					支出信息<font style="color: red; padding-left: 100px;">共（${count}）条</font>
				</th>
			</tr>
			<tr style="height: 25px;" >
			  <th>序号</th>
			  <th>支出金额</th>
			  <th>支出人</th>
			  <th>支出时间</th>
			  <th>支出类型</th>
			  <th>价值等级</th>
			  <th>创建人</th>
			  <th>创建时间</th>
			  <!-- 表单用form ，列表用链接就行了 -->
			  <th><a href="payInfo.do?method=add&pageIndex=${pageIndex}">添加</a></th>
			</tr>
			<c:forEach items="${payInfos}" var="payInfo" varStatus="status">
			<tr>
			  <td>${status.count }</td> <!-- 打印出循环次数，可用来做序号 -->
			  <td>${payInfo.payNumber }</td>
			  <td>${payInfo.payer.name }</td>
			  <td>
			  	<fmt:formatDate value="${payInfo.dayTime}" pattern="yyyy-MM-dd"/>
			  </td>
			  <td>${payInfo.accountType.typeName}</td>
			  <td>
			  	<c:choose>
			  		<c:when test="${payInfo.payValue == '0'}">
			  			值得
			  		</c:when>
			  		<c:when test="${payInfo.payValue == '1'}">
			  			尚可
			  		</c:when>
			  		<c:when test="${payInfo.payValue == '2'}">
			  			凑合
			  		</c:when>
			  		<c:when test="${payInfo.payValue == '3'}">
			  			不值
			  		</c:when>
			  	</c:choose>
			  </td>
			  <td>${payInfo.creator.name }</td>
			  <td>
			  	<fmt:formatDate value="${payInfo.createdTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
			  </td>
			  <th style="font-size: 12px; background-color: FFFFFF;">
			  	<a href="payInfo.do?method=modify&recordId=${payInfo.recordId }&pageIndex=${pageIndex}">修改</a>
			    <a href="payInfo.do?method=details&recordId=${payInfo.recordId }">详情</a>
			    <!-- js控制a标签跳转。 -->
			    <a href="javascript:void(0)" onclick="confim(${payInfo.recordId }, 'payInfo', ${pageIndex}); return false;">删除</a>
			  </th>
			</tr>
			</c:forEach>
		</table>
		<br/>
		<a href="payInfo.do?method=show&pageIndex=1">首页</a>
		<a href="payInfo.do?method=show&pageIndex=${pageIndex - 1}">上一页</a>
		<input type="text" size="3" id="page_index" name="page_index" value="${pageIndex }">
		<a href="payInfo.do?method=show&pageIndex=${pageIndex + 1}">下一页</a>
		<a href="payInfo.do?method=show&pageIndex=${lastIndex}">尾页</a>
	</div>
</center>
</body>
</html>