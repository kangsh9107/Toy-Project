/**
 * 관리자페이지 회원관리  member.jsp
 */
let frmMemberSearch = document.frm_member_search;

if(frmMemberSearch != null) {
	frmMemberSearch.btnSelect.addEventListener('click', function() {
		frmMemberSearch.action = 'action.admin?job=memberSearch';
		frmMemberSearch.nowPage.value = 1; // 어떤 페이지에서 조회를 누르던 1페이지부터 보여주기 위해서.
		frmMemberSearch.submit();
	});
}
function movePage(nowPage) {
	frmMemberSearch.action = 'action.admin?job=memberSearch';
	frmMemberSearch.nowPage.value = nowPage;
	frmMemberSearch.submit();
}

function memberView(id) {
	frmMemberSearch.action = 'action.admin?job=memberView&id=' + id;
	frmMemberSearch.submit();
}


