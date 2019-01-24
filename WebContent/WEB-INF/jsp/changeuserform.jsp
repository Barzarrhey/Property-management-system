<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
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
<body bgcolor="d3eaef">
<c:if test="${bys==1}">
 <script type="text/javascript">

      alert("密码为空或新密码不同");

</script>
</c:if>
<c:if test="${bys==2}">
 <script type="text/javascript">

      alert("修改失败，原密码错误！");

</script>
</c:if>
<c:if test="${bys==3}">
 <script type="text/javascript">

      alert("修改失败，请重新修改！");

</script>
</c:if>

<div class="right">
<div class="right">
        <div class="providerAdd">
            <form id="userForm" name="userForm" method="post" 
            action="changeUserPassword.html"
            enctype="multipart/form-data" 
            >
				
                <br/>
                <!--div的class 为error是验证错误，ok是验证成功-->
                <table class="providerTable" width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
                <div>
                    <label for="userName">当前密码：</label>
                    <input type="password" name="oldpwd" id="userName" value=""> 
					<font color="red"></font>
                </div>
                <br/>
                <div> 
                    <label for="userPassword">新&nbsp;&nbsp;密&nbsp;&nbsp;码：</label>
                    <input type="password" name="newpwd" id="userPassword" value=""> 
					<font color="red"></font>
                </div>
                <br/>
                <div>
                    <label for="phone">确认密码：</label>
                    <input type="password" name="newpwdag" id="phone" value=""> 
					<font color="red"></font>
                </div>
                <br/>
                <div class="providerAddBtn">
                
                    <input type="submit" name="add" id="add" value="确认修改" >
                </div>
                </table>
            </form>
        </div>
</div>
</section>
</body>