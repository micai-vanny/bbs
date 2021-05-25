<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script>
	function noticeInsert() {
		let title = document.getElementById("ititle").value;
		let content = document.getElementById("icontent").value;
		
		frm.title.value = title;
		frm.content.value = content;
		frm.submit();
	}
</script>
<div align= "center">
	<h3>공지사항 글쓰기</h3>
	<form id="frm" action="noticeInsert.do" method="post">
		<input type="hidden" name = "title">
		<input type="hidden" name = "content">
	</form>
		<div>
			<table border="1">
				<tr>
					<th>제목</th>
					<td><input type="text" id="ititle" name="ititle"></td>
				</tr>
				<tr>
					<td>내용</td>
					<td colspan="2"><textarea id="icontent" rows="6" cols="90"></textarea></td>
				</tr>
			</table>
			<div>
				<button type="button" onclick="location.href='noticeListPaging.do'">돌아가기</button>
				<button type="button" onclick="noticeInsert()">등록</button>
			</div>
		</div>
</div>