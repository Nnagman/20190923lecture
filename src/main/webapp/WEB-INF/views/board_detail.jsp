<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<!DOCTYPE html>
<html>
<head>
	<title>board_register</title>
	
	<script src="http://code.jquery.com/jquery-3.4.0.min.js"></script>
	<script src="resources/js/datatables.min.js"></script>
	<link href="resources/css/bootstrap.min.css" rel="stylesheet">
	
	<script>
		$(document).ready(function() {
			$(document).on("click", ".comment_edit" , function(){	
				$(this).parent().children('.id').hide();
				$(this).parent().children('.content').hide();
				$(this).parent().children('.delete').hide();
				$(this).parent().children('.comment_edit').hide();
				$(this).parent().children('.comment_edit_form').show();
			});
		});
	</script>

</head>
<body  class="jumbotron">
	<%@ include file="header.jsp"%>
	<h4>  Board Detail </h4>
	<hr><h3>Title</h3>
	<p class="lead">${board_detail.title}</p>
	<hr><h3>Content</h3>
	<p class="lead">${board_detail.content}</p>
	<hr>
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
		<input class="form-control" type="text" name="content" id="content"/>
		<input type="hidden" name="board_id" id="${board_detail.board_id}" value="${board_detail.board_id}"/>
		<input type="hidden" name="id" id="${member.id}" value="${member.id}"/>
		<input class="btn btn-primary" type="submit" value="comment wirte"/>
	</form>
	<hr>
	<c:forEach var="row" items="${list}">
		<c:if test="${row.is_delete == 0}">
			<div>
				<p class="id">ID : ${row.id}</p>
				<p class="content">${row.content}</p>
				<c:if test="${row.id == member.id}">
					<a href="http://localhost:8080/comment_delete?comment_id=${row.comment_id}&&id=${member.id}&&board_id=${board_detail.board_id}" class="delete">DELETE</a>
					<button class="comment_edit">EDIT</button>
					<form action="http://localhost:8080/comment_modify" method="POST" class="comment_edit_form" style="display: none;">
						<input class="form-control" type="text" name="content" id="content"/>
						<input type="hidden" name="id" value="${member.id}"/>
						<input type="hidden" name="comment_id" value="${row.comment_id}" />
						<input type="hidden" name="board_id" value="${board_detail.board_id}" />
						<input class="btn btn-primary" type="submit" value="comment edit"/>
					</form>
				</c:if>
			</div>
		</c:if>
		<c:if test="${row.is_delete == 1}">
			<p>ID : ${row.id}</p>
			<p>삭제된 댓글입니다.</p>
		</c:if>
		<hr>
	</c:forEach>
</body>
</html>