/* Back to list */
function backToList(findStr, nowPage, category) {
	const kangCategoryDetailFrm = document.kangCategoryDetailFrm;
	
	kangCategoryDetailFrm.action = "action.kang?job=select";
	kangCategoryDetailFrm.findStr.value = findStr;
	kangCategoryDetailFrm.nowPage.value = nowPage;
	kangCategoryDetailFrm.category.value = category;
	kangCategoryDetailFrm.submit();
}

/* Buy */
function buyRightNow(findStr, nowPage, category) {
	const kangCategoryDetailFrm = document.kangCategoryDetailFrm;
	
	if(Number(kangCategoryDetailFrm.quantity.value) > 0  &&
	   Number(kangCategoryDetailFrm.quantity.value) < 11 &&
	   Number.isInteger(Number(kangCategoryDetailFrm.quantity.value)) == true) {
		kangCategoryDetailFrm.action = "action.kang?job=buyRightNow";
		kangCategoryDetailFrm.findStr.value = findStr;
		kangCategoryDetailFrm.nowPage.value = nowPage;
		kangCategoryDetailFrm.category.value = category;
		kangCategoryDetailFrm.submit();
	} else {
		alert("구매수량에는 1~10 사이의 정수만 입력 해주세요.")
	}
}