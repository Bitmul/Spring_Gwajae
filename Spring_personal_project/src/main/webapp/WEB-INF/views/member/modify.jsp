<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<jsp:include page="../layout/header.jsp"></jsp:include>
<jsp:include page="../layout/nav.jsp"></jsp:include>
<!DOCTYPE html>
<div class="container">
	<form action="/member/modify" method="post">
		<div class="mb-3">
			<label for="e" class="form-label">email</label> <input
				type="text" class="form-control" name="email" id="e" value="${mvo.email}" readonly="readonly">
		</div>
		<div class="mb-3">
			<label for="p" class="form-label">password</label> <input
				type="password" class="form-control" name="pwd" id="p" value="">
		</div>
		<div class="mb-3">
			<label for="n" class="form-label">nickname</label> <input
				type="text" class="form-control" name="nickname" id="n" value="${mvo.nickname}">
		</div>
		<button type="submit" class="btn btn-dark">회원정보수정</button>
	</form>
</div>
<jsp:include page="../layout/footer.jsp"></jsp:include>