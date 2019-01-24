

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>	
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 引入分页插件 -->
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/yangshi.css" />


</head>

<script language="javascript">
function sumbit_sure(){
var gnl=confirm("确定已经解决了此条报修?");
if (gnl==true){
return true;
}else{
return false;
}
}
</script>

<body bgcolor="d3eaef">
<%-- <c:if test="${admin.type==1}"></c:if> --%>



	<div class="right">	<div class="location">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="6%" height="19"><div align="center"><img src="${pageContext.request.contextPath }/static/images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" height="30"  ><span class="STYLE1"> 查看报修</span></td>
              </tr>
            </table></td>          
          </tr>
        </table></td>
      </tr>
    </table>	
    <br/>
			
		</div>

		<!--用户列表-->
		<%--pge.pager是分页插件,url是分页的功能
		items当前你要显示多少条数据
		maxPageItems每页显示多少条数据,一定要和后台对应
		export一般不动
		maxIndexPages最大显示的页码数 --%>
		<pg:pager url="pgRepairList_admin.do" items="${totalRecord}"
			export="pageOffset,currentPage=pageNumber" maxIndexPages="5"
			maxPageItems="5">
			
			<div class="search">
			<pg:param name="userId" value="${userId }"/>
			<pg:param name="resId" value="${resId }"/>
				<form method="post"	action="${pageContext.request.contextPath }/repairquery_admin.do">
					<span>业主房号:</span> 
					<input name="userId" class="input-text"	type="text" value="${userId }"> 
					<span>物品编号:</span> 
					<input name="resId" class="input-text" type="text" value="${resId }">
					
					<input value="查 询" type="submit" id="searchbutton"> 
				</form>
			</div>
			<br/>

			<table border="1" class="providerTable" cellpadding="0" cellspacing="0">
			
				<tr class="firstTr">
					<th width="90" align="center" height="40">业主房号</th>
					<th width="90" align="center">物品编号</th>
					<th width="120" align="center">报修日期</th>
					<th width="120" align="center">解决日期</th>
					<th width="200" align="center">报修原因</th>
					<th width="200" align="center">确定</th>
				</tr>
				
				<c:forEach var="repair" items="${repairList}" varStatus="status">
					<tr>
						<td height="60" class="STYLE19"><div align="center"><span>${repair.userId}</span></div></td>
						<td height="60" class="STYLE19"><div align="center"><span>${repair.resId}</span></div></td>
						<td height="60" class="STYLE19"><div align="center"><span>${repair.subDate}</span></div></td>
						<td height="60" class="STYLE19"><div align="center"><span>${repair.solDate}</span></div></td>
						<td height="60" class="STYLE19"><div align="center"><span>${repair.reason}</span></div></td>
						<td height="60" class="STYLE19"><div align="center">
						<c:if test="${null == repair.solDate }">
							<form name="solveRepairForm" onsubmit="return sumbit_sure()" method="post" action="${pageContext.request.contextPath }/solveRepair.html"> 
								<input type="hidden"  name="id" value="${repair.id}" />
  								<input type="submit"  value="确定"> 
  							</form> 
  						</c:if>
  						<c:if test="${null != repair.solDate }">
							已解决
  						</c:if>
						</div></td>
						<td>
					</tr>
				</c:forEach>
			</table>
			<%--在数据外面直接复制粘贴begin --%>
			<pg:index>
			共${totalRecord}条记录&nbsp;&nbsp;当前第${currentPage}页&nbsp;&nbsp;
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