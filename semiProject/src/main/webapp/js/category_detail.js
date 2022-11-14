/* Back to list */
function backToList(findStr, nowPage, category) {
	const kangCategoryDetailFrm = document.kangCategoryDetailFrm;
	
	kangCategoryDetailFrm.action = "action.kang?job=backToList";
	kangCategoryDetailFrm.findStr.value = findStr;
	kangCategoryDetailFrm.nowPage.value = nowPage;
	kangCategoryDetailFrm.category.value = category;
	kangCategoryDetailFrm.submit();
}

/* Buy */
function buyRightNow(findStr, nowPage, category) {
	const kangCategoryDetailFrm = document.kangCategoryDetailFrm;
	
	kangCategoryDetailFrm.action = "action.kang?job=buyRightNow";
	kangCategoryDetailFrm.findStr.value = findStr;
	kangCategoryDetailFrm.nowPage.value = nowPage;
	kangCategoryDetailFrm.category.value = category;
	kangCategoryDetailFrm.submit();
}