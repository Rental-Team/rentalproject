<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

  <table id="review-list" class="table align-items-center table-flush" style="width:100%; padding-left: 0; padding-top:0">
					<thead class="thead-light">
                       <tr style="text-align:center">
                       </tr>
                   </thead>
               <tbody> 
					   <c:forEach var="itemReview" items="${itemReviews}">
			        <tr style="text-align:center">
			        </tr>
			        <tr>
			            <td>
			                <table>
			                    <tr>
			                        <td style="padding-left:0;">
                            <c:forEach begin="0" end="${itemReview.depth}"> 
                            </c:forEach>
                            <c:if test="${itemReview.depth > 0}">
                                <img src="/rental-project/resources/image/re.gif" width="10px" height="10px"> 
                            </c:if>
                        </td>
                        <td colspan="5">
                            <div class="review-edit-area"
                                id="review-edit-area-${itemReview.itemNo}">
                                <div id="repview-area-${itemReview.itemNo}" class="reply-container">
								    <div class="info">
									    ${itemReview.reviewWriter} &nbsp;&nbsp;
									    <fmt:formatDate value="${itemReview.reviewDate}" pattern="yyyy-MM-dd-HH:mm" />   
									</div>  
								    <br />
								    <br /> 
								    <c:choose>
								        <c:when test="${not itemReview.itemReviewDelete}">
								            <a> ${itemReview.reviewContent}</a>
								            <br>
								            <br>
								        </c:when>
								        <c:otherwise>
								            <span class="itemReviewDelete" style="color: gray"><< 삭제된 후기입니다 >></span>
								        </c:otherwise>
								    </c:choose>
								    
						    <div class="user-actions">
						        <div style='display:${(not empty loginuser and loginuser.memberId == itemReview.reviewWriter and not itemReview.itemReviewDelete)? "block" : "none"}'>
						            <a class="btn btn-sm btn-secondary edit-review" data-review-no="${itemReview.reviewNo}"
						                href="javascript:void(0)" style="color: navy;">수정</a>
						            &nbsp;
						            <a class="btn btn-sm btn-secondary delete-review" data-review-no="${itemReview.reviewNo}" data-item-no="${item.itemNo}" href="javascript:void(0)"
						                href="javascript:void(0)" style="color: navy">삭제</a>
						            &nbsp;&nbsp; 
					            </div>
					           <div style='display:${(not empty loginuser and loginuser.memberNo == 17 and not itemReview.itemReviewDelete)? "block" : "none"}'>
						            <a class="write-reply btn btn-sm btn-secondary" data-review-no="${itemReview.reviewNo}"
						                href="javascript:void(0)" style="color: navy">후기답변작성</a>
					        	</div>
						    </div> 
    					<span style="clear:both"></span>
						</div> 
						<div class="review-edit-area"
                                    id="review-edit-area-${itemReview.reviewNo}" style="display: none">
                                    ${sessionScope.loginuser.memberId} &nbsp;&nbsp;
                                    [${itemReview.reviewDate}] <br />
                                    <br />
                                    <form action="edit-review" method="post"
                                        style="width: 200%; resize: none;">
                                        <input type="hidden" name="reviewNo"
                                            value="${itemReview.reviewNo}" />
                                        <textarea name="reviewContent"
                                            style="width: 150%; resize: none; border-radius: 80px"
                                            rows="2" maxlength="200">${itemReview.reviewContent}</textarea>
                                        <br />
                                        <br />
                                        <div class="btn btn-sm btn-secondary">
                                            <a class="update-review" data-review-no="${itemReview.reviewNo}"
                                                href="javascript:void(0)" style="color: ligtblue">수정완료</a>
                                        </div>
                                        <div class="btn btn-sm btn-secondary">
                                            <a class="cancel-edit-review" data-review-no="${itemReview.reviewNo}"
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