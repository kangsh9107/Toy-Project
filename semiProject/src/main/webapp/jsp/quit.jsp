<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<link href="../css/quit.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<title>jsp/quit.jsp</title>
</head>
<body>
	<div id='seolQuitDiv' name='seolQuitDiv' >
		<h4>회원탈퇴</h4>
		<span>
		회원탈퇴 시 개인정보 및 INJAEMALL에서 만들어진 모든 데이터는 삭제됩니다.<br/>
		(단, 아래 항목은 표기된 법률에 따라 특정 기간 동안 보관됩니다.)	<br/>
		</span>
		<span id='seolQuitSpan'>
		1. 걔약 또는 청약철회 등에 관한 기록 보존 이유 : 전자상거래 등에서의 소비자보호에 관한 법률/보존 기간 : 5년<br/>
		2. 대금결제 및 재화 등의 공급에 관한 기록 보존 이유 : 전자상거래 등에서의 소비자보호에 관한 법률/보존 기간 : 5<br/>
		3. 전자금융 거래에 관한 기록 보존 이유 : 전자금융거래법 보존 기간/5년<br/>
		4. 소비자의 불만 또는 분쟁처리에 관한 기록 보존 이유 : 전자상거래 등에서의 소비자보호에 관한 법률 보존 기간/3년<br/>
		5. 신용정보의 수집/처리 및 이용 등에 관한 기록 보존 이유 : 신용정보의 이용 및 보호에 관한 법률 보존기간/3년<br/>
		</span>
		<h4 id='seolQuitHead'>유의사항</h4>
		<span id='seolQuitSpan'>
			- 회원탈퇴 처리 후에는 회원님의 개인정보를 복원할 수 없으며, 회원탈퇴 진행 시 해당 아이디는 영구적으로 삭제되어 재가입이 불가합니다.<br/>
			- 소속된 회사가 존재할 경우, '탈퇴'회원으로 조회됩니다.<br/>
			- 회사가 INJAEMALL 내에 존재하는 경우, 회사에 귀속된 데이터에 대해서는 보관 됩니다.<br/>
		</span>
		<input type="checkbox" id="seolQuitCheckbox" name="seolQuitCheckbox" value="해당 내용을 모두 확인했으며, 회원탈퇴에 동의합니다">
		해당 내용을 모두 확인했으며, 회원탈퇴에 동의합니다
		<form name='seolFrm' id='seolFrm' method='post'>
			<label id='seolQuitLabel'>ID</label><input type='text' name='seolId' id='seolId'/><br/>
			<label id='seolQuitLabel'>PASSWORD</label><input type='text' name='seolPwd' id='seolPwd'/><br/>
			<input type='submit' class='btn btn-secondary' value = '회원탈퇴' />
		</form>
	</div>
</body>
</html>