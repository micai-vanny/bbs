<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberLoginForm.jsp</title>
<script>
	function formCheck() {
		if(frm.memberId.value == "") {
			alert("ID를 입력하세요!");
			frm.memberId.focus();
			return false;
		}
		if(frm.memberPwd.value == "") {
			alert("패스워드를 입력하세요!");
			frm.memberPwd.focus();
			return false;
		}
		frm.submit();
	}
</script>
</head>
<body>
	<div align="center">
		<h1>Login Page</h1>
		<form id="frm" action="memberLogin.do" method="post">
			<div>
				<table border="1">
					<tr>
						<th>ID</th>
						<td><input type="text" name="memberId" id="memberId"></td>
					</tr>
					<tr>
						<th>패스워드</th>
						<td><input type="password" name="memberPwd" id="memberPwd"></td>
					</tr>
				</table>
			</div>
			<div>
				<button type="button" onclick="formCheck()">로그인</button>
				<button type="reset">취소</button>
				<button type="button" onclick="location.href='main.do'">홈으로</button>
			</div>
		</form>
	</div>
</body>
</html>