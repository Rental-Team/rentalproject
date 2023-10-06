<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<h1>Upload with Ajax</h1>

<div class='uploadDiv'>
	<input type="file" name='uploadFile' multiple>
</div>
<button id='uploadBtn'>Upload</button>

<script src="/rental-project/resources/js/plugins/jquery/dist/jquery.min.js"></script>

<script>
$(document).ready(function(){
	
	$("#uploadBtn").on("click", function(e){
		
		var formData = new FormData();
		
		var inputFile = $("input[name='uploadFile']");
		
		var files = inputFile[0].files;
		
		console.log(files);
	});
});
</script>
</body>
</html>