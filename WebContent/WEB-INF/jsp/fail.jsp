<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>小区物业管理平台</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/css/style.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/js.js"></script>
</head>
<body>
<div id="top"> </div>
<form id="login" name="login" action="tologin.html" method="post">
  <div id="center">
   <%=session.getAttribute("error") %><br/>
    <input  type="submit" value="重新登录"/>	
        	
      
    </div>
    <div id="center_right"></div>
</form>
<div id="footer"></div>
</body>
</html>