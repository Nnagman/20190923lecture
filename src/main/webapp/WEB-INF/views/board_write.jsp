<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Board Write</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	
	<P>  Board Write </P>
	<form action="/board_write" method="post">
		<input type="hidden" name="id" id="id" value="${member.id}"/>
		Title<br>
		<input type="text" name="title" id="title"/><br>
		Content<br>
		<input type="text" name="content" id="content"/><br>
		<input type="submit" value="글쓰기"/>
	</form>
</body>
</html>