<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script defer src="test.js"></script>
<title>Insert title here</title>
</head>
<body>
<%
String inc = "temp.html";
if(request.getParameter("inc") != null) {
	inc = request.getParameter("inc");
}

String sessionId = (String)request.getAttribute("sessionId");
%>
<div>
	<%if(sessionId == null) { %>
		<a href='#'>로그인</a>
		<input type='button' name='btnTest'/>
	<%} else { %>
		[<%=sessionId %> 님 방가]
		<a href='#'>로그아웃</a>
	<%} %>
</div>

<form name='frm' method='post'>
	<input type='text' name='id' value='a003'/>
	<input type='password' name='pwd' autocomplete='off'/>
	<input type='button' name='btnLogin' id='btnLogin' value='로그인'/>
</form>

<div>
	<jsp:include page="<%=inc %>"/>
</div>

</body>
</html>