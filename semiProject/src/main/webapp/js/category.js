/* category --> category_detail */
const kangCategoryFrm = document.kangCategoryFrm;

function showDetail() {
	
}

/* MovePage */
function movePage(nowPage) {
	kangCategoryFrm.action = "action.kang?job=select";
	kangCategoryFrm.nowPage.value = nowPage;
	kangCategoryFrm.submit();
}

/* Search */
function productSearch() {
	kangCategoryFrm.action = "action.kang?job=search";
	kangCategoryFrm.nowPage.value = 1;
	kangCategoryFrm.submit();
}