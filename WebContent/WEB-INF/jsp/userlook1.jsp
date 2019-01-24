
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 引入分页插件 -->
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
<!--
body {
	margin-left: 3px;
	margin-top: 0px;
	margin-right: 3px;
	margin-bottom: 0px;
}
.STYLE1 {
	color: #e1e2e3;
	font-size: 12px;
}
.STYLE6 {color: #000000; font-size: 12; }
.STYLE10 {color: #000000; font-size: 16px; }
.STYLE19 {
	color: #344b50;
	font-size: 16px;
}
.STYLE21 {
	font-size: 12px;
	color: #3b6375;
}
.STYLE22 {
	font-size: 12px;
	color: #295568;
}
-->
</style>
</head>
<script language="javascript">
function ifsumbit(){
	var gnl=confirm("是否删除?");
	if (gnl==true){
		return true;
	}
	else{
		return false;
	}
}
</script>
<script language="javascript">
function ifchange(){
	var gnl=confirm("是否修改?");
	if (gnl==true){
		return true;
	}
	else{
		return false;
	}
}
</script>
<body>

	<div class="right">
		<br/>
      
		<!--用户列表-->
		<%--pge.pager是分页插件,url是分页的功能
		items当前你要显示多少条数据
		maxPageItems每页显示多少条数据,一定要和后台对应
		export一般不动
		maxIndexPages最大显示的页码数 --%>
		<pg:pager url="pgUserList.do" 
		items="${totalRecord}"
		export="pageOffset,currentPage=pageNumber" 
		maxIndexPages="5"
		maxPageItems="5">
			<div class="search">
			
			<pg:param name="userName" value ="${userName }"/>
			<pg:param name="userPhone" value ="${userPhone }"/>
				<form method="post"
					action="${pageContext.request.contextPath }/userquery.do">

					<div align="center" ><span>用户名：</span> <input name="userName" class="input-text"
						type="text" value="${userName }"> <span>&nbsp;&nbsp;用户电话：</span> <input
						name="userPhone" class="input-text" type="text" value="${ userPhone }">

					<input type="hidden" name="pageIndex" value="1" /> <input
						value="查 询" type="submit" id="searchbutton"> </div>
				</form>
			</div>
           <br/>
           <tr>
			<td><table class="providerTable" width="100%" border="1" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
				<tr class="firstTr">
                    
					<td height="40" width="20%" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">用户编号</span></div></td>
					<td height="40" width="20%" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">用户姓名</span></div></td>
					<td height="40" width="20%" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">注册时间</span></div></td>
					<td height="40" width="20%" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">联系方式</span></div></td>
					<td height="40" width="50" colspan="3" bgcolor="d3eaef" class="STYLE6"><div align="center"><span>操作</span></div></td>
				</tr>
				<c:forEach var="user" items="${userList }" varStatus="status">

					<tr>
                    
						<td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><span>${user.userId}</span></div></td>
						<td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><span>${user.userName }</span></div></td>
						<td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><span>${user.userTime }</span></div></td>
						<td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><span>${user.userPhone}</span></div></td>
						<td height="40" widtn="60" bgcolor="#FFFFFF" class="STYLE19">	<div align="center">						
							<form name="userview" method="post" action="${pageContext.request.contextPath }/userview.do"> 
								<input type="hidden"  name="userId" value="${user.userId}" />
  								<input type="submit"  value="查看"> </td>
							</form></span> <td bgcolor="#FFFFFF" class="STYLE19"><div align="center">	
							<form name="userchange" method="post" onsubmit="return ifchange()" action="${pageContext.request.contextPath }/userchange.do"> 
								<input type="hidden"  name="userId" value="${user.userId}" />
  								<input type="submit"  value="修改"> </td>
							</form> </span><td bgcolor="#FFFFFF" class="STYLE19">	<div align="center">		
							<form name="userdelete" method="post" onsubmit="return ifsumbit()" action="${pageContext.request.contextPath }/userdelete.do"> 
								<input type="hidden"  name="userId" value="${user.userId}" />
  								<input type="submit"  value="删除"> </span></td>
							</form> 
								
						</td>
					</tr>

				</c:forEach>
			</table></td>
			</tr>
			<%--在数据外面直接复制粘贴begin --%>
			<br/><br/>
			<pg:index>
			共${totalRecord}条记录&nbsp;&nbsp;&nbsp;&nbsp;当前第${currentPage}页&nbsp;&nbsp;&nbsp;&nbsp;
				共${totalPages}页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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