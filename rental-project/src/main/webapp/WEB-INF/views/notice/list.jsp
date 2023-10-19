<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
    .nav-link2.notice {
	
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
	<jsp:include page="/WEB-INF/views/modules/navbar-top4.jsp" />
    <!-- End Navbar -->
    <!-- Header -->
    <jsp:include page="/WEB-INF/views/modules/navbar-content6.jsp" />
    <div class="container-fluid mt--7">
    
      <div class="row">
       
        <div class="col-xl-12 mb-5 mb-xl-0">
       
          <div class="card shadow">
            <div class="card-header border-0">
              <div class="row align-items-center">
                <div class="col">
                  <h3 style="font-weight:bold" class="mb-0">공지사항</h3>
                </div>
                <div class="col text-right">
                 <!--  <a href="write" class="btn btn btn-primary">게시글 작성</a> -->
                </div>
              </div>
            </div>
            <div class="table-responsive">  
         <!-- Projects table -->
         <table class="table align-items-center table-flush">
           <thead class="thead-light">
             <tr style="text-align:center;">
               <th scope="col" style="width:100px; font-size:10pt">게시글 번호</th>
               <th scope="col" style="width:500px; font-size:10pt">게시글 제목</th> 
               <!-- <th scope="col" style="width:200px; font-size:10pt">작성자</th> -->
               <th scope="col" style="width:100px; font-size:10pt">조회수</th>
               <th scope="col" style="width:150px; font-size:10pt">게시글 작성 일자</th>
             </tr>
           </thead>
           <tbody>
            <c:forEach var="notice" items="${ requestScope.noticeList }">
             <tr style="text-align:center">
                <td scope="col" style="width:100px">${ notice.noticeNo } </td>
                <td scope="col" style="width:500px"><a href="/rental-project/notice/detail?noticeNo=${ notice.noticeNo }"> ${ notice.noticeTitle }</a> </td>
                <%-- <td scope="col" style="width:100px">${ notice.viewCount } </td> --%>
                <td scope="col" style="width:100px">${ notice.viewCount } </td>
                <td scope="col" style="width:150px"><fmt:formatDate value="${ notice.noticeDate }" pattern="yyyy-MM-dd HH:mm"/></td>
             </tr>
            </c:forEach>
                </tbody>
              </table>
              ${ pager }
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
</body>

</html>