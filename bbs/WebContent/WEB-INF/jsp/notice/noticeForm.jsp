<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
	function noticeInsert() {
		let title = document.getElementById("ititle").value;
		let content = document.getElementById("icontent").value;
		
		frm.title.value = title;
		frm.content.value = content;
		
		frm.submit();
	}
	
	function backPage() {
		history.back();
	}
</script>
<style>
	#ititle {
	width : 800px
	}
	#icontent {
	width : 800px;
	height : 300px;
	}

</style>
<div align="center">
	<!-- title, content 입력하고 submit -> noticeInsert.do -> list 로 return -->
	<form id="frm" action="noticeForm.do" method="post">
		<input type="hidden" name="title">
		<input type="hidden" name="content">
	</form>
	<table>
		<tr>
			<th>제목</th>
			<td><input type="text" id="ititle"></td>			
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea id="icontent"></textarea></td>			
		</tr>
	</table>
</div><br>
<div align="center">
	<c:if test="${id eq 'admin' }">
		<button type="button" onclick="noticeInsert()">등록</button>
		<button type="button" onclick="backPage()">뒤로가기</button>
	</c:if>
</div>