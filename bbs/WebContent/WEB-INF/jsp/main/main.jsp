<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty id }">
	<div align= "center">
		<h1>동동마켓에 오신 것을 환영합니다 :▷</h1>
		<p>귀여운 동동이 굿즈가 다양하게 준비되어있어요!<br>
		구매를 원하시면 회원가입이 필요합니다!</p>
	</div>
</c:if>
<c:if test="${!empty id }">
	<h1>${mname }, Welcome to Dongdong's Home!</h1>
</c:if>