let btnLogin = document.querySelector('#btnLogin');
if(btnLogin!=null) {
	frm.btnLogin.onclick = function() {
		let frm = document.frm;
		frm.action = 'test.do?job=login';
		frm.submit();
	}
}