/**
 * 
 */
function regul4(target){
 		  target.value = target.value
  		  .replace(/[^0-9]/g, '')
 		  .replace(/^(\d{0,3})(\d{0,4})(\d{0,4})$/g, "$1-$2-$3").replace(/(\-{1,2})$/g, "");
}


function sendForm(){
	let frm = document.frm_input;
	
	var regul1= /^[a-zA-Z0-9]{4,12}$/;
       //4~12글자 사이에서 영소문자 대문자 혹은 0~9 숫자 값 받아오기
    var regul2= /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/
          //[첫글자는 영어또는숫자][-_.이 하나들어가거나 0개][영어또는 숫자 맘대로 입력가능]@이 붙고뒤에서는 같음
    var regul3= /^[가-힣a-zA-Z]{2,}$/;


	if(frm.id.value == ""){
		alert("아이디를 입력하시오");
		frm.id.focus();
		return;
		
	}
	if (!(regul1.test(frm.id.value))){
		alert("아이디는 4~12자의 대소문자와 숫자로만 입력 가능합니다.");
		frm.id.focus();
		return;
		
    }
   	if(frm.pwd.value == ""){
		alert("비밀번호를 입력하시오");
		frm.pwd.focus();
		return;
		
	}
	if(frm.pwd.value != frm.loginPwConfirm.value){
		alert("비밀번호 확인이 틀렸습니다");
		frm.loginPwConfirm.focus();
		return;
		
	}
	if (!(regul1.test(frm.pwd.value)) || frm.pwd.length < 4){
		alert("비밀번호는 4~12자의 대소문자와 숫자로만 입력 가능합니다.");
		frm.pwd.focus();
		return;
	}
	if(frm.name.value == ""){
		alert("이름을 입력해주시오");
		frm.name.focus();
		return;
	}
	if(!(regul3.test(frm.name.value))){
		alert("잘못된 이름의 형식입니다");
		return;
	}
	if(!frm.gender[0].checked && !frm.gender[1].checked){
		alert("성별을 체크해주시오");
		frm.gender.focus();
		return;
	}
	if(frm.age.value == ""){
		alert("나이를 입력하시오");
		frm.age.focus();
		return;
	}
	if(frm.postalCode.value == ""){
		alert("우편번호를 검색해주시오");
		frm.postalCode.focus();
		return;
	}
	if(frm.address2.value==""){
		alert("상세주소를 입력하시오");
		frm.address2.focus();
		return;
	}
	if(frm.phone.value==""){
		alert("핸드폰 번호를 입력하시오");
		frm.phone.focus();
		return;
	}else{
		regul4(frm.phone);
	}
	if(frm.email.value==""){
		alert("이메일을 입력하시오");
		frm.email.focus();
		return;
	}
	if(!(regul2.test(frm.email.value))){
		alert("잘못된 이메일 형식입니다");
		return;
	} else {
		frm.action='action.Choi?job=saveForm';
		frm.submit();
	}
}