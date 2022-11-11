<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script defer src="js/mypage.js"></script>
<title>jsp/mypage.jsp</title>
<style>
@import url('https://fonts.googleapis.com/css?family=Do+Hyeon:400');

#mypage_chowrap {
        margin:5px;
        float : left;
        clear : both;
}
#mypage_chonavi {
        width: 220px;
        text-indent: 20px;		//들여쓰기
        line-height:50px;		/*텍스트 세로정렬 위해 50px로 라인하이트 설정*/
        margin:0;
        padding:0;
}


#mypage_chotitle {
        height: 60px;
        line-height: 55px;		
        background:#917a5a;
        font-family: 'Do Hyeon';
		font-size : 30px;
       
}
.mypage_chosub{
        margin-bottom: 2px;
        height:50px;
        line-height:50px;		/*텍스트 세로정렬 위해 50px로 라인하이트 설정*/
        background:#f4f4f4;
        cursor:pointer;
        font-family: 'Do Hyeon';
		font-size : 25px;
		vertical-align : center;
}
.mypage_chosub a {
        display: block;	<!--앵커태그 확장해서 클릭될수 있도록  크기조절위해 블락으로설정-->
        width: 100%;
        height:100%;
        text-decoration:none;
        color:#000;
}
.mypage_chosub:hover {
        background:#9135a5;
}

#mypage_content {

width : 1200px;
height : 600px;
display : inline-block;
text-align : center;
}
</style>
</head>
<body>

<%
String incMY="mymain.jsp";		
if(request.getParameter("incMY") != null){	
	incMY = request.getParameter("incMY");	
	
}
%>
<div id="mypage_chowrap">
    <ul id="mypage_chonavi">
		<li id="mypage_chotitle">MY</li>
	    <li class="mypage_chosub"><a href='index.jsp?inc1=jsp/mypage.jsp&incMY=mymain.jsp'>회원정보</a></li>
	    <li class="mypage_chosub"><a href='index.jsp?inc1=jsp/mypage.jsp&incMY=myorder.jsp'>주문정보확인</a></li>
	    <li class="mypage_chosub"><a href='index.jsp?inc1=jsp/mypage.jsp&incMY=quit.jsp'>회원탈퇴</a></li>
    </ul>
</div>    
<!--  -->
<div id='mypage_content'>
		<jsp:include page="<%=incMY %>"/>	<!-- content 부분에 inc를 불러줌 inc에는 여러 페이지들이 매개변수로 담겨져서 해당 매개변수로 담겨진 페이지들이 창에 띄워지게 된다. -->
		<!-- inc의 매개변수로 페이지가 담겨져서 창이 띄워질때 css적용 경로에대해서는 해당페이지가 띄워지는 창(index.jsp)을 기준으로 css파일 경로설정을해줘야한다. 이때 해당 창의 css는 단위페이지에서 코딩하지말고 띄워지는 창 즉 index.jsp 기준으로 보면서 css코딩을 해줘야하는 점 주의 해야한다.(단위페이지에서 보면서 코딩할경우 실제로 띄워지는 창에서 안맞게 나올수 있기 때문 -->
</div>
</body>
</html>