<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<jsp:include page="../layout/header.jsp"></jsp:include>
<jsp:include page="../layout/nav.jsp"></jsp:include>
<div class="container-md">
	<h1>즐거운 이야기 게시판</h1>
	<div class="mb-3">
		<label for="c" class="form-label">email</label> <input type="email"
			class="form-control" name="email" id="email" value="${mvo.email}" readonly="readonly">
	</div>
	<div class="mb-3">
		<label for="w" class="form-label">pwd</label> <input type="password"
			class="form-control" name="pwd" id="pwd" value="${mvo.pwd}" readonly="readonly">
	</div>
	<div class="mb-3">
		<label for="w" class="form-label">nickname</label> <input type="text"
			class="form-control" name="nickname" id="nickname" value="${mvo.nickname}" readonly="readonly">
	</div>

	<a href="/member/list"><button type="button" class="btn btn-success">리스트로</button></a>
		<a href="/member/modify?email=${mvo.email}"><button type="button"
			class="btn btn-danger">수정하기</button></a>
	<a href="/member/remove?email=${mvo.email}"><button type="button"
			class="btn btn-danger">삭제하기</button></a>
</div>

<jsp:include page="../layout/footer.jsp"></jsp:include>