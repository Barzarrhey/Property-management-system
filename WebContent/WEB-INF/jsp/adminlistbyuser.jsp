<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 引入分页插件 -->
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="d3eaef">
	<div class="right">
		
		<!--用户列表-->
		<%--pge.pager是分页插件,url是分页的功能
		items当前你要显示多少条数据
		maxPageItems每页显示多少条数据,一定要和后台对应
		export一般不动
		maxIndexPages最大显示的页码数 --%>
		<pg:pager url="pgAdminListbyuser.html" items="${totalRecord}"
			export="pageOffset,currentPage=pageNumber" maxIndexPages="5"
			maxPageItems="5">
			<div class="search">

              <pg:param name="adminName" value="${adminName }"/>
			<pg:param name="adminPhone" value="${adminPhone}"/>
				<form method="post"
					action="adminquerybyuser.html">

					<span>用户名：</span> <input name="adminName" class="input-text"
						type="text" value="${adminName}"> <span>用户电话：</span> <input
						name="adminPhone" class="input-text" type="text" value="${adminPhone}">

					<input type="hidden" name="pageIndex" value="1" /> <input
						value="查 询" type="submit" id="searchbutton"> 
				</form>
			</div>

			<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
				<tr class="firstTr">
					<td width="10%" height="35" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">ID</td>
					<td width="15%" height="35" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">姓名</td>
					<td width="14%" height="35" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">性别</td>
					<td width="16%" height="35" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">电话</td>
					<td width="27%" height="35" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">注册日期</td>
					<br/>
				</tr>
				<c:forEach var="ad" items="${adminList}" varStatus="status">
					<tr>
						<td height="35"  bgcolor="d3eaed" class="STYLE6"><div align="center"><span class="STYLE19">${ad.adminId}</span></div></td>
						<td height="35" bgcolor="d3eaed" class="STYLE19"><div align="center">${ad.adminName}</div></td>
						<td height="35" bgcolor="d3eaed" class="STYLE19"><div align="center">${ad.adminSex}</div></td>
						<td height="35" bgcolor="d3eaed" class="STYLE19"><div align="center">${ad.adminPhone}</div></td>
						<td height="35" bgcolor="d3eaed" class="STYLE19"><div align="center">${ad.adminTime}</div></td>	
					</tr>
				</c:forEach>
			</table>
			<%--在数据外面直接复制粘贴begin --%>
			<pg:index>
			&nbsp;&nbsp;&nbsp;共${totalRecord}条记录&nbsp;&nbsp;当前第${currentPage}页&nbsp;&nbsp;
				共${totalPages}页&nbsp;&nbsp;&nbsp;&nbsp; 
				<pg:first>
					<a href="${pageUrl}">首页</a>
				</pg:first>
				<pg:prev>
					<a href="${pageUrl }">上一页</a>
				</pg:prev>
				<pg:pages>
					<c:choose>
						<%--当循环页码是当前页码，则该页码不可以导航，并显示为红色--%>
						<c:when test="${currentPageNumber eq pageNumber}">
							<font color="red">[${pageNumber }]</font>
						</c:when>

						<%-- 当循环页码不是当前页码，则该页码可以导航 --%>
						<c:otherwise>
							<a href="${pageUrl }">[${pageNumber }]</a>
						</c:otherwise>
					</c:choose>
				</pg:pages>
				<pg:next>
					<a href="${pageUrl }">下一页</a>
				</pg:next>
				<pg:last>
					<a href="${pageUrl }">尾页</a>
				</pg:last>
			</pg:index>
			<%--在数据外面直接复制粘贴end --%>
		</pg:pager>


	</div>
</body>
</html>