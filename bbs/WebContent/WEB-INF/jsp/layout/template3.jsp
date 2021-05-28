<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>
	<tiles:getAsString name="title" />
</title> 
<!-- 컨트롤 통해서만 가능하게 WEB-INF 내부에 만듬 -->

  <link rel="stylesheet" href="${pageContext.request.contextPath }/bootstrap/css/styles.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath }/bootstrap/js/scripts.js"></script>
  <!-- Bootstrap icons-->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
  <!-- Bootstrap core JS-->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
  <style>
    .fakeimg {
      height: 200px;
      background: #aaa;
    }
  </style>

</head>
<body>
	
	<tiles:insertAttribute name="menu">
	</tiles:insertAttribute>
	
	
	<tiles:insertAttribute name="head">
	</tiles:insertAttribute>
	
	
	<tiles:insertAttribute name="body">
	</tiles:insertAttribute>	
	
	
	<tiles:insertAttribute name="foot">
	</tiles:insertAttribute>
	
</body>
</html>