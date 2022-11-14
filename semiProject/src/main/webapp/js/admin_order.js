/**
 *  관리자페이지 주문관리 order.jsp
 */
let frmOrderSearch = document.frm_order_search;

if(frmOrderSearch != null) {
	frmOrderSearch.btnSelect.addEventListener('click', function() {
		frmOrderSearch.action = 'action.admin?job=orderSearch';
		frmOrderSearch.nowPage.value = 1
		frmOrderSearch.submit();	
	});
}
function movePage(nowPage) {
	frmOrderSearch.action = 'action.admin?job=orderSearch';
	frmOrderSearch.nowPage.value = nowPage;
	frmOrderSearch.submit();
}

function orderView(id) {
	frmOrderSearch.action = 'action.admin?job=orderView&id=' + id; 
	frmOrderSearch.submit();
}

