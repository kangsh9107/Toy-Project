<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>login</title>
<link rel="stylesheet" href="css/login.css">
<script defer src="js/login.js"></script>
</head>
<body>
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
			<button type="button" onclick="checkLogin()">로그인</button>
			<button type="button" onclick="moveSignup()">회원가입</button>
			<br/>
			<a href="#"><p class="small">비밀번호를 잊어버리셨습니까?</p></a>
		</div>
	</div>
</form>
</body>
</html>