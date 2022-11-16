/* Login */
const ChoiLoginFrm = document.ChoiLoginFrm;

function login() {
	ChoiLoginFrm.action = "action.kang?job=login";
	ChoiLoginFrm.submit();
}

/* login.jsp --> signup.jsp */
function moveSignup() {
	ChoiLoginFrm.action = "action.kang?job=moveSignup";
	ChoiLoginFrm.submit();
}