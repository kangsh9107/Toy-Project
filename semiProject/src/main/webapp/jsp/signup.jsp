<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>signupex3</title>
<link rel="stylesheet" type='text/css' href='css/signup.css'>
<script src='//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js'></script>
</head>
<body>
<form name="frm_input" action="doJoin" method="POST" class="joinForm">
      <h1 id='csc'>인재몰<span>  -회원가입-</span></h1>
      <div class="textForm">
        <input name="loginId" type="text" class="id" placeholder="아이디"/>
      </div>
      <div class="textForm">
        <input name="loginPw" type="password" class="pw" placeholder="비밀번호" autocomplete="off">
      </div>
       <div class="textForm">
        <input name="loginPwConfirm" type="password" class="pw" placeholder="비밀번호 확인" autocomplete="off">
      </div>
      <div class="textForm">
        <input name="name" type="text" class="name" placeholder="이름">
		</div>
      <div id="gender">
      <span>
        <span class="choiGender">성별</span>
        <span class="choiGenderRight">
	      	남성<input name="gender" type="radio" value='m'/>
	      	여성<input name="gender" type="radio" value='f'/>
      	</span>
      </span>
      <hr class="choiHr"/>
      </div>
      <div class='textForm'>
      	<input name="age" type="number" class="age" placeholder="나이" min="0"/>
      </div>
         <div class="textForm">
        <input name="address" type="text" class="address" placeholder="주소" readonly>
      </div>
      <div class="textForm">
        <input name="zipcode" type="text" size='25' required readonly/>
        <input type="button" value="우편번호" name="btnFindZip"/>
      </div>
        <div class="textForm">
        <input name="address2" type="text" class="address" placeholder="상세주소">
      </div>
       <div class="textForm">
        <input name="cellphoneNo" type="number" class="cellphoneNo" placeholder="전화번호">
      </div>
      <div class="textForm">
        <input name="email" type="text" class="email" placeholder="이메일">
      </div>
   
      <input type="submit" class="choiBtnJoin" value="J O I N"/>
    </form>
</body>
<script>
let frm = document.frm_input;

frm.btnFindZip.onclick = function(){//우편번호}
	new daum.Postcode({
		oncomplete : function(data){
			frm.address.value = data.address;
			frm.zipcode.value = data.zonecode;
		}
	}).open();
}
</script>
</html>