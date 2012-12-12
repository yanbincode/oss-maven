<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>查看支出信息</title>
	<link rel="stylesheet" href="./css/commons.css" type="text/css"></link>
</head>
<body>
<center>
   <div id="form">
     <form:form modelAttribute="payInfo">
      <table>
        <caption style = "font-size: 18px; color: #BB3700; font-weight: bold; text-align: left;">
       	 查看支出信息
        </caption>
        <tr><td>&nbsp;</td></tr>
        <tr>
          <th align="right">支出金额：</th>
          <td>
          	${payInfo.payNumber }
          </td>
        </tr>
        <tr>
          <th align="right">支出信息：</th>
          <td>
          	<textarea readonly="readonly">${payInfo.payContent }</textarea>
          </td>
        </tr>
        <tr>
          <th align="right">支出人：</th>
          <td>
           	${payInfo.payer.name }
          </td>
        </tr>
        <tr>
          <th align="right">支出时间：</th>
          <td>
          	<fmt:formatDate value="${payInfo.dayTime }" pattern="yyyy-MM-dd"/>
          </td>
        </tr>
        <tr>
          <th align="right">支出类型：</th>
          <td>
          	${payInfo.accountType.typeName}
          </td>
        </tr>
        <tr>
          <th align="right">价值等级：</th>
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
        </tr>
        <tr>
          <th align="right">备注：</th>
          <td>
          	<textarea readonly="readonly">${payInfo.remark }</textarea>
          </td>
        </tr>
        <tr>
          <th align="right">添加人：</th>
          <td>
           	${payInfo.creator.name }
          </td>
        </tr>
        <tr>
          <th align="right">添加时间：</th>
          <td>
          	${payInfo.createdTime }
          </td>
        </tr>
        <tr>
          <th align="right">修改人：</th>
          <td>
           	${payInfo.modifier.name }
          </td>
        </tr>
        <tr>
          <th align="right">修改时间：</th>
          <td>
          	${payInfo.modifiedTime }
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
