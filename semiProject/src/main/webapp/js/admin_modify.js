/**
 * 회원정보 관리
 */


/*수정-----*/

var btnModify = document.querySelector('#btnModify');
if(btnModify != null){
	var frm = document.frm_user;
	btnModify.onclick = function(){
		frm.action = 'result.jsp?job=updateR';
		frm.submit();
	}
}

/*삭제----*/
var btnDelete = document.querySelector('#btnDelete');
if(btnDelete != null ){
	var frm = document.frm_user;
	btnDelete.onclick = function(){
		frm.action = 'result.jsp?job=deleteR';
		frm.submit();
	}
}

















