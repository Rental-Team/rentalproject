<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                </div>
				
				
<!-- 	 -->			
<div class="container mt-2">
  <div class="row">
    <div class="col-md-6">
     
      <select id="viewOption" class="form-control form-control-sm">
        <option value="unanswered">미답변 목록 조회</option>
        <option value="all">전체 목록 조회</option>
      </select>
    </div>
    <div class="col-md-6 d-flex align-items-start">
      <button id="viewButton" class="btn btn-primary btn-sm" onclick="viewList()">목록 조회</button>
    </div>
  </div>
</div>
<!--  --> 

<div class="container mt-2">
  <div class="row">
    <div class="col-md-6">
      <input type="text" id="searchQuery" class="form-control form-control-sm" placeholder="검색어 입력">
    </div>
    <div class="col-md-6 d-flex align-items-start">
      <button class="btn btn-primary btn-sm" onclick="searchList()">검색</button>
    </div>
  </div>
</div>



                
                
                
                
              
                <div class="col text-right">
                  <a href="privateqnawrite" class="btn btn-sm btn-primary">1대1문의 작성</a>
                </div>
              </div>
            </div>
            <div class="table-responsive">
              <!-- Projects table -->
  
   <c:if test="${not empty searchResult}">
      <!-- 검색 결과가 있는 경우 -->
      <h3>검색 결과</h3>
      <table class="table">
        <thead>
          <tr>
            <th>문의번호</th>
            <th>작성자</th>
            <th>제목</th>
            <th>문의유형</th>
            <th>문의글 작성 일자</th>
            <th>답변여부</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="result" items="${searchResult}">
            <tr>
              <td>${result.qnaNo}</td>
              <td>${result.memberId}</td>
              <td><a href="privateqnadetail?qnaNo=${result.qnaNo}&pageNo=${pageNo}">${result.qnaTitle}</a></td>
              <td>${result.qnaType}</td>
              <td>${result.qnaDate}</td>
              <td>
                <c:choose>
                  <c:when test="${result.answered}">
                    <span class="badge badge-success">답변 완료</span>
                  </c:when>
                  <c:otherwise>
                    <span class="badge badge-warning">미답변</span>
                  </c:otherwise>
                </c:choose>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </c:if>
    <c:if test="${empty searchResult}">
      <!-- 검색 결과가 없는 경우 -->
      <p>검색 결과가 없습니다.</p>
    </c:if>
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
  var memberNo = <%= request.getAttribute("memberNo") %>;

  // memberNo 값을 콘솔에 로깅하여 확인
  console.log("memberNo: " + memberNo);

  // memberNo가 17인 경우에만 보이도록 설정
  if (memberNo === 17) {
    document.getElementById("viewOption").style.display = "block"; // 보이게 설정
    document.getElementById("viewButton").style.display = "block"; // 보이게 설정
  } else {
    document.getElementById("viewOption").style.display = "none"; // 숨기게 설정
    document.getElementById("viewButton").style.display = "none"; // 숨기게 설정
  }
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