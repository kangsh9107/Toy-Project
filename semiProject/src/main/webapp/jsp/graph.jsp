<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<h3>전체 회원 분포도</h3>
<div id="baik_graphSearch">
  <form name="frm_graph" method="post">
     <input type='search' name='findStr' value="${pageVo.findStr }"/>
     <input type='button' value='조회' name='btnSelect'/>
  </form>
</div>
	<div id="piechart"></div>
	<div id="chart_div"></div>
</div>
</body>
</html>