<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp/mymain.jsp</title>
<style>
@import url('https://fonts.googleapis.com/css?family=Do+Hyeon:400');



#mymain_chowrap{
	width : 1000p
}

.mymain_name2, .mymain_point2{
	text-indent: 20px;
	font-family: 'Do Hyeon';
	font-size : 40px;
	display : block;
}

/* #mymain_name의 컬러는 해당 등급의 동그라미사진의 컬러와 같은 컬러로 변경 */
#mymain_name{
	background-color : #D8B250;
	width : 230px;
	height : 120px;
	text-align : left;
	float : left;
	clear : both;
}

#mymain_point{
	background-color : #EEEEEE;
	width : 230px;
	height : 120px;
	text-align : left;
	float : left;
	clear : both;
}
#mymain_chowrap_gradeimg2{
	height : 250px;
	width : 680px;
}

</style>
</head>
<body>

<div id = "mymainchowrap">
	<div id = "mymain_text">
		<div id = "mymain_name">
			<span class = "mymain_name2">홍길동 님</span>
			<span class = "mymain_name2">GOLD 등급</span>
		</div>
		<div id = "mymain_point" >
			<span class = "mymain_point2">마일리지</span>
			<span class = "mymain_point2">15,000 점</span>
		</div>
	</div>
		
	<div>
		<img id = "mymain_chowrap_gradeimg" src = "img/mymain_grade2.png">
	</div>
</div>
</body>
</html>