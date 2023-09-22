<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                  <h3 class="mb-0">자유게시판</h3>
                </div>
                <div class="col text-right">
                  <a href="freeboardwrite" class="btn btn-sm btn-primary">게시글 작성</a>
                </div>
              </div>
            </div>
            <div class="table-responsive">  
         <!-- Projects table -->
         <table class="table align-items-center table-flush">
           <thead class="thead-light">
             <tr style="text-align:center">
               <th scope="col" style="width:100px">게시글 번호</th>
               <th scope="col" style="width:500px">게시글 제목</th> 
               <th scope="col" style="width:200px">작성자</th>
               <th scope="col" style="width:100px">조회수</th>
               <th scope="col" style="width:150px">게시글 작성 일자</th>
             </tr>
           </thead>
           <tbody>
            <c:forEach var="freeBoard" items="${ requestScope.freeBoardList }">
             <tr style="text-align:center">
                <td scope="col" style="width:100px">${ freeBoard.freeBoardNo } </td>
                <td scope="col" style="width:500px"> 
                <c:choose>
                	<c:when test="${ not freeBoard.freeBoardDelete }">
                	<a href="freeboarddetail?freeBoardNo=${ freeBoard.freeBoardNo }"> ${ freeBoard.freeBoardTitle }</a>  <!-- 삭제 아닐때 그냥 제목 보이기 -->
                	</c:when>
                	<c:otherwise>
                	<span class="freeBoardDelete" style="color : gray"><< 삭제된 게시글입니다 >></span> <!-- 삭제된 글 화면 보이기 -->
                	</c:otherwise>
               	</c:choose>
                </td>
                <td scope="col" style="width:200px"> ${ freeBoard.freeBoardDelete? '' : sessionScope.loginuser.memberId } <!-- 게시글 삭제시 작성자 안보이게 설정 -->
		                							 <input type="hidden" name="memberNo" value="${ loginuser.memberId }"> 
		                							 <!-- 오류 : 다시확인 로그인한 유저로 걍 다 바뀜 -->
                
                <td scope="col" style="width:100px">${ freeBoard.freeBoardViewCount } </td>
                <td scope="col" style="width:150px"><fmt:formatDate value="${ freeBoard.freeBoardDate }" pattern="yyyy-MM-dd HH:mm"/></td>
             </tr>
            </c:forEach>
                </tbody>
              </table>
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