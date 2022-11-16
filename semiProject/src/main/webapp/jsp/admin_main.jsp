<%@page import="java.util.List"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="VIEWPORT" content="width=device-width, initial-scale=1.0">
<title>jsp/admin_main.jsp</title>
<link rel="stylesheet" href="css/admin_main.css">
</head>
<body>
<!-- 총 매출 출력 부분 -->
<div class="Hwang_admin_main">
   
	<div class="t_div">
		<span id= text_span>
			<span id="to_text">TODAY TOTAL PRICE</span><span><fmt:formatNumber value="${vo.today_tot}" pattern="#,###"/>₩</span><br/>
			<span id="we_text">WEEKLY TOTAL PRICE</span><span><fmt:formatNumber value="${vo.weekly_tot}" pattern="#,###"/>₩</span><br/>
			<span id="mo_text">MONTHLY TOTAL PRICE</span><span><fmt:formatNumber value="${vo.monthly_tot}" pattern="#,###"/>₩</span>
		</span>

	</div>
	
<!-- work view -->
	<div class="b_div">
		<div class="vi_view">
			<h4>오늘 방문자수</h4>
			<span id="today_visit">${vo.today_visit}</span>
		</div>
		<div class="od_view">
			<h4>오늘 주문건수</h4>
			<span id="today_orders">${vo.today_orders}</span>
		</div>
		<div class="od_sta">	
			<h4>오늘 주문상태</h4><br/>
	        <ul class="order_status">
		   		<span id="text_status1">입금완료 &nbsp&nbsp&nbsp&nbsp${vo.status1 }</span><br/>
		   		<span id="text_status1">배송중 &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp${vo.status2 }</span><br/>
		   		<span id="text_status1">배송완료 &nbsp&nbsp&nbsp&nbsp${vo.status3 }</span><br/>
		   		<span id="text_status4">환불요청 &nbsp&nbsp&nbsp&nbsp${vo.status4 }</span>   		
		   </ul>
		</div>
	</div>
</div>
</body>
</html>