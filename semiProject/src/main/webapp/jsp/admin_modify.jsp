<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp/admin_modify.jsp</title>
<link rel='stylesheet' href='../css/admin_modify.css'>
<script src='//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js'></script>
<script defer src="../js/admin_modify.js"></script>
</head>
<body>

<div id='admin_input' class='admin_input'>
<form name="frm_user" action="doJoin" method="POST" class="joinForm">
      <h1 id='csc'>인재몰</h1>                                                                 
      <h2>회원정보 수정</h2>
      
      <div class="textForm">
      <label>아이디</label>
        <input name="loginId" type="text" class="id" placeholder="id" value='${vo.id }'/>
      </div>
      <div class="textForm">
      	<label>비밀번호</label>
        <input name="loginPw" type="password" class="pw"placeholder="pw" >
      </div>
      <div class="textForm">
       	<label>비밀번호 확인</label>
        <input name="loginPwConfirm" type="password" class="pw" placeholder="pw" >
      </div>
      <div class="textForm">
       	<label>이름</label>
        <input name="name" type="text" class="name" placeholder="name"value='${vo.name }'/>
	  </div>
      <div class="gender">
      	<label>성별</label>
      	남성<input name="gender" type="radio" value='m'
      	${ (vo.gender eq 'm')? 'checked':'' }/>
      	
      	여성<input name="gender" type="radio" value='f'
      	${ (vo.gender eq 'f')? 'checked':'' }/>
      	<hr/>
      </div>
      <div class='textForm'>
     	<label>나이</label>
      	<input name="age" type="text" class="age"  placeholder="age" value='${vo.age }'/>
      </div>
      <div class="textForm">
      	<label>주소</label>
        <input name="address" type="text" class="address" placeholder="adr" value='${vo.address }'>
      </div>
      <div class="textForm">
        <input name="zipcode" type="text" size='35' value='${vo.zipcode }'/>
        <input type="button" value="우편번호" name="btnFindZip"/>
      </div>
        <div class="textForm">
        <label>상세주소</label>
        <input name="address2" type="text" class="address" placeholder="상세주소"  value='${vo.address2 }'>
      </div>
      <div class="textForm">
        <label>전화번호</label>
        <input name="cellphoneNo" type="text" class="cellphoneNo"  placeholder="phone" value='${vo.phone}' }>
      </div>
      <div class="textForm">
      	<label>이메일</label>
        <input name="email" type="text" class="email"  placeholder="email" value='${vo.email }'>
      </div>
      <div class="textForm">
      	<label>포인트</label>
		<input name="point" type="text" class="point" placeholder="point" value='${vo.point }'>     
      </div>
      
      <div class='btnZone'>
      	<span></span>
      	<input type='button' value='수정' id='btnModify' />
        <input type='button' value='회원 삭제' id='btnDelete' />
        <input type='button' value='목록' id='btnSelect' />
      </div>
    </form>
	<script>
	let frm = document.frm_input; // 이거 찾아야댐.. 2022-11-11일
	
	frm.btnFindZip.onclick = function(){//우편번호}
		new daum.Postcode({
			oncomplete : function(data){
				frm.address.value = data.address;
				frm.zipcode.value = data.zonecode;
			}
		}).open();
	}
	
	</script>
</div>
</body>
</html>