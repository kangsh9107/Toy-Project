/**
 * 회원정보 관리
 */



var frm = document.frm_user;

var btnModify = document.querySelector('#btnModify');
/*우편번호 DAUM */
if(frm.btnFindZip != null){
	frm.btnFindZip.onclick = function(){
		new daum.Postcode({
			oncomplete : function(data){
				frm.address1.value = data.address;
				frm.postalCode.value = data.zonecode;
			}
		}).open();
	}	
}


if(btnModify != null){
	btnModify.onclick = function(){
	if(sessionId.value != 'admin'){	
		if(frm.pwd.value != frm.pwConfirm.value){
			alert('비밀번호를 일치시켜 주세요.');
			frm.pwd.value.focus();
		}else{
			frm.action = 'myPage?myJob=myModifyR';
			frm.submit();
			
		}   
	}
	else{
		frm.action = 'action.admin?job=memberUpdate';
		frm.submit();
		
	}
 }
}
var btnDelete = document.querySelector('#btnDelete');
if(btnDelete != null ){
	var frm = document.frm_user;
	btnDelete.onclick = function(){
		frm.action = 'action.admin?job=memberDelete';
		frm.submit();
	}
}

/* 목록 -------*/
var btnSelect = document.querySelector('#btnCancle');
if(btnSelect != null){
	btnSelect.onclick = function(){
		var frm = document.frm_user;
		frm.action='action.admin?job=memberSearch';
		frm.submit();
	}
  }


