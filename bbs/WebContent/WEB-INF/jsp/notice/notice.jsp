<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	function noticeUpdate() {
		let id = document.getElementById("cid").innerHTML;
		let title = document.getElementById("ctitle").value;
		let content= document.getElementById("ccontent").value;
		
		frm.id.value = id;
		frm.title.value = title;
		frm.content.value = content;
		frm.submit();
	}
</script>
<div align="center">
	<h3>불꽃가능캣 공지사항 내용보기</h3>
	<form id="frm" action="noticeUpdate.do" method="post">
		<input type="hidden" name="id">
		<input type="hidden" name="title">
		<input type="hidden" name="content">
	</form>
	<table border="1">
		<tr>
			<th>no.</th><td id="cid">${notice.id }</td>
			<th>작성일자</th><td>${notice.regDate }</td>
			<th>조회수</th><td>${notice.hit }</td>
		</tr>
		<tr>
			<th>제목</th>
			<td colspan="5"><input id="ctitle" type="text" value="${notice.title }"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td colspan="5"><textarea id="ccontent" rows="6" cols="90">${notice.content }</textarea></td>
		</tr>
	</table>
	<div>
		<button type="button" onclick="location.href='noticeListPaging.do'">돌아가기</button>
		<c:if test="${id eq 'admin' }">
			<button type="button" onclick="noticeUpdate()">수정하기</button>
			<button type="button" onclick="noticeDelete()">삭제하기</button>
		</c:if>
	</div>
</div>