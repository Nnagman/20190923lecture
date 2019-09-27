<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Board Write</title>
	
	<script src="http://code.jquery.com/jquery-3.4.0.min.js"></script>
	<script src="resources/js/dropzone.js"></script>
	<link href="resources/css/dropzone.css" rel="stylesheet">
	
	<script>
	Dropzone.autoDiscover = false;
	var formData = new FormData();
	
	$(document).ready(function() {
		
		var myDropzone = new Dropzone("div#dZUpload", 
				
			{ 	url: "/board_file_upload",
				addRemoveLinks :true,
				success: function(file){
					formData.append(file.name, dataURLtoBlob(file.dataURL), file.name);
					console.log(formData.get(file.name));
				},
				removedfile: function(file) {
					formData.delete(file.name);
					file.previewElement.remove();
				}
			});
	});
	
	$(document).on("click", "#write", function(){
		var form = $("#board_form").serialize();
		
		var obj = {"form" : form,"formData" : formData};
		alert(obj);
		
		$.ajax({
			url: "http://localhost:8080/board_write",
			type: "POST",
			dataType: "formData",
			enctype: 'multipart/form-data',
			contentType : false,
	        processData : false,
			data: obj,
			error : function(request, status, error){
				alert(request);
				alert(status);
				alert(error);
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
<body>
	<%@ include file="header.jsp"%>
	
	<c:if test='${msg == null}'>
		<P>  Board Write </P>
		<form id="board_form">
			<input type="hidden" name="id" id="id" value="${member.id}"/>
			Title<br>
			<input type="text" name="title" id="title"/><br>
			Content<br>
			<input type="text" name="content" id="content"/><br>
		</form>
		<button id="write">WRITE</button>
		<div class="outerDorpzone">
			<br />
			<div id="dZUpload" class="dropzone">
				<div class="dz-default dz-message">Drop files here or click to upload.</div>
			</div>
		</div>
	</c:if>
	<c:if test='${msg == "edit"}'>
		<P>  Board Edit </P>
		<form id="board_form">
			<input type="hidden" name="id" id="id" value="${board_detail.id}"/>
			<input type="hidden" name="board_id" id="board_id" value="${board_detail.board_id}"/>
			Title<br>
			<input type="text" name="title" id="title" value="${board_detail.title}"/><br>
			Content<br>
			<input type="text" name="content" id="content" value="${board_detail.content}"/><br>
		</form>
		<button id="edit">EDIT</button>
		<div class="outerDorpzone">
			<br />
			<div id="dZUpload" class="dropzone">
				<div class="dz-default dz-message">Drop files here or click to upload.</div>
			</div>
		</div>
	</c:if>
</body>
</html>