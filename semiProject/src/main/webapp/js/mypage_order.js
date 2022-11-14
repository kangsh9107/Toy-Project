/**
 * 
 */

var frmmolist = document.querySelector("#frm_myorderlist");
var btnRefund = document.querySelector("#btnRefund");
let on;

btnRefund.onclick = function(){
  frmmolist.action = 'myPage?myJob=myRefund';
  frmmolist.submit();
}