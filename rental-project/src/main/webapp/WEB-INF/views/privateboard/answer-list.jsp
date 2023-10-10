<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>





		<table id="answer-list" style="text-align: center"
			class="table align-items-center table-flush">
			<thead class="thead-light">
				<tr style="text-align: center">
					<th scope="col" style="width: 300px">답변내용</th>
					<!--   <th scope="col" style="width:150px">답변작성일자</th> -->
				</tr>
			</thead>
			<tbody>
				<!--답변 조회 됨 -->
				<c:forEach var="privateAnswer"
					items="${ privateqna.privateQnaAnswerList }">
					<tr style="text-align: center"
						id="answer-view-area-${ privateAnswer.qnaNo }">
						<td scope="col" style="width: 100px">${ privateAnswer.answerContent }</td>
						<%-- <td scope="col" style="width:200px">${ sessionScope.loginuser.memberId }</td> --%>
						<td><c:if test="${requestScope.memberNo == 17}">
								<a class="btn btn-sm btn-primary edit-answer-link"
									data-reply-no="${privateAnswer.qnaNo}"
									href="javascript:void(0)" style="color: white">답변수정</a>
							</c:if></td>
					</tr>
					<div id="answer-edit-area-${privateAnswer.qnaNo}"
						style="display: none">
						<!--답변 수정 -->
						<form action="edit-answer" method="post"
							style="width: 105%; resize: none;">
							<input type="hidden" name="qnaNo" value="${privateAnswer.qnaNo}">
							<textarea name="answerContent" style="width: 100%; resize: none;">${privateAnswer.answerContent}</textarea>
							 <input type="submit" value="저장" onclick="goToPage(${pageNo})">
						</form>
					</div>
				</c:forEach>
			</tbody>
		</table>




		
  
  
