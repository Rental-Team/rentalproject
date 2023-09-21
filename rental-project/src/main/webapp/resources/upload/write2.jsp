<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>write</title>
</head>
<body>


		
		<div style="padding-top:25px;text-align:center">
		<div id="inputcontent">
		    <div id="inputmain">
		       
		       <form action="write" method="post">
		        <table>
		            <tr>
		                <th>제목</th>
		                <td>
		                    <input type="text" name="qnaTitle" style="width:580px" />
		                </td>
		            </tr>
		            <tr>
		                <th>작성자</th>
		                <td>
		                	
		                </td>
		            </tr>
		             <tr>
		                <th>문의유형</th>
		                <td>
		                	<input type="text" name="qnaType" />
		                </td>
		            </tr>
		            
		            
		            <tr>
		                <th>글내용</th>
		                <td>
		                	<textarea name="qnaContent" style="width:580px" rows="15"></textarea>
		                </td>
		            </tr>
		        </table>
		        <div class="buttons">
		        	<input type="submit" value="글쓰기" style="height:25px" />
		        	<input type="button" value="취소" style="height:25px"  />
		        </div>
		        </form>
		    </div>
		</div>   	
	
	</div>
	




</body>
</html>