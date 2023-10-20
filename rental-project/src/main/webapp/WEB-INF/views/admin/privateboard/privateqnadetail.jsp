<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



<!DOCTYPE html>
<html lang="en">

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
    <link href="/rental-project/resources/css/navbar-top.css" rel="stylesheet" />
</head>

<body class="">
  
<div class="main-content">
    <!-- Navbar -->
   	<jsp:include page="/WEB-INF/views/admin/modules/navbar-top.jsp" />
	<jsp:include page="/WEB-INF/views/admin/modules/navbar-top2.jsp" />
	<jsp:include page="/WEB-INF/views/admin/modules/navbar-top3.jsp" />
    <!-- End Navbar -->
    <!-- Header -->
    <jsp:include page="/WEB-INF/views/admin/modules/navbar-content.jsp" />
    <div class="container-fluid mt--7">
      <div class="row mt-5">
        <div class="col-xl-12 mb-5 mb-xl-0">
          <div class="card shadow">
            <div class="card-header border-0">
              <div class="row align-items-right">
                <div class="col">
                  <h3 style="font-weight:bold" class="mb-0">1:1문의 상세정보</h3>
                </div>
                <div class="col text-right">
                  <!-- 이 곳에 내용을 추가할 수 있습니다 -->
                </div>
              </div>
            </div>
            <div class="card-body">
              <form action="write" method="post">
                <div class="pl-lg-12" style="magin : 0 auto;">
                  <div class="row">
                    <div class="col-lg-6">
                      <div class="form-group focused">
                        <label style="font-size:12pt" class="form-control-label" for="input-qnaTitle">문의 제목</label>
                        <input disabled="disabled" type="text" id="input-qnaTitle" name="qnaTitle" class="form-control form-control-alternative" value="${privateqna.qnaTitle}" />
                      </div>
                    </div>
                    <div class="col-lg-6">
                      <div class="form-group">
                        <label style="font-size:12pt" class="form-control-label" for="input-qnaNo">글번호</label>
                        <input disabled="disabled" type="text" id="input-qnaNo" name="qnaNo" class="form-control form-control-alternative" value="${requestScope.privateqna.qnaNo}" />
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-lg-6">
                      <div class "form-group">
                        <label style="font-size:12pt" class="form-control-label" for="input-qnaType">문의 유형</label>
                        <input disabled="disabled" type="text" id="input-qnaType" name="qnaType" class="form-control form-control-alternative" value="${privateqna.qnaType}" />
                      </div>
                    </div>
                    <div class="col-lg-6">
                      <div class="form-group focused">
                        <label style="font-size:12pt" class="form-control-label" for="input-qnaDate">작성 일자</label>
                        <input disabled="disabled" type="regdate" id="input-qnaDate" name="qnaDate" class="form-control form-control-alternative" value="${privateqna.qnaDate}" pattern="yyyy-MM-dd HH:mm" />
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-lg-6">
                      <div class="form-group">
                        <label style="font-size:12pt" class="form-control-label" for="input-qnaId">작성자</label>
                        <input disabled="disabled" type="text" id="input-qnaId" name="qnaId" class="form-control form-control-alternative" value="${privateqna.memberId}" />
                      </div>
                    </div>
				<div class="col-lg-6">
				  <div class="form-group">
				    <label style="font-size:12pt" class="form-control-label">첨부파일</label>
				    <c:forEach var="attach" items="${privateqna.privateQnaAttachList}">
				      <div>
				        <img src="${pageContext.request.contextPath}/resources/upload/${attach.savedFileName}" alt="이미지" height="100px" width="100px">
				        <a href="download?attachNo=${attach.attachNo}" class="download-link" >${attach.attachFileName}</a>
				      </div>
				    </c:forEach>
				  </div>
				</div>
                  </div>
                  <div class="row">
                    <div class="col-lg-12">
                      <div class="form-group focused">
                        <label style="font-size:12pt" class="form-control-label" for="input-qnaContent">글내용</label>
                        <textarea style="resize:none" disabled="disabled" rows="15" id="input-qnaContent" name="qnaContent" class="form-control form-control-alternative">${privateqna.qnaContent}</textarea>
                      </div>
                    </div>
                  </div>
                </div>
              </form>
              <div class="col text-center">
                <input type="button" id="getBack" class="btn btn-sm btn-success" value="목록으로 돌아가기" onclick="goToPage(${pageNo})">
              	 <input type="button" id="getBack-unanswer" class="btn btn-success btn-sm" value="미답변 목록으로 돌아가기" onclick="gotoPageUnanswer()">
              </div>
             </div>
          </div> 
         </div>
       </div>
     </div>
  

<!-- ////////////
 -->

<!-- 관리자 답변 작성 form -->

	<div class="container-fluid mt--7">
			<div class="row mt-8">
				<div class="col-xl-12 mb-5 mb-xl-0">
				<div class="card shadow">
				<div class="card-header border-0">
				<div class="row align-items-center">
				<div class="col">
			<h5 class="mb-0">1대1게시글 답변</h5>
	

		<!--답변 조회 -->							
	<table id="answer-list"
			class="table align-items-center table-flush text-center">
			<thead class="thead-light">
				<tr>
					<th scope="col" style="width: 2000px;">답변내용</th>
					<th scope="col"></th>
				</tr>
			</thead>
			<!--답변 조회 출력 -->	
			<tbody>
			  <c:forEach var="privateAnswer" items="${privateqna.privateQnaAnswerList}">
				  <tr id="answer-view-area-${privateAnswer.qnaNo}">
					<td scope="col" style="width: 100px">${privateAnswer.answerContent}</td>
					 <td><c:if test="${requestScope.memberNo == 17}">
				        <a class="btn btn-sm btn-success edit-answer-link"  data-reply-no="${privateAnswer.qnaNo}"	href="javascript:void(0)" style="color: white">답변수정</a>
					  </c:if>
					</td>
				  </tr>
				
				<!-- 답변 수정 form-->
				<div id="answer-edit-area-${privateAnswer.qnaNo}" style="display: none">						
				   <form action="edit-answer" method="post" style="width: 105%; resize: none;">
					 <input type="hidden" name="qnaNo" value="${privateAnswer.qnaNo}">
						<textarea name="answerContent" style="width: 100%; resize: none;">${privateAnswer.answerContent}</textarea>
				     <input type="submit"  class="btn btn-sm btn-success value="저장" onclick="goToPage(${pageNo})">
				      <button class="btn btn-sm btn-success cancel-edit-button" data-reply-no="${privateAnswer.qnaNo}" type="button">취소</button>
				  </form>
			    </div>
			  </c:forEach>
		  </tbody>
	</table>
	
	  <form id="comment-Answer" action="write-answer" method="post">
		         <input type="hidden" name="qnaNo" value="${privateqna.qnaNo}" />
		<table class="table align-items-center">
		   <tr>
		     <td><textarea id="comment_content" name="answerContent" style="width: 100%; resize: none;" rows="2"></textarea></td>			  				
			   <td style="width: 50px; vertical-align: middle; border-radius: 80px">
			     <input type="submit" class="btn btn-sm btn-success" id="admin-write-answer" value="답변등록하기">					
			  </td>
		  </tr>
		</table>
	  </form>
	
	
	
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
    </div>
  </div>
<br>
<br>
<br>
<br>
<br>


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
  $(function() {
    $("#comment-Answer").submit(function(event) {
      var answerContent = $("#comment_content").val();

      if (answerContent.trim() === "") {
        alert("답변을 입력하세요.");
        event.preventDefault();
      } else {
        if (!confirm("답변을 등록하시겠습니까?")) {
          event.preventDefault();
        }
      }
    });
  });

    function goToPage(pageNo) {
      var url = 'privateqnalist?pageNo=' + pageNo;
      location.href = url;
    }
  
    $(function() {
      $(".edit-answer-link").on('click', function() {
          var qnaNo = $(this).attr("data-reply-no");
          $('#answer-edit-area-' + qnaNo).css('display', '');
          $('#answer-view-area-' + qnaNo).css('display', 'none');
      });
  
      $('#answer-edit-area').submit(function(event) {
          event.preventDefault();
        
          window.location.href = 'privateqnadetail?pageNo=' + pageNo;
      });
  
      // 서버에서 memberNo 값을 JSP로부터 가져오는거임
      var memberNo = <%= request.getAttribute("memberNo") %>;

      if (memberNo !== 17) {
        var commentAnswerForm = document.getElementById("comment-Answer");
        if (commentAnswerForm) {
          commentAnswerForm.style.display = "none";
        }
        
        // 미답변 목록으로 돌아가기 버튼 숨기기
        var getBackUnanswerButton = document.getElementById("getBack-unanswer");
        if (getBackUnanswerButton) {
          getBackUnanswerButton.style.display = "none";
        }
      }
    });

    // 미답변 목록으로 돌아가기 버튼 표시/숨김
    function gotoPageUnanswer() {
      var memberNo = <%= request.getAttribute("memberNo") %>;
      if (memberNo === 17) {
        var url = 'unanswer-list';
        location.href = url;
      } else {
        alert("17번 멤버만 접근 가능합니다.");
      }
    }
  </script>
  
<script>
  $(function() {
    // 이벤트 핸들러를 등록하여 링크 클릭 시 확인 메시지를 표시
    $("a.download-link").click(function(event) {
      var confirmDownload = confirm("다운로드 하시겠습니까?");
      if (!confirmDownload) {
        event.preventDefault(); // 다운로드 링크 클릭을 취소
      }
    });
  });
</script>


<script>
  $(function() {
    // 답변 수정 폼 취소 버튼 클릭 이벤트 처리
    $(".cancel-edit-button").on('click', function() {
      var qnaNo = $(this).attr("data-reply-no");
      $('#answer-view-area-' + qnaNo).show();
      $('#answer-edit-area-' + qnaNo).hide();
    });
  });
</script>
  
  
  
  
</body>

</html>