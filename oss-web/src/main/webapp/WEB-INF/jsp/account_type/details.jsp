<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
     <form:form modelAttribute="accountType">
      <table>
        <caption style = "font-size: 18px; color: #BB3700; font-weight: bold; text-align: left;">
       	 查看统计类型
        </caption>
        <tr><td>&nbsp;</td></tr>
        <tr>
          <th align="right">类型代号：</th>
          <td>
          	${accountType.typeId }
          </td>
        </tr>
        <tr>
          <th align="right">类型名称：</th>
          <td>
          	${accountType.typeName }
          </td>
        </tr>
        <tr>
          <th align="right">使用情况：</th>
          <td>
          	<c:choose>
          		<c:when test="${accountType.usePlace == '1' }">
          			支出信息
          		</c:when>
          		<c:when test="${accountType.usePlace == '2' }">
          			收入信息
          		</c:when>
          	</c:choose>
           </td>
        </tr>
        <tr>
          <th align="right">状态：</th>
          <td>
          	<c:choose>
          		<c:when test="${accountType.active == '0' }">
          			无效
          		</c:when>
          		<c:when test="${accountType.active == '1' }">
          			有效
          		</c:when>
          	</c:choose>
          </td>
        </tr>
        <tr>
          <th align="right">类型描述：</th>
          <td>
          	<textarea readonly="readonly">${accountType.description }</textarea>
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
