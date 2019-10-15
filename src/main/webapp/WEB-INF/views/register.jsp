<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WELCOME</title>

<script>
	function myFunction(){
		document.getElementById("register").click();
	}
</script>
</head>
<body style="opacity: 1; background-color: rgb(0, 0, 0);">
	<p style="white-space: inherit; text-align: center; font-size: 81px; line-height: 117px; margin-bottom: 0;" data-size-leading-linked="true" data-size-leading-ratio="1.4444444444444444">
		<span style="color: #f2fcfc;">REGISTER</span>
	</p>
	<form style="margin: 0 auto; width:250px;" method="post" action="/register">
		<input type="text" placeholder="ID" name="id" style="height: 48px; width: 286px; margin-bottom: 20px; background-color: rgb(247, 247, 247); border-radius: 0px; box-shadow: rgb(0, 0, 0) 0px 0px 0px 0px inset; font-family: Roboto; font-style: normal; font-weight: 400; letter-spacing: 0px; font-size: 18px;"/>
		<input type="password" placeholder="PASSWORD" name="password" style="height: 48px; width: 286px; margin-bottom: 20px; background-color: rgb(247, 247, 247); border-radius: 0px; box-shadow: rgb(0, 0, 0) 0px 0px 0px 0px inset; font-family: Roboto; font-style: normal; font-weight: 400; letter-spacing: 0px; font-size: 18px;">
		<input type="password" placeholder="NAME" name="name" style="height: 48px; width: 286px; margin-bottom: 20px; background-color: rgb(247, 247, 247); border-radius: 0px; box-shadow: rgb(0, 0, 0) 0px 0px 0px 0px inset; font-family: Roboto; font-style: normal; font-weight: 400; letter-spacing: 0px; font-size: 18px;">
		<div class="button" style="height: 48px; width: 286px; line-height: 48px; background-color: rgb(76, 76, 76); border-radius: 0px; box-shadow: rgb(0, 0, 0) 0px 0px 0px 0px inset; font-family: Roboto; font-style: normal; font-weight: 400; color: rgb(255, 255, 255); font-size: 18px; letter-spacing: 0px; text-align: center; text-indent: 0px;" data-default="height: 48px; width: 286px; line-height: 48px; background-color: rgb(76, 76, 76); border-radius: 0px; box-shadow: rgb(0, 0, 0) 0px 0px 0px 0px inset; font-family: Roboto; font-style: normal; font-weight: 400; color: rgb(255, 255, 255); font-size: 18px; letter-spacing: 0px; text-align: center; text-indent: 0px;" data-hover="height: 48px; width: 286px; line-height: 48px; background-color: rgba(76, 76, 76, 0.9); border-radius: 0px; box-shadow: rgb(0, 0, 0) 0px 0px 0px 0px inset; font-family: Roboto; font-style: normal; font-weight: 400; color: rgb(255, 255, 255); font-size: 18px; letter-spacing: 0px; text-align: center; text-indent: 0px;">
			<div class="caption" onclick="myFunction()">DONE</div>
		</div>
		<input type="submit" id="register" style="display: none;"/>
	</form>
</body>
</html>