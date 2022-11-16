/**
 * 주문정보 관리
 */

/*환불승인----*/
var btnDelete = document.querySelector('#btn_order_Delete');
if(btnDelete != null ){
	var frm = document.frm_order_modify;
	btnDelete.onclick = function(){
		frm.action = 'action.admin?job=orderDelete';
		frm.submit();
	}
}

/*환불요청----*/
var btnDelete = document.querySelector('#btn_order_Update');
if(btnDelete != null ){
	var frm = document.frm_order_modify;
	btnDelete.onclick = function(){
		frm.action = 'action.admin?job=orderUpdate';
		frm.submit();
	}
}

/* 목록 --------*/
var btnSelect = document.querySelector('#btn_order_Select');
if(btnSelect != null){
	btnSelect.onclick = function(){
		var frm = document.frm_order_modify;
		frm.action='action.admin?job=orderSearch';
		frm.submit();
	}
}

