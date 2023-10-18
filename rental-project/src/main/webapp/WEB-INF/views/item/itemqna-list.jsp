<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

  <table id="itemqna-list" class="table align-items-center table-flush" style="width:100%; padding-left: 0; padding-top:0">
					<thead class="thead-light">
                       <tr style="text-align:center">
                       </tr>
                   </thead>
               <tbody> 
					   <c:forEach var="itemQna" items="${itemQnas}">
			        <tr style="text-align:center">
			        </tr>
			        <tr>
			            <td>
			                <table>
			                    <tr>
			                        <td style="padding-left:0;">
                            <c:forEach begin="0" end="${itemQna.depth}"> 
                            
                            </c:forEach>
                            <c:if test="${itemQna.depth > 0}">
                             &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; 
                                <img src="/rental-project/resources/image/re.gif" width="10px" height="10px">
                                &nbsp; 
                            </c:if>
                        </td>
                        <td colspan="5">
                            <div class="itemqna-edit-area"
                                id="itemqna-edit-area-${itemQna.itemNo}">
                                <div id="itemqna-area-${itemQna.itemNo}" class="reply-container">
								    <div class="info">
									    ${itemQna.itemQnaWriter} &nbsp;&nbsp;
									    <fmt:formatDate value="${itemQna.itemqnaDate}"/>   
									</div>  
								    <br />
								    <br /> 
								    <c:choose>
								        <c:when test="${not itemQna.itemqnaDelete}">
								            <a>${itemQna.itemqnaContent}</a>
								            <br>
								            <br>
								        </c:when>
								        <c:otherwise>
								            <span class="itemQnaDelete" style="color: gray"><< 삭제된 문의입니다 >></span>
								        </c:otherwise>
								    </c:choose>
								    
						    <div class="user-actions">
						        <div style='display:${(not empty loginuser and loginuser.memberId == itemQna.itemQnaWriter and not itemQna.itemqnaDelete)? "block" : "none"}'>
						            <a class="btn btn-sm btn-secondary edit-itemqna" data-itemqna-no="${itemQna.itemqnaNo}"
						                href="javascript:void(0)" style="color: navy;">수정</a>
						            &nbsp;
						            <a class="btn btn-sm btn-secondary delete-itemqna" data-itemqna-no="${itemQna.itemqnaNo}" data-item-no="${item.itemNo}" href="javascript:void(0)"
						                href="javascript:void(0)" style="color: navy">삭제</a>
						            &nbsp;&nbsp; 
					            </div>
					           <div style='display:${(not empty loginuser and loginuser.memberNo == 17 and not itemQna.itemqnaDelete)? "block" : "none"}'>
						            <a class="write-reply btn btn-sm btn-secondary" data-itemqna-no="${itemQna.itemqnaNo}"
						                href="javascript:void(0)" style="color: navy">문의답변작성</a>
					        	</div>
						    </div> 
    					<span style="clear:both"></span>
						</div> 
						<div class="itemqna-edit-area"
                                    id="itemqna-edit-area-${itemQna.itemqnaNo}" style="display: none">
                                    ${sessionScope.loginuser.memberId} &nbsp;&nbsp;
                                    [${itemQna.itemqnaDate}] <br />
                                    <br />
                                    <form action="edit-itemqna" method="post"
                                        style="width: 200%; resize: none;">
                                        <input type="hidden" name="itemqnaNo"
                                            value="${itemQna.itemqnaNo}" />
                                        <textarea name="itemqnaContent"
                                            style="width: 150%; resize: none; border-radius: 80px"
                                            rows="2" maxlength="200">${itemQna.itemqnaContent}</textarea>
                                        <br />
                                        <br />
                                        <div class="btn btn-sm btn-secondary">
                                            <a class="update-itemqna" data-itemqna-no="${itemQna.itemqnaNo}"
                                                href="javascript:void(0)" style="color: ligtblue">수정완료</a>
                                        </div>
                                        <div class="btn btn-sm btn-secondary">
                                            <a class="cancel-edit-itemqna" data-itemqna-no="${itemQna.itemqnaNo}"
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