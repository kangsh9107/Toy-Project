<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp/order_modify.jsp</title>
<link rel='stylesheet' href='css/order_modify.css'>
<script defer src="js/admin_modify.js"></script>
</head>
<body>

<div id='admin_input' class='admin_input'>
<form name="frm_user" action="doJoin" method="POST" class="joinForm">
      <h1 id='csc'>인재몰</h1>                                                                 
      <h2>주문정보 수정</h2>
      
      <div class="textForm">
      <label>아이디</label>
        <input name="Id" type="text" class="id" placeholder="id" value='${vo.id }' readOnly/>
      </div>
     
      <div class="textForm">
      <label>카테고리</label>
        <input name="category" type="text" size='35' value='${vo.category }'/>
      </div>
      <div>
      	<label>상품번호</label>
        <input name="serial" type="text" class="serial" value='${vo.serial }'>
      </div>
        <div class="textForm">
        <label>가격</label>
        <input name="price" type="text" class="price" value='${vo.price }'>
      </div>
      <div class="textForm">
        <label>주문번호</label>
        <input name="orderNumber" type="text" class="orderNumber" value='${vo.orderNumber}' }>
      </div>
      <div class="textForm">
      	<label>주문날짜</label>
        <input name="orderDate" type="text" class="orderDate" value='${vo.orderDate }'>
      </div>
      <div class="textForm">
      	<label>주문상태</label>
		<input name="status" type="text" class="status" value='${vo.status }'>     
      </div>
      
      <div class='btnZone'>
      	<span></span>
      	<input type='button' value='수정' id='btnModify' />
        <input type='button' value='환불 수락' id='btnDelete' />
        <input type='button' value='목록' id='btnSelect' />
      </div>
    </form>
</div>
</body>
</html>