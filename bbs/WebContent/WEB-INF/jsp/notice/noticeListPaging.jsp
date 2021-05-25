<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>불꽃가능캣 동동이 ::: 공지사항</title>
<style>
.pagination {
  display: inline-block;
}

.pagination a {
  color: black;
  float: left;
  padding: 8px 16px;
  text-decoration: none;
}

.pagination a.active {
  background-color: #4CAF50;
  color: white;
}

.pagination a:hover:not(.active) {background-color: #ddd;}
</style>
<script>
	function formSubmit(id) {
		frm.id.value = id;
		frm.submit();
	}
	
	function goPage(page) {
		location.href = "noticeListPaging.do?page=" + page;
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
		<p />
		     <!-- 페이징 호출 -->
         <jsp:include page="../common/paging.jsp" flush="true">
            <jsp:param name="firstPageNo" value="${paging.firstPageNo}" />
            <jsp:param name="prevPageNo" value="${paging.prevPageNo}" />
            <jsp:param name="startPageNo" value="${paging.startPageNo}" />
            <jsp:param name="pageNo" value="${paging.pageNo}" />
            <jsp:param name="endPageNo" value="${paging.endPageNo}" />
            <jsp:param name="nextPageNo" value="${paging.nextPageNo}" />
            <jsp:param name="finalPageNo" value="${paging.finalPageNo}" />
         </jsp:include>
         <!-- 페이징 호출 종료 -->
	</div>
</body>
</html>