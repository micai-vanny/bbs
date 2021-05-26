<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>불꽃가능캣 동동이 ::: 자유게시판</title>
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
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
<script>
	function formSubmit(id) {
		frm.id.value = id;
		frm.submit();
	}
	
	function goPage(page) {
		location.href = "bulletinListPaging.do?page=" + page;
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
					<td>
					<c:choose>
					<c:when test="${vo.hit > 9 }">${vo.hit } <i style='font-size:24px' class='far'>&#xf164;</i></c:when>
					<c:otherwise>${vo.hit }</c:otherwise>
					</c:choose>
					</td>
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