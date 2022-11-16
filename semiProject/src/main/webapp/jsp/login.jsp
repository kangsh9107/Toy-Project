<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>jsp/login.jsp</title>
<link rel="stylesheet" href="css/login.css">
<script defer src="js/login.js"></script>
</head>
<body>

<%
if(request.getParameter("alert") != null) {
	out.print("<script>");
	out.print("    alert('아이디와 비밀번호를 확인해주세요.')");
	out.print("</script>");
}

if(request.getParameter("signup") != null) {
	out.print("<script>");
	out.print("alert('회원가입 완료!')");
	out.print("</script>");
}
%>

<form name="ChoiLoginFrm" method="post">
	<div class="container">
		<div class="top">
			<h1 id="title" class="hidden">인재몰</h1>
		</div>
		<div class="login-box animated fadeInUp">
			<div class="box-header">
				<h2>Log In</h2>
			</div>
			<label for="username">ID</label>
			<br/>
			<input type="text" name="id" id="username">
			<br/>
			<label for="password">PASSWORD</label>
			<br/>
			<input type="password" name="pwd" id="password" autocomplete="off">
			<br/>
			<button type="button" onclick="login()">로그인</button>
			<button type="button" onclick="moveSignup()">회원가입</button>
			<br/>
			<a href="#"><p class="small">비밀번호를 잊어버리셨습니까?</p></a>
		</div>
	</div>
	
	<!-- Temp -->
	<input type="hidden" name="findStr" value=""/>
	<input type="hidden" name="nowPage" value="1"/>
	<input type="hidden" name="category" value=""/>
</form>

</body>
</html>