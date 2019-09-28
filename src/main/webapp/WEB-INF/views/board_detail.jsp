<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<!DOCTYPE html>
<html>
<head>
<title>board_register</title>

</head>
<body>
	<%@ include file="header.jsp"%>
	<P>  Board Detail </P>
	Title<br>
	<p>${board_detail.title}</p>
	Content<br>
	<div>${board_detail.content}</div>
	<c:forEach var="file" items="${file_list}" > 
		<span class="viewImg">
			<img style="width: 400px; height: 400px;" src="<spring:url value='/resources${file.file_name}' />" />				
		</span>
	</c:forEach>
	<c:if test="${board_detail.id == member.id}">
		<a href="http://localhost:8080/board_edit?board_id=${board_detail.board_id}">EDIT</a>
		<a href="http://localhost:8080/board_delete?board_id=${board_detail.board_id}">DELETE</a>
	</c:if>
	<hr>
	<form action="http://localhost:8080/comment_write" method="POST">
		<input type="text" name="content" id="content"/>
		<input type="hidden" name="board_id" id="${board_detail.board_id}" value="${board_detail.board_id}"/>
		<input type="hidden" name="id" id="${member.id}" value="${member.id}"/>
		<input type="submit" value="댓글 쓰기"/>
	</form>
	<c:forEach var="row" items="${list}">
		<c:if test="${row.is_delete == 0}">
			<p>ID : ${row.id}</p>
			<p>${row.content}</p>
		</c:if>
		<c:if test="${row.is_delete == 1}">
			<p>ID : ${row.id}</p>
			<p>삭제된 댓글입니다.</p>
		</c:if>
		<hr>
	</c:forEach>
</body>
</html>