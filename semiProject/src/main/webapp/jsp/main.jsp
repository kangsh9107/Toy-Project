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
<link href="css/main.css" rel="stylesheet">
<!-- JavaScript -->
<script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
		crossorigin="anonymous"></script>
<script defer src=js/main.js></script>
<title>jsp/main.jsp</title>
</head>
<body>

<!-- Carousel -->
<div id="carouselExampleFade" class="carousel slide carousel-fade" data-bs-ride="carousel">
	<div class="carousel-inner kangCarouselInner"></div>
</div>

<!-- Top 8 show btn -->
<div id="kangNavWrap2">
	<div class="kangNavInner2">
		<div class="btn-toolbar" style="justify-content: center; margin-bottom: -40px;" role="toolbar" aria-label="Toolbar with button groups">
			<div class="btn-group me-6" role="group" aria-label="Second group">
				<a class="btn btn-outline-dark" href="index.jsp" style="font-size: 10pt !important; height: 25px !important; line-height: 10px !important;">Editor Pick</a>
				<a class="btn btn-outline-dark" href="showBest.do?job=outer" style="font-size: 10pt !important; height: 25px !important; line-height: 10px !important;">Outer Best8</a>
				<a class="btn btn-outline-dark" href="showBest.do?job=top" style="font-size: 10pt !important; height: 25px !important; line-height: 10px !important;">Top Best8</a>
				<a class="btn btn-outline-dark" href="showBest.do?job=bottom" style="font-size: 10pt !important; height: 25px !important; line-height: 10px !important;">Bottom Best8</a>
				<a class="btn btn-outline-dark" href="showBest.do?job=shoes" style="font-size: 10pt !important; height: 25px !important; line-height: 10px !important;">Shoes Best8</a>
				<a class="btn btn-outline-dark" href="showBest.do?job=acc" style="font-size: 10pt !important; height: 25px !important; line-height: 10px !important;">Acc Best8</a>
			</div>
		</div>
	</div>
</div>

<!-- Top 8 -->
<c:if test="${category ne null }">
	<section class="py-5">
		<div class="container px-4 px-lg-5 mt-5">
			<div id="kangBestWrap" class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
				<c:forEach var='v' items="${list}" varStatus='status'>
					<div class="col mb-5">
						<div class="card h-100">
							<!-- Sale badge -->
							<div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Best</div>
							<!-- Product image 450x300 -->
							<img class="card-img-top" src="img/${v.productName }.png" alt="${v.productName }.png" />
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
									<button class="btn btn-outline-dark">View</button>
								</div>
							</div>
						</div>
					</div>
		    	</c:forEach>
			</div>
		</div>
	</section>
</c:if>

<!-- Editor pick 8 -->
<c:if test="${category eq null }">
	<section class="py-5">
		<div class="container px-4 px-lg-5 mt-5">
			<div id="kangBestWrap" class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
				<c:forEach var="i" begin="0" end="7" step="1">
					<div class="col mb-5">
						<div class="card h-100">
							<!-- Sale badge-->
							<div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem;">
								Editor Pick
							</div>
							<!-- Product image-->
							<img class="card-img-top" src="img/outer${i+62}.png" alt="outer${i+62}.png" />
							<div class="card-body p-4">
								<div class="text-center">
									<!-- Product name -->
									<h5 class="fw-bolder">Outer${i+62}</h5>
									<!-- Product price -->
									₩ 2,500,000
								</div>
							</div>
							<!-- View -->
							<div class="card-footer p-2 pt-0 border-top-0 bg-transparent">
								<div class="text-center">
									<button class="btn btn-outline-dark">View</button>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>
</c:if>
 
</body>
</html>