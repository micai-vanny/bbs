<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:getAsString name="title" /></title>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/bootstrap/css/styles.css">
 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  	<script src="${pageContext.request.contextPath }/bootstrap/js/scripts.js"></script>
  	<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
  	<!-- ↓ 이거 있어야 풀다운 메뉴 됨  -->
  	<!-- Bootstrap core JS-->
       <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Core theme JS-->
       <script src="js/scripts.js"></script>
  <style>
    .fakeimg {
      height: 200px;
      background: #aaa;
    }
    
    .wrap {
    	min-height: 100%;
    	padding: 50px;
    }
    
    .footer {
    	clear: both;
    }
  </style>
</head>
<body>
	<tiles:insertAttribute name="head"></tiles:insertAttribute>
   	<tiles:insertAttribute name="menu"></tiles:insertAttribute>
   	<div class="wrap">
   		<tiles:insertAttribute name="body"></tiles:insertAttribute>
   	</div>
   	<div class="footer">
   	<tiles:insertAttribute name="foot"></tiles:insertAttribute>
   </div>
</body>
</html>