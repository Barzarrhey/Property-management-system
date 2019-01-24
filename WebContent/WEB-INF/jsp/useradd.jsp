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
<c:if test="${fl==2 }">

 <script type="text/javascript">

      alert("<%=mess%>");

</script>
</c:if>

<c:if test="${fl==1 }">

 <script type="text/javascript">

      alert("<%=mess%>");

</script>
</c:if>

<c:if test="${fla==1 }">

 <script type="text/javascript">

      alert("<%=mess%>");

</script>
</c:if>
<c:if test="${fla==-1 }">

 <script type="text/javascript">

      alert("<%=mess%>");

</script>
</c:if>

<c:if test="${fla==-1 }">

 <script type="text/javascript">

      alert("<%=mess%>");

</script>
</c:if>

<div class="right">
<div class="right">
        <div class="providerAdd">
            <form id="userForm" name="userForm" method="post" 
            action="useradd1.do"
            enctype="multipart/form-data" 
            >
				
                <!--div的class 为error是验证错误，ok是验证成功-->
                <br/>
                <div>
                    <label for="userName">用&nbsp;户&nbsp;名：&nbsp;&nbsp;</label>
                    <input type="text" name="name" id="userName" value=""> 
					
                </div>
                <br/>
                <div>
                    <label for="sex">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：&nbsp;&nbsp;</label>
                   <select name="sex">
	                  <option value="男">男</option>
	                  <option value="女">女</option>
                  </select> 

                </div>
                <br/>
                <div>
                    <label for="area">房屋面积：</label>
                    <input type="text" name="area" id="area" value=""> 
					
                </div>
                <br/>
                <div>
                    <label for="area">密&nbsp;&nbsp;&nbsp;&nbsp;码：&nbsp;&nbsp;&nbsp;&nbsp;</label>
                    <input type="password" name="password" id="password" value=""> 
					
                </div>
                <br/>
                <div>
                    <label for="area">联系方式：</label>
                    <input type="text" name="phone" id="phone" value=""> 
					
                </div>
                <br/>
                <div class="providerAddBtn">
                    <input type="submit" name="add" id="add" value="提交" >
                </div>
            </form>
        </div>
</div>
</section>
</body>
</html>