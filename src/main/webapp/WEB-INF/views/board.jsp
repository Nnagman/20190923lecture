<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<head>
	<title>board</title>
	
	<script src="http://code.jquery.com/jquery-3.4.0.min.js"></script>
	<script src="resources/js/datatables.min.js"></script>
	<link href="resources/css/bootstrap.min.css" rel="stylesheet">
	
	<script>
		var msg = "<c:out value="${msg}" />";
		if(msg != ""){
			alert(msg);
		}
	
		$(document).ready(function() {
		    $('#board').DataTable( {
		        "order": [[ 2, "desc" ]]
		    } );
		} );
	</script>
</head>
<body class="jumbotron">
	<%@ include file="header.jsp"%>
	<h2> Board </h2>
	
	<table class="table table-hover" id="board">
	  <thead>
	    <tr>
	      <th scope="col">Title</th>
	      <th scope="col">Writer</th>
	      <th scope="col">Date</th>
	      <th scope="col">views</th>
	    </tr>
	  </thead>
	  <tbody>
	  	<c:forEach var="row" items="${list}">
	  		<c:if test="${row.is_delete == 0}">
			  	<tr>
			      <td><a href="http://localhost:8080/board_detail?board_id=${row.board_id}&&id=${member.id}">${row.title}</a></td>
			      <td>${row.id}</td>
			      <td>${fn:substring(row.board_id, 0, 14)}</td>
			      <td>${row.count}</td>
			    </tr>
		    </c:if>
	  	</c:forEach>
	  </tbody>
	</table>
</body>
</html>