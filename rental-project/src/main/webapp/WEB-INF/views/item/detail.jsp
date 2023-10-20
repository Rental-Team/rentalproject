<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!--

=========================================================
* Argon Dashboard - v1.1.2
=========================================================

* Product Page: https://www.creative-tim.com/product/argon-dashboard
* Copyright 2020 Creative Tim (https://www.creative-tim.com)
* Licensed under MIT (https://github.com/creativetimofficial/argon-dashboard/blob/master/LICENSE.md)

* Coded by Creative Tim

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software. -->
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  
  <!-- Favicon -->
  <link href="/rental-project/resources/img/brand/favicon.png" rel="icon" type="image/png">
  <!-- Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet">
  <!-- Icons -->
  <link href="/rental-project/resources/js/plugins/nucleo/css/nucleo.css" rel="stylesheet" />
  <link href="/rental-project/resources/js/plugins/@fortawesome/fontawesome-free/css/all.min.css" rel="stylesheet" />
  <!-- CSS Files -->
  <link href="/rental-project/resources/css/argon-dashboard.css?v=1.1.2" rel="stylesheet" />
  <link href="/rental-project/resources/css/navbar-top.css" rel="stylesheet" />
    <link href="/rental-project/resources/css/custom-btn.css" rel="stylesheet" />
  
  <style>
  .float-right {
    float: right;
}
   .nav-link2 {
    border: none;
    font-size: 35px;
    border-bottom: none
    position: relative; /* 요소의 위치를 상대적으로 설정합니다. */
    z-index: 1; /* 텍스트가 배경색 위에 표시될 순서를 설정합니다. */
    color: black;
   
    
  
}

@font-face {
    font-family: 'neon';
    src: url("/rental-project/resources/fonts/225.ttf") format('truetype');
}

.nav-link.active {
    border: none;
    border-bottom: none;
}

.container {
   
      z-index: 0; /* 배경색이 텍스트 뒤에 나타날 순서를 설정합니다. */
     padding: 2px; 
     
}
.nav-link2:hover {
	
    text-decoration: underline double; /* 강조선 표시 */
    text-decoration-color: rgba(255, 0, 0, 0.2); /* 강조선 색상 설정 (흰색) */
}

.nav-link2.item {
	
    text-decoration: underline double; /* 강조선 표시 */
    text-decoration-color: rgba(255, 0, 0, 0.2); /* 강조선 색상 설정 (흰색) */
    font-size:40px;
}

</style>
</head>

<body class="">
  
	<div class="main-content">
		<!-- Navbar -->
		<jsp:include page="/WEB-INF/views/modules/navbar-top.jsp" />
		<jsp:include page="/WEB-INF/views/modules/navbar-top2.jsp" />
		<jsp:include page="/WEB-INF/views/modules/navbar-top3.jsp" />
		<jsp:include page="/WEB-INF/views/modules/navbar-top4.jsp" />
		<!-- End Navbar -->
		<!-- Header -->
		<jsp:include page="/WEB-INF/views/modules/navbar-content5.jsp" />
	<div class="container-fluid mt--7">
		<div class="row">
			<div class="col-xl-12 mb-5 mb-xl-0">
				<div class="card shadow">
					<div class="card-header border-0">
						<div class="row align-items-center">
							<div class="col">
								<h3 class="mb-1">상품 설명</h3>
							</div>
							<div class="col text-right">
								<a href="list" class="btn btn btn-success">목록</a>

							</div>
						</div>
					</div>
					<form action="write" method="post">
						<input type="hidden" class="itemNo_input" name="itemNo" value="${ item.itemNo }" /> 
						<input type="hidden" name="pageNo" value="${ pageNo }" /> 
						<input type="hidden" name="writer" value="${ loginuser.memberNo }" />
						<div class="card-body">
							<input disabled="disabled" type="hidden" id="itemNo" name="itemNo" class="form-control form-control-alternative" value="${ item.itemNo }">
							<!-- <h6 class="heading-small text-muted mb-4">User information</h6> -->
							<div class="container">
								<br>
								<div class="pl-lg-10" style="margin: 0 auto;">
									<div class="row">
										<div class="col-lg-7">
											<!-- 오른쪽 영역 -->
											<br> <br> <br> <br>
											<div class="row">
												<div class="col-lg-11">
													<div id="imageCarousel" class="carousel slide"
														data-ride="carousel">
														<!-- 이미지 슬라이드 인디케이터 (점) -->
														<ol class="carousel-indicators">
															<li data-target="#imageCarousel" data-slide-to="0"
																class="active"></li>
															<li data-target="#imageCarousel" data-slide-to="1"></li>
															<li data-target="#imageCarousel" data-slide-to="2"></li>
															<li data-target="#imageCarousel" data-slide-to="3"></li>
															<li data-target="#imageCarousel" data-slide-to="4"></li>
															<!-- 필요에 따라 슬라이드 수를 조절하세요. -->
														</ol>

														<!-- 이미지 슬라이드 내용 -->
														<div class="carousel-inner text-center"
															style="width: 600; height: 600; border-radius: 10px;">
															<c:forEach var="itemAttach" varStatus="status"
																items="${item.itemAttachList}">
																<div
																	class="carousel-item${status.index == 0 ? ' active' : ''}">
																	<img
																		src="${pageContext.request.contextPath}/resources/upload/${itemAttach.savedFileName}"
																		alt="Image${status.index + 1}"
																		style="border-radius: 10px;" height="600px"
																		width="600px">
																</div>
															</c:forEach>
														</div>
														<!-- 슬라이드 컨트롤 (이전/다음) -->
														<a class="carousel-control-prev" href="#imageCarousel"
															role="button" data-slide="prev"> <span
															class="carousel-control-prev-icon" aria-hidden="true"></span>
															<span class="sr-only">Previous</span>
														</a> 
														<a class="carousel-control-next" href="#imageCarousel"
															role="button" data-slide="next"> <span
															class="carousel-control-next-icon" aria-hidden="true"></span>
															<span class="sr-only">Next</span>
														</a>
													</div>
												</div>
											</div>
										</div>
										<div class="col-lg-5">
											<!-- 오른쪽 영역 (빈 영역) -->
											<div class="pl-lg-13" style="margin: 0 auto;">
												<div class="row">
													<div class="col-lg-12">
														<div class="form-group focused">
															<label class="form-control-label" for="input-itemName">상품명</label>
															<input disabled="disabled" type="text"
																id="input-itemName" name="itemName"
																class="form-control form-control-alternative"
																value="${item.itemName}">
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-lg-12">
														<div class="form-group">
															<label class="form-control-label" for="input-itemStock">상품
																수량</label> <input disabled="disabled" type="text"
																id="input-itemStock" name="itemStock"
																class="form-control form-control-alternative"
																value="${item.itemStock}">
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-lg-12">
														<div class="form-group">
															<label class="form-control-label" for="input-cateName">카테고리</label>
															<input disabled="disabled" type="text"
																id="input-cateName" name="cateName"
																class="form-control form-control-alternative"
																value="${item.cateName}">
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-lg-12">
														<div class="form-group">
															<label class="form-control-label" for="input-itemDetail">상세
																설명</label>
															<textarea disabled="disabled" id="input-itemDetail"
																name="itemDetail"
																class="form-control form-control-alternative" rows="13"
																style="resize: none">${item.itemDetail}</textarea>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-lg-12">
														<div class="form-group">
															<label class="form-control-label" for="input-itemPrice">상품
																가격</label> <input disabled="disabled" type="number"
																id="input-itemPrice" name="itemPrice"
																class="form-control form-control-alternative"
																value="${item.itemPrice}">
															<div>
																적립 포인트: <span class="point_span"></span>원
															</div>
														</div>
													</div>
													<div class="col-lg-8"></div>
												</div>
												<div class="row">
													<div class="col-lg-12">
														<div class="button_count">
															<div class="row align-items-center">
																<div class="col">
																	<label class="form-control-label" for="input-itemCount">대여수량</label>
																</div>
																<div class="col">
																	<input type="text"
																		class="quantity_input form-control custom-small-input"
																		style="width: 3cm; height: 1cm"
																		aria-label="Sizing example input"
																		aria-describedby="inputGroup-sizing-xs" value="1">
																</div>
																<div class="col">
																	<button class="plus_btn btn-outline-success">+</button>
																	<button class="minus_btn btn-outline-success">-</button>
																</div>
															</div>
														</div>
														<br> <br>
														<div class="text-right">
															<button type="button" id="btn_zzim" class='btn btn-outline-success float-right'>찜하기</button>
															<button type="button" id="btn_rental" class='btn btn-outline-success float-right'>대여하기</button>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</form> 
					<br />
					<br />
					<div class="row mt-1">
						<div class="col-lg-12">
							<div class="btn-group btn-group-toggle" data-toggle="buttons" style="width: 100%;">
								<label class="btn btn-success active product-review" style="width: 100%;">
									<input type="radio" name="options" id="option1" checked> 상품 후기
								</label> 
								<label class="btn btn-success product-qna" style="width: 100%;"> 
									<input type="radio" name="options" id="option2"> 상품 문의
								</label>
							</div>
						</div>
						<!-- 상품 후기 리스트 보기 기능 구현  -->
						<div class="col-lg-12 mb-5 mb-xl-0" id="itemReviewSection">
							<div class="card shadow" style="width: 100%;">
								<div class="card-body">
									<div class="card-header border-0">
										<div class="row align-items-center">
											<div class="col">
												<h4 class="mb-0">상품 후기</h4>
												<form id="itemReviewForm" action="item-review"
													method="post">
													<input type="hidden" name="itemNo"
														value="${ item.itemNo }" /> <input type="hidden"
														name="pageNo" value="${ pageNo }" /> <input
														type="hidden" name="reviewWriter"
														value="${ loginuser.memberId }" />
													<table class="table align-items-center">
														<tr>
															<td style="width: 1700px;"><textarea
																	id="review_content" name="reviewContent"
																	style="width: 100%; resize: none; border-radius: 80px"
																	rows="2">     </textarea></td>
															<td style="vertical-align: middle; text-align: right;">
																<a class="btn btn-sm btn-success"
																id="write-item-review-lnk" href="javascript:void(0)">상품
																	후기 등록</a>
															</td>
													</table>
												</form>
												<table id="review-list"
													class="table align-items-center table-flush"
													style="width: 100%; padding-left: 0; padding-top: 0">
													<thead class="thead-light">
														<tr style="text-align: center">
														</tr>
													</thead>
													<tbody>
														<c:forEach var="itemReview" items="${itemReviews}">
															<tr style="text-align: center">
															</tr>
															<tr>
																<td>
																	<table>
																		<tr>
																			<td style="padding-left: 0;"><c:forEach
																					begin="0" end="${itemReview.depth}">
																				</c:forEach> <c:if test="${itemReview.depth > 0}">
																					<img src="/rental-project/resources/image/re.gif"
																						width="10px" height="10px">
																				</c:if></td>
																			<td colspan="5">
																				<div class="review-edit-area"
																					id="review-edit-area-${itemReview.itemNo}">
																					<div id="review-area-${itemReview.itemNo}"
																						class="reply-container">
																						<div class="info">
																							${itemReview.reviewWriter} &nbsp;&nbsp;
																							<fmt:formatDate value="${itemReview.reviewDate}"
																								pattern="yyyy-MM-dd-HH:mm" />
																						</div>
																						<br /> <br />
																						<c:choose>
																							<c:when
																								test="${not itemReview.itemReviewDelete}">
																								<a> ${itemReview.reviewContent}</a>
																								<br>
																								<br>
																							</c:when>
																							<c:otherwise>
																								<span class="itemReviewDelete"
																									style="color: gray"><< 삭제된 후기입니다 >></span>
																							</c:otherwise>
																						</c:choose>

																						<div class="user-actions">
																							<div
																								style='display:${(not empty loginuser and loginuser.memberId == itemReview.reviewWriter and not itemReview.itemReviewDelete)? "block" : "none"}'>
																								<a class="btn btn-sm btn-secondary edit-review"
																									data-review-no="${itemReview.reviewNo}"
																									href="javascript:void(0)" style="color: navy;">수정</a>
																								&nbsp; <a
																									class="btn btn-sm btn-secondary delete-review"
																									data-review-no="${itemReview.reviewNo}"
																									data-item-no="${item.itemNo}"
																									href="javascript:void(0)"
																									href="javascript:void(0)" style="color: navy">삭제</a>
																								&nbsp;&nbsp;
																							</div>
																							<div
																								style='display:${(not empty loginuser and loginuser.memberNo == 17 and not itemReview.itemReviewDelete)? "block" : "none"}'>
																								<a class="write-reply btn btn-sm btn-outline-success"
																									data-review-no="${itemReview.reviewNo}"
																									href="javascript:void(0)">후기답변작성</a>
																							</div>
																						</div>
																						<span style="clear: both"></span>
																					</div>
																					<div class="review-edit-area"
																						id="review-edit-area-${itemReview.reviewNo}"
																						style="display: none">
																						${sessionScope.loginuser.memberId} &nbsp;&nbsp;
																						[${itemReview.reviewDate}] <br /> <br />
																						<form action="edit-review" method="post"
																							style="width: 200%; resize: none;">
																							<input type="hidden" name="reviewNo"
																								value="${itemReview.reviewNo}" />
																							<textarea name="reviewContent"
																								style="width: 150%; resize: none; border-radius: 80px"
																								rows="2" maxlength="200">${itemReview.reviewContent}</textarea>
																							<br /> <br />
																							<div class="btn btn-sm btn-secondary">
																								<a class="update-review"
																									data-review-no="${itemReview.reviewNo}"
																									href="javascript:void(0)"
																									style="color: ligtblue">수정완료</a>
																							</div>
																							<div class="btn btn-sm btn-secondary">
																								<a class="cancel-edit-review"
																									data-review-no="${itemReview.reviewNo}"
																									href="javascript:void(0)"
																									style="color: ligtblue">수정취소</a>
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
												<!-- 상품 후기 리스트 보기 기능 구현 끝-->
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-12 mb-5 mb-xl-0" id="itemQnASection">
		       <div class="card shadow" style="width: 100%;"> 
		           <div class="card-body">
		             <div class="card-header border-0">
		             <div class="row align-items-center">
		               <div class="col">
		               	<h4 class="mb-0">상품 문의</h4>
		               	<form id="itemQnaForm" action="item-review" method="post">
		               		<input type="hidden" name="itemNo" value="${ item.itemNo }" />
		               		<input type="hidden" name="pageNo" value="${ pageNo }" />
		               		<input type="hidden" name="itemQnaWriter" value="${ loginuser.memberId }"/>
		                		<table class="table align-items-center">
		                			<tr>
		                				<td style="width: 1700px;">
		                				<textarea id="itemqna_content" name="itemqnaContent" style="width:100%; resize:none;  border-radius:80px" rows="2">     </textarea>
		                				</td>
										<td style="vertical-align: middle; text-align:right;">
										<a class ="btn btn-sm btn-success" id="write-item-qna-lnk" href="javascript:void(0)">상품 문의 등록</a> 
										</td>
		                			</tr>
		                		</table>
		           		</form> 
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
								            <a> ${itemQna.itemqnaContent}</a>
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
						                href="javascript:void(0)" style="color: navy">${itemQna.itemqnaNo}번 삭제</a>
						            &nbsp;&nbsp; 
					            </div>
					           <div style='display:${(not empty loginuser and loginuser.memberNo == 17 and not itemQna.itemqnaDelete)? "block" : "none"}'>
						            <a class="write-qna-reply btn btn-sm btn-outline-success" data-itemqna-no="${itemQna.itemqnaNo}"
						                href="javascript:void(0)" >문의답변작성</a>
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
		 <!-- 자유게시글 댓글 리스트 보기 기능 구현 끝-->  
		            </div>
		        </div>
		    </div>
		</div>
      </div>
      </div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
	<!-- 후기 답변 작성 Modal -->
	<div class="modal fade" id="reply-modal" tabindex="-1" role="dialog"
		aria-labelledby="reply-modal-Label" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="reply-modal-Label" >후기 답변 작성</h5>
					<button type="button" class="btn btn-success" data-dismiss="modal" 
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form id="replyform" action="write-reply" method="post">
						<input type="hidden" name="reviewNo" value="" /> <input
							type="hidden" name="reviewWriter" value="${ loginuser.memberId }" />
						<textarea id="review-content" name="reviewContent"
							style="width: 100%; resize: none; border-radius: 80px" rows="2">       </textarea>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-success"
						data-dismiss="modal">닫기</button>
					<button type="button" class="btn btn-outline-success" id="write-reply-btn">후기답변작성</button>
				</div>
			</div>
		</div>
	</div>
     
     
      <!-- 문의 답변 작성 Modal -->
	<div class="modal fade" id="replyqna-modal" tabindex="-1" role="dialog" aria-labelledby="replyqna-modal-Label" aria-hidden="true">
		  <div class="modal-dialog modal-dialog-centered" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="replyqna-modal-Label">문의 답변 쓰기</h5>
		        <button type="button" class="btn btn-success" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		       	<form id="replyqnaform" action="write-replyqna" method="post"> 
	          		<input type="hidden" name="itemqnaNo" value="" />
	          		<input type="hidden" name="itemQnaWriter" value="${ loginuser.memberId }"/>  
	   				<textarea id="itemqna-Content" name="itemqnaContent" style="width:100%; resize:none;  border-radius:80px" rows="2">       </textarea>		 
	   			</form> 
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-success" data-dismiss="modal">닫기</button>
		        <button type="button" class="btn btn-outline-success" id="write-replyqna-btn" >문의답변쓰기</button>
		      </div>
		    </div>
		  </div> 
		</div>   
	<!-- 주문 폼 -->
	<form action="/rental-project/directRental" method="get" class="rental_form">
		<input type="hidden" name="itemNo" value="${item.itemNo}">
		<input type="hidden" name="itemCount" value="">
	</form>


	<!-- Footer -->
    <jsp:include page="/WEB-INF/views/modules/footer.jsp" /> 


	<!--   Core   -->
	<script
		src="/rental-project/resources/js/plugins/jquery/dist/jquery.min.js"></script>
	<script
		src="/rental-project/resources/js/plugins/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
	<!--   Optional JS   -->
	<script
		src="/rental-project/resources/js/plugins/chart.js/dist/Chart.min.js"></script>
	<script
		src="/rental-project/resources/js/plugins/chart.js/dist/Chart.extension.js"></script>
	<!--   Argon JS   -->
	<script
		src="/rental-project/resources/js/argon-dashboard.min.js?v=1.1.2"></script>
	<script src="https://cdn.trackjs.com/agent/v3/latest/t.js"></script>
	<script>
		window.TrackJS && TrackJS.install({
			token : "ee6fab19c5a04ac1a32a645abde4613a",
			application : "argon-dashboard-free"
		});
	</script>

	<script>
		$(document).ready(function() {

			let itemPrice = "${item.itemPrice}"
			let point = itemPrice * 0.05;
			point = Math.floor(point);
			$(".point_span").text(point);
	
			// 주문 수량 조절
			let quantity = $(".quantity_input").val(); 
			/* $(".plus_btn").on("click", function() {
				$(".quantity_input").val(++quantity);
			});
			$(".minus_btn").on("click", function() {
				if ( quantity > 1) { 
			$(".plus_btn").on("click", function(event) {
				event.preventDefault();
				$(".quantity_input").val(++quantity);
			});
			$(".minus_btn").on("click", function(event) {
				event.preventDefault();
				if (quantity > 1) { 
					$(".quantity_input").val(--quantity);
				}
			}); */ 
			
			$(".plus_btn").on("click", function(event) {
				  event.preventDefault();
				  let quantityInput = $(".quantity_input");
				  let quantity = parseInt(quantityInput.val());  
				  quantityInput.val(quantity + 1);  
				});

				$(".minus_btn").on("click", function(event) {
				  event.preventDefault();
				  let quantityInput = $(".quantity_input");
				  let quantity = parseInt(quantityInput.val());  
				  if (quantity > 1) {
				    quantityInput.val(quantity - 1);  
				  }
				});


			// 서버로 보낼 데이터
			const form = {
				memberNo : '${ loginuser.memberNo}',
				itemNo : '${ item.itemNo }',
				itemCount : ''
			}
	
			// 장바구니 추가 버튼
			$("#btn_zzim").on("click", function(e) {
	
				form.itemCount = $(".quantity_input").val();
				$.ajax({
					url : 'zzim-add', // 호출 url
					type : 'post', // 메서드
					data : form, // 서버로 보낼 데이터
					success : function(result) {
						zzimAlert(result);
						location.href = 'list';
					}
				})
	
			});
	
			function zzimAlert(result) {
				if (result == '0') {
					alert("찜하기에 실패하였습니다.");
				} else if (result == '1') {
					alert("찜 하였습니다!");
				} else if (result == '2') {
					alert("이미 찜한 상품입니다!");
				} else if (result == '4') {
					alert("로그인이 필요합니다.");
				}
			}

			$("#btn_rental").on("click", function() {
					let itemCount = $(".quantity_input").val();
					$(".rental_form").find("input[name='itemCount']").val(itemCount);
					$(".rental_form").submit();
				});

			
			
			
			$("#write-item-review-lnk").on("click", function(event) { // 상품 후기 작성하기  
					event.preventDefault();
					const formData = $('#itemReviewForm').serialize();
					var reviewContent = $("#review_content").val();
					if (reviewContent.trim() == "") {
						alert("상품후기 내용을 입력하세요.");
						return;
					}
					$.ajax({
						"url" : "write-item-review",
						"method" : "post",
						"data" : formData,
						"success" : function(data, status, xhr) {
							if (data == "success") {
								$('#review-list').load(
										'review-list?itemNo=${item.itemNo}')
								$('#review_content').val('')
							} else {
								alert("<<상품후기를 작성하려면 먼저 로그인을 해주세요>>");
							}

						},
						"error" : function(xhr, status, err) {
							alert("후기 작성이 실패하였습니다.");
						}
					});
				}
			);

			$("#review-list").on(
				"click",
				".delete-review",
				function(event) { //상품 후기 삭제하기 
					const reviewNo = $(this).attr("data-review-no");

					const yn = confirm(reviewNo + "번 후기를 삭제할까요?");
					if (yn) {
						$.ajax({
							"url" : "delete-review",
							"method" : "get",
							"data" : {
								"reviewNo" : reviewNo
							},
							"success" : function(data, status, xhr) {
								$('#review-list').load(
										'review-list?itemNo=${item.itemNo}')
								currentEditReviewNo = null;
							},
							"error" : function(xhr, status, err) {
								alert("후기 삭제를 실패하였습니다.")
							},
						})
					}
				}
			);
		
			let currentEditReviewNo = null;

			$("#review-list").on(
				'click',
				".edit-review",
				function(event) {
					const reviewNo = $(this).attr("data-review-no");

					$('#review-edit-area-' + reviewNo).css('display', '');
					$('#review-area-' + reviewNo).css('display', 'none');

					if (currentEditReviewNo) {
						$('#review-edit-area-' + currentEditReviewNo).css(
								'display', 'none');
						$('#review-area-' + currentEditReviewNo).css('display',
								'');
					}
					currentEditReviewNo = reveiwNo;
				}
			)

			// 후기 수정취소
			$("#review-list").on('click', ".cancel-edit-review", function(event) {
				const reviewNo = $(this).attr("data-review-no");
	
				$('#review-edit-area-' + reviewNo).css('display', 'none');
				$('#review-area-' + reviewNo).css('display', '');
	
				currentEditReviewNo = null;
	
			})

			// 후기 수정완료
			$("#review-list").on(
				'click',
				".update-review",
				function(event) {
					const reviewNo = $(this).data("review-no");
					const formData = $(
							'#review-edit-area-' + reviewNo + ' form')
							.serialize();
					$.ajax({
						"url" : "edit-review",
						"method" : "post",
						"data" : formData,
						"success" : function(data, status, xhr) {
							$('#review-list').load(
									'review-list?itemNo=${item.itemNo}');
							currentEditReviewNo = null;
						},
						"error" : function(xhr, status, err) {
							alert('댓글 수정을 실패하였습니다')
						}
					})
				}
			);

		
			$(".write-reply").click(function() {
				const reviewNo = $(this).data("review-no");

				$('#replyform #reply-content').val("");
				$('#replyform input[name=reviewNo]').val(reviewNo);

				$("#reply-modal").modal("show");
			});
		

			$("#write-reply-btn").on(
				'click',
				function(event) {
					const formData = $('#replyform').serialize();
					$.ajax({
						"url" : "write-reply",
						"method" : "post",
						"data" : formData,
						"success" : function(data, status, xhr) {
							$('#reply-modal').modal("hide");
							$('#review-list').load(
									'review-list?itemNo=${item.itemNo}');
							currentEditFreeBoardReplyNo = null;
						},
						"error" : function(xhr, status, err) {
						}
					})
				}
			);
	/////////////////////////////////	
	
	
	////////////////////////////////   <상품문의 작성 ajax 부분>
	
	$("#write-item-qna-lnk").on("click", function(event) {  // 상품 문의 작성하기  
		event.preventDefault();   
		const formData = $('#itemQnaForm').serialize();
		var itemQnaContent = $("#itemqna_content").val();
		
		
		
		
		if (itemQnaContent.trim()== ""){
			alert("상품문의 내용을 입력하세요.");
            return;
		} 
		$.ajax({
			"url" : "write-item-Qna",
			"method" : "post",
			"data" : formData,
			"success" : function(data, status, xhr) {
				if (data == "success") {
					$('#itemqna-list').load('itemqna-list?itemNo=${item.itemNo}')
					$('#itemqna_content').val('')
					alert("상품문의 작성완료");
					
				} else { 
					alert("<<상품문의를 작성하려면 먼저 로그인을 해주세요>>"); 
				} 
				
			},
			"error" : function(xhr, status, err){ 
				alert("문의 작성이 실패하였습니다.");
			}
		});
	})
	
		 $("#itemqna-list").on("click",".delete-itemqna", function(event) {       //상품 문의글 삭제하기 
   				const itemQnaNo = $(this).attr("data-itemqna-no");
   				
   				  
	   			const yn = confirm(itemQnaNo  + "번 문의를 삭제할까요?");
   				if (yn) { 
					$.ajax({
						"url" : "delete-itemqna",
						"method" : "get",
						"data" :  {
			                "itemqnaNo": itemQnaNo, // itemQnaController.java 매개변수 itemqnaNo
			               
			            },
						"success" : function(data, status, xhr) {
							$('#itemqna-list').load('itemqna-list?itemNo=${item.itemNo}')
							currentEditItemQnaNo = null;
						},
						"error" : function(xhr, status, err) {
							alert(itemQnaNo)
							alert("문의 삭제를 실패하였습니다.")
						},
					})
					}
	   			}) 
		let currentEditItemQnaNo = null;
	
	$("#itemqna-list").on('click',".edit-itemqna", function(event) {
		const itemqnaNo = $(this).attr("data-itemqna-no");
		
		$('#itemqna-edit-area-' + itemqnaNo).css('display', '');
		$('#itemqna-area-' + itemqnaNo).css('display', 'none');
		
		if (currentEditItemQnaNo) {
			$('#itemqna-edit-area-' + currentEditItemQnaNo).css('display', 'none');
    		$('#itemqna-area-' + currentEditItemQnaNo).css('display', '');
		}
		currentEditItemQnaNo = itemqnaNo;
	})
	

	// 문의 수정취소
	$("#itemqna-list").on('click',".cancel-edit-itemqna", function(event) {
			const itemqnaNo = $(this).attr("data-itemqna-no");
			
		$('#itemqna-edit-area-' + itemqnaNo).css('display', 'none');
		$('#itemqna-area-' + itemqnaNo).css('display', '');
			
		currentEditItemQnaNo = null;
   		
		})
		
		// 문의 수정완료
		$("#itemqna-list").on('click',".update-itemqna", function(event) {
			const itemqnaNo = $(this).data("itemqna-no"); 
			const formData = $('#itemqna-edit-area-' + itemqnaNo + ' form').serialize();
			$.ajax({
				"url" : "edit-itemqna",
				"method" : "post",
				"data" : formData,
				"success" : function(data, status, xhr){
					$('#itemqna-list').load('itemqna-list?itemNo=${item.itemNo}');
					currentEditItemQnaNo = null;
				},
				"error" : function(xhr, status, err) {
					alert('문의 수정을 실패하였습니다')
				}
			})
		})   
		
	
	//여기서 write-reply (문의 작성버튼있는 부분)

	$(document).ready(function() {             // 문의 답변 작성 
			  $(".write-qna-reply").click(function() {
			    const  itemqnaNo = $(this).data("itemqnaNo");
			    
			    $('#replyqnaform #itemqna-Content').val("");
			    $('#replyqnaform input[name=itemqnaNo]').val(itemqnaNo);
			    
			   
			    $("#replyqna-modal").modal("show");
			  })
			})
	
	$("#write-replyqna-btn").on('click', function(event){
				const formData = $('#replyqnaform').serialize(); 
				$.ajax({
					"url" : "write-replyqna",
					"method" : "post",
					"data" : formData,
					"success" : function(data, status, xhr){
						$('#replyqna-modal').modal("hide");
						$('#itemqna-list').load('itemqna-list?itemNo=${item.itemNo}');
						currentEditItemQnaNo = null;
					},
					"error" : function(xhr, status, err){ 
						alert("답변실패")
					}
				}) 
			})  	
	////////////////////////////////// <상품문의 작성 ajax 부분>
	
	
	
	/////////////////////////////////
	        /* var option1 = document.getElementById('option1');
	        var option2 = document.getElementById('option2');
	        var itemReviewSection = document.getElementById('itemReviewSection');
	        var itemQnASection = document.getElementById('itemQnASection');
	        
	        option1.addEventListener('click', function () {
	            itemReviewSection.style.display = 'block';
	            itemQnASection.style.display = 'none';
	        });
	        
	        option2.addEventListener('click', function () {
	            itemReviewSection.style.display = 'none';
	            itemQnASection.style.display = 'block';
	        });
	        
	        // 초기 상태: 상품 후기 섹션 표시, 상품 문의 섹션 숨김
	        itemReviewSection.style.display = 'block';
	        itemQnASection.style.display = 'none'; */
	        
	        const itemReviewSection = $('#itemReviewSection');
	        const itemQnASection = $('#itemQnASection');
	        
	        function changeDisplay(isReview) {
	        	itemReviewSection.css('display', isReview ? 'block' : 'none');
	        	itemQnASection.css('display', isReview ? 'none' : 'block');
	        }
	        
	        $('.product-review').on('click', function(event) {
	        	changeDisplay(true);
	        });
	        
	        $('.product-qna').on('click', function(event) {
	        	changeDisplay(false);
	        });
	        
	        changeDisplay(true);
	        
	        
	        
		});
	</script>
				
</body>
</html>