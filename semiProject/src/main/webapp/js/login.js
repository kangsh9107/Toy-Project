/* login.jsp --> signup.jsp */
const ChoiLoginFrm = document.ChoiLoginFrm;

function moveSignup() {
	ChoiLoginFrm.action = "action.kang?inc=signup.jsp&job=temp&findStr=temp&nowPage=1&category=temp";
	ChoiLoginFrm.submit();
}

/* 로그인 */
function checkLogin() {
	ChoiLoginFrm.action = "action.kang?inc=main.jsp&job=temp&findStr=temp&nowPage=1&category=temp";
	ChoiLoginFrm.submit();
}