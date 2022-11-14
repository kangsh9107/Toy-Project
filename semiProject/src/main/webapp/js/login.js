/* Login */
const ChoiLoginFrm = document.ChoiLoginFrm;

function checkLogin() {
	ChoiLoginFrm.action = "action.kang?job=checkLogin";
	ChoiLoginFrm.submit();
}

/* login.jsp --> signup.jsp */
function moveSignup() {
	ChoiLoginFrm.action = "action.kang?job=moveSignup";
	ChoiLoginFrm.submit();
}