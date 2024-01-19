<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ page session="false"%>
<jsp:include page="../layout/header.jsp"></jsp:include>
<jsp:include page="../layout/nav.jsp"></jsp:include>

<sec:authentication property="principal.mvo.email" var="authEmail" />
<form action="/board/register" method="post"
	enctype="multipart/form-data">
	<div class="mb-3">
		<label for="t" class="form-label">title</label> <input type="text"
			class="form-control" name="title" id="title">
	</div>
	<div class="mb-3">
		<label for="w" class="form-label">writer</label> <input type="text"
			class="form-control" name="writer" id="writer" value="${authEmail}" readonly="readonly">
	</div>
	<div class="mb-3">
		<label for="c" class="form-label">title</label> <input type="text"
			class="form-control" name="content" id="content">
	</div>
	<div class="mb-3">
		<input type="file" class="form-control" name="files" id="files"
			multiple="multiple" style="display: none">
		<button type="button" id="trigger" class="btn btn-outline-primary">FileUpload</button>
	</div>
	<div class="mb-3" id="fileZone"></div>
	<button type="submit" class="btn btn-primary" id="regBtn">작성하기</button>
</form>
<script src="/resources/js/boardRegister.js"></script>
<jsp:include page="../layout/footer.jsp"></jsp:include>
