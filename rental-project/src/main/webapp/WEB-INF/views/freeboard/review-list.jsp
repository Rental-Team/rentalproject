<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


 <table id="review-list" class="table align-items-center table-flush" style="width:100%; padding-left: 0; padding-top:0" >
                   <thead class="thead-light">
                       <tr style="text-align:center">
                       </tr>
                   </thead>
               <tbody>
			    <c:forEach var="freeBoardReview" items="${freeBoardReviews}">
			        <tr style="text-align:center">
			        </tr>
			        <tr>
			            <td>
			                <table>
			                    <tr>
			                        <td style="padding-left:0;">
                            <c:forEach begin="0" end="${freeBoardReview.replyDepth}"> 
                            </c:forEach>
                            <c:if test="${freeBoardReview.replyDepth > 0}">
                                <img src="/rental-project/resources/image/re.gif" width="10px" height="10px"> 
                            </c:if>
                        </td>
                        <td colspan="5">
                            <div class="reply-view-edit-area"
                                id="reply-view-edit-area-${freeBoardReview.freeBoardReplyNo}">
                                <div id="reply-view-area-${freeBoardReview.freeBoardReplyNo}" class="reply-container">
								    <div class="info">
									    ${freeBoardReview.replyWriter} &nbsp;&nbsp;
									    <fmt:formatDate value="${freeBoardReview.replyCreateDate}" pattern="yyyy-MM-dd-HH:mm" />  
									    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									    <a title="추천" id="goodbtn">
									        <img src="/rental-project/resources/img/icons/good.png" alt="추천" width="20" height="20"/>
									        <span id="goodCount" class="FreeBoardGoodCount">0</span>
									    </a>
									    <a title="반대" id="badbtn">
										    <img src="/rental-project/resources/img/icons/bad.png" alt="반대" width="20" height="20"/>
										    <span id="badCount" class="count">0</span> 
									    </a> 
									    <a title="신고" id="reportbtn">
										<img src="/rental-project/resources/img/icons/report.png" alt="신고" width="20" height="20"/>
										</a>
									</div>  
								    <br />
								    <br /> 
								    <c:choose>
								        <c:when test="${not freeBoardReview.replyDelete}">
								            <a> ${freeBoardReview.replyContent}</a>
								            <br>
								            <br>
								        </c:when>
								        <c:otherwise>
								            <span class="replyDelete" style="color: gray"><< 삭제된 댓글입니다 >></span>
								        </c:otherwise>
								    </c:choose>
								    
						    <div class="user-actions">
						        <div style='display:${(not empty loginuser and loginuser.memberId == freeBoardReview.replyWriter and not freeBoardReview.replyDelete)? "block" : "none"}'>
						            <a class="btn btn-sm btn-secondary edit-reply" data-reply-no="${freeBoardReview.freeBoardReplyNo}"
						                href="javascript:void(0)" style="color: navy;">댓글수정</a>
						            &nbsp;
						            <a class="btn btn-sm btn-secondary delete-reply" data-reply-no="${freeBoardReview.freeBoardReplyNo}"
						                href="javascript:void(0)" style="color: navy">댓글삭제</a>
						            &nbsp;&nbsp; 
						            <a class="write-rereply btn btn-sm btn-secondary" data-reply-no="${freeBoardReview.freeBoardReplyNo}"
						                href="javascript:void(0)" style="color: navy">대댓글 작성</a>
						        </div>
						    </div>
    
    					<span style="clear:both"></span>
						</div>
                                <div class="reply-edit-area"
                                    id="reply-edit-area-${freeBoardReview.freeBoardReplyNo}" style="display: none">
                                    ${sessionScope.loginuser.memberId} &nbsp;&nbsp;
                                    [${freeBoardReview.replyCreateDate}] <br />
                                    <br />
                                    <form action="edit-reply" method="post"
                                        style="width: 200%; resize: none;">
                                        <input type="hidden" name="freeBoardReplyNo"
                                            value="${freeBoardReview.freeBoardReplyNo}" />
                                        <textarea name="replyContent"
                                            style="width: 150%; resize: none; border-radius: 80px"
                                            rows="2" maxlength="200">${freeBoardReview.replyContent}</textarea>
                                        <br />
                                        <br />
                                        <div class="btn btn-sm btn-secondary">
                                            <a class="update-reply" data-reply-no="${freeBoardReview.freeBoardReplyNo}"
                                                href="javascript:void(0)" style="color: ligtblue">수정완료</a>
                                        </div>
                                        <div class="btn btn-sm btn-secondary">
                                            <a class="cancel-edit-reply" data-reply-no="${freeBoardReview.freeBoardReplyNo}"
                                                href="javascript:void(0)" style="color: ligtblue">수정취소</a>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </c:forEach>
</tbody>
               </table>