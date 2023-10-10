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
<jsp:include page="/WEB-INF/views/admin/modules/navbar-vertical.jsp" />
  <div class="main-content">
    <!-- Navbar -->
	<jsp:include page="/WEB-INF/views/admin/modules/navbar-top.jsp" />
    <!-- End Navbar -->
    <!-- Header -->
    <jsp:include page="/WEB-INF/views/admin/modules/navbar-content.jsp" />
    <div class="container-fluid mt--7">
      <div class="row">
      	<div class="col-xl-12 mb-5 mb-xl-0">
          <div class="card shadow">
            <div class="card-header border-0">
              <div class="row align-items-center">
                <div class="col">
                  <h3 class="mb-0">상품 게시판</h3>
                </div>
                <div class="col text-right">
                  <a href="write" class="btn btn-sm btn-primary">상품 등록</a>
                </div>
              </div>
            </div>
            <div class="table-responsive">
              <!-- Projects table -->
              <table class="table align-items-center table-flush">
                <thead class="thead-light">
                  <tr>
                    <th scope="col" style="width:100px">이미지</th>
                    <th scope="col" style="width:200px">상품 이름</th>
                    <th scope="col" style="width:100px">상품 가격</th>
                    <th scope="col" style="width:100px">재고</th>
                    <th scope="col" style="width:100px">조회수</th>
                    <th scope="col" style="width:150px">등록 일자</th>
                  </tr>
                </thead>
                <tbody>
                 <c:forEach var="item" items="${ itemList }">
                 	<tr>
                 		<td>
                        	<img src="${pageContext.request.contextPath}/resources/upload/thumbnail_${item.thumbnail}" alt="Image">                    	
						</td>
                    	<td style="text-align:left;padding-left:10px">
						<c:choose>
							<c:when test="${ not item.deleted }">
								<a href="detail?itemNo=${ item.itemNo }&pageNo=${ pageNo }">${ item.itemName }</a>
							</c:when>
							<c:otherwise>
								<span class="deleted" style="color=lightgray"> === 대여가 끝난 상품입니다. === </span>
							</c:otherwise>
						</c:choose>
						</td>      
						<td><c:out value="${item.itemPrice}" /></td>
						<td><c:out value="${item.itemStock}" /></td>      	 
                    	 <td><c:out value="${item.viewCount}" /></td>
                    	 <td><fmt:formatDate pattern="yyyy-MM-dd"
                    	  value="${item.itemDate }" /></td>
                  </tr>
                 </c:forEach>
         
                </tbody>
              </table>
              
              <div class="search_wrap">
              	<div class="search_input">
                  <form id="searchForm" action="/admin/item/list" method="get" >
                    	<input type="text" name="keyword" />
            		    <button class='btn search_btn'>검 색</button>                				
                  </form>
				</div>
               </div>
              
              <br><br>
                 ${ pager }
                 <br /><br />
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