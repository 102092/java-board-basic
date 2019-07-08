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

<h3 id="header">상품 상세정보</h3>

<div id='menu'>
<%@ include file="mymenu.jsp" %>
</div>

<div id="main">	
 <div class="container">

   <div class="row" align="center">
      <div class="col-md-5">
      	<img src = ./image/${article.filename} width="100%" >
      </div>
		<div class="col-md-6">
		<h3>상품이름 ${article.pname} </h3>
		<p>상품설명 : ${article.description}</p>
		<p><b>상품 코드 : </b><span class="badge badge-danger"> ${article.productId} </span></p>
		<p><b>제조사</b> : ${article.manufacturer} </p>
		<p><b>분류</b> :  ${article.category} </p>
		<p><b>재고 수</b> :  ${article.unitsInStock} </p>
		<h4>가격 ${article.unitPrice} 원</h4>
		<p><form name="addForm" action="./addCart.jsp?prodid= " method="post">
		<a href="#" class="btn btn-info" onclick="addToCart()"> 상품 주문 &raquo;</a>
		<a href=" " class="btn btn-warning"> 장바구니 &raquo;</a> 
		<a href=" " class="btn btn-secondary"> 상품 목록 &raquo;</a>
		</form>			
  		</div>
	</div>

	<hr>
	</div>		
 </div>

</div>
</body>
</html>