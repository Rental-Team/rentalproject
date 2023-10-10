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
                  <h3 class="mb-0">상품 수정</h3>
                  <br>
                  <form action="edit" method="post">
	                  <input type="hidden" name="categoryName" value="가전">
	                  <input type="hidden" name="itemNo" value="${ item.itemNo }">
	                  <div class="pl-lg-4">
			              <div class="row">
			              <div class="col-lg-12">
			              <div class="form-group">
			              	<label class="form-control-label" for="input-itemName">상품명</label>
			               <input type="text" id="input-itemName" name="itemName" class="form-control form-control-alternative" value=${ item.itemName }/>
			              </div>
			              </div>
			              </div>
			            <div class="row">
			            <div class="col-lg-12">
			            <div class="form-group">
			            <label class="form-control-label" for="input-cateName">카테고리</label>
			            <input type="text" id="input-cateName" name="cateName" class="form-control form-control-alternative" value=${ item.cateName } />
			            </div>
			            </div>
			            </div>
		           
		                    
		            <div class="row">
			            <div class="col-lg-12">
			            <div class="form-group">
			            <label class="form-control-label" for="input-itemPrice">상품 가격</label>
			             <input type="text" id="input-itemPrice" name="itemPrice" class="form-control form-control-alternative" value="${ item.itemPrice }" />
			            </div>
			            </div>
			            </div>
		                   
		            <div class="row">
			            <div class="col-lg-12">
			            <div class="form-group">
			            <label class="form-control-label" for="input-itemDetail">상세 설명</label>
			            <textarea name="itemDetail" id="input-itemDetail" class="form-control form-control-alternative" style="resize:none;" rows="15" > ${ item.itemDetail }</textarea>
			            </div>
			            </div>
			            </div>
		                	
		          		<div class="row">
		          		<div class="col-lg-12">
		          		<div class="text-right">
				        <input type="submit" class ="btn btn-sm btn-primary" value="상품 수정" >
				        <input type="button" class ="btn btn-sm btn-primary" id="btnCancel" value="취소" >
		          		</div>
          			</div> 
       	   	    </div>  
               </div>
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