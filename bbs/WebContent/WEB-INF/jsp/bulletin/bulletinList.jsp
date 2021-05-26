<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>불꽃가능캣 ::: 게시판</title>
<script>
	function formSubmit(bulletinId) {
		// 게시글의 id값으로 게시글 조회.
		frm.id.value = bulletinId;
		frm.submit();
	}
</script>
</head>
<body>
	<form id="frm" action="bulletinSelect.do" method="post">
		<input type="hidden" id="id" name="id">
	</form>
	<div align = "center">
		<h3>자유게시판 리스트</h3>
		<div style="width:80%">
		<table class="table" border="1">
			<tr>
				<th width="20">no.</th>
				<th width="200">제목</th>
				<th width="30">작성자</th>
				<th width="30">작성일자</th>
				<th width="20">조회수</th>
			</tr>
			<c:forEach items="${bulletinList }" var="vo">
				<tr onclick="formSubmit(${vo.id })">
					<td>${vo.id }</td>
					<td>${vo.title }</td>
					<td>${vo.writer }</td>
					<td>${vo.regDate }</td>
					<td>${vo.hit }</td>
				</tr>
			</c:forEach>
		</table>
		</div>
		<div>
			<button type="button" onclick="location.href='main.do'">홈으로</button>
			<c:if test="${!empty id }">
				<button type="button" onclick="location.href='bulletinForm.do'">글쓰기</button>
			</c:if>
		</div>
	</div>
</body>
</html>