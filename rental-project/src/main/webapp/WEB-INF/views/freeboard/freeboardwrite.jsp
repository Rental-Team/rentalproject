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
  .nav-link2.free {
	
    text-decoration: underline double; /* 강조선 표시 */
    text-decoration-color: rgba(255, 0, 0, 0.2); /* 강조선 색상 설정 (흰색) */
    font-size:40px;
}</style>
</head>

<body class="">
  <div class="main-content">
    <!-- Navbar -->
	<jsp:include page="/WEB-INF/views/modules/navbar-top.jsp" />
	<jsp:include page="/WEB-INF/views/modules/navbar-top2.jsp" />
	<jsp:include page="/WEB-INF/views/modules/navbar-top3.jsp" />
    <!-- End Navbar -->
    <!-- Header -->
    <jsp:include page="/WEB-INF/views/modules/navbar-content3.jsp" />
    <div class="container-fluid mt--7">
      <div class="row">
        <div class="col-xl-12 mb-5 mb-xl-0">
        	<div class="card bg-secondary shadow">
            <div class="card-header border-0">
              <div class="row align-items-center">
                <div class="col-8">
                  <h3 class="mb-0">자유게시글쓰기</h3>
                  </div>
                  </div>
                  </div>
                  <div class="card-body">
                  <form action="freeboardwrite" method="post" enctype="multipart/form-data">         
                  	<div class="pl-lg-4">
	                  	<div class="row">
	                  	<div class="col-lg-12">
	                  	<div class="form-group">
	                  	<label class="form-control-label" for="input-freeBoardTitle">제목</label>
	                  	<input type="text" id="input-freeBoardTitle" name="freeBoardTitle" class="form-control form-control-alternative" placeholder="제목을 입력하세요" />          	 
	                  	</div>
	                  	</div>
	                  	</div>                  	
	                   	<div class="row">
	                  	<div class="col-lg-12">
	                  	<div class="form-group">
	                  	<label class="form-control-label" for="input-memberNo">작성자</label>
	                  	${ sessionScope.loginuser.memberId }
	                	<input type="hidden" id="input-memberId" name="memberId" class="form-control form-control-alternative" value="${ loginuser.memberId }">   	 
	                  	</div>
	                  	</div>
	                  	</div>
			            	<div class="row">
	                  	<div class="col-lg-12">
	                  	<div class="form-group">
	                  	<label class="form-control-label" for="input-freeBoardAttach">첨부파일</label>
	                  	<input type="file" id="attach" class ="btn btn-sm btn-success" class="form-control form-control-alternative" name="attach" style="width:200px">  
	                  	</div>
	                  	</div>
	                  	</div>		                   
			            	<div class="row">
	                  	<div class="col-lg-12">
	                  	<div class="form-group">
	                  	<label class="form-control-label"  for="input-freeBoardContent">글내용</label>
	                  	 <textarea name="freeBoardContent" id="input-freeBoardContent" style="resize: none;" rows="15" placeholder="내용을 입력하세요" class="form-control form-control-alternative" ></textarea>
	                  	</div>
	                  	</div>
	                  	</div>    
			            <div class="row">
			            <div class="col-lg-12">
			            <div class="text-right">
				        <input type="submit" class ="btn btn-success" value="글쓰기" >
				        <input type="button" class ="btn btn-success" id="btnCancel" value="취소" >
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
    window.TrackJS &&
      TrackJS.install({
        token: "ee6fab19c5a04ac1a32a645abde4613a",
        application: "argon-dashboard-free"
      });
  </script>
  
  <script>
  window.addEventListener("load", function(event) {
  	const btnCancel = document.querySelector("#btnCancel");
  	btnCancel.addEventListener("click",function(event) {
  		location.href="freeboardlist";
  	});
  });
  
  $("form").submit(function(event) {
	  var freeBoardTitle = $("#input-freeBoardTitle").val(); 
	  if (freeBoardTitle.trim() === "") {
		  alert("게시글 제목을 입력해주세요!");
		  event.preventDefault();
	  } 
  });
  </script>
</body>

</html>