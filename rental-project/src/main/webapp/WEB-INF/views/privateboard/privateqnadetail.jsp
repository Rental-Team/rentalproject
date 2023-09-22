<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
      <div class="row">
      <div class="col-xl-12 mb-5 mb-xl-0">
          <div class="card shadow">
            <div class="card-header border-0">
              <div class="row align-items-center">
                <div class="col">
                  <h3 class="mb-1">1:1 문의 게시판</h3>
                  </div>
                  </div>
                  </div>

<br>
					<div class="card-body">
                  <form action="write" method="post">
                   <div class="pl-lg-12" style="magin : 0 auto;">
                  <div class="row">
                    <div class="col-lg-6" >
                      <div class="form-group focused">
                        <label class="form-control-label"for="input-qnaTitle">문의글 제목</label>
                        <input disabled="disabled" type="text" id="input-qnaTitle"  name="qnaTitle" class="form-control form-control-alternative" value="${ privateqna.qnaTitle }">
                           </div>
                    </div>
                        <div class="col-lg-6">
                      <div class="form-group">
                        <label class="form-control-label"  for="input-qnaNo">문의글 번호</label>
                        <input disabled="disabled" type="text" id="input-qnaNo" name="qnaNo" class="form-control form-control-alternative"  value="${ requestScope.privateqna.qnaNo }"/>           
                   </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-lg-6">
                      <div class="form-group">
                        <label class="form-control-label" for="input-qnaType">문의글 유형</label>
                        <input disabled="disabled" type="text" id="input-qnaType" name="qnaType" class="form-control form-control-alternative" value="${ privateqna.qnaType }">
                      </div>
                  </div>
                  
                    <div class="col-lg-6">
                      <div class="form-group">
                        <label class="form-control-label" for="input-qnaDate">문의글 날짜</label>
                        <input disabled="disabled" type="regdate" id="input-qnaDate" name="qnaDate" class="form-control form-control-alternative" value="${ privateqna.qnaDate }">
                      </div>
                    </div>
                  </div>
                   <div class="row">
                    <div class="col-lg-12">
                      <div class="form-group">
                        <label class="form-control-label" for="input-qnaContent">문의글 내용</label>
                        <textarea disabled="disabled" id="input-qnaContent" name="qnaContent" class="form-control form-control-alternative" rows="15" style="resize: none" >${ requestScope.privateqna.qnaContent }</textarea>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-lg-12">
                      <div class="text-right">
                         <input type="button"  id="getBack" class ="btn btn-sm btn-primary" value="목록으로 돌아가기" >
			        <!-- <input type="button" class ="btn btn-sm btn-primary" id="btnCancel" value="게시글 수정하기" > -->
			        <input type="button" class ="btn btn-sm btn-primary" id="btnCancel" value="게시글 삭제하기" >
                      </div>
                    </div>
                  </div>
                  </div>
<%--                    <table>
		            <tr>
		                <th>문의글 번호</th>
		                <td>
		                 ${ requestScope.privateqna.qnaNo }  
		                </td>
		            </tr>
		            <tr>
		                <th>문의글 유형</th>
		                <td>
		                	${ privateqna.qnaType }
		                </td>
		            </tr>
		            <tr>
		                <th>문의글 제목</th>
		                <td>
		                	${ privateqna.qnaTitle }
		                </td>
		            </tr>
		            <tr>
		                <th>문의글 내용 </th>
		                <td>
		                	${ privateqna.qnaContent }
		                </td>
		            </tr>
		            <tr>
		                <th>문의글 날짜</th>
		                <td> 
		                	${ privateqna.qnaDate }
		                </td>
		            </tr>
		            <tr>
		                <th></th>
		                <td>
		                	
		                </td>
		            </tr>
		            <tr>
		                <th></th>
		                <td>
		                	
		                </td>
		            </tr> 
		        </table>  --%>
			        <br>
			       <!--  <div class="col text-center" >
			       
	              
	                </div> -->
                </form>
                </div>
              </div>
           </div> 
          </div>
          </div>

<br>
<br>
		<!-- 자유게시글 댓글 쓰기 기능 구현 --> 
	<div class="container-fluid mt--7">
      <div class="row mt-5">
        <div class="col-xl-12 mb-5 mb-xl-0">
          <div class="card shadow">
            <div class="card-header border-0">
              <div class="row align-items-center">
                <div class="col">
                <h5 class="mb-0">1대1게시글 답변</h5>
                	<form id="commentform" action="freeboard-comment" method="post">
                		<input type="hidden" name="" value="" />
                		<table class="table align-items-center">
                			<tr>
                				<td style="width: 1400px"><textarea id="comment_content"
								name="content" style="width: 100%; resize:none;" rows="2"></textarea></td>
								<td style="text-align:right; vertical-align: middle; border-radius:80px">
								<input type="button" class ="btn btn-primary" id="btnCancel" value="답변등록하기" >
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
</body>

</html>