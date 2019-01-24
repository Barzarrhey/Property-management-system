<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/chili-1.7.pack.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery.easing.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery.dimensions.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery.accordion.js"></script>
<script language="javascript">
	jQuery().ready(function(){
		jQuery('#navigation').accordion({
			header: '.head',
			navigation1: true, 
			event: 'click',
			fillSpace: true,
			animated: 'bounceslide'
		});
	});
</script>
<style type="text/css">
<!--
body {
	margin:0px;
	padding:0px;
	font-size: 12px;
}
#navigation {
	margin:0px;
	padding:0px;
	width:147px;
}
#navigation a.head {
	cursor:pointer;
	background:url(${pageContext.request.contextPath }/static/images/main_34.gif) no-repeat scroll;
	display:block;
	font-weight:bold;
	margin:0px;
	padding:5px 0 5px;
	text-align:center;
	font-size:12px;
	text-decoration:none;
}
#navigation ul {
	border-width:0px;
	margin:0px;
	padding:0px;
	text-indent:0px;
}
#navigation li {
	list-style:none; display:inline;
}
#navigation li li a {
	display:block;
	font-size:12px;
	text-decoration: none;
	text-align:center;
	padding:3px;
}
#navigation li li a:hover {
	background:url(${pageContext.request.contextPath }/static/images/tab_bg.gif) repeat-x;
		border:solid 1px #adb9c2;
}
-->
</style>
</head>
<body>
<div  style="height:100%;">
  <ul id="navigation">
    <li> <a class="head">管理员</a>
      <ul>
      <c:if test="${admin.type==1}">
        <li><a href="admininformation.html" target="rightFrame">个人信息</a></li>
        <li><a href="pgAdminListbyadmin.html" target="rightFrame">管理员查看</a></li>
        <li><a href="adminadd.html" target="rightFrame">添加管理员</a></li>
		</c:if>
		<c:if test="${admin.type==2}">
        <li><a href="pgAdminListbyuser.html" target="rightFrame">管理员查看</a></li>
		</c:if>
      </ul>
    </li>
     <li> <a class="head">用户信息</a>
      <ul>
        <c:if test="${admin.type==1}">
        <li><a href="userquery.do" target="rightFrame">用户信息管理</a></li>
	      <li><a href="useradd.do" target="rightFrame">添加用户信息</a></li>
	     </c:if>
	      <c:if test="${admin.type==2}">
	      <li><a href="userlook.do" target="rightFrame">用户个人信息</a></li>

	      </c:if>
      </ul>
    </li>
    <li> <a class="head">报修</a>
      <ul>
        <li><a href="repairpost.html" target="rightFrame">我要报修</a></li>
        <li><a href="repairview.html" target="rightFrame">查看报修</a></li>
      </ul>
    </li>
    <li> <a class="head">业主投诉</a>
      <ul>
        <li><a href="Comp1.html" target="rightFrame">投诉</a></li>
        <li><a href="Comp3.html" target="rightFrame">投诉历史</a></li>
      </ul>
    </li>
  </ul>
</div>
</body>
</html>