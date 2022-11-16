<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp/graph.jsp</title>
<link rel="stylesheet" href="css/admin_graph.css">
<script defer type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script defer src="js/admin_graph.js"></script>
  
</head>
<body>
<div id="baik_graph">
		<h3>회원 통계 분석</h3>
	  <form name="frm_graph" method="post">
	    <input type='search' placeholder="상품번호 입력" value="${pageVo.findStr}" name='findStr'/>
	    <input type='hidden' name='nowPage' value="${pageVo.nowPage }"/>
   	    <input type='hidden' name='SERIAL' value=""/>
		<input type='button' value='조회' name='btnSelect'/>
		<div id='blank'></div>
		<br>
	  </form>
	    <h5 id ='graph_title'>${gVo.title }</h5>
		<div id="piechart"></div>
		<div id="chart_div"></div>
	    <input type='hidden' id='mSize' value="${gVo.mSize }"/>
	    <input type='hidden' id='fSize' value="${gVo.fSize }"/>
	    <input type='hidden' id='tenSize' value="${gVo.tenSize }"/>
	    <input type='hidden' id='twentySize' value="${gVo.twentySize }"/>
	    <input type='hidden' id='thirtySize' value="${gVo.thirtySize }"/>
	    <input type='hidden' id='fourtySize' value="${gVo.fourtySize }"/>
	    <input type='hidden' id='fiftySize' value="${gVo.fiftySize }"/>
</div>
</body>
</html>