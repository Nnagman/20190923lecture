<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="collapse navbar-collapse" id="navbarColor01">
		<ul class="navbar-nav mr-auto">
			<c:if test="${member == null}">
				<li class="nav-item">
					<a class="nav-link" href="http://localhost:8080/login">로그인</a>
				</li>
				
				<li class="nav-item">
					<a class="nav-link" href="http://localhost:8080/register">회원가입</a>
				</li>
				
				<li class="nav-item">
					<a class="nav-link" href="http://localhost:8080/board">게시판</a>
				</li>
			</c:if>
			<c:if test="${member != null}">
				<li class="nav-item">
					<a class="nav-link" href="http://localhost:8080/logout">로그아웃</a>
				</li>
				
				<li class="nav-item">
					<a class="nav-link" href="http://localhost:8080/board_write">글쓰기</a>
				</li>
				
				<li class="nav-item">
					<a class="nav-link" href="http://localhost:8080/board">게시판</a>
				</li>
			</c:if>
		</ul>
	</div>
</nav>