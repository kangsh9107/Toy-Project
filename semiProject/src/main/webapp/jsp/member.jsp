<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="VIEWPORT" content="width=device-width, initial-scale=1.0">
<title>jsp/member.jsp</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<link defer rel="stylesheet" href="css/admin_member.css">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<script defer src="js/admin_member.js"></script>
</head>
<body>

<div id='baik_member_view'>
	<h3>회원정보 조회</h3>
	<form name='frm_member_search' id='search' method='post'>
		<input type='search' name='findStr' value="${pageVo.findStr }"/>
		<input type='button' value='입력' name='btnMemberSearch'/>
	</form>
	<ul>
		<li class='title'> <!-- 타이틀 -->
			<span class='id'>아이디</span>
			<span class='name'>성명</span>
			<span class='gender'>성별</span>
			<span class='age'>나이</span>
			<span class='postalCode'>우편번호</span>
			<span class='address1'>주소</span>
			<span class='address2'>상세주소</span>
			<span class='phone'>연락처</span>
			<span class='email'>이메일</span>
		    <span class='point'>마일리지</span>
		</li>
		
		<c:forEach var='v' items="${list }" varStatus='status'>
		<li class='item' onclick="memberView('${v.id}')">
				<span class='id'>${v.id }</span>
				<span class='name'>${v.name }</span>
				<span class='gender'>${v.gender }</span>
				<span class='age'>${v.age }</span>
				<span class='postalCode'>${v.postalCode }</span>
				<span class='address1'>${v.address1 }</span>
				<span class='address2'>${v.address2 }</span>
				<span class='phone'>${v.phone }</span>
				<span class='email'>${v.email }</span>
				<span class='point'>${v.point }</span>
		</li>
		</c:forEach>
	  </ul>
		<!-- PageButton -->
		<div class="btn-toolbar" style="justify-content: center;" role="toolbar" aria-label="Toolbar with button groups">
		<c:if test="${pageVo.startPage > 1 }"> 
		   <div class="btn-group me-2" role="group" aria-label="First group">
		      <button type="button" class="btn btn-outline-light" onclick='movePage(1)'  
		              style="background-color: rgb(183, 154, 113);">Start</button>
		      <button type="button" class="btn btn-outline-light" onclick= 'movePage(${pageVo.startPage - 1})' 
		              style="background-color: rgb(183, 154, 113);"></button>
		   </div>
	    </c:if>   
		   <c:forEach var='i' begin='${pageVo.startPage }' end='${pageVo.endPage }'>
		   <div class="btn-group me-2" role="group" aria-label="Second group">
		      <button type="button" class="btn btn-outline-light" onclick='movePage(${i })'
		              style="background-color: rgb(183, 154, 113);">${i }</button>
		   </div>
		   </c:forEach>
		   <c:if test="${pageVo.endPage lt pageVo.totPage }">
		   <div class="btn-group" role="group" aria-label="Third group">
		      <button type="button" class="btn btn-outline-light" onclick='movePage(${pageVo.endPage + 1})'
		              style="background-color: rgb(183, 154, 113);">></button>
		      <button type="button" class="btn btn-outline-light" onclick='movePage(${pageVo.totPage })'
		              style="background-color: rgb(183, 154, 113);">End</button>
		   </div>
		   </c:if>
		</div>
	
</div>

</body>
</html>