<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<!-- JavaScript -->
<script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
		crossorigin="anonymous"></script>
<script defer src="js/category_detail.js"></script>
<title>jsp/category_detail.jsp</title>
</head>
<body>

<%
if(request.getParameter("buy1") != null) {
	out.print("<script>");
	out.print("    alert('재고가 부족합니다😢')");
	out.print("</script>");
}

if(request.getParameter("buy2") != null) {
	out.print("<script>");
	out.print("    alert('구매 완료💕')");
	out.print("</script>");
}
%>

<!-- findStr, nowPage -->
<form name="kangCategoryDetailFrm" method="post">
	<input type="hidden" name="findStr" value="${pageVo.findStr }"/>
	<input type="hidden" name="nowPage" value="${pageVo.nowPage }"/>
	<input type="hidden" name="category" value="${pageVo.category }"/>
	<!-- CategoryProductsDetail -->
	<section class="py-5">
		<div class="container px-4 px-lg-5 my-5">
			<div class="row gx-4 gx-lg-5 align-items-center">
				<c:forEach var="v" items="${list }" varStatus="status" begin="0" end="0">
					<input type="hidden" name="serial" value="${v.serial }"/>
					
					<!-- Product image 600x700 -->
					<div class="col-md-6">
						<img class="card-img-top mb-5 mb-md-0" src="img/${v.productName }.png" alt="${v.productName }.png"/>
					</div>
					<div class="col-md-6">
						<!-- Product serial -->
						<div class="small mb-1">SERIAL : ${v.serial }, STOCK : ${v.stock }</div>
						<!-- Product name -->
						<h3 class="display-5 fw-bolder">${v.productName }</h3>
						<!-- Product price -->
						<div class="fs-5 mb-5">
							<span>₩ <fmt:formatNumber value="${v.price }" pattern="#,###"/></span>
						</div>
						<!-- Product info -->
						<p class="lead">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s. It was popularised in the 1960s with the release. PageMaker including versions of Lorem Ipsum.</p>
						<!-- Buy -->
						<div class="d-flex">
							<input class="form-control text-center me-3" name="quantity" id="inputQuantity" value="1" style="max-width: 3rem" />
							<button class="btn btn-outline-dark" onclick="buyRightNow('${pageVo.findStr }', ${pageVo.nowPage }, '${pageVo.category }')">Buy</button>
							<i class="bi-cart-fill me-2"></i>
							<button class="btn btn-outline-dark">Add to cart</button>
							<i class="bi-cart-fill me-2"></i>
							<button class="btn btn-outline-dark" onclick="backToList('${pageVo.findStr }', ${pageVo.nowPage }, '${pageVo.category }')">Back to list</button>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>
</form>

</body>
</html>