<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--<script defer src="js/mypage.js"></script>-->
<title>jsp/mypage.jsp</title>
<link rel="stylesheet" href="css/mypage.css">
</head>
<body>

<%
String incMY="mymain.jsp";		
if(request.getParameter("incMY") != null){	
	incMY = request.getParameter("incMY");	
}
	String sessionId = (String)session.getAttribute("sessionId");
%>
<div id="mypage_chowrap">
    <ul id="mypage_chonavi">
		<li id="mypage_chotitle">MY</li>
	    <li class="mypage_chosub"><a href='myPage?id=f0083&myJob=myPage'>회원정보</a></li>
	    <li class="mypage_chosub"><a href="myPage?id=f0083&myJob=showOrder">주문정보확인</a></li>
	    <li class="mypage_chosub"><a href='myPage?id=f0083&myJob=quit'>회원탈퇴</a></li>
    </ul>

</div>    

<div id='mypage_content'>
		<jsp:include page="<%=incMY %>"/>	<!-- content 부분에 inc를 불러줌 inc에는 여러 페이지들이 매개변수로 담겨져서 해당 매개변수로 담겨진 페이지들이 창에 띄워지게 된다. -->
		<!-- inc의 매개변수로 페이지가 담겨져서 창이 띄워질때 css적용 경로에대해서는 해당페이지가 띄워지는 창(index.jsp)을 기준으로 css파일 경로설정을해줘야한다. 이때 해당 창의 css는 단위페이지에서 코딩하지말고 띄워지는 창 즉 index.jsp 기준으로 보면서 css코딩을 해줘야하는 점 주의 해야한다.(단위페이지에서 보면서 코딩할경우 실제로 띄워지는 창에서 안맞게 나올수 있기 때문 -->
</div>
</body>
</html>