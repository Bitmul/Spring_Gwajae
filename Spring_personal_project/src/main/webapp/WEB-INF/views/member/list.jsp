<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<jsp:include page="../layout/header.jsp"></jsp:include>
<jsp:include page="../layout/nav.jsp"></jsp:include>

<div class="container">
	<table class="table">
		<thead>
			<tr>
				<th scope="col">email</th>
				<th scope="col">nickname</th>
				<th scope="col">pwd</th>
				<th scope="col">reg_at</th>
				<th scope="col">lastLogin</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${mvo}" var="mvo">
				<tr>
					<th scope="row"><a href="/member/detail?email=${mvo.email}">${mvo.email}</a></th>
					<td>${mvo.nickname}</td>
					<td>${mvo.pwd}</td>
					<td>${mvo.regAt}</td>
					<td>${mvo.lastLogin}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<jsp:include page="../layout/footer.jsp"></jsp:include>