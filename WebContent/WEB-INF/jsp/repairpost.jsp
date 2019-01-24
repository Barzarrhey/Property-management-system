<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
.STYLE10 {color: #000000; font-size: 12px; }
.STYLE19 {
	color: #344b50;
	font-size: 12px;
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
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/yangshi.css" />
</head>
<body bgcolor="d3eaef">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="${pageContext.request.contextPath }/static/images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="STYLE1"> 填写报修表</span></td>
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

    <form action="${pageContext.request.contextPath}/submit.do"
        method="post">
        <table>
            <tr height="36">
                <td>房间号:</td>
                <td><input type="text"  name="userId"></td>
            </tr>
            <tr height="36">
                <td>物品:</td>
                <td>
                	<select name="resId"> 
					<option value="1">卧室窗</option> 
					<option value="2">卧室门</option>
					<option value="3">客厅门</option> 
					<option value="4">客厅窗</option>
					</select> 
                </td>
            </tr>
            <tr height="36">
                <td  valign="top">具体说明:<br/>(50字以内)</td>
                <td><textarea name="reason" rows="4" cols="21" style="OVERFLOW:hidden" autofocus id="area"  onkeydown="sy()" maxlength="50" placeholder="只能输入五十个字"></textarea>
                
                  <span>你还可以输入:<strong id="span" >50</strong>个字</span>

  <script type="text/javascript">

     function sy() {

       var num=document.getElementById("area").value.length;

       //console.log(num);

       var sheng=50-num;

       if(sheng==0){

         //变颜色为红色

         console.log(sheng);

         document.getElementById("span").style.color="#ff0000";

       }else{

         //变颜色为黑色

         document.getElementById("span").style.color="#000000";

       }

       document.getElementById("span").innerHTML=sheng;

     }
  </script>
                </td>
            </tr>
            <tr height="36">
            	<td>报修日期:</td>
                <!-- 直接使用，只要改name和id，改成类的属性 -->
                <td>
                	<%
					Date d = new Date();
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String now = df.format(d);
					%>
					<%=now %>
					<input type="hidden"  name="subDate" value="<%=now %>">
				</td>
            </tr>
            <tr height="36">
                <td colspan="2"><input type="submit" value="确认"></td>
            </tr>

        </table>
    </form>
</body>
</html>
 