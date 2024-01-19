<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="../layout/header.jsp"></jsp:include>
<jsp:include page="../layout/nav.jsp"></jsp:include>
<p>REGISTER
<form action="/member/register" method="post">
	<div class="mb-3">
		<label for="c" class="form-label">email</label> <input type="email"
			class="form-control" name="email" id="email">
	</div>
	<div class="mb-3">
		<label for="w" class="form-label">pwd</label> <input type="password"
			class="form-control" name="pwd" id="pwd">
	</div>
	<div class="mb-3">
		<label for="w" class="form-label">nickname</label> <input type="text"
			class="form-control" name="nickname" id="nickname">
	</div>
	<button type="submit" class="btn btn-primary">회원가입</button>
</form>
<jsp:include page="../layout/footer.jsp"></jsp:include>