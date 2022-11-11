/**
 * 관리자 페이지 상품관리 product.jsp
 */
let frmProductSearch = document.frm_product_search;

if(frmProductSearch != null) {
	frmProductSearch.btnMemberSearch.addEventListener('click', function() {
		frmProductSearch.action = 'action.do?job=productSearch';
		frmProductSearch.submit();	
	});
}

function productView(serial) {
	frmProductSearch.action = 'acton.do?job=productView&serial=' + serial;
	frmProductSearch.submit();
}

function movePage(nowPage) {
	frmProductSearch.action = 'action.do?job=select';
	frmProductSearch.nowPage.value = nowPage;
	frmProductSearch.submit();
}