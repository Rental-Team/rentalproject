<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
		<form action="/item/write" method="post" enctype="multipart/form-data">
		    <div class="form-group">
				<label>Title</label> <input class="form-control" name='title'>
			</div>

				<div class="form-group">
					<label>Text area</label>
					<textarea class="form-control" rows="3" name='content'></textarea>
				</div>

			<div class="form-group">
				<label>Writer</label> <input class="form-control" name="writer">
			</div>

		        <div class="buttons">
					<button type="submit">Submit</button>
					<button type="reset">Reset Button</button>
		        </div>
		        </form>
</body>
</html>