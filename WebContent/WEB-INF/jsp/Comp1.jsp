<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<script> 
function show(){ 
var date = new Date(); //日期对象 
var now = ""; 
now = date.getFullYear()+"/"; //读英文就行了 
now = now + (date.getMonth()+1)+"/"; //取月的时候取的是当前月-1如果想取当前月+1就可以了 
now = now + date.getDate()+"     "; 
now = now + date.getHours()+":"; 
now = now + date.getMinutes()+":"; 
now = now + date.getSeconds(); 
document.getElementById("nowDiv").innerHTML = now; //div的html是now这个字符串 
setTimeout("show()",1000); //设置过1000毫秒就是1秒，调用show方法 
} 
</script> 
<body onload="show()" bgcolor="d3eaef">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="6%" height="40" ><div align="center"><img src="${pageContext.request.contextPath }/static/images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" ><span class="STYLE1"> 投诉</span></td>
              </tr>
              </table>
            </td>
          </tr>
        </table>
        </td>
       </tr>
     </table>
     </td>
   </tr>
</table>

    <form action="${pageContext.request.contextPath}/compsubmit.do"
        method="post">
        <table width="500" height="300">
            <tr >
                <td width="20%"><div align="center">业主房号:</div></td>
                <td ><input type="text"  name="userId" style="font-size:29px"></td>
            </tr>
            <tr>
                <td><div align="center">物管编号:</div></td>
                <td><input type="text"  name="adminId" style="font-size:29px"></td>
            </tr>
            <tr>
                <td><div align="center">投诉原因:</div></td>
                <td><input type="text"  name="reason" style="font-size:29px"></td>
            </tr>
            <tr>
            	<td><div align="center">投诉日期:</div></td>
                <!-- 直接使用，只要改name和id，改成类的属性 -->
                <td style="font-size:29px">
                	<div id="nowDiv" ></div>
                	<%
						Date d = new Date();
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						String now = df.format(d);
					%> 					 
					<input type="hidden"  name="subDate" value="<%=now %>">
				</td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="确认"></td>
            </tr>

        </table>
    </form>
</body>
</html>
 