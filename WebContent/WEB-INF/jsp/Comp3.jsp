

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



</head>
<script language="javascript">
function ifsumbit(){
	var gnl=confirm("确定已解决了此条投诉?");
	if (gnl==true){
		return true;
	}
	else{
		return false;
	}
}
</script>
<body bgcolor="d3eaef">
    
    

	<div class="right">	<div class="location">
		<table width="100%" border="1" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="6%" height="19"><div align="center"><img src="${pageContext.request.contextPath }/static/images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" height="30"  ><span class="STYLE1"> 业主投诉历史</span></td>
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
		<pg:pager url="pgCompList.do" items="${totalRecord}"
			export="pageOffset,currentPage=pageNumber" maxIndexPages="5"
			maxPageItems="5">
			
			
			
			<div class="search">
				
			<pg:param name="userId" value="${userId }"/>
			<pg:param name="adminId" value="${adminId}"/>
				<form method="post"	action="${pageContext.request.contextPath }/compquery.do">
					<span>业主房号:</span> 
					<input name="userId" class="input-text"	type="text" value="${userId }"> 
					<span>管理员编号:</span> 
					<input name="adminId" class="input-text" type="text" value="${adminId }">
					
					<input value="查 询" type="submit" id="searchbutton"> 
					<a href="${pageContext.request.contextPath}/toCompAdd.html">立即投诉</a>
				</form>
		
			</div>
			<br/>
            
			<table class="STYLE21" border="1" cellpadding="0" cellspacing="0" >
			
				<tr class="firstTr">
					<th width="90" align="center" height="40">业主房号</th>
					<th width="120" align="center">管理员编号</th>
					<th width="120" align="center">投诉日期</th>
					<th width="120" align="center">解决日期</th>
					<th width="250" align="center">投诉原因</th>
				<c:if test="${admin.type==1}">
						<th width="100" align="center">确定解决</th>
				</c:if>
				</tr>
				<c:forEach var="comp" items="${compList}" varStatus="status">
					<tr>
						<td height="60" class="STYLE19"><div align="center"><span>${comp.userId}</span></div></td>
						<td height="60" class="STYLE19"><div align="center"><span>${comp.adminId}</span></div></td>
						<td height="60" class="STYLE19"><div align="center"><span>${comp.subDate}</span></div></td>
						<td height="60" class="STYLE19"><div align="center"><span>${comp.solDate}</span></div></td>
						<td height="60" class="STYLE19"><div align="center"><span>${comp.reason}</span></div></td>
						<c:if test="${admin.type==1}">
				
						<td height="60" class="STYLE19"><div align="center">	
						<c:if test="${comp.solDate == null}">			
							<form name="compsolve" method="post" onsubmit="return ifsumbit()" action="${pageContext.request.contextPath }/compsolve.do"> 
								<input type="hidden"  name="id" value="${comp.id}" />
  								<input type="submit"  value="确定"> 
							</form> 
						</c:if>
						<c:if test="${comp.solDate != null}">
							已解决
						</c:if>
							</div>
						</td>
						</c:if>
						
										
					</tr>
				</c:forEach>
			</table>
			<br/>
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