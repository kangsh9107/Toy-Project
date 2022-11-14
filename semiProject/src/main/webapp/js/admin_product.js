/**
 * 관리자 페이지 상품관리 product.jsp
 */
let frmProductSearch = document.frm_product_search;

if(frmProductSearch != null) {
	frmProductSearch.btnSelect.addEventListener('click', function() {
		frmProductSearch.action = 'action.admin?job=productSearch';
		frmProductSearch.nowPage.value = 1;
		frmProductSearch.submit();	
	});
}
function movePage(nowPage) {
	frmProductSearch.action = 'action.admin?job=productSearch';
	frmProductSearch.nowPage.value = nowPage;
	frmProductSearch.submit();
}

