<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<!DOCTYPE html>
<html>
<head>
	<title>Board Write</title>
	
	<script src="http://code.jquery.com/jquery-3.4.0.min.js"></script>
	<script src="resources/js/dropzone.js"></script>
	<link href="resources/css/dropzone.css" rel="stylesheet">
	<script src="resources/js/datatables.min.js"></script>
	<link href="resources/css/bootstrap.min.css" rel="stylesheet">
	
	<script>
	Dropzone.autoDiscover = false;
	var formData = new FormData();
	var file_name;
	
	$(document).ready(function() {
		
		var myDropzone = new Dropzone("div#dZUpload", 	
			{ 	url: "/board_file",
				addRemoveLinks :true,
				removedfile: function(file) {
					formData.delete(file.name);
					file.previewElement.remove();
				}
			});
		
		myDropzone.on("thumbnail", function(file){
			formData.append(file.name, dataURLtoBlob(file.dataURL), file.name);
			console.log(file.dataURL);
		});
	});
	
	$(document).on("click", "#write", function(){
		$.ajax({
			url: "http://localhost:8080/board_file_upload",
			type: "POST",
			contentType : false,
	        processData : false,
			data: formData,
			success : function(data){
				file_name = data;
				
				var str = '<input type="hidden" name="file_name" id="file_name" value="'+file_name+'"/>';
				
				$("#board_form").append(str);
				$("#board_form").append('<input type="submit" id="submit"/>');
				$("#submit").trigger("click");
			}
		});
	});
	
	$(document).on("click" ,".file_delete" ,function(){	
		var element = this;
		var file_name = $(this).attr('id');
		
		$.ajax({
			url: "http://localhost:8080/board_file_delete",
			type: "POST",
			data: file_name,
  			dataType: 'json',
  			contentType: 'application/json; charset=UTF-8',
			success : function(data){
				$(element).parent().remove();
			}
		});
	});
	
	$(document).on("click", "#edit", function(){
		$.ajax({
			url: "http://localhost:8080/board_file_upload",
			type: "POST",
			contentType : false,
	        processData : false,
			data: formData,
			success : function(data){
				file_name = data;
				
				var str = '<input type="hidden" name="file_name" id="file_name" value="'+file_name+'"/>';
				
				$("#board_form").append(str);
				$("#board_form").append('<input type="submit" id="submit"/>');
				$("#submit").trigger("click");
			}
		});
	});
	
	//**dataURL to blob**
	function dataURLtoBlob(dataurl) {
	    var arr = dataurl.split(','), mime = arr[0].match(/:(.*?);/)[1],
	        bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
	    while(n--){
	        u8arr[n] = bstr.charCodeAt(n);
	    }
	    return new Blob([u8arr], {type:mime});
	}
	</script>
</head>
<body  class="jumbotron">
	<%@ include file="header.jsp"%>
	
	<c:if test='${msg == null}'>
		<legend>  Board Write </legend>
		<form method="POST" action="http://localhost:8080/board_write" id="board_form">
			<input type="hidden" name="id" id="id" value="${member.id}"/>
			<h3>Title</h3><br>
			<input class="form-control" type="text" name="title" id="title"/><br>
			<h3>Content</h3><br>
			<input class="form-control" type="text" name="content" id="content"/><br>
		</form>
		<button class="btn btn-primary" id="write">WRITE</button>
		<div class="outerDorpzone">
			<br />
			<div id="dZUpload" class="dropzone">
				<div class="dz-default dz-message">Drop files here or click to upload.</div>
			</div>
		</div>
	</c:if>
	<c:if test='${msg == "edit"}'>
		<legend>  Board Edit </legend>
		<form method="POST" action="http://localhost:8080/board_edit" id="board_form">
			<input type="hidden" name="id" id="id" value="${board_detail.id}"/>
			<input type="hidden" name="board_id" id="board_id" value="${board_detail.board_id}"/>
			<h3>Title</h3><br>
			<input class="form-control" type="text" name="title" id="title" value="${board_detail.title}"/><br>
			<h3>Content</h3><br>
			<input class="form-control" type="text" name="content" id="content" value="${board_detail.content}"/><br>
		</form>
		<c:forEach var="file" items="${file_list}" > 
			<span>
				<img style="width: 400px; height: 400px;" src="<spring:url value='/resources${file.file_name}' />"/>
				<button id="${file.file_name}" class="file_delete">DELETE FILE</button>			
			</span>
		</c:forEach>
		<button class="btn btn-primary" id="edit">EDIT</button>
		<div class="outerDorpzone">
			<br />
			<div id="dZUpload" class="dropzone">
				<div class="dz-default dz-message">Drop files here or click to upload.</div>
			</div>
		</div>
	</c:if>
</body>
</html>