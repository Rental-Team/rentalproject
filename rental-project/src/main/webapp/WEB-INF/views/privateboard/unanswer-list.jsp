<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Argon Dashboard - Free Dashboard for Bootstrap 4 by Creative Tim</title>
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
        <div class="row">
            <div class="col-xl-12 mb-5 mb-xl-0">
                <div class="card shadow">
                    <div class="card-header border-0">
                        <div class="row align-items-center">
                            <div class="col">
                                <h3 class="mb-0">1:1문의</h3>
                            </div>
		
	<!--미답변 목록 조회 	 -->			
	<div class="container mt-2">
	  <div class="row">
	    <div class="col-md-6 d-flex align-items-start">
	      <select id="viewOption" class="form-control form-control-sm" style="width: 150px;">
	       <option value="all">전체 목록 조회</option>
	        <option value="unanswered">미답변 목록 조회</option>
	        
	      </select>
	      <button id="viewButton" class="btn btn-primary btn-sm ml-2" onclick="viewList()">목록 조회</button>
	    </div>
	  </div>
	</div>
	<!--미답변 목록 조회   --> 
			                     




<!--   <div class="col-md-6">
    <form action="searchByQnaNo" method="get">
        <label for="qnaNo">Qna 번호:</label>
        <input type="text" id="qnaNo" name="qnaNo">
        <button type="submit">검색</button>
    </form>
</div>	 -->		                     
			       
			       
<!--검색   -->
  <div id="qnaSearchSection" class="col-md-6">
  <form action="/rental-project/privateboard/privateqnalist" method="get" onsubmit="return validateSearch();">
    <select id="searchType" name="searchType">
      <option value="qnaNo">문의번호</option>
    </select>
    <input type="text" id="qnaNo" name="qnaNo">
    <button type="submit" id="searchButton">검색</button>
  </form>
</div>
<!--검색   -->       
			                     
			                     
			                        
                           
                            <div class="col text-right">
                                <a href="privateqnawrite" class="btn btn-sm btn-primary">1대1문의 작성</a>
                            </div>
                        </div>
                    </div>
                    <div class="table-responsive">
                        <!-- Projects table -->
                        <table class="table align-items-center table-flush">
                            <thead class="thead-light">
                            <tr>
                                <th scope="col">문의번호</th>
                                <th scope="col">작성자</th>
                                <th scope="col">제목</th>
                                <th scope="col">문의유형</th>
                                <th scope="col">문의글 작성 일자</th>
                                <th scope="col">답변여부</th>
                            </tr>
                            </thead>
                            <tbody id="unansweredQnasTable">
                            <!-- 여기에 미답변 목록이 표시됩니다. -->
                            </tbody>
							 <c:forEach var="unAnswer" items="${requestScope.unAnswer}">
							    <tr>
							        <td>${unAnswer.qnaNo }</td>
							        <td>${unAnswer.memberId}</td>
							        <td>
							            <a href="privateqnadetail?qnaNo=${unAnswer.qnaNo }&pageNo=${pageNo}">${unAnswer.qnaTitle }</a>
							        </td>
							        <td>${unAnswer.qnaType }</td>
							        <td>${unAnswer.qnaDate }</td>
							        <!-- 답변 여부를 표시 -->
							        <td>
							            <c:choose>
							                <c:when test="${unAnswer.answered}">
							                    <span class="badge badge-success">답변 완료</span>
							                </c:when>
							                <c:otherwise>
							                    <span class="badge badge-warning">미답변</span>
							                </c:otherwise>
							            </c:choose>
							        </td>
							    </tr>
							</c:forEach>
                        </table>
                        <br><br>
                        ${ pager }
                        <br/><br/>
                    </div>
                </div>
            </div>
        </div>
        <!-- Footer -->
        <footer class="footer">
            <div class="row align-items-center justify-content-xl-between">
                <div class="col-xl-4">
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
  
  <script>
  function viewList() {
    var viewOption = document.getElementById("viewOption").value;
    if (viewOption === "unanswered") {
      window.location.href = "unanswer-list"; // 미답변 목록 조회 페이지로 이동
    } else if (viewOption === "all") {
      window.location.href = "privateqnalist"; // 전체 목록 조회 페이지로 이동
    }
  }
</script>
  
  
  
  
</body>

</html>