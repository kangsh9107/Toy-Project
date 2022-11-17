<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp/mymain.jsp</title>
<link rel="stylesheet" href="css/mymain.css">

</head>
<body>
<!-- 정보 수정완료후 수정완료alert 나오기전에 mymain.jsp로 포워딩되어서 알러트창이 뜨지 않는 에러 디버깅 위해 아래 코드를 입력 -->
<c:set var="c" value="${ch}" scope="request"/>
<c:if test="${c eq 'ch'}"> 
	<Script>
	 alert("회원정보가 수정되었습니다.");
	</Script>
</c:if>

<!-- -------------------------------------- -->

<div id = "mymainchowrap" style="width:1000px;">
	<div id = mymain_info>
		<div id = "mymain_text">
			<c:set var = "v" value="${mpmmVo}" scope='request'/>
			<div id = "mymain_name">
				<c:choose>
					<c:when test= "${v.totalprice<200000}"><span class = "mymain_name2"style="background-color:#A4653E;">${v.name} 님</span></c:when>
					<c:when test= "${v.totalprice>=200000 && v.totalprice<500000}"><span class = "mymain_name2" style="background-color:#9C9EA2;">${v.name} 님</span></c:when>
					<c:when test= "${v.totalprice>=500000 && v.totalprice<1000000}"><span class = "mymain_name2" style="background-color:#D8B250;">${v.name} 님</span></c:when>
					<c:when test= "${v.totalprice>=1000000}"><span class = "mymain_name2" style="background-color:#141414;">${v.name} 님</span></c:when>
				</c:choose>
				<c:choose>
					<c:when test= "${v.totalprice<200000}"><span class = "mymain_name2" id = "mymain_gradeinput" style="background-color:#A4653E;">BRONZE 등급</span></c:when>
					<c:when test= "${v.totalprice>=200000 && v.totalprice<500000}"><span class = "mymain_name2" style="background-color:#9C9EA2;">SILVER 등급</span></c:when>
					<c:when test= "${v.totalprice>=500000 && v.totalprice<1000000}"><span class = "mymain_name2" style="background-color:#D8B250;">GOLD 등급</span></c:when>
					<c:when test= "${v.totalprice>=1000000}"><span class = "mymain_name2" id = "mymain_gradeinput" style="background-color:#141414;">VIP 등급</span></c:when>
					
				</c:choose>
			</div>
			
				<div  id = "mymain_point" >
					<span class = "mymain_point2">마일리지</span>
					<span class = "mymain_point2">${v.point} 점</span>
				</div>
				<div  id = "mymain_totalprice">
					<span class = "mymain_totalprice2">총 구매금액</span>
               		<fmt:formatNumber value ="${v.totalprice}" var ='su' pattern="#,###,###"/>
					<span class = "mymain_totalprice2">${su} 원</span>
				</div>
				<div  id = "mymain_odercount" >
					<span class = "mymain_odercount2">총 구매건수</span>
					<span class = "mymain_odercount2">${v.countorder} 건</span>
				</div>
			
		</div>
	</div>	
	<div id = "mymain_img">
		<div id = "mymain_img_text">
		*총 구매금액별 등급안내
		</div>
		<img id = "mymain_chowrap_gradeimg" src = "img/mymain_grade2.png">
	</div>
</div>
</body>
</html>