/**
 *  관리자페이지 주문관리 order.jsp
 */
let frmOrderSearch = document.frm_order_search;
let frmOrderView = document.frm_orderView;

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

function orderView(orderNumber) {
	frmOrderSearch.action = 'action.admin?job=orderView&orderNumber=' + orderNumber; 
	frmOrderSearch.submit();
}

