<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>小区物业管理平台</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/css/style.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/js.js"></script>
</head>
<body>
<div id="top"> </div>
<form id="login" name="login" action="login.do" method="post">
  <div id="center">
    <div id="center_left"></div>
    <div id="center_middle">
      <div class="user">
        <label>用户
        <input type="text" name="name" id="user" />
        </label>
      </div>
      <div class="user">
        <label>密码
        <input type="password" name="password" id="pwd" />
        </label>
      </div>
      
       类型<select name="type">
		<option value="1" >管理员</option>
		<option value="2" selected="selected">业主</option>

	</select>
        	
      
    </div>
    <div id="center_middle_right"></div>
    <div id="center_submit">
      <div class="button"> <img src="${pageContext.request.contextPath }/static/images/dl.gif" width="57" height="20" onclick="form_submit()" > </div>
      <div class="button"> <img src="${pageContext.request.contextPath }/static/images/cz.gif" width="57" height="20" onclick="form_reset()"> </div>
    </div>
    <div id="center_right"></div>
  </div>
</form>
<div id="footer"></div>
</body>
</html>
