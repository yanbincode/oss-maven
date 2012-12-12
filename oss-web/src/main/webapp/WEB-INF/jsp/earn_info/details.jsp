<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>查看收入信息</title>
	<link rel="stylesheet" href="./css/commons.css" type="text/css"></link>
</head>
<body>
<center>
   <div id="form">
     <form:form modelAttribute="payInfo">
      <table>
        <caption style = "font-size: 18px; color: #BB3700; font-weight: bold; text-align: left;">
       	 查看收入信息
        </caption>
        <tr><td>&nbsp;</td></tr>
        <tr>
          <th align="right">收入金额：</th>
          <td>
          	${earnInfo.earnNumber }
          </td>
        </tr>
        <tr>
          <th align="right">收入信息：</th>
          <td>
          	<textarea readonly="readonly">${earnInfo.earnContent }</textarea>
          </td>
        </tr>
        <tr>
          <th align="right">收入人：</th>
          <td>
           	${earnInfo.earner.name }
          </td>
        </tr>
        <tr>
          <th align="right">收入时间：</th>
          <td>
          	<fmt:formatDate value="${earnInfo.dayTime }" pattern="yyyy-MM-dd"/>
          </td>
        </tr>
        <tr>
          <th align="right">收入类型：</th>
          <td>
          	${earnInfo.accountType.typeName}
          </td>
        </tr>
        <tr>
          <th align="right">备注：</th>
          <td>
          	<textarea readonly="readonly">${earnInfo.remark }</textarea>
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
