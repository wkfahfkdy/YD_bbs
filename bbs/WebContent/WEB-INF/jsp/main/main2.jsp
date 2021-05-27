<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	function formCheck() {
		if(frm.memberId.value == "") {
			alert("아이디를 입력하시오");
			frm.memberId.focus;
			return false;
		}
		if(frm.memberPwd.value == "") {
			alert("비밀번호를 입력하시오");
			frm.memberPwd.focus;
			return false;
		}
		frm.submit();
	}
</script>
<body class="bg-dark py-5">
	<div class="container px-4 px-lg-5 my-5">
		<div class="text-center text-black">
			<div class="col mb-5">
				<div class="card h-100">
					<!-- 로그인 ID -->
					<div class="card-body p-4">
						<!-- 로그인 PW -->
						<form id="frm" action="memberLogin.do" method="post">
							<div align="center">
								<table border="1">
									<tr>
										<th>아이디</th>
										<td><input type="text" id="memberId" name="memberId"></td>
									</tr>
									<tr>
										<th>패스워드</th>
										<td><input type="password" id="memberPwd"
											name="memberPwd"></td>
									</tr>
								</table>
							</div>
							<br>
							<div>
								<button type="button" onclick="formCheck()">로그인</button>
								<button type="reset">취소</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>