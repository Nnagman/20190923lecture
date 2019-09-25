<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>register</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	
	<P>  Register </P>
	<form method="post" action="/register">
		ID <input type="text" id="id" name="id"/>
		PASSWORD <input type="password" id="password" name="password"/>
		NAME <input type="text" id="name" name="name"/>
		<input type="submit" value="register"/>
	</form>
</body>
</html>