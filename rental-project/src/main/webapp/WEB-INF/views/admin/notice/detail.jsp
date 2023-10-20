<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
  
</head>

<body class="">
  <div class="main-content">
    <!-- Navbar -->
	<jsp:include page="/WEB-INF/views/admin/modules/navbar-top.jsp" />
	<jsp:include page="/WEB-INF/views/admin/modules/navbar-top2.jsp" />
	<jsp:include page="/WEB-INF/views/admin/modules/navbar-top3.jsp" />
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
                  <h3 style="font-weight:bold" class="mb-0">공지사항 상세정보</h3>
                </div>
                <div class="col text-right">
                	
                </div>
              </div>
            </div>
                <div class="card-body"> 
              <form action="write" method="post">
               <div class="pl-lg-12" style="magin : 0 auto;">
                  <div class="row">
                    <div class="col-lg-6" >
                      <div class="form-group focused">
                        <label style="font-size:12pt" class="form-control-label"for="input-noticeTitle">게시글 제목</label>
                        <input disabled="disabled" type="text" id="input-noticeTitle"  name="noticeTitle" class="form-control form-control-alternative" value="${ notice.noticeTitle }"/>
                           </div>
                    </div>
                        <div class="col-lg-6">
                      <div class="form-group">
                        <label style="font-size:12pt" class="form-control-label"  for="input-noticeNo">글번호</label>
                        <input disabled="disabled" type="text" id="input-noticeNo" name="noticeNo" class="form-control form-control-alternative"  value="${ requestScope.notice.noticeNo }"/>
                   </div>
                    </div>
                  </div>
                     <div class="row">
                    <div class="col-lg-6" >
                      <div class="form-group focused">
                        <label style="font-size:12pt" class="form-control-label"for="input-noticeDate">작성 일자</label>
                        <input disabled="disabled" type="regdate" id="input-noticeDate"  name="noticeDate" class="form-control form-control-alternative" value="${ notice.noticeDate }" pattern="yyyy-MM-dd HH:mm"/>
                           </div>
                    </div>
                        <div class="col-lg-6">
                      <div class="form-group">
                        <label style="font-size:12pt" class="form-control-label"  for="input-viewCount">조회수</label>
		                        <input disabled="disabled" type="text" id="input-viewCount" name="viewCount" class="form-control form-control-alternative"  value="${ notice.viewCount +1 }"/>     		
                   </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-lg-12" >
                      <div class="form-group focused">
                        <label style="font-size:12pt" class="form-control-label"for="input-noticeContent">글내용</label>
                        <textarea style="resize:none" disabled="disabled" rows="15" id="input-noticeContent"  name="noticeContent" class="form-control form-control-alternative">${ notice.noticeContent }</textarea>
                      </div>
                    </div>   
                  </div>
                 </div>
                </form>
                	<div class="col text-center" >
				        <input type="button" class ="btn btn btn-success" id="btnBackToList" value="목록으로 돌아가기" >
				        <input type="button" class ="btn btn btn-success" id="btnedit" value="게시글 수정하기" >
	                </div> 
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
			$(function(event) {
	    	$("#btnBackToList").on("click",function(event) {               
	    		location.href="list";
    		})
			
	    	
        	$("#btnedit").on("click", function(event) {                    
        		location.href="edit" + "?noticeNo=" +  ${ notice.noticeNo };
	    	})
	    	
	   	
    		
	    	});
			</script>
</body>

</html>