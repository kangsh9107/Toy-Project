<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>signup.jsp</title>
<link rel="stylesheet" type='text/css' href='css/signup.css'>
<script src='//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js'></script>
<script defer src="js/signup.js"></script>
</head>
<body>
<form name="frm_input" action="save_do.jsp" method="POST" class="joinForm">
      <h1 id='csc'>인재몰<span>  -회원가입-</span></h1>
      <div class="textForm">
        <input name="id" type="text" class="id" placeholder="아이디" autocomplete="off"/>
      </div>
      <div class="textForm">
        <input name="pwd" type="password" class="pw" placeholder="비밀번호" autocomplete="off" maxlength="12"/>
      </div>
       <div class="textForm">
        <input name="loginPwConfirm" type="password" class="pw" placeholder="비밀번호 확인" autocomplete="off" maxlength="12"/>
      </div>
      <div class="textForm">
        <input name="name" type="text" class="name" placeholder="이름" autocomplete="off"/>
		</div>
      <div id="gender">
      <span>
        <span class="choiGender">성별</span>
        <span class="choiGenderRight">
	      	남성<input name="gender" type="radio" value='m' checked/>
	      	여성<input name="gender" type="radio" value='f'/>
      	</span>
      </span>
      <hr class="choiHr"/>
      </div>
      <div class='textForm'>
      	<input name="age" type="number" class="age" placeholder="나이" min="0" autocomplete="off"/>
      </div>
         <div class="textForm">
        <input name="address1" type="text" class="address" placeholder="주소" readonly autocomplete="off">
      </div>
      <div class="textForm">
        <input name="postalCode" type="text" size='25' required readonly autocomplete="off"/>
        <input type="button" value="우편번호" name="zipCode"/>
      </div>
        <div class="textForm">
        <input name="address2" type="text" class="address" placeholder="상세주소" autocomplete="off"/>
      </div>
       <div class="textForm">
        <input name="phone" type="text" class="cellphoneNo" placeholder="전화번호" oninput="regul4(this)" maxlength="13" autocomplete="off"/>
      </div>
      <div class="textForm">
        <input name="email" type="text" class="email" placeholder="이메일" autocomplete="off"/>
      </div>
   
      <input type="button" class="choiBtnJoin" value="J O I N" onclick="sendForm()"/>
    </form>
</body>
<script>
let frm = document.frm_input;

frm.zipCode.onclick = function(){//우편번호}
	new daum.Postcode({
		oncomplete : function(data){
			frm.address1.value = data.address;
			frm.postalCode.value = data.zonecode;
		}
	}).open();
}
</script>
</html>