<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
		<form action="write" method="post" enctype="multipart/form-data">
		        <table>
		            <tr>
		                <th>제목</th>
		                <td>
		                    <input type="text" name="title" style="width:580px">
		                </td>
		            </tr>
		            <tr>
		                <th>작성자</th>
		                <td>
		                	
		                	${ loginuser.memberId }
		                	<input type="hidden" name="writer" value="${ loginuser.memberId }">
		                	
		                	<%-- 
		                	<input type="text" name="writer" value="${ loginuser.memberId }" 
		                		   style="width:580px" readonly>
		                	--%>
		                </td>
		            </tr>
		            <tr>
		                <th>첨부파일</th>
		                <td>
		                	<input type="file" name="attach" style="width:580px">
		                </td>
		            </tr>
		            <tr>
		                <th>글내용</th>
		                <td>
		                	<textarea name="content" style="width:580px" rows="15"></textarea>
		                </td>
		            </tr>
		        </table>
		        <div class="buttons">
		        	<input type="submit" value="글쓰기" style="height:25px" />
		        	<input type="button" id="btnCancel" value="취소" style="height:25px" />
		        </div>
		        </form>
</body>
</html>