<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<title>理财小系统</title>

	<link rel="stylesheet" href="./css/commons.css" type="text/css"></link>
	<script type="text/javascript" src="./js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="./js/deal_string.js"></script>
	<script type="text/javascript" src="./js/commons.js"></script>
	<script type="text/javascript" src="./js/wpCalendar.js"></script>
	
	<script type="text/javascript">
	
		// TODO: 用了异步加载，其实这个不起作用了。
		$(window).load(initTd());
	
		// 点击异步加载页面
		function changeList(obj) {
			$("#list").attr("src",obj);
		}
		
	</script>

</head>
<body style="font-family: 微软雅黑;">
<center>
  <div id="super">
  	<!-- ===============================左边层========================================== -->
    <div id="left">
    	<!-- 左上层 -->
    	<div id="left_up">
	    	<a href="javascript:changeList('payInfo.do?method=show&pageIndex=1')" >支出信息</a><br/> 
	    	<a href="javascript:changeList('earnInfo.do?method=show&pageIndex=1')">收入信息</a> <br/>
	    	<!-- 
	    	<a href="javascript:changeList('monthRecord.do?method=show&pageIndex=1')">日报信息</a> <br/> -->
	    	<a href="javascript:changeList('monthRecord.do?method=show&pageIndex=1')">月报信息</a> <br/>
	    	<a href="javascript:changeList('yearRecord.do?method=show&pageIndex=1')">年报信息</a> <br/>
    	</div> 
    	<hr>
    	<!-- 左下层 -->
    	<div id="left_down">
    		<a href="javascript:changeList('accountType.do?method=show&pageIndex=1')">成员管理</a> <br/>
    		<a href="javascript:changeList('accountType.do?method=show&pageIndex=1')">类型管理</a> <br/>
    	</div>
    </div>
    <!-- ===============================中间层========================================== -->
    <div id="middle" onclick="fullRight()">
    	<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>点<br>此<br>切<br/>换
	</div>
    <!-- ===============================右边层========================================== -->
    <div id="right">
      <div id="top">
      	<p style="color: black; font-family: 黑体; font-size: 28px; font-weight: bold;">${sessionScope.name }欢迎登录理财小系统</p> 
      </div>
      <iframe id="list" src="" frameborder="0" width="95%" height="600px" ></iframe>
    </div>
  </div>
</center>
</body>
</html>
