/**
 * 
 */
 
 let frm = document.querySelector('#seolFrm');
 let btn = frm.quitButton;
 let chkBox = document.querySelector('#seolQuitCheckbox');
 
btn.onclick = function(){
 	let chk = chkBox.checked;
 	let inputPwd = document.querySelector('#seolPwd').value;
	if(chk != true){
		alert("약관에 동의해주세요!");
		return false;
	}
	else if(inputPwd == ""){
		alert("비밀번호를 입력하세요!");
		return false;
	}
	else{
		frm.action = 'myPage?myJob=quitR'
		frm.submit();
	}
}