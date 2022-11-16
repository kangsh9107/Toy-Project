/**
 * 
 */
var frmRefund = document.frm_refund;

function refund(ordernumber){
	frmRefund.action='myPage?myJob=myRefund&ordernumber='+ ordernumber;
	frmRefund.submit();
}

function movePage(nowPage){
	frmRefund.action = 'myPage?myJob=showOrder';
	frmRefund.nowPage.value = nowPage;
	frmRefund.submit();
}

frmRefund.btnSearch.onclick=function(){
	frmRefund.action = 'myPage?myJob=searchMyOrder' ;
	frmRefund.nowPage.value = 1;
	frmRefund.submit();
	}

function showOrderDetails(orderNumber){
	frmRefund.action = 'myPage?myJob=showOrderDetails&orderNumber=' + orderNumber;
	frmRefund.submit();
}