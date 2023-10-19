<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">

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
  
  <style>
  .row.text-center.justify-content-center input[type="button"] {
    margin-right: 1px; /* 버튼 간 간격 조절 */
}
.nav-link2.free {
	
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
    <jsp:include page="/WEB-INF/views/modules/navbar-content3.jsp" />
    <div class="container-fluid mt--7"> 
      <div class="row mt-5">
        <div class="col-xl-12 mb-5 mb-xl-0">
          <div class="card shadow">
            <div class="card-header border-0">
              <div class="row align-items-right">
                <div class="col">
                  <h3 style="font-weight:bold" class="mb-0">자유게시글 상세정보</h3>
                </div>
                <div class="col text-right"> 
                </div>
              </div>
            </div>
                <div class="card-body"> 
              <form action="freeboardwrite" method="post">
               <div class="pl-lg-12" style="margin : 0 auto;">
                  <div class="row">
                    <div class="col-lg-6" >
                      <div class="form-group focused">
                        <label style="font-size:12pt" class="form-control-label"for="input-freeBoardTitle">게시글 제목</label>
                        <input disabled="disabled" type="text" id="input-freeBoardTitle"  name="freeBoardTitle" class="form-control form-control-alternative" value="${ freeBoard.freeBoardTitle }"/>
                      </div>
                    </div>
                        <div class="col-lg-6">
                      <div class="form-group">
                        <label style="font-size:12pt" class="form-control-label"  for="input-freeBoardNo">글번호</label>
                        <input disabled="disabled" type="text" id="input-freeBoardNo" name="freeBoardNo" class="form-control form-control-alternative"  value="${ requestScope.freeBoard.freeBoardNo }"/>
               		  </div>
                    </div>
                  </div>
                     <div class="row">
                    <div class="col-lg-6" >
                      <div class="form-group focused">
                        <label style="font-size:12pt" class="form-control-label"for="input-freeBoardDate">게시글 작성 일자</label>
                        <input disabled="disabled" type="datetime" id="input-freeBoardDate"  name="freeBoardDate" class="form-control form-control-alternative" value="${ freeBoard.freeBoardDate }" pattern="yyyy-MM-dd HH:mm"/>
                      </div>
                    </div>
                        <div class="col-lg-6">
                      <div class="form-group">
                        <label style="font-size:12pt" class="form-control-label"  for="input-freeBoardNo">작성자</label>
                        <c:choose>
						    <c:when test="${empty freeBoard.memberImage}">
						        <img src="/rental-project/resources/img/theme/default.png" draggable="false" class="style-scope yt-img-shadow" height="32" width="32">
						    </c:when>
						    <c:otherwise>
						        <img src="${pageContext.request.contextPath}/resources/upload/${freeBoard.memberImage}" alt="Image" draggable="false" class="style-scope yt-img-shadow" height="32" width="32">
						    </c:otherwise>
						</c:choose>
                        <input disabled="disabled" type="text" id="input-freeBoardNo" name="freeBoardNo" class="form-control form-control-alternative"  value="${freeBoard.memberId}"> 
                   </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-lg-6" >
                      <div class="form-group focused">
                        <label style="font-size:12pt" class="form-control-label"for="input-freeBoardAttach">첨부파일</label> 
                        	<br>
	                        <td>
			                	<c:forEach var="freeBoardAttach" items="${ freeBoard.freeBoardAttachList }">
			                		<a href="download?attachNo=${ freeBoardAttach.attachNo }"> ${freeBoardAttach.attachFileName}</a>
			                		<img src="${pageContext.request.contextPath}/resources/upload/${freeBoardAttach.savedFileName}" alt="Image" height="100px" width="100px">
			                	</c:forEach>
			                </td>
                       </div>
                    </div>
                        <div class="col-lg-6">
	                      	<div class="form-group">
		                        <label style="font-size:12pt" class="form-control-label"  for="input-freeBoardViewCount">조회수</label>
		                        <input disabled="disabled" type="text" id="input-freeBoardViewCount" name="freeBoardViewCount" class="form-control form-control-alternative"  value="${ freeBoard.freeBoardViewCount + 1 }"/>
	                   		</div>
                    	</div>
                  </div>
                  <div class="row">
                    <div class="col-lg-12" >
                      <div class="form-group focused">
                        <label style="font-size:12pt" class="form-control-label"for="input-freeBoardContent">글내용</label>
                        <textarea style="resize:none" disabled="disabled" rows="12" id="input-freeBoardContent"  name="freeBoardContent" class="form-control form-control-alternative">${ freeBoard.freeBoardContent }</textarea>
                      </div>
                    </div>   
                  </div>
                 </div>
                </form>
                	<br><br>
                	<div class="col-12 text-center" >
                		<a title="추천" id="recommandbtn">
					        <img src="/rental-project/resources/img/icons/good.png" alt="추천" width="50" height="50"/>
					        <span id="recommandCount" class="count">${recommandCount}</span>
					    </a> 
			    	    <a title="신고" id="reportbtn">
							<img src="/rental-project/resources/img/icons/report.png" alt="신고" width="50" height="50"/>
							<span id="reportCount" class="count">${count}</span> 
						</a>
					    </div> <br><br>
					    
				 
				         <br><br> 
						<br />
						<div class="row text-center justify-content-center"> 
					        <div class="btn-group" style="margin-right: 10px">
					     	   <input type="button" class="btn btn-outline-success btn-circle" id="btnBackToList" value="목록으로 돌아가기"> 
					            <input type="button" class="btn btn-outline-success btn-circle" id="btnedit" value="게시글 수정하기" style="display:${(not empty loginuser and loginuser.memberId == freeBoard.memberId) ? 'block' : 'none'}">
					            <input type="button" class="btn btn-outline-success btn-circle" id="btndelete" value="게시글 삭제하기" style="display:${(not empty loginuser and loginuser.memberId == freeBoard.memberId) ? 'block' : 'none'}">
					        </div> 
						</div> 
				</div>
               </div>  
             </div>
          	</div>
     	   </div>
      	 
      <!-- 자유게시글 댓글 쓰기 기능 구현 --> 
<div class="container-fluid mt--5">
   <div class="row mt-5">
   <div class="col-xl-12 mb-5 mb-xl-0">
       <div class="card shadow"> 
           <div class="card-body">
             <div class="card-header border-0">
             <div class="row align-items-center">
               <div class="col">
               	<h4 class="mb-0">자유게시글 댓글</h4>
               	<form id="freeBoardReviewForm" action="freeboard-review" method="post">
               		<input type="hidden" name="freeBoardNo" value="${ freeBoard.freeBoardNo }" />
               		<input type="hidden" name="pageNo" value="${ pageNo }" />
               		<input type="hidden" name="replyWriterImage" value="${ loginuser.memberImage }" />
               		<input type="hidden" name="replyWriter" value="${ loginuser.memberId }"/>
                		<table class="table align-items-center">
                			<tr>
                				<td style="width: 1500px;">
                				<textarea id="comment_content" name="replyContent" style="width:100%; resize:none;  border-radius:80px" rows="2">     </textarea>
                				</td>
								<td style="vertical-align: middle; text-align:right;">
								<a class ="btn btn-sm btn-success" id="write-freeboard-review-lnk" href="javascript:void(0)">댓글등록하기</a> 
								</td>
                			</tr>
                		</table>
           		</form> 
               <table id="review-list" class="table align-items-center table-flush" style="width:100%; padding-left: 0; padding-top:0" >
                   <thead class="thead-light">
                       <tr style="text-align:center">
                       </tr>
                   </thead>
               <tbody>
			    <c:forEach var="freeBoardReview" items="${freeBoard.freeBoardReviewList}">
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
                                    ${sessionScope.loginuser.memberImage} &nbsp;&nbsp;
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
 <!-- 자유게시글 댓글 리스트 보기 기능 구현 끝-->  
            </div>
        </div>
    </div>
</div>
    </div>
    </div>
    </div> 

<!-- 대댓글 쓰기 Modal -->
	<div class="modal fade" id="rereply-modal" tabindex="-1" role="dialog" aria-labelledby="rereply-modal-Label" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="rereply-modal-Label">대댓글쓰기</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	       	<form id="rereplyform" action="write-rereply" method="post"> 
          		<input type="hidden" name="freeBoardReplyNo" value="" />
          		<input type="hidden" name="replyWriter" value="${ loginuser.memberId }"/>
          		<input type="hidden" name="replyWriterImage" value="${ loginuser.memberImage }"/>  
   				<textarea id="reply-content" name="replyContent" style="width:100%; resize:none;  border-radius:80px" rows="2">     </textarea>		 
   			</form> 
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
	        <button type="button" class="btn btn-primary" id="write-rereply-btn" >대댓글쓰기</button>
	      </div>
	    </div>
	  </div>
	</div>​
	
      <!-- Footer -->
                 <jsp:include page="/WEB-INF/views/modules/footer.jsp" /> 

      
    </div>
    
    </div>
  <!--   Core   -->
  <script src="/rental-project/resources/js/plugins/jquery/dist/jquery.min.js"></script>
  <script src="/rental-project/resources/js/plugins/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
  <!--   Optional JS   -->
  <script src="/rental-project/resources/js/plugins/chart.js/dist/Chart.min.js"></script>
  <script src="/rental-project/resources/js/plugins/chart.js/dist/Chart.extension.js"></script>
  <!--   Argon JS   -->
  <script src="/rental-project/resources/js/argon-dashboard.min.js?v=1.1.2"></script>
  <script src="https://cdn.trackjs.com/agent/v3/latest/t.js"></script>
  <script>
    window.TrackJS &&
      TrackJS.install({
        token: "ee6fab19c5a04ac1a32a645abde4613a",
        application: "argon-dashboard-free"
      });
  </script>
   <script>
			$(function(event) {
	    	$("#btnBackToList").on("click",function(event) {                // 자유게시판 목록으로 돌아가기 누르면 freeboardlist로 가기 
	    		location.href="freeboardlist" +"?pageNo=" + ${pageNo};
    		})
			
	    	
        	$("#btnedit").on("click", function(event) {                     // 게시글 수정하기 누르면 게시글 수정하기 form으로가기
	    		location.href="freeboardedit" + "?freeBoardNo=" +  ${ freeBoard.freeBoardNo } + "&pageNo=" + ${pageNo};
	    	})
	    
		   $("#btndelete").on("click",function(event) {                    // 게시글 삭제 이벤트    
		   			const yes = confirm(${ freeBoard.freeBoardNo } + "번 게시글을 삭제할까요?");
	   				if (yes) {
	   					location.href="freeboarddelete/" + ${ freeBoard.freeBoardNo } + "?pageNo=" + ${pageNo};
	   						}
	   		})
	    	
	    	$("#write-freeboard-review-lnk").on("click", function(event) {  // 댓글 작성하기 이벤트   
	    		event.preventDefault();   
	    		const formData = $('#freeBoardReviewForm').serialize();
	    		var replyContent = $("#comment_content").val();
	    		if (replyContent.trim()== ""){
					alert("댓글 내용을 입력하세요.");
		            return;
				} 
	    		$.ajax({
	    			"url" : "write-freeboard-review",
	    			"method" : "post",
	    			"data" : formData,
	    			"success" : function(data, status, xhr) {
	    				if (data != "unauthorized") {
	    					$('#review-list').load('review-list?freeBoardNo=${freeBoard.freeBoardNo}')
	    					$('#comment_content').val('')
	    				} else { 
	    					const yn = confirm('로그인한 사용자만 댓글을 작성할 수 있습니다. 로그인 할까요?');
							if (yn){
								returnUrl= '/freeboard/freeboarddetail?freeBoardNo=${freeBoard.freeBoardNo}!pageNo=${pageNo}'
								location.href = '/rental-project/account/login?returnUrl=' + returnUrl;
    						}	
	    				}
	    			},
	    			"error" : function(xhr, status, err){ 
	    			}
	    		});
	    	})
	    	
	    	//$(".delete-reply").on("click",function(event) {
	   		$("#review-list").on("click",".delete-reply", function(event) {                    // 댓글 삭제하기 이벤트 
   				const freeBoardReplyNo = $(this).attr("data-reply-no");
	   			const yn = confirm(freeBoardReplyNo  + "번 댓글을 삭제할까요?");
   				if (yn) { 
					$.ajax({
						"url" : "delete-reply",
						"method" : "get",
						"data" : "freeBoardReplyNo=" + freeBoardReplyNo,
						"success" : function(data, status, xhr) {
							$('#review-list').load('review-list?freeBoardNo=${freeBoard.freeBoardNo}')
							currentEditFreeBoardReplyNo = null;
						},
						"error" : function(xhr, status, err) {
							alert("댓글 삭제를 실패하였습니다.")
						},
					})
					}
	   			}) 
	   		
	   		let currentEditFreeBoardReplyNo = null;
	   		
	   		// 댓글 수정하기 클릭 이벤트   
	   		$("#review-list").on('click',".edit-reply", function(event) {
	    		const freeBoardReplyNo = $(this).attr("data-reply-no");
	    		
	    		$('#reply-edit-area-' + freeBoardReplyNo).css('display', '');
	    		$('#reply-view-area-' + freeBoardReplyNo).css('display', 'none');
	    		
	    		if (currentEditFreeBoardReplyNo) {
	    			$('#reply-edit-area-' + currentEditFreeBoardReplyNo).css('display', 'none');
		    		$('#reply-view-area-' + currentEditFreeBoardReplyNo).css('display', '');
	    		}
	    		currentEditFreeBoardReplyNo = freeBoardReplyNo;
	    		
    		})
    		
    		// 댓글 수정취소 이벤트 
    		$("#review-list").on('click',".cancel-edit-reply", function(event) {
	   			const freeBoardReplyNo = $(this).attr("data-reply-no");
	   			
	   			$('#reply-edit-area-' + freeBoardReplyNo).css('display', 'none');
	    		$('#reply-view-area-' + freeBoardReplyNo).css('display', '');
	   			
	    		currentEditFreeBoardReplyNo = null;
		   		
	   		})
	   		
	   		// 댓글 수정완료 이벤트  
	   		$("#review-list").on('click',".update-reply", function(event) {
	   			const freeBoardReplyNo = $(this).data("reply-no");
	   			//$('#reply-edit-area-' + freeBoardReplyNo + ' form').submit();
	   			const formData = $('#reply-edit-area-' + freeBoardReplyNo + ' form').serialize();
	   			$.ajax({
	   				"url" : "edit-reply",
	   				"method" : "post",
	   				"data" : formData,
	   				"success" : function(data, status, xhr){
	   					$('#review-list').load('review-list?freeBoardNo=${freeBoard.freeBoardNo}');
	   					currentEditFreeBoardReplyNo = null;
   					},
   					"error" : function(xhr, status, err) {
   						alert('댓글 수정을 실패하였습니다')
   					}
	   			})
	   		})   
			$(document).ready(function() {
			  $(".write-rereply").click(function() {
			    const freeBoardReplyNo = $(this).data("reply-no");
			    
			    $('#rereplyform #rereply-content').val("");
			    $('#rereplyform input[name=freeBoardReplyNo]').val(freeBoardReplyNo);
			    
			   
			    $("#rereply-modal").modal("show");
			  })
			})
	   		
			$("#write-rereply-btn").on('click', function(event){
				const formData = $('#rereplyform').serialize(); 
				$.ajax({
					"url" : "write-rereply",
					"method" : "post",
					"data" : formData,
					"success" : function(data, status, xhr){
						$('#rereply-modal').modal("hide");
						$('#review-list').load('review-list?freeBoardNo=${freeBoard.freeBoardNo}');
						currentEditFreeBoardReplyNo = null;
					},
					"error" : function(xhr, status, err){ 
					}
				}) 
			})  
			// 서버로 보낼 데이터 
			const form = {
	   			memberNo : '${loginuser.memberNo}',
	   			freeBoardNo : '${freeBoard.freeBoardNo}' 
	   		}
			
			// 게시글 신고버튼
			$("#reportbtn").on("click", function(e){ 
				if (!form.memberNo) {
					const yn = confirm('로그인이 필요합니다.로그인 화면으로 이동할까요?');
					if (yn) {
						returnUrl= '/freeboard/freeboarddetail?freeBoardNo=${freeBoard.freeBoardNo}!pageNo=${pageNo}'
						location.href = '/rental-project/account/login?returnUrl=' + returnUrl;
					}
					return;
				}
				
	   			$.ajax({
	   			url : 'freeBoard-report',
	   			method : 'post',
	   			data : form,
	   			success : function(result) {
	   				reportAlert(result);
	   			}
   			})	 
	   		})
	   		
	   		function reportAlert(result) {
	   			if(result == '0'){
	   				alert("신고가 안되었습니다 !");
	   			} else if(result == '1') {
	   				const yn = confirm("정말 신고하시겠습니까? 신고 후에는 취소할 수 없습니다.")
	   				if(yn){
	   				alert("신고가 완료되었습니다.");}
	   				return;
	   			} else if (result === '2'){
	   				alert("이미 신고한 게시글입니다.");
	   			}  
	   		}
	   		
	   	// 게시글 추천버튼
			$("#recommandbtn").on("click", function(e){ 
				if (!form.memberNo) {
					const yn = confirm('로그인이 필요합니다.로그인 화면으로 이동할까요?');
					if (yn) {
						returnUrl= '/freeboard/freeboarddetail?freeBoardNo=${freeBoard.freeBoardNo}!pageNo=${pageNo}'
						location.href = '/rental-project/account/login?returnUrl=' + returnUrl;
					}
					return;
				}
				
	   			$.ajax({
	   			url : 'freeBoard-recommand',
	   			method : 'post',
	   			data : form,
	   			success : function(recommandresult) {
	   				recommandAlert(recommandresult);
	   			}
   			})	 
	   		})
	   		
	   		function recommandAlert(recommandresult) {
	   			if(recommandresult == '0'){
	   				alert("추천이 안되었습니다 !");
	   			} else if(recommandresult == '1') { 
	   				alert("게시글이 추천 되었습니다 !"); 
	   			} else if (recommandresult === '2'){
	   				alert("이미 추천한 게시글입니다.");
	   			}  
	   		} 
			
});
	    	
			$(document).ready(function() {
			    // 페이지 로딩 시 AJAX 요청
			    $.ajax({
			        url: "freeboarddetail", // 실제 엔드포인트로 대체
			        method: "POST",
			        success: function(data) {
			            // 조회수 증가 후 조회수를 화면에 업데이트
			            $("#input-freeBoardViewCount").val(data.updatedViewCount);
			        },
			        error: function() { 
			        }
			    });
			});
			
</script>
</body>

</html>