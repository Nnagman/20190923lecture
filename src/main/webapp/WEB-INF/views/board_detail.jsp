<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>board_register</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	
	<P>  Board Detail </P>
	
	<p>${board_detail.title}</p>
	<div>${board_detail.content}</div>
	
	<form action="https://localhost:8080/comment_write" method="POST">
		<input type="text" name="content" id="content"/>
		<input type="hidden" name="board_id" id="${board_id}"/>
		<input type="hidden" name="id" id="${member.id}"/>
	</form>
</body>
</html>