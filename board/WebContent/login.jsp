<%@ page contentType="text/html;charset=utf-8" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>게시판 글 작성</title>
<link href="mystyle.css" rel="stylesheet" type="text/css">
<style>
table {
  width: 30%;
  margin: auto;
  padding : 10px;  
  text-align:center;
   background-color : #e0ffff;
  border-color     : #b0e0e6;
  border-style     : dotted;
}
 
</style>
</head>
<body>
<h3 id="header">로그인</h3>

<div id='menu'>
<%@ include file="mymenu.jsp" %>
</div>

<div id="main">
<h3 style="text-align : center;"> 로그인</h3>
	<div  id="confirmed" style="text-align : center;">
		<form method='post' action='./login.do'  >
		 <table >
		     <tr>
		       <td style="width:100;text-align:left;">아이디</td>
		       <td><input type="text" id="userid" name="userid" size="20" maxlength="15"/></td>
		      </tr>
		     <tr>
		      <td style="width:100;text-align:left;">비밀번호</td>
		        <td><input type="password" id="userpwd" name="userpwd" size="20" maxlength="15"/></td>
		        </tr>
		        <tr><td colspan="2" align="center">
		        <input type="submit" id="login" value="로그인" />&nbsp;&nbsp;
		        <a href="member.jsp"  ><span style="font-size:small">회원가입</span></a>&nbsp;&nbsp;<a href=""><span style="font-size:small;">아이디/비밀번호 찾기</span></a></td>
		        </tr>
		      </table>
		</form>
	</div>
</div>


</body>
</html>
