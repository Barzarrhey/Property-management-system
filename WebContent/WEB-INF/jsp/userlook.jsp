<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:if test="${bys==4}">
 <script type="text/javascript">

      alert("修改成功");

</script>
</c:if>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="d3eaef">


<c:if test="${admin.type==2}">
        <br/><br/>
		用户编号：${admin.userId }<br/><br/>
		用户角色：业主 <br/><br/>
		用户姓名：${admin.userName }<br/><br/>
		联系方式：${admin.userPhone }<br/><br/>
		用户入住时间：${admin.userTime}<br/><br/>
		房屋面积：${admin.userHouseArea }<br/><br/>
		用户密码：${admin.userPassword }<br/><br/>
		
	</c:if>

</body>
</html>