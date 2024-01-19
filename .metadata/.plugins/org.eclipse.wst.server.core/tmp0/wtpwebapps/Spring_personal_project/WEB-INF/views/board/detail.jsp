<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<jsp:include page="../layout/header.jsp"></jsp:include>
<jsp:include page="../layout/nav.jsp"></jsp:include>

<c:set value="${bdto.bvo}" var="bvo"></c:set>

<div class="container-md">
	<div class="mb-3">
		<label class="form-label">bno</label> <input type="text"
			class="form-control" name="bno" id="bno" readonly="readonly"
			value="${bvo.bno}">
	</div>
	<div class="mb-3">
		<label class="form-label">title</label> <input type="text"
			class="form-control" name="title" id="title" readonly="readonly"
			value="${bvo.title}">
	</div>
	<div class="mb-3">
		<label class="form-label">writer</label> <input type="text"
			class="form-control" name="writer" id="writer" readonly="readonly"
			value="${bvo.writer}">
	</div>
	<div class="mb-3">
		<label class="form-label">content</label> <input type="text"
			class="form-control" name="content" id="content" readonly="readonly"
			value="${bvo.content}">
	</div>
	<div class="mb-3">
		<label class="form-label">regAt</label> <input type="text"
			class="form-control" name="regAt" id="regAt" readonly="readonly"
			value="${bvo.regAt}">
	</div>
	<div class="mb-3">
		<label class="form-label">modAt</label> <input type="text"
			class="form-control" name="modAt" id="modAt" readonly="readonly"
			value="${bvo.modAt}">
	</div>
	<div class="mb-3">
		<label class="form-label">readCount</label> <input type="text"
			class="form-control" name="readCount" id="readCount"
			readonly="readonly" value="${bvo.readCount}">
	</div>

	<c:set value="${bdto.flist}" var="flist"></c:set>

	<div class="col-12">
		<label for="f" class="form-label">File</label>
		<ul class="list-group list-group-flush">
			<c:forEach items="${flist}" var="fvo">
				<li class="list-group-item"><c:choose>
						<c:when test="${fvo.filetype == 1}">
							<div>
								<img alt=""
									src="/upload/${fvo.savedir}/${fvo.uuid}_th_${fvo.filename}">
							</div>
						</c:when>
						<c:otherwise>
							<div>
								<a href="/upload/${fvo.savedir}/${fvo.uuid}_${fvo.filename}"
									download="${fvo.filename}"><svg
										xmlns="http://www.w3.org/2000/svg" width="16" height="16"
										fill="currentColor" class="bi bi-card-image"
										viewBox="0 0 16 16">
  <path d="M6.002 5.5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0z" />
  <path
											d="M1.5 2A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-13zm13 1a.5.5 0 0 1 .5.5v6l-3.775-1.947a.5.5 0 0 0-.577.093l-3.71 3.71-2.66-1.772a.5.5 0 0 0-.63.062L1.002 12v.54A.505.505 0 0 1 1 12.5v-9a.5.5 0 0 1 .5-.5h13z" />
</svg></a>
							</div>
						</c:otherwise>
					</c:choose>
					<div class="ms-2 me-auto">
						<div class="fw-bold">${fvo.filename }</div>
						<span class="badge rounded-pill text-bg-secondary">${fvo.filesize}</span>
					</div></li>
			</c:forEach>
		</ul>
	</div>

	<div class="mb-3">
		<label class="form-label">추천수</label> <input type="text"
			class="form-control" name="gaechu" id="gaechu" readonly="readonly"
			value="${bvo.gaechu}"> <a href="/board/gaechu?bno=${bvo.bno}"><button
				type="button" class="btn btn-success">개추</button></a> <a
			href="/board/bichu?bno=${bvo.bno}"><button type="button"
				class="btn btn-dark">비추</button></a>
	</div>
	<a href="/board/list"><button type="button" class="btn btn-success">리스트로</button></a>

	<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal.mvo.email" var="authEmail" />
		<c:if test="${bvo.writer == authEmail}">
			<a href="/board/modify?bno=${bvo.bno}"><button type="button"
					class="btn btn-primary" id="modBtn">수정하기</button></a>
			<a href="/board/delete?bno=${bvo.bno}"><button type="button"
					class="btn btn-danger" id="delBtn">삭제하기</button></a>
		</c:if>
	</sec:authorize>

	<div class="input-group mb-3">
		<span class="input-group-text" id="cmtWriter">${authEmail}</span> <input
			type="text" class="form-control" id="cmtText"
			aria-label="Amount (to the nearest dollar)">
		<button type="button" class="btn btn-primary" id="cmtPostBtn">Post</button>
	</div>

	<ul class="list-group list-group-flush" id="cmtListArea">
		<li class="list-group-item">
			<div class="mb-3">
				<div class="fw-bold">Writer</div>
				Content
			</div> <span class="badge rounded-pill text-bg-warning">modAt</span>
		</li>
	</ul>

	<div>
		<button type="button" class="btn btn-outline-dark" id="moreBtn"
			style="visibility: hidden" data-page="1">More+</button>
	</div>
</div>

<div class="modal" id="myModal" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Writer</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<input type="text" class="form-control" id="cmtTextMod">
				<button type="button" class="btn btn-primary"
					data-bs-dismiss="modal" id="cmtModBtn">Post</button>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary">Save
					Changes</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	let bnoVal = `<c:out value="${bvo.bno}" />`;
</script>
<script src="/resources/js/boardComment.js"></script>
<script type="text/javascript">
	spreadCommentList(bnoVal);
</script>
<jsp:include page="../layout/footer.jsp"></jsp:include>
