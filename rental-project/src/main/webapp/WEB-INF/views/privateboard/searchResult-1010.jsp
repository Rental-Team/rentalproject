<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>검색 결과</title>
</head>
<body>
    <h1>검색 결과</h1>

    <c:if test="${not empty searchResult}">
        <table>
            <thead>
                <tr>
                    <th>문의 번호</th>
                    <th>질문 제목</th>
                    <th>질문 내용</th>
                    <th>작성자</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>${searchResult.qnaNo}</td>
                    <td>${searchResult.qnaTitle}</td>
                    <td>${searchResult.qnaContent}</td>
                    <td>${searchResult.memberId}</td>
                </tr>
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty searchResult}">
        <p>검색 결과가 없습니다.</p>
    </c:if>

    <a href="<c:url value="/privateqnalist"/>" class="btn btn-primary">목록으로 돌아가기</a>
</body>
</html>
