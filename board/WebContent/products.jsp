<%@page import="java.util.Vector"%> 
<%@ page contentType="text/html;charset=utf-8" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html><head><title>상품 목록</title>
<script>
 
</script>
</head>
<link href="mystyle.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="bootstrap.min.css" />
<body>
<c:if test='${empty user}'>
 <div style ='text-align:right'>
  <a href="./login.jsp" style="font-size:small">로그인</a>&nbsp;&nbsp;&nbsp;&nbsp;
  <a href="./member.jsp" style="font-size:small">회원가입</a>&nbsp;&nbsp;&nbsp;&nbsp;
  </div>
</c:if>

<c:if test='${not empty user}'>
 <div style ='text-align:right'>
 ${user.username}(${user.userid})님 로그인 중 &nbsp;&nbsp;&nbsp;&nbsp;
 <a href='./member_edit.jsp' style="font-size:small">회원 정보 수정</a> &nbsp;&nbsp;&nbsp;
 </div>
</c:if>

<h3 id="header">상품 목록</h3>

<div id='menu'>
<%@ include file="mymenu.jsp" %>
</div>

<div id="main">	
 <div class="container">
   <div class="row" align="center">
<c:forEach var="products" items="${headers}">
	<div class="col-md-4">			
		<img src = ./image/${products.filename} width="100%" >
		<h3>${products.pname} </h3>
		<p>${products.description}</p>
		<p>${products.unitPrice}원</p>
		<p><a href="./productview.do?proid=${products.productId}" class="btn btn-secondary" role="button">상세정보&raquo;</a></p>
		
  </div>

</c:forEach>
	<hr>
	</div>		
 </div>

</div>
</body>
</html>