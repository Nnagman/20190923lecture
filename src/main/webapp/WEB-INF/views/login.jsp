<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
	<title>Login</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	
	<P>  Login </P>
	<form method="post" action="/login">
		<input type="text" id="id" name="id"/>
		<input type="password" id="password" name="password"/>
		<input type="submit" value="login"/>
	</form>
</body>
</html>
