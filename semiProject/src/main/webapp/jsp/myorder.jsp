<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<link href="css/myorder.css" rel="stylesheet">
<script defer src='js/mypage_order.js'></script>

<title>jsp/myorder.jsp</title>
</head>
<body>
	<div id='seolMyOrder'>
		<div>
			<h4>
			주문정보확인
			</h4>
			<ul id='seolMyorder'>
				<li id='seolMyorderLi'>
					<span class='l1'>No</span>
					<span class='l2'>category</span>
					<span class='l3'>serial</span>
					<span class='l4'>price</span>
					<span class='l5'>orderDate</span>
					<span class='l6'>status</span>
					<span class='l7'>refund</span>
				</li>
	
				<c:forEach var='v' items="${list}" varStatus='status'>
              	 <form name='frm_myorderlist' method='post'>
             	  <li class='item' id='seolMyorderDetail'>
	                  <span class=no>${status.count }</span>
	                  <span class=category>${v.category }</span>
	                  <span class=serial>${v.SERIAL }</span>
	                  <span class=price>${v.price}</span>
	                  <span class=orderDate>${v.orderDate }</span>
	                  <span class=status>
	                 	 <c:choose>
					 	   <c:when test="${v.status eq '1'}">입금완료</c:when>
					 	   <c:when test="${v.status eq '2'}">배송 중</c:when>
					 	   <c:when test="${v.status eq '3'}">배송완료</c:when>
					 	   <c:when test="${v.status eq '4'}">환불대기</c:when>
						</c:choose>
	                  </span>
	                  <span class=refund><input type='button' value='환불요청' id='btnRefund'>
	                  <input type='text' id='orderNumber' name='orderNumber' value='${v.orderNumber}' style='display:none'/>
	                  </span>
             	  </li>
             	  </form>
            	</c:forEach>
			</ul>
		</div>
		<div class="btn-toolbar" style="justify-content:center;" role="toolbar" aria-label="Toolbar with button groups">
	        <div class="btn-group me-2" role="group" aria-label="First group">
	          <button type="button" class="btn btn-outline-light" style="background-color: rgb(183,154,113);">1</button>
	          <button type="button" class="btn btn-outline-light" style="background-color: rgb(183,154,113);">1</button>
	        </div>
	        <div class="btn-group me-2" role="group" aria-label="Second group">
	          <button type="button" class="btn btn-outline-light" style="background-color: rgb(183,154,113);">1</button>
	          <button type="button" class="btn btn-outline-light" style="background-color: rgb(183,154,113);">1</button>
	          <button type="button" class="btn btn-outline-light" style="background-color: rgb(183,154,113);">1</button>
	        </div>
	        <div class="btn-group" role="group" aria-label="Third group">
	          <button type="button" class="btn btn-outline-light" style="background-color: rgb(183,154,113);">1</button>
	          <button type="button" class="btn btn-outline-light" style="background-color: rgb(183,154,113);">1</button>
	        </div>
      </div>
		
	</div>
</body>
</html>