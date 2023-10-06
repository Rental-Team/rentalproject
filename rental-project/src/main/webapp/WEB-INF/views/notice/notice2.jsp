<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- 여기서부터 5개의 공지사항을 표시하는 코드 -->
<div class="table-responsive">  
    <!-- Projects table -->
    <table class="table align-items-center table-flush">
        <thead class="thead-light">
        </thead>
        <tbody>
            <c:forEach var="notice" items="${noticeList}">
                <tr style="text-align:center">
                    <td scope="col" style="width:500px">
                        <a href="/rental-project/notice/detail?noticeNo=${notice.noticeNo}">${notice.noticeTitle}</a>
                         <td scope="col" style="width:150px"><fmt:formatDate value="${ notice.noticeDate }" pattern="yyyy-MM-dd"/></td>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<!-- 여기까지 -->