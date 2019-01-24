<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 日历插件 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" 
src="${pageContext.request.contextPath }/static/calendar/WdatePicker.js"></script>
<!DOCTYPE html>
<c:if test="${pe==1}">
 <script type="text/javascript">

      alert("信息为空或信息不完整！");

</script>
</c:if>
<c:if test="${pe==2}">
 <script type="text/javascript">

      alert("添加失败，请重新添加！");

</script>
</c:if>
<c:if test="${pe==3}">
 <script type="text/javascript">

      alert("添加管理员成功，您管理员ID为："+<%=session.getAttribute("success") %>);

</script>
</c:if>
<body bgcolor="d3eaea">
<div class="right" >
<div class="right">
<br>
        <div class="location">
            <strong>请填入相关信息:</strong>
        </div>
        <br>
        <div class="providerAdd">
            <form id="adminForm" name="adminForm" method="post" 
            action="adminAdd.html"
            enctype="multipart/form-data" 
            >
				
                <!--div的class 为error是验证错误，ok是验证成功-->
                <div>
                    <label for="adminName"><td width="10%" height="35" bgcolor="d3eaef" class="STYLE6"><div ><span class="STYLE10">姓名：</td></label>
                    <input type="text" name="adminName" id="adminName" value=""> 
					<font color="red"></font>
                </div>
                <br>
                <div>
                    <label for="adminPassword"><td width="10%" height="35" bgcolor="d3eaef" class="STYLE6">密码：</td></label>
                    <input type="password" name="adminPassword" id="adminPassword" value=""> 
					<font color="red"></font>
                </div>
                <br>
                <div>
                    <label >用户性别：</label>
					<select name="adminSex" id="gender">
					    <option value="男" selected="selected">男</option>
					    <option value="女">女</option>
					 </select>
                </div>
                <br>
                <div>
                    <label for="phone">电话：</label>
                    <input type="text" name="adminPhone" id="phone" value=""> 
					<font color="red"></font>
                </div>
                
               
				<br>
                <div class="providerAddBtn">
                    <input type="submit" name="add" id="add" value="保存" >
                </div>
            </form>
        </div>
</div>
</section>
</body>