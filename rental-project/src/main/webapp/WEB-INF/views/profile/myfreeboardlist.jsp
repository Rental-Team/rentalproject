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
   <link href="/rental-project/resources/css/navbar-top.css" rel="stylesheet" />
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
    <jsp:include page="/WEB-INF/views/modules/navbar-content3.jsp" />
    <div class="container-fluid mt--7">
      <div class="row">
        <div class="col-xl-12 mb-5 mb-xl-0">
          <div class="card shadow">
            <div class="card-header border-0">
              <div class="row align-items-center">
                <div class="col">
                  <h3 style="font-weight:bold" class="mb-0">자유게시판</h3>
                </div>
                <div class="col text-right">
                  <a href="freeboardwrite" class="btn btn btn-success">게시글 작성</a>
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
               <th scope="col" style="width:200px; font-size:10pt">작성자</th>
               <th scope="col" style="width:100px; font-size:10pt">조회수</th>
               <th scope="col" style="width:150px; font-size:10pt">게시글 작성 일자</th>
             </tr>
           </thead>
           <tbody>
			  <c:forEach var="freeBoard" items="${requestScope.myFreeBoardList}">
			    <tr style="text-align:center">
			      <td scope="col" style="width:100px">${freeBoard.freeBoardNo}</td>
			      <td scope="col" style="width:500px">
			        <c:choose>
			          <c:when test="${not freeBoard.freeBoardDelete}">
			            <a href="freeboarddetail?freeBoardNo=${freeBoard.freeBoardNo}&pageNo=${pageNo}">
			              ${freeBoard.freeBoardTitle}
			            </a>
			          </c:when>
			          <c:otherwise>
			            <span class="freeBoardDelete" style="color: gray">&lt;&lt; 삭제된 게시글입니다 &gt;&gt;</span>
			          </c:otherwise>
			        </c:choose>
			      </td>
			      <td scope="col" style="width:200px">${freeBoard.memberId}</td>
			      <td scope="col" style="width:100px">${freeBoard.freeBoardViewCount}</td>
			      <td scope="col" style="width:150px">
			        <fmt:formatDate value="${freeBoard.freeBoardDate}" pattern="yyyy-MM-dd HH:mm" />
			      </td>
			    </tr>
			  </c:forEach>
			</tbody>
           </table>
           <!-- 검색 form  -->
           		<div class="p-4 bg-secondary" style="width:1735px">
				  <form name="search-form" method="get" action="search-list" autocomplete="off" 
				  class="d-flex align-items-center justify-content-center" style="width:100%; text-align:center;">
					<div class="input-group input-group-alternative" style="width:180%;"> 
					<div class="input-group-prepend"></div> 
						<select name="type" class="form-control"> 
							<option selected value=""> 검색내용을 선택하세요</option>
					        <option value="freeBoardTitle">제목</option> 
					        <option value="freeBoardContent">내용</option> 
							<option value="memberId">작성자</option> 
						</select> 
						<input type="text" name="keyword" value="" style="width:70%" class="form-control form-control-alternative" placeholder="    검색어를 입력하세요">
				        <button type="submit" class="btn btn btn-success" id="btnsearch">검색</button>
				    </div>  
				    <input type="hidden" name="pageNo" value="${pageNo}"> 
					</form> 
				</div> 
              	<br><br>
           	 <!-- 검색 form  -->
                 ${ pager }
                 <br /><br />
				
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