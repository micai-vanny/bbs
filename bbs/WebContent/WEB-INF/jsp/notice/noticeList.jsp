<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>불꽃가능캣 동동이 ::: 공지사항</title>
<script>
	function formSubmit(id) {
		frm.id.value = id;
		frm.submit();
	}
</script>
</head>
<body>
	<form id="frm" action="notice.do" method="post">
		<input type="hidden" id="id" name="id">
	</form>
	<div align = "center">
		<h3>공지사항 리스트</h3>
		<div style="width:80%">
		<table class="table" border="1">
			<tr>
				<th width="20">no.</th>
				<th width="200">제목</th>
				<th width="30">작성일자</th>
				<th width="20">조회수</th>
			</tr>
			<c:forEach items="${noticeList }" var="vo">
				<tr onclick="formSubmit(${vo.id })">
					<td>${vo.id }</td>
					<td>${vo.title }</td>
					<td>${vo.regDate }</td>
					<td>${vo.hit }</td>
				</tr>
			</c:forEach>
		</table>
		</div>
		<div>
			<button type="button" onclick="location.href='main.do'">홈으로</button>
			<c:if test="${id eq 'admin' }">
				<button type="button" onclick="location.href='noticeForm.do'">글쓰기</button>
			</c:if>
		</div>
	</div>
</body>
</html>