<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	  rel="stylesheet"
	  integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	  crossorigin="anonymous">
<link href="css/index.css" rel="stylesheet">
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<!-- JavaScript -->
<script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
		crossorigin="anonymous"></script>
<script defer src="js/index.js"></script>
<title>index.jsp</title>
</head>
<body>
<%
String inc1 = "jsp/main.jsp";

if(request.getParameter("inc1") != null) {
	inc1 = request.getParameter("inc1");
}

if(request.getParameter("buy2") != null) {
	out.print("<script>");
	out.print("    alert('구매 완료💕')");
	out.print("</script>");
}

%>

<!-- Navigation-->
<div id="kangNavWrap">
	<div class="kangNavInner">
		<nav class="navbar navbar-expand-lg">
			<div class="container px-4 px-lg-5">
				<a id="choiLogoWrap" class="navbar-brand" href="index.jsp">
					<img class="choiLogo" src="img/logo.gif" width="100">
				</a>
				<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
						data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
						aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
						<li class="nav-item"><a class="nav-link active" aria-current="page" href="index.jsp">Home</a></li>
						<li class="nav-item"><a class="nav-link" href="action.kang?job=select&category=outer&findStr=''">Outer</a></li>
						<li class="nav-item"><a class="nav-link" href="action.kang?job=select&category=top&findStr=''">Top</a></li>
						<li class="nav-item"><a class="nav-link" href="action.kang?job=select&category=bottom&findStr=''">Bottom</a></li>
						<li class="nav-item"><a class="nav-link" href="action.kang?job=select&category=shoes&findStr=''">Shoes</a></li>
						<li class="nav-item"><a class="nav-link" href="action.kang?job=select&category=acc&findStr=''">Acc</a></li>
					</ul>
					<form class="d-flex" name="kangIndexFrm" method="post">
						<c:choose>
							<c:when test="${sessionId eq null }">
								<a class="btn btn-outline-light kangBtnLogin" href="action.kang?job=moveLoginForm">
									Login
								</a>
							</c:when>
							<c:when test="${sessionId ne null && sessionId ne 'admin' }">
								<i class="bi-cart-fill me-2"></i>
								<a class="btn btn-outline-light kangBtnMyPage" href="myPage?myJob=myMain">
									MyPage
								</a>
							</c:when>
							<c:when test="${sessionId eq 'admin' }">
								<i class="bi-cart-fill me-2"></i>
								<a class="btn btn-outline-light kangBtnAdminPage" href="action.admin?job=adminMain">
									AdminPage
								</a>
							</c:when>
						</c:choose>
						<c:if test="${sessionId ne null }">
							<i class="bi-cart-fill me-2"></i>
							<a class="btn btn-outline-light kangBtnLogout" href="action.kang?job=logout">
								Logout
							</a>
						</c:if>
					</form>
				</div>
			</div>
		</nav>
	</div>
</div>

<!-- main -->
<div id="kangMainWrap">
	<div class="kangMain">
		<jsp:include page="<%=inc1 %>"/>
	</div>
</div>

<!-- Footer -->
<div id="kangFooterWrap">
	<footer class="bg-dark text-center text-white kangFooter">
		<div class="container p-4">
			<section class="mb-4">
				<p>SEMI PROJECT</p>
			</section>
			<!-- FooterLinks -->
			<section class="">
				<div id="kangFooterLinksWrap" class="row"></div>
			</section>
		</div>
		<!-- Copyright -->
		<div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2);">
			<p class="m-0 text-center text-white">Copyright &copy; 2022 인재몰</p>
		</div>
	</footer>
</div>

</body>
</html>