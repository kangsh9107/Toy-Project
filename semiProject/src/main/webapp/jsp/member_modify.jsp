<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp/admin_modify.jsp</title>
<link rel='stylesheet' href='css/member_modify.css'>
<script src='//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js'></script>
<script defer src="js/member_modify.js"></script>
</head>
<body>

<div id='admin_input' class='admin_input'>
<form name="frm_user" action="doJoin" method="POST" class="joinForm">
<input type='hidden' name='sessionId' id='sessionId' value="${sessionId }"/>
      <h1 id='csc'>회원정보 수정</h1>                                                                 
      <div class="textForm">
      <label>아이디 </label>
        <input name="id" type="text" class="id" placeholder="id" value='${vo.id }' readOnly />
      </div>
      <input type="hidden" name=ppwwdd value='${vo.pwd}'>
	      <!-- 유저ID인 경우 보이는 비밀번호 칸 -->
	<c:if test="${sessionId ne 'admin' }">
      <div class="textForm">
      	<label>비밀번호</label>
        <input name="pwd" type="password" class="pw"placeholder="pw" >
      </div>
      <div class="textForm">
       	<label>비밀번호 확인</label>
        <input name="pwConfirm" type="password" class="pw" placeholder="pw" >
      </div>
   </c:if>
		  <!-- ------------------------- -->
      <div class="textForm">
       	<label>이름</label>
        <input name="name" type="text" class="name" placeholder="name"value='${vo.name }'/>
	  </div>
      <div class="textForm">
      	<label>성별</label>
      	남성<input name="gender" type="radio" value='m'
      	${ (vo.gender eq 'm')? 'checked':'' }/>
      	
      	여성<input name="gender" type="radio" value='f'
      	${ (vo.gender eq 'f')? 'checked':'' }/>
      </div>
      <div class='textForm'>
     	<label>나이</label>
      	<input name="age" type="text" class="age"  placeholder="age" value='${vo.age }'/>
      </div>
      <div class="textForm">
        <label>우편번호</label>
        <input name="postalCode" type="text" size='20' value='${vo.postalCode}'/>
        <input type="button" value="우편번호 찾기" name="btnFindZip"/>
      </div>
      <div class="textForm">
      	<label>주소</label>
        <input name="address1" type="text" class="address" placeholder="adr" value='${vo.address1 }'>
      </div>
        <div class="textForm">
        <label>상세주소</label>
        <input name="address2" type="text" class="address2" placeholder="상세주소"  value='${vo.address2 }'>
      </div>
      <div class="textForm">
        <label>전화번호</label>
        <input name="phone" type="text" class="cellphoneNo"  placeholder="phone" value='${vo.phone}'>
      </div>
      <div class="textForm">
      	<label>이메일</label>
        <input name="email" type="text" class="email"  placeholder="email" value='${vo.email }'>
      </div>
      	<!-- admin일때만 보이는 포인트칸 -->
	<c:if test="${sessionId eq 'admin' }">
      <div class="textForm">
      	<label>포인트</label>
		<input name="point" type="text" class="point" placeholder="point" value='${vo.point }'>     
      </div>
    </c:if>
			<!-- --------------------- -->
      <div class='btnZone'>
      	<span></span>
      	<input type='button' value='수정' id='btnModify' />
      	 	<!-- admin일때만 보이는 회원삭제버튼 -->
	  <c:if test="${sessionId eq 'admin' }">
        <input type='button' value='회원 삭제' id='btnDelete' />
        <input type='button' value='취소' id='btnCancle' />
      </c:if>
      </div>
	        <!-- --------------------- -->
      <input type="hidden" name="findStr" value='${pageVo.findStr }'/>
      <input type="hidden" name="nowPage" value="1"/>
    </form>

</div>
</body>
</html>