<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty id }">
	<h1>Welcome to Dongdong's Home Guest!</h1>
</c:if>
<c:if test="${!empty id }">
	<h1>${mname }, Welcome to Dongdong's Home!</h1>
</c:if>