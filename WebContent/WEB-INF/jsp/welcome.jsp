<%@page import="com.pms.util.Constants"%>
<%@page import="com.pms.pojo.*"%>
<%@page import="com.pms.dao.impl.*"%>
<%@page import="com.pms.dao.*"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="d3eaea">

<c:if test="${admin.type==1}">
		
		欢迎管理员${admin.adminName}登录物业管理中心!!!
		
	</c:if>
<c:if test="${admin.type==2}">
		
		欢迎业主${admin.userName}登录物业管理中心!!!
		
	</c:if>
</body>
</html>