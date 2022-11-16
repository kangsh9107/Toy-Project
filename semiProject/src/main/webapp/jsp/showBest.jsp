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
<!-- JavaScript -->
<script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
		crossorigin="anonymous"></script>
<script defer src=js/main.js></script>

<title>jsp/showBest.jsp</title>
</head>
<body>
	<section class="py-5">
	<div class="container px-4 px-lg-5 mt-5">
		<div id="kangBestWrap" class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
			<c:forEach var='v' items="${list}" varStatus='status'>
				<div class="col mb-5">
					<div class="card h-100">
						<!-- Sale badge-->
						<div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Best</div>
						<!-- Product image-->
						<img class="card-img-top" name="aa" id= "${status.count}" src="img/${v.category}${v.serial}.png" alt="${v.category}${v.serial}.png" />
						<div class="card-body p-4">
							<div class="text-center">
								<!-- Product name -->
								<h5 class="fw-bolder">${v.category}${v.serial}</h5>
								<!-- Product price -->
								${v.price}
							</div>
						</div>
						<!-- Buy -->
						<div class="card-footer p-2 pt-0 border-top-0 bg-transparent">
							<div class="text-center">
								<a class="btn btn-outline-dark" href="#">Buy</a>
								<a class="btn btn-outline-dark">Add to cart</a>
							</div>
						</div>
					</div>
				</div>
	    	</c:forEach>
		</div>
	</div>
</section>
	
</body>
</html>