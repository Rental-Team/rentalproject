<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>
    Argon Dashboard - Free Dashboard for Bootstrap 4 by Creative Tim
  </title>
  <!-- Favicon -->
  <link href="/rental-project/resources/img/brand/favicon.png" rel="icon" type="image/png">
  <!-- Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet">
  <!-- Icons -->
  <link href="/rental-project/resources/js/plugins/nucleo/css/nucleo.css" rel="stylesheet" />
  <link href="/rental-project/resources/js/plugins/@fortawesome/fontawesome-free/css/all.min.css" rel="stylesheet" />
  <!-- CSS Files -->
  <link href="/rental-project/resources/css/argon-dashboard.css?v=1.1.2" rel="stylesheet" />
</head>

<body class="">
<jsp:include page="/WEB-INF/views/modules/navbar-vertical.jsp" />
  <div class="main-content">
    <!-- Navbar -->
	<jsp:include page="/WEB-INF/views/modules/navbar-top.jsp" />
    <!-- End Navbar -->
    <!-- Header -->
    <jsp:include page="/WEB-INF/views/modules/navbar-content.jsp" />
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
                        <textarea style="resize:none" disabled="disabled" rows="15" id="input-freeBoardContent"  name="freeBoardContent" class="form-control form-control-alternative">${ freeBoard.freeBoardContent }</textarea>
                      </div>
                    </div>   
                  </div>
                 </div>
                </form>
                	<div class="col text-center" >
				        <input type="button" class ="btn btn btn-primary" id="btnBackToList" value="목록으로 돌아가기" ></div>  
				        <br />
				        <div class="col text-center" style='display:${ (not empty loginuser and loginuser.memberId == freeBoard.memberId)? "block" : "none"}'>
				        <input type="button" class ="btn btn btn-primary" id="btnedit" value="게시글 수정하기" >
				        <input type="button" class ="btn btn btn-primary" id="btndelete" value="게시글 삭제하기" >
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
               		<input type="hidden" name="replyWriter" value="${ loginuser.memberId }"/>
                		<table class="table align-items-center">
                			<tr>
                				<td style="width: 1500px;">
                				<textarea id="comment_content" name="replyContent" style="width:100%; resize:none;  border-radius:80px" rows="2">     </textarea>
                				</td>
								<td style="vertical-align: middle; text-align:right;">
								<a class ="btn btn-sm btn-primary" id="write-freeboard-review-lnk" href="javascript:void(0)">댓글등록하기</a> 
								</td>
                			</tr>
                		</table>
           		</form> 
               <table id="review-list" class="table align-items-center table-flush">
                   <thead class="thead-light">
                       <tr style="text-align:center">
                       </tr>
                   </thead>
                   <tbody>
                       <c:forEach var="freeBoardReview" items="${freeBoard.freeBoardReviewList}">
                           <tr style="text-align:center"> 
                           </tr>
                           <tr>
                               <td colspan="5">
                                   <div class="reply-view-edit-area" id="reply-view-edit-area-${freeBoardReview.freeBoardReplyNo}"> 
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
                                           <div style='float:left; display:${ (not empty loginuser and loginuser.memberId == freeBoardReview.replyWriter and not freeBoardReview.replyDelete)? "block" : "none"}'>
                                               <a class="btn btn-sm btn-secondary edit-reply " data-reply-no="${freeBoardReview.freeBoardReplyNo}" href="javascript:void(0)" style="color: navy;">댓글수정</a>
                                                &nbsp;
                                               <a class="btn btn-sm btn-secondary delete-reply" data-reply-no="${freeBoardReview.freeBoardReplyNo}" href="javascript:void(0)" style="color: navy">댓글삭제</a>
                                           </div>
                                       </div> 
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
 <!-- 자유게시글 댓글 리스트 보기 기능 구현 끝-->  
            </div>
        </div>
    </div>
</div>
      </div>
      </div>
      </div>
      </div>

      <!-- Footer -->
      <footer class="footer">
        <div class="row align-items-center justify-content-xl-between">
          <div class="col-xl-6">
            <div class="copyright text-center text-xl-left text-muted">
              &copy; 2018 <a href="https://www.creative-tim.com" class="font-weight-bold ml-1" target="_blank">Creative Tim</a>
            </div>
          </div>
          <div class="col-xl-6">
            <ul class="nav nav-footer justify-content-center justify-content-xl-end">
              <li class="nav-item">
                <a href="https://www.creative-tim.com" class="nav-link" target="_blank">Creative Tim</a>
              </li>
              <li class="nav-item">
                <a href="https://www.creative-tim.com/presentation" class="nav-link" target="_blank">About Us</a>
              </li>
              <li class="nav-item">
                <a href="http://blog.creative-tim.com" class="nav-link" target="_blank">Blog</a>
              </li>
              <li class="nav-item">
                <a href="https://github.com/creativetimofficial/argon-dashboard/blob/master/LICENSE.md" class="nav-link" target="_blank">MIT License</a>
              </li>
            </ul>
          </div>
        </div>
      </footer> 
      
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
	    		$.ajax({
	    			"url" : "write-freeboard-review",
	    			"method" : "post",
	    			"data" : formData,
	    			"success" : function(data, status, xhr) {
	    				if (data == "success") {
	    					$('#review-list').load('review-list?freeBoardNo=${freeBoard.freeBoardNo}')
	    					$('#comment_content').val('')
	    				} else { 
	    					alert("<<댓글을 작성하려면 먼저 로그인을 해주세요>>"); 
    					} 
	    			},
	    			"error" : function(xhr, status, err){ 
	    			}
	    		})
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
    		
   	});
			</script>
</body>

</html>