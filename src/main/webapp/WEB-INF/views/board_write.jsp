<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Board Write</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	
	<c:if test='${msg == null}'>
		<P>  Board Write </P>
		<form action="/board_write" method="post">
			<input type="hidden" name="id" id="id" value="${member.id}"/>
			Title<br>
			<input type="text" name="title" id="title"/><br>
			Content<br>
			<input type="text" name="content" id="content"/><br>
			<input type="submit" value="WRITE"/>
		</form>
	</c:if>
	<c:if test='${msg == "edit"}'>
		<P>  Board Edit </P>
		<form action="/board_edit" method="post">
			<input type="hidden" name="id" id="id" value="${board_detail.id}"/>
			<input type="hidden" name="board_id" id="board_id" value="${board_detail.board_id}"/>
			Title<br>
			<input type="text" name="title" id="title" value="${board_detail.title}"/><br>
			Content<br>
			<input type="text" name="content" id="content" value="${board_detail.content}"/><br>
			<input type="submit" value="EDIT"/>
		</form>
	</c:if>
</body>
</html>