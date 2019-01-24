<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<c:if test="${by==1}">
 <script type="text/javascript">

      alert("密码为空或新密码不同");

</script>
</c:if>
<c:if test="${by==2}">
 <script type="text/javascript">

      alert("修改失败，原密码错误！");

</script>
</c:if>
<c:if test="${by==3}">
 <script type="text/javascript">

      alert("修改失败，请重新修改！");

</script>
</c:if>
<c:if test="${by==4}">
 <script type="text/javascript">
      alert("修改成功，请重新登录！");
</script>
</c:if>
<body bgcolor="d3eaea">
<div class="right">
<div class="right">
<br>
        <div class="providerAdd">
            <form id="adminForm" name="adminForm" method="post" 
            action="changeAdminPassword.html"
            enctype="multipart/form-data" 
            >
				<br>
                <!--div的class 为error是验证错误，ok是验证成功-->
                <div>
                    <label for="adminName">当前密码：</label>
                    <input type="password" name="oldpwd" id="adminName" value=""> 
					<font color="red"></font>
                </div>
                <br>
                <div>
                    <label for="adminPassword">新&nbsp;&nbsp;密&nbsp;&nbsp;码：</label>
                    <input type="password" name="newpwd" id="adminPassword" value=""> 
					<font color="red"></font>
                </div>
                <br>
                <div>
                    <label for="phone">确认密码：</label>
                    <input type="password" name="newpwdag" id="phone" value=""> 
					<font color="red"></font>
                </div>
                <br>
                <div class="providerAddBtn">
                    <input type="submit" name="add" id="add" value="修改" >
                </div>
            </form>
        </div>
</div>
</div>
</body>
</section>
