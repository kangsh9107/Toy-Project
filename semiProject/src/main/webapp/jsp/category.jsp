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
<script defer src="js/category.js"></script>
<title>jsp/category.jsp</title>
</head>
<body>

<!-- products order by select, findStr, nowPage, category -->
<form name="kangCategoryFrm" method="post">
	<nav class="navbar navbar-expand-lg">
		<div class="container px-4 px-lg-5">
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4"></ul>
				<div class="d-flex">
					<!-- products order by select -->
					<select class="form-select" aria-label="Default select example">
						<option selected>정렬 선택</option>
						<option value="mostSalesRate">판매량 높은 순</option>
						<option value="highPrice">높은 가격 순</option>
						<option value="lowPrice">낮은 가격 순</option>
					</select>
					<i class="bi-cart-fill me-2"></i>
					<input type="search" name="findStr" class="form-control" id="inlineFormInputName" value="${pageVo.findStr }" placeholder="Search"/>
					<i class="bi-cart-fill me-2"></i>
					<button type="button" class="btn btn-outline-dark" onclick="productSearch()">Search</button>
					<input type="hidden" name="nowPage" value="${pageVo.nowPage }"/>
					<input type="hidden" name="category" value="${pageVo.category }"/>
					<input type="hidden" name="serial" value=""/>
				</div>
			</div>
		</div>
	</nav>
</form>

<!-- CategoryProducts -->
<section class="py-5">
	<div class="container px-4 px-lg-5 mt-5">
		<div id="kangCategoryWrap" class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
			<c:forEach var="v" items="${list }" varStatus="status">
				<div class="col mb-5">
					<div class="card h-100">
						<!-- Product image 450x300 -->
						<img class="card-img-top" src="img/${v.productName }.png" alt="${v.productName }.png"/>
						<div class="card-body p-4">
							<div class="text-center">
								<!-- Product name -->
								<h5 class="fw-bolder">${v.productName }</h5>
								<!-- Product price -->
								₩ <fmt:formatNumber value="${v.price }" pattern="#,###"/>
							</div>
						</div>
						<!-- View -->
						<div class="card-footer p-2 pt-0 border-top-0 bg-transparent">
							<div class="text-center">
								<button class="btn btn-outline-dark" onclick="showCategoryDetail(${pageVo.nowPage }, ${v.serial }, '${pageVo.category }')">View</button>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</section>

<!-- PageButton -->
<div class="btn-toolbar" style="justify-content: center;" role="toolbar" aria-label="Toolbar with button groups">
	<c:if test="${pageVo.startPage > 1 }">
		<div class="btn-group me-2" role="group" aria-label="First group">
			<button type="button" class="btn btn-outline-light" style="background-color: rgb(183, 154, 113);" onclick="movePage(1)">Start</button>
			<button type="button" class="btn btn-outline-light" style="background-color: rgb(183, 154, 113);" onclick="movePage(${pageVo.startPage - 1 })"><</button>
		</div>
	</c:if>
	
	<div class="btn-group me-2" role="group" aria-label="Second group">
		<c:forEach var="i" begin="${pageVo.startPage }" end="${pageVo.endPage }" step="1">
			<button type="button" class="btn btn-outline-light" style="background-color: rgb(183, 154, 113);" onclick="movePage(${i })">${i }</button>
		</c:forEach>
	</div>
	
	<c:if test="${pageVo.endPage lt pageVo.totPage }">
		<div class="btn-group" role="group" aria-label="Third group">
			<button type="button" class="btn btn-outline-light" style="background-color: rgb(183, 154, 113);" onclick="movePage(${pageVo.endPage + 1 })">></button>
			<button type="button" class="btn btn-outline-light" style="background-color: rgb(183, 154, 113);" onclick="movePage(${pageVo.totPage })">End</button>
		</div>
	</c:if>
</div>

</body>
</html>