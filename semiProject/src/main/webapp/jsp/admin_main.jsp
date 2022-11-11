<%@page import="java.util.List"%>
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
		<table>
			<tr>
				<td class="td1" >TODAY TOTAL PRICE</td><td class="td2"><input type="text" name="today" value="1,654,000" readOnly/></td>
			</tr>
			<tr>
				<td class="td1" >WEEKLY TOTAL PRICE</td><td class="td2"><input  type="text" name="weekly" value="9,640,210" /></td>
			</tr>
			<tr>
				<td class="td1" >MONTLY TOTAL PRICE</td><td class="td2"><input type="text" name="monthly" value="24,800,765" /></td>
			</tr>
		</table>
	</div>
	
<!-- work view -->
	<div class="b_div">
		<div>
		<h4>오늘 방문자수</h4>
		</div>
		<div>
		<h4>오늘 주문건수</h4>
		</div>
		<div>
		<h4>오늘 주문상태</h4>
		<br>
		   <ul>
		      <li>입금완료 <input type='text' id='status1' readOnly></li><br>
		      <li>배송중 &nbsp&nbsp&nbsp<input type='text' id='status1'  readOnly></li><br>
		      <li>배송완료 <input type='text' id='status1' readOnly></li><br>
		      <li>환불요청 <input type='text' id='status1' readOnly></li>
		   </ul>
		</div>
	</div>
</div>
</body>
</html>