<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<title>Insert title here</title>
<style>
.pagination {
	display: inline-block;
}

.pagination a {
	color: black;
	float: left;
	padding: 8px 16px;
	text-decoration: none;
	transition: background-color .3s;
	border: 1px solid #ddd;
}

.pagination a.active {
	background-color: #4CAF50;
	color: white;
	border: 1px solid #4CAF50;
}

.pagination a:hover:not(.active) {
	background-color: #ddd;
}
body h3 {
	text-align: center;
}
</style>
</head>
<script>
	function formSubmit(id) {
		frm.id.value = id;
		frm.submit();
	}
	
	function goPage(page) {
		location.href = "bulletinListPaging.do?page=" + page;	
	}
</script>
<body>
	<h3>게시글</h3>
	<form id="frm" action="bulletin.do" method="post">
		<input type="hidden" id="id" name="id">
	</form>
	<div align="center">
		<div style="width: 80%">
			<table class="table" border="1">
				<tr>
					<th width="100">순번</th>
					<th width="200">제목</th>
					<th width="150">작성자</th>
					<th width="150">작성일자</th>
					<th width="100">조회수</th>
				</tr>
				<c:forEach items="${bulletinList }" var="vo">
					<tr>
						<td>${vo.id }</td>
						<td onclick="formSubmit(${vo.id })">${vo.title }</td>
						<td>${vo.writer }</td>
						<td>${vo.regDate }</td>
						<td><i class="material-icons" style="font-size:28px;color:red">filter_${vo.hit }</i></td>
					</tr>
				</c:forEach>
			</table>
			<div>
				<button type="button" onclick="location.href='main.do'">메인 화면</button>
				<c:if test="${!empty id }">
					<button type="button" onclick="location.href='bulletinInsert.do'">등록</button>
				</c:if>
			</div>
		</div>
		<jsp:include page="../common/paging.jsp" flush="true">
			<jsp:param name="firstPageNo" value="${paging.firstPageNo}" />
			<jsp:param name="prevPageNo" value="${paging.prevPageNo}" />
			<jsp:param name="startPageNo" value="${paging.startPageNo}" />
			<jsp:param name="pageNo" value="${paging.pageNo}" />
			<jsp:param name="endPageNo" value="${paging.endPageNo}" />
			<jsp:param name="nextPageNo" value="${paging.nextPageNo}" />
			<jsp:param name="finalPageNo" value="${paging.finalPageNo}" />
		</jsp:include>
	</div>
	<p>${paging }</p>
</body>
</html>