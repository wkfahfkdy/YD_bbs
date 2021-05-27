<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
	th, td {
		text-align: center;
	}
</style>
<body class="bg-dark py-5">
	<div class="container px-4 px-lg-5 my-5">
		<div class="text-center text-black">
			<div class="col mb-5">
				<div class="card h-100">
					<div class="card-body p-4">
						<div id = "cen" align="center">
							<h2>LIST</h2>
							<table>
								<tr>
									<th>구매자</th>
									<th>상품명</th>
									<th>상품</th>
									<th>가격</th>
									<th>수량</th>
									<th>총액</th>
								</tr>
								<c:forEach items="${list }" var="vo">
									<tr>
										<td width="100">${vo.id }</td>
										<td width="100">${vo.itemName }</td>
										<td width="100"><img src="upload/${vo.itemImage }" alt="..." width="50px" height="50px" /></td>
										<td width="100"><fmt:formatNumber type="currency" value="${vo.salePrice }"></fmt:formatNumber></td>
										<td width="100">${vo.itemQty }</td>
										<td witdh="100"></td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>