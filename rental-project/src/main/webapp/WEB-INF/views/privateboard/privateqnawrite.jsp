<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
                        <label class="form-control-label" for="input-qnaName">작성자</label>
                        <input type="text" id="input-qnaName" name="qnaName"  class="form-control form-control-alternative">
                      </div>
                    </div>
                  </div>
                   <div class="row">
                    <div class="col-lg-12">
                      <div class="form-group">
                        <label class="form-control-label" for="input-qnaType">문의유형</label>
                         <select name="qnaType" >
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
                        <input type="file" id="input-qnaAttach" name="Attach"  class="form-control form-control-alternative">
                      </div>
                    </div>
                  </div>
 				  <div class="row">
                    <div class="col-lg-12">
                      <div class="form-group">
                        <label class="form-control-label" for="input-qnaContent">문의 내용</label>
                        <textarea id="input-qnaContent" name="qnaContent" class="form-control form-control-alternative" placeholder="설명을 입력하세요" rows="15" style="resize: none"></textarea>
                      </div>
                    </div>
                  </div>
                  	<div class="row">
                  	<div class="col-lg-12">
                  	<div class="text-right">
			        <input type="submit" class ="btn btn-sm btn-primary" value="글쓰기" >
			        <input type="button" class ="btn btn-sm btn-primary" id="btnCancel" value="취소" />
                  	
                  	</div>
                  	</div>
       	     </div>
       	     </div>
                   

                   
               <%--     <table>
		            <tr>
		                <th>문의제목</th>
		                <td>
		                    <input type="text" name="qnaTitle" style="width:580px" />
		                </td>
		            </tr>
		            <tr>
		                <th>작성자</th>
		                <td>
		                	${ sessionScope.loginuser.memberId }
		                	<input type="hidden" name="memberNo" value="${ loginuser.memberId }">
		                </td>
		            </tr>
		            <tr>
		                <th>문의유형</th>
		                <td>
		                <select name="qnaType" >
		                <option value="상품문의">상품문의</option>
		                <option value="배송문의">배송문의</option>
		                <option value="환물문의">환물문의</option>
		                </select>
		                	<!-- <input type="radio" name="qnaType" value="상품문의"/>상품문의<br> 
		                	<input type="radio" name="qnaType" value="배송문의"/>배송문의<br>
		                	<input type="radio" name="qnaType" value="환불문의"/>환불문의<br> -->
		                	 
		                </td>
		            </tr>
		            <tr>
		            <th>첨부파일</th>
		           		<td>
		           		<input type="file" name="attach" >
		           		<td>
		            </tr>
		            <tr>
		                <th>문의내용</th>
		                <td>
		                	<textarea name="qnaContent" style="width:580px" rows="15"></textarea>
		                </td>
		            </tr>
		        </table>
			        <div class="col text-center">
			        <input type="submit" class ="btn btn-sm btn-primary" value="글쓰기" >
			        <input type="button" id="btnCancel" value="취소" />
	                </div> --%>
                </form>
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