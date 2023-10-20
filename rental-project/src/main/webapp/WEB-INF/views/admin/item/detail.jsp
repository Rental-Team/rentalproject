<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!--

=========================================================
* Argon Dashboard - v1.1.2
=========================================================

* Product Page: https://www.creative-tim.com/product/argon-dashboard
* Copyright 2020 Creative Tim (https://www.creative-tim.com)
* Licensed under MIT (https://github.com/creativetimofficial/argon-dashboard/blob/master/LICENSE.md)

* Coded by Creative Tim

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software. -->
<!DOCTYPE html>
<html lang="en">

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
    <jsp:include page="/WEB-INF/views/admin/modules/navbar-content.jsp" />
    <div class="container-fluid mt--7">
      <div class="row">
        <div class="col-xl-12 mb-5 mb-xl-0">
          <div class="card shadow">
            <div class="card-header border-0">
              <div class="row align-items-center">
                <div class="col">
                  <h3 class="mb-1">상품 설명</h3>
                </div>
                <div class="col text-right">
                  <a href="list" class="btn btn-success">목록</a>
                </div>
              </div>
            </div>
			 <div class="card-body">
                <!-- <h6 class="heading-small text-muted mb-4">User information</h6> -->
                <div class="pl-lg-12" style="margin : 0 auto;">
                  <div class="row">
                    <div class="col-lg-6" >
                      <div class="form-group focused">
                        <label class="form-control-label"for="input-itemName">상품명</label>
                        <input disabled="disabled" type="text" id="input-itemName"  name="itemName" class="form-control form-control-alternative" value="${ item.itemName }">
                           </div>
                    </div>
                    
                        <div class="col-lg-6">
                      <div class="form-group">
                        <label class="form-control-label"  for="input-itemDate">등록 날짜</label>
                        <input disabled="disabled" type="datetime" id="input-itemDate" name="itemDate" class="form-control form-control-alternative"  value="${ item.itemDate }" pattern="yyyy-MM-dd HH:mm"/>           
                   </div>
                    </div>
                  </div>
                  
                  <div class="row">
                    <div class="col-lg-6">
                      <div class="form-group">
                        <label class="form-control-label" for="input-itemStock">상품 수량</label>
                        <input disabled="disabled" type="text" id="input-itemStock" name="itemStock" class="form-control form-control-alternative" value="${ item.itemStock }">
                      </div>
                  </div>
                  
                    <div class="col-lg-6">
                      <div class="form-group">
                        <label class="form-control-label" for="input-cateName">카테고리</label>
                        <input disabled="disabled" type="text" id="input-cateName" name="cateName" class="form-control form-control-alternative" value="${ item.cateName }">
                      </div>
                    </div>
                  </div>
                  
                  <div class="row">
                  
                  	<div class="col-lg-6" >
                      	<div class="form-group focused">
                        	<label style="font-size:12pt" class="form-control-label" for="input-itemAttach">첨부파일</label> 
                        		<br>
	                        	<div>
			                		<c:forEach var="itemAttach" items="${item.itemAttachList}">
										<img src="${pageContext.request.contextPath}/resources/upload/${itemAttach.savedFileName}" alt="Image" height="100px" width="100px">
									</c:forEach>
			                	</div>
			                	
			  
                       </div>
                    </div>
                  
                    <div class="col-lg-2">
                      <div class="form-group">
                        <label class="form-control-label" for="input-itemPrice">상품 가격</label>
                        <input disabled="disabled" type="number" id="input-itemPrice" name="itemPrice" class="form-control form-control-alternative" value="${ item.itemPrice }">
                      </div>
                    </div>
                  
                  </div>  
                   
                  
                  <div class="row">
                    <div class="col-lg-12">
                      <div class="form-group">
                        <label class="form-control-label" for="input-itemDetail">상세 설명</label>
                        <textarea disabled="disabled" id="input-itemDetail" name="itemDetail" class="form-control form-control-alternative" rows="15" style="resize: none" >${ item.itemDetail }</textarea>
                      </div>
                    </div>
                  </div>
                  
                  <div class="row">
                    <div class="col-lg-12">
                      <div class="text-right">
                        <a href="edit?itemNo=${ item.itemNo }&pageNo=${ pageNo }" class ="btn btn-success">수정</a> 
				        <a href="javascript:" class ="btn btn-success" id="delete-item">삭제</a>
                      </div>
                    </div>
                  </div>
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
	  $('#delete-item').on('click', function(event) {
		  const ok = confirm(${ item.itemNo } + "번 삭제?");
			if (ok) {
				location.href = 'delete/' + ${ item.itemNo } + "?pageNo=" + ${pageNo};
			}
	  });
	  
	  
  });
  </script>
</body>

</html>