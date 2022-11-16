/* category --> category_detail */
const kangCategoryFrm = document.kangCategoryFrm;

function showCategoryDetail(nowPage, serial, category) {
	kangCategoryFrm.action = "action.kang?job=showCategoryDetail";
	kangCategoryFrm.nowPage.value = nowPage;
	kangCategoryFrm.serial.value = serial;
	kangCategoryFrm.category.value = category;
	kangCategoryFrm.submit();
}

/* MovePage */
function movePage(nowPage) {
	kangCategoryFrm.action = "action.kang?job=select";
	kangCategoryFrm.nowPage.value = nowPage;
	kangCategoryFrm.submit();
}

/* Search */
function searchProduct() {
	kangCategoryFrm.action = "action.kang?job=select";
	kangCategoryFrm.nowPage.value = 1;
	kangCategoryFrm.submit();
}