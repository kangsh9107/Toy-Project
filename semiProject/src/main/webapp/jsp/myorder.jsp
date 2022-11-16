<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<script defer src="js/mypage_order.js"></script>

<title>jsp/myorder.jsp</title>
</head>
<body>

	<div id='seolMyOrder'>
		<div>
			<h4>
			주문정보확인
			</h4>
			<ul id='seolMyorder'>
				 <form name="frm_refund" method="post">
				 	<input type='hidden' name='nowPage' value="${pageVo.nowPage}"/>
				 	<input type='search' name='findStr' value="${pageVo.findStr}"/>
				 	<input type='button' value='검색' id='btnSearch' name='btnSearch'/>
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
	             	  <li class='item' id='seolMyorderDetail' onclick="showOrderDetails('${v.orderNumber}')" >
	             	  	  <fmt:formatNumber value = "${v.price}" var="vp" pattern="#,###"/>
		                  <span class=no >${status.count }</span>
		                  <span class=category>${v.category }</span>
		                  <span class=serial>${v.SERIAL }</span>
		                  <span class=price>${vp}</span>
		                  <span class=orderDate>${v.orderDate }</span>
		    			  <c:choose>
							<c:when test='${v.status eq 1}'><span class=status>입금완료</span> </c:when>
							<c:when test='${v.status eq 2}'><span class=status>배송중</span> </c:when>
							<c:when test='${v.status eq 3}'><span class=status>배송완료</span> </c:when>
							<c:when test='${v.status eq 4}'><span class=status>환불요청</span> </c:when>
						  </c:choose>
		                  <span class=refund><input type='button' name="btnRefund" id="btnRefund" value='환불요청' onclick='event.stopPropagation(refund("${v.orderNumber}"));'></span>
					  </li>
	            	</c:forEach>
             	  </form>
             	  <!-- 앵커태그를 통해 myJob=myRefund 와 v.ordernumber를(c:forEach 로 만들어진) 서블릿으로 보내서 작업하는 경우(앵커태그css추가작업필요함)
					<c:forEach var='v' items="${list}" varStatus='status'>
	             	  <li class='item' id='seolMyorderDetail'>
		                  <span class=no >${status.count }</span>
		                  <span class=category>${v.category }</span>
		                  <span class=serial>${v.SERIAL }</span>
		                  <span class=price>${v.price}</span>
		                  <span class=orderDate>${v.orderDate }</span>
		    			  <c:choose>
							<c:when test='${v.status eq 1}'><span class=status>입금완료</span> </c:when>
							<c:when test='${v.status eq 2}'><span class=status>배송중</span> </c:when>
							<c:when test='${v.status eq 3}'><span class=status>배송완료</span> </c:when>
							<c:when test='${v.status eq 4}'><span class=status>환불요청</span> </c:when>
						  </c:choose>
		                    <span class=refund><a href ="myPage?myJob=myRefund&ordernumber=${v.orderNumber}">환불요청</a></span>
	             	  </li>
	            	</c:forEach>
             	   -->
			</ul>
		</div>
		<div class="btn-toolbar" style="justify-content:center;" role="toolbar" aria-label="Toolbar with button groups">
			<c:if test="${pageVo.startPage>1}">
		        <div class="btn-group me-2" role="group" aria-label="First group">
		          <button type="button" class="btn btn-outline-light" style="background-color: rgb(183,154,113);" onclick='movePage(1)'>처음</button>
		          <button type="button" class="btn btn-outline-light" style="background-color: rgb(183,154,113);" onclick='movePage(${pageVo.startPage-1})'>이전</button>
		        </div>
	        </c:if>
		        <div class="btn-group me-2" role="group" aria-label="Second group">
	        <c:forEach var='i' begin='${pageVo.startPage }' end='${pageVo.endPage }'>
		          <button type="button" class="btn btn-outline-light" style="background-color: rgb(183,154,113);" onclick = 'movePage(${i})'>${i}</button>
	        </c:forEach>
		        </div>
	        <c:if test="${pageVo.getEndPage() < pageVo.getTotPage() }">
		        <div class="btn-group" role="group" aria-label="Third group">
		          <button type="button" class="btn btn-outline-light" style="background-color: rgb(183,154,113);" onclick='movePage(${pageVo.endPage+1})'>다음</button>
		          <button type="button" class="btn btn-outline-light" style="background-color: rgb(183,154,113);" onclick='movePage(${pageVo.totPage})'>맨끝</button>
		        </div>
		    </c:if>
      </div>
		
	</div>
</body>
</html>