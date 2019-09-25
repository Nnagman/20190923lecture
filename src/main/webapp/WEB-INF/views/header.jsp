<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${member == null}">
	<a href="http://localhost:8080/login">로그인</a>
	<a href="http://localhost:8080/register">회원가입</a>
	<a href="http://localhost:8080/">게시판</a>
</c:if>
<c:if test="${member != null}">
	<a href="http://localhost:8080/logout">로그아웃</a>
	<a href="http://localhost:8080/board_write">글쓰기</a>
	<a href="http://localhost:8080/">게시판</a>
</c:if>