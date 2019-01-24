<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="d3eaef">
<%

    String mess=(String)session.getAttribute("message"); 

 %>

<c:if test="${fla==1 }">

 <script type="text/javascript">

      alert("<%=mess%>");

</script>
</c:if>

<div class="right">
<div class="right">
        <div class="providerAdd">
            <form id="userForm" name="userForm" method="post" 
            action="userchange1.do"
            enctype="multipart/form-data" 
            >
				
                <!--div的class 为error是验证错误，ok是验证成功-->
                <br/>
                <div>
                    <label for="userId">用户编号：${user.userId}</label>
                    <input type="hidden" name="userId" id="userId" value="${user.userId}"> 
					
                </div>
                <br/>
                 <div>
                    <label for="userName">用户姓名：</label>
                    <input type="text" name="userName" id="userName" value="${user.userName}"> 
					
                </div>
                <br/>
                <div>
                    <label for="userSex">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：&nbsp;&nbsp;</label>
                  <select name="userSex">
		           <c:if test="${fo==1}">
	                  <option value="男" check="checked">男</option>
	                  <option value="女">女</option>
	              </c:if>
	              <c:if test="${fo==2}">
	                   <option value="男">男</option>
	                   <option value="女" check="checked">女</option>
	             </c:if>>
                </select> 

                </div>
                <br/>
                <div>
                    <label for="time">注册时间：${user.userTime}</label>
                    <input type="hidden" name="userTime" id="userTime" value="${user.userTime}"> 
					
                </div>
                <br/>
                <div>
                    <label for="area">房屋面积：</label>
                    <input type="text" name="userHouseArea" id="area" value="${user.userHouseArea}"> 
					
                </div>
                <br/>
                <div>
                    <label for="phone">联系方式：</label>
                    <input type="text" name="userPhone" id="phone" value="${user.userPhone}"> 
					
                </div>
                <br/>
                <div class="providerAddBtn">
                    <input type="submit" name="add" id="add" value="修改" >
                </div>
            </form>
        </div>
</div>
</section>
</body>
</html>