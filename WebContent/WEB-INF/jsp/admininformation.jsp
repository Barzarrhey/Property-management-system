<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<c:if test="${by==4}">
 <script type="text/javascript">

      alert("修改成功！");

</script>
</c:if>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/js.js"></script>

</head>
<body bgcolor="d3eaea">
<br>
<td width="10%" height="35" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">您的个人信息：</td>
<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
<br>
	<tr>
		<td width="10%" height="35" bgcolor="d3eaef" class="STYLE6">I&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D：${admin.adminId}</td>
	</tr>
	<tr>
		<td width="10%" height="35" bgcolor="d3eaef" class="STYLE6">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：${admin.adminName}</td>
		
	</tr>
	<tr>
		<td width="10%" height="35" bgcolor="d3eaef" class="STYLE6">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：${admin.adminSex}</td>
		
	</tr>
	<tr>
		<td width="10%" height="35" bgcolor="d3eaef" class="STYLE6">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：${admin.adminPhone}</td>
	</tr>
	<tr>
		<td width="10%" height="35" bgcolor="d3eaef" class="STYLE6">注册日期：${admin.adminTime}</td>
	</tr>
	<tr>
		<td width="10%" height="35" bgcolor="d3eaef" class="STYLE6">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：${admin.adminPassword}</td>
	</tr>
	</table>
</body>
</html>