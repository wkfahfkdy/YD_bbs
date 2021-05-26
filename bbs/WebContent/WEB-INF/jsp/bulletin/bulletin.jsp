<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="//cdn.ckeditor.com/4.16.1/full/ckeditor.js"></script>
<script>
	$(function() {
		CKEDITOR.replace('content', {
			filebrowserUploadUrl: '${pageContext.request.contextPath }/fileUpload',
			height: '600px',
			width: '800px'
		});
	});
	
/* 	function bulletinUpdate() {
		let id = document.getElementById("cid").innerHTML;
		let title = document.getElementById("ctitle").value;
		let content = document.getElementById("content").value;
		
		frm.id.value = id;
		frm.title.value = title;
		frm.content.value = content;
		
		frm.submit();
	} 
	
	function bulletinDelete() {
		let id = document.getElementById("cid").innerHTML;
		
		frm2.id.value = id;
		
		frm2.submit();
	} */
	
	$('#deleteBtn').click(function (e) {
		e.preventDefault();
		
		let id = $('#id').val();
		$.ajax({
			url: 'bulletinDelete.do',
			data: {id},
			type: 'post',
			success: function(result){
				
			},
			error: function(err){
				console.log(err);
			}
		});
	});
	
	function deleteBtn() {
		
		let id = $('#id').val();
		
		$.ajax({
			url: 'deleteServlet',
			data: {id: id},
			type: 'post',
			success: function(result){
				location.href='bulletinDelete.do'
			},
			error: function(err){
				console.log(err);
			}
		});
	}
		
	// CKEDITROR.instances.(id 아니면 name).getDAta() 이용하여 수정 버튼 function
	$('#btnUpdate').click(function (e) {
		e.preventDefault();
		console.log(CKEDITOR.instances.content.getData());
		
		let id = $('#id').val();
		let title = $('#title').val();
		let content = CKEDITOR.instances.conten.getData();
		$.ajax({
			url: 'ajaxBulletinUpdate',
			data: {
				id: id,
				title: title,
				content: content
			},
			type: 'post',
			success: function(result){
				//location.href ??? 'bulletinUpdate.do'
			},
			error: function(err){
				console.log(err);
			}
		});
	});
</script>
<div align="center">
	<h3>게시글</h3>
	<form id="frm" action="bulletinUpdate.do" method="post">
	<table border="1">
		<tr>
			<th>순번</th>
			<td><input id="id" type="hidden" name="id" value="${bulletin.id }">${bulletin.id }</td>
			<th>작성자</th>
			<td>${bulletin.writer }</td>
			<th>작성일자</th>
			<td>${bulletin.regDate }</td>
			<th>조회수</th>
			<td>${bulletin.hit }</td>
		</tr>
		<tr>
			<th>제목</th>
			<td colspan="7">
				<c:if test="${id eq bulletin.writer || id eq 'admin'}">
					<input id="title" name="title" type="text" value="${bulletin.title }">
				</c:if>
				<c:if test="${id ne bulletin.writer }">
					<input id="title" name="title" type="text" value="${bulletin.title }" readonly>
				</c:if>
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td colspan="7">
				<c:if test="${id eq bulletin.writer || id eq 'admin'}">
					<!-- c:if 대신 c:choose 를 사용해도 될듯 -->
					<textarea id="content" name="content" rows="6" cols="90">${bulletin.content }</textarea> 
					<!-- CKEDITOR 를 넣지 않으니까 input 과 textarea 를 사용했을 시에 제대로 출력되지 않았다. 그래서 깡으로 td에 때려박았었음 -->
					<!-- CKEDITOR 의 값을 추출할려니 value나 innerHTML 둘 다 되지않았다. 알고보니
						CKEDITROR.instances.(id 아니면 name).getDAta() 을 하면 값을 추출할 수 있다.-->
				</c:if>
				<c:if test="${id ne bulletin.writer }">
					<textarea id="content" name="content" rows="6" cols="90" readonly>${bulletin.content }</textarea> 
				</c:if>
			</td>
		</tr>
	</table><br>
	<div>
		<button type="button" onclick="location.href='bulletinListPaging.do'">목록 보기</button>
		<c:if test="${id eq bulletin.writer || id eq 'admin'}">
			<button type="submit">수정</button>
			<button type="button" onclick="deleteBtn()">삭제</button>
		</c:if>
	</div>
	</form>
</div>
