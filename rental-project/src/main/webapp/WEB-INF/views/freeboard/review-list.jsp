<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<table id="review-list" class="table align-items-center table-flush">
                    <thead class="thead-light">
                        <tr style="text-align:center">
                            <!-- 테이블 헤더 설정 ... -->
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="freeBoardReview" items="${freeBoardReviews}">
                            <tr style="text-align:center">
                                <!-- 테이블 데이터 설정 ... -->
                            </tr>
                            <!-- 댓글 뷰 영역과 수정 폼 영역을 하나의 div로 묶기 -->
                            <tr>
                                <td colspan="5">
                                    <div class="reply-view-edit-area" id="reply-view-edit-area-${freeBoardReview.freeBoardReplyNo}">
                                        <!-- 댓글 뷰 영역 -->
                                        <div id="reply-view-area-${freeBoardReview.freeBoardReplyNo}">
                                           ${freeBoardReview.replyWriter} &nbsp;&nbsp; 
                                           <fmt:formatDate value="${freeBoardReview.replyCreateDate}" pattern="yyyy-MM-dd-HH:mm" />  <br />
                                            <br />
                                            <c:choose>
                                                <c:when test="${ not freeBoardReview.replyDelete }">
                                                    <a> ${ freeBoardReview.replyContent }</a>
                                                    <br>
                                                    <br>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="replyDelete" style="color : gray"><< 삭제된 댓글입니다 >></span> 
                                                </c:otherwise>
                                            </c:choose>
                                            <!-- 수정 버튼 추가 -->
                                            <div style='display:${ (not empty loginuser and loginuser.memberId == freeBoardReview.replyWriter and not freeBoardReview.replyDelete)? "block" : "none"}'>
                                                <a class="btn btn-sm btn-secondary edit-reply" data-reply-no="${freeBoardReview.freeBoardReplyNo}" href="javascript:void(0)" style="color: navy;">댓글수정</a>
                                                &nbsp;
                                                <a class="btn btn-sm btn-secondary delete-reply" data-reply-no="${freeBoardReview.freeBoardReplyNo}" href="javascript:void(0)" style="color: navy">댓글삭제</a>
                                            </div>
                                        </div>
                                        <!-- 댓글 수정 폼 영역 (초기에는 display:none) -->
                                        <div class="reply-edit-area" id="reply-edit-area-${freeBoardReview.freeBoardReplyNo}" style="display: none">
                                            ${sessionScope.loginuser.memberId} &nbsp;&nbsp; [${freeBoardReview.replyCreateDate}] <br />
                                            <br />
                                            <form action="edit-reply" method="post" style="width: 105%; resize: none;">
                                                <input type="hidden" name="freeBoardReplyNo" value="${freeBoardReview.freeBoardReplyNo}" />
                                                <textarea name="replyContent" style="width: 70%; resize: none; border-radius: 80px" rows="2" maxlength="200" >${freeBoardReview.replyContent}</textarea>
                                                <br />
                                                <br />
                                                <div class="btn btn-sm btn-secondary">
                                                    <a class="update-reply" data-reply-no="${freeBoardReview.freeBoardReplyNo}" href="javascript:void(0)" style="color: ligtblue">수정완료</a>
                                                </div>
                                                <div class="btn btn-sm btn-secondary">
                                                    <a class="cancel-edit-reply" data-reply-no="${freeBoardReview.freeBoardReplyNo}" href="javascript:void(0)" style="color: ligtblue">수정취소</a>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>