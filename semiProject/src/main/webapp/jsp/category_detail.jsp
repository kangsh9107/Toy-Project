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
<title>jsp/category_detail.jsp</title>
</head>
<body>

<!-- CategoryProductsDetail -->
<section class="py-5">
	<div class="container px-4 px-lg-5 my-5">
		<div class="row gx-4 gx-lg-5 align-items-center">
			<c:forEach var="v" items="${list }" varStatus="status">
				<!-- Product image 600x700 -->
				<div class="col-md-6">
					<img class="card-img-top mb-5 mb-md-0" src="img/${v.productName }.png" alt="${v.productName }.png"/>
				</div>
				<div class="col-md-6">
					<!-- Product serial -->
					<div class="small mb-1">${v.productSerial }</div>
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
						<input class="form-control text-center me-3" id="inputQuantity" type="num" value="1" style="max-width: 3rem" />
						<a class="btn btn-outline-dark" href="#">Buy</a>
						<i class="bi-cart-fill me-2"></i>
						<a class="btn btn-outline-dark">Add to cart</a>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</section>

</body>
</html>