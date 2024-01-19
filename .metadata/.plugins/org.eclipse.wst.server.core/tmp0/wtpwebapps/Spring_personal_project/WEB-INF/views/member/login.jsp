<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<jsp:include page="../layout/header.jsp"></jsp:include>
<jsp:include page="../layout/nav.jsp"></jsp:include>
<p>REGISTER
<form action="/member/login" method="post">
	<div class="mb-3">
		<label for="c" class="form-label">email</label> <input type="email"
			class="form-control" name="email" id="email">
	</div>
	<div class="mb-3">
		<label for="w" class="form-label">pwd</label> <input type="password"
			class="form-control" name="pwd" id="pwd">
	</div>
	<c:if test="${not empty param.errMsg }">
		<div class="mb-3 text-danger">
			<c:choose>
				<c:when test="${errMsg eq 'Bad credentials'}">
					<c:set value="이메일 & 비밀번호가 일치하지 않습니다." var="errText"></c:set>
				</c:when>
				<c:otherwise>
					<c:set value="관리자에게 문의해주세요." var="errText"></c:set>
				</c:otherwise>
			</c:choose>
			${errText}
		</div>
	</c:if>
	<button type="submit" class="btn btn-primary">로그인</button>
</form>
<jsp:include page="../layout/footer.jsp"></jsp:include>