<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp/admin.jsp</title>
<link rel="stylesheet" href="css/admin.css">
</head>
<body>
<%
String inc2 = "admin_main.jsp";
if(request.getParameter("inc2") != null) {
	inc2 = request.getParameter("inc2");
}
%>
<main class="baik_main">
	<div class='baik_admin_list'>
	   <div class='list_name'>관리자 페이지</div>
	   <ul class="list">
	      <a href='index.jsp?inc1=jsp/admin.jsp&inc2=signup.jsp'><li>관리자 홈</li></a>
	      <a href='action.admin'><li>회원관리</li></a>
	      <a href='index.jsp?inc1=jsp/admin.jsp&inc2=order.jsp'><li>주문관리</li></a>
	      <a href='index.jsp?inc1=jsp/admin.jsp&inc2=product.jsp'><li>상품관리</li></a>
	      <a href='index.jsp?inc1=jsp/admin.jsp&inc2=graph.jsp'><li>통계</li></a>
	   </ul>
	</div>
	
	<div class='admin_center'>
	   <jsp:include page="<%=inc2 %>"/>
	</div>
</main>
</body>
</html>