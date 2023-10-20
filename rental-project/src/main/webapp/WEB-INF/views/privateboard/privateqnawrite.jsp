<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
  .nav-link2.private {
	
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
    <!-- End Navbar -->
    <!-- Header -->
    <jsp:include page="/WEB-INF/views/modules/navbar-content4.jsp" />
    <div class="container-fluid mt--7">
      <div class="row">
        <div class="col-xl-12 mb-5 mb-xl-0">
          <div class="card shadow">
            <div class="card-header border-0">
              <div class="row align-items-center">
                <div class="col">
                  <h3 class="mb-0">1:1문의</h3>
                  <br>
                  </div>
                  </div>
                  </div>
                  <div class="card-body">
                  <form action="privateqnawrite" method="post" enctype="multipart/form-data">
                   
                   <div class="pl-lg-4">
                  <div class="row">
                    <div class="col-lg-12">
                      <div class="form-group">
                        <label class="form-control-label" for="input-qnaTitle">문의제목</label>
                        <input type="text" id="input-qnaTitle" name="qnaTitle"  class="form-control form-control-alternative" placeholder="제목을 입력하세요.">
                      </div>
                    </div>
                  </div>
                   <div class="row">
                    <div class="col-lg-12">
                      <div class="form-group">
                        <label class="form-control-label" for="input-qanName">작성자</label>
                      	 
                      	 ${ loginuser.memberId }
                        <input type="hidden" id="input-qnaName" name="qnaName"  class="form-control form-control-alternative" value="${ loginuser.memberId }">
                     	
                      </div>
                    </div>
                  </div>
                   <div class="row">
				        <div class="col-lg-12">
				            <div class="form-group">
				                <label class="form-control-label" for="input-qnaType">문의유형</label>
				                <select name="qnaType" required> <!-- required 속성 추가 -->
				                    <option value="" disabled selected>문의유형을 선택하세요</option>
				                    <option value="상품문의">상품문의</option>
				                    <option value="배송문의">배송문의</option>
				                    <option value="환물문의">환물문의</option>
				                </select>
				            </div>
				        </div>
				    </div>
                   <div class="row">
                    <div class="col-lg-12">
                      <div class="form-group">
                        <label class="form-control-label" for="input-qnaAttach">첨부파일</label>
                        <input type="file" id="input-qnaAttach" name="attach"  class="btn btn-sm btn-success">
                      </div>
                    </div>
                  </div>
 				  <div class="row">
                    <div class="col-lg-12">
                      <div class="form-group">
                        <label class="form-control-label" for="input-qnaContent">문의 내용</label>
                        <textarea id="input-qnaContent" name="qnaContent" class="form-control form-control-alternative" placeholder="설명을 입력하세요, 1대1문의 작성후 수정 삭제가 안되니 유의하시길 바랍니다" rows="15" style="resize: none"></textarea>
                      </div>
                    </div>
                  </div>
                  	<div class="row">
                  	<div class="col-lg-12">
                  	<div class="text-right">
			        <input type="submit" class ="btn btn-sm btn-success" value="글쓰기" >
			        <input type="button" class ="btn btn-sm btn-success" id="btnCancel" value="취소" />
                  	 </div>
                    </div>
                 	 </div>
                	</div>
                   </form>
                  </div>
                </div>
       	     </div>
       	    </div>
                   
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
$(document).ready(function() {
    // "취소" 버튼 클릭 시 이벤트 처리
    $("#btnCancel").click(function() {
       location.href = "privateqnalist";
    });

  
    $("form").submit(function(event) {
        var qnaType = $("select[name='qnaType']").val();
        var qnaTitle = $("#input-qnaTitle").val();
        var qnaContent = $("#input-qnaContent").val();

        if (qnaType === "문의유형") {
            alert("문의 유형을 선택하세요.");
            event.preventDefault();
        } else if (qnaTitle.trim() === "") {
            alert("제목을 입력하세요.");
            event.preventDefault();
        } else if (qnaContent.trim() === "") {
            alert("문의 내용을 입력하세요.");
            event.preventDefault();
        } else {
            if (!confirm("게시글을 작성하시겠습니까? 작성 후 수정 및 삭제가 불가능합니다.")) {
                event.preventDefault();
            }
        }
    });
});
</script>
 
 
 
 
  <script>
    window.TrackJS &&
      TrackJS.install({
        token: "ee6fab19c5a04ac1a32a645abde4613a",
        application: "argon-dashboard-free"
      });
  </script>
 
  
  
</body>

</html>