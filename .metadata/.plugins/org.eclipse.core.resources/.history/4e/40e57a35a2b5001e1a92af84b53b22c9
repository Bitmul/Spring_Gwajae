<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<jsp:include page="../layout/header.jsp"></jsp:include>
<jsp:include page="../layout/nav.jsp"></jsp:include>

<div class="container">
	<form action="/board/list" method="get">
		<div class="input-group mb-3">
			<c:set value="${ph.pgvo.type}" var="typed"></c:set>
			<select name="type" class="form-select" id="search">
				<option ${typed eq null ? 'selected' : ''}>Choose...</option>
				<option value="t" ${typed eq 't' ? 'selected' : ''}>Title</option>
				<option value="w" ${typed eq 'w' ? 'selected' : ''}>Writer</option>
				<option value="c" ${typed eq 'c' ? 'selected' : ''}>Content</option>
				<option value="tw" ${typed eq 'tw' ? 'selected' : ''}>Title
					& Writer</option>
				<option value="tc" ${typed eq 'tc' ? 'selected' : ''}>Title
					& Content</option>
				<option value="wc" ${typed eq 'wc' ? 'selected' : ''}>Writer
					& Content</option>
				<option value="twc" ${typed eq 'twc' ? 'selected' : ''}>All</option>
			</select> <input type="hidden" name="pageNo" value="1"> <input
				type="hidden" name="qty" value="10"> <input type="search"
				name="keyword" placeholder="Search..." value="${ph.pgvo.keyword}">
			<button type="submit" class="btn btn-outline-success">
				Search <span
					class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
					${ph.totalCount} <span class="visually-hidden">unread
						messages</span>
				</span>
			</button>
		</div>
	</form>

	<table class="table">
		<thead>
			<tr>
				<th scope="col">#</th>
				<th scope="col">title</th>
				<th scope="col">writer</th>
				<th scope="col">reg_at</th>
				<th scope="col">mod_at</th>
				<th scope="col">read_count</th>
				<th scope="col">gaechu</th>
				<th scope="col">has_file</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="bvo">
				<tr>
					<th scope="row"><a href="/board/detail?bno=${bvo.bno}">${bvo.bno}</a></th>
					<td>${bvo.title}</td>
					<td>${bvo.writer}</td>
					<td>${bvo.regAt}</td>
					<td>${bvo.modAt}</td>
					<td>${bvo.readCount}</td>
					<td>${bvo.gaechu}</td>
					<td>${bvo.hasFile}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<nav aria-label="Page navigation example">
		<ul class="pagination">
			<c:if test="${ph.prev}">
				<li class="page-item"><a class="page-link"
					href="/board/list?pageNo=${ph.startPage-1}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}"><span
						aria-hidden="true">PREV</span></a></li>
			</c:if>
			<c:forEach begin="${ph.startPage}" end="${ph.endPage}" var="i">
				<li class="page-item"><a class="page-link"
					href="/board/list?pageNo=${i}&qty=${ph.pgvo.qty}">${i}</a></li>
			</c:forEach>
			<c:if test="${ph.next}">
				<li class="page-item" ${(ph.next eq false) ? 'disabled' : ''}><a
					class="page-link"
					href="/board/list?pageNo=${ph.endPage+1}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}"><span
						aria-hidden="true">NEXT</span></a></li>
			</c:if>
		</ul>
	</nav>
</div>
<jsp:include page="../layout/footer.jsp"></jsp:include>