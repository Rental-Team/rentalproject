<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
		<form action="write" method="post">
		    <div class="form-group">
				<label>��ǰ��</label> <input class="form-control" name='itemName'>
			</div>
				<div class="form-group">
					<label>��ǰ ����</label>
					<textarea class="form-control" rows="3" name='itemPrice'></textarea>
				</div>
				<div class="form-group">
					<label>��ǰ �ڵ�</label>
					<textarea class="form-control" rows="3" name='itemCode'></textarea>
				</div>

				<div class="form-group">
					<label>�� ����</label>
					<textarea class="form-control" rows="3" name='itemDetail'></textarea>
				</div>


		        <div class="buttons">
					<button type="submit">Submit</button>
					<button type="reset">Reset Button</button>
		        </div>
		        </form>
</body>
</html>