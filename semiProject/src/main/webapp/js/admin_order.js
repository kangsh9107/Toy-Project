/**
 *  관리자페이지 주문관리 order.jsp
 */
let frmOrderSearch = document.frm_order_search;

if(frmOrderSearch != null) {
	frmOrderSearch.btnOrderSearch.addEventListener('click', function() {
		frmOrderSearch.action = 'action.do?job=orderSearch';
		frmOrderSearch.submit();	
	});
}

function orderView(id) {
	frmOrderSearch.action = 'action.do?job=orderView&id=' + id; // 1) get타입
	frmOrderSearch.submit();
}

function movePage(nowPage) {
	frmOrderSearch.action = 'action.do?job=select';
	frmOrderSearch.nowPage.value = nowPage;
	frmOrderSearch.submit();
}
