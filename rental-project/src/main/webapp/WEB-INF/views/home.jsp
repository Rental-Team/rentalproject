<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
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
        <div class="col-xl-8 mb-5 mb-xl-0">
          <div class="card bg-gradient-default shadow">
            <div class="card-header bg-transparent">
              <div class="row align-items-center">
                <div class="col">
                  <h6 class="text-uppercase text-light ls-1 mb-1">Overview</h6>
                  <h2 class="text-white mb-0">Sales value</h2>
                </div>
                <div class="col">
                  <ul class="nav nav-pills justify-content-end">
                    <li class="nav-item mr-2 mr-md-0" data-toggle="chart" data-target="#chart-sales" data-update='{"data":{"datasets":[{"data":[0, 20, 10, 30, 15, 40, 20, 60, 60]}]}}' data-prefix="$" data-suffix="k">
                      <a href="#" class="nav-link py-2 px-3 active" data-toggle="tab">
                        <span class="d-none d-md-block">Month</span>
                        <span class="d-md-none">M</span>
                      </a>
                    </li>
                    <li class="nav-item" data-toggle="chart" data-target="#chart-sales" data-update='{"data":{"datasets":[{"data":[0, 20, 5, 25, 10, 30, 15, 40, 40]}]}}' data-prefix="$" data-suffix="k">
                      <a href="#" class="nav-link py-2 px-3" data-toggle="tab">
                        <span class="d-none d-md-block">Week</span>
                        <span class="d-md-none">W</span>
                      </a>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
            <div class="card-body">
              <!-- Chart -->
              <div class="chart">
                <!-- Chart wrapper -->
                <canvas id="chart-sales" class="chart-canvas"></canvas>
              </div>
            </div>
          </div>
        </div>
        <div class="col-xl-4">
          <div class="card shadow">
            <div class="card-header bg-transparent">
              <div class="row align-items-center">
                <div class="col">
                  <h6 class="text-uppercase text-muted ls-1 mb-1">Performance</h6>
                  <h2 class="mb-0">Total orders</h2>
                </div>
              </div>
            </div>
            <div class="card-body">
              <!-- Chart -->
              <div class="chart">
                <canvas id="chart-orders" class="chart-canvas"></canvas>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="row mt-5">
     		<div class="card" style="width: 18rem;">
			  <img class="card-img-top" src="/rental-project/resources/img/brand/fridge.png" alt="Card image cap" width=20% height=60%>
			  <div class="card-body">
			    <h1 class="card-title">냉장고</h1>
			    <p class="card-text">SAMSUNG
			    세프컬렉션 냉장고 930L, 세라블랙</p>
			    <a href="#" class="btn btn-primary">상품보러가기</a>
			  </div>
			</div>
			<div class="card" style="width: 18rem;">
			  <img class="card-img-top" src="/rental-project/resources/img/brand/range.png" alt="Card image cap" width=20% height=60%>
			  <div class="card-body">
			    <h1 class="card-title">전자레인지</h1>
			    <p class="card-text">SK매직
			    MWO-M8A01, 용량20L, 출력 700W, 화이트</p>
			    <a href="#" class="btn btn-primary">상품보러가기</a>
			  </div>
			</div>
        <div class="col-xl-7"  >
          <div class="card shadow">
            <div class="card-header border-0">
              <div class="row align-items-center">
                <div class="col">
                  <h3 class="mb-0">공지사항</h3>
                 
                </div>
                <div class="col text-right">
                  <a href="./notice/list" class="btn btn-sm btn-primary">더보기</a>

                </div>
              </div>
            </div>
            
            <div class="table-responsive" id="notice-list">
            
           
              <!-- Projects table -->
              <table class="table align-items-center table-flush">

                   <tbody>
                 

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
  
<!--   <script>
  function loadNotices() {
	  $.ajax({
		  url: '/rental-project/notice/list',
		  method: 'GET',
		  dataType: 'json',
		  success:function(data) {
			  displayNotices(data);
		  },
		  error: function(err) {
			  console.error("공지사항 목록을 가져오는 도중 오류 발생:", err);
		  }
	  })
  }
  
  $(document).ready(function() {
	  loadNotices();
	  
  
  
  setInterval(function() {
	  loadNotices();
  }, 5000);
  });
 
  
  function displayNotices(notices) {
	  var tbody = $('#notice-container table tbody');
	  tbody.empty();
	  
	  for(var i = 0; i< notices.length; i++) {
		  var notice = notices[i];
		  var row = '<tr>';
		  row += '<td>' + notice.noticeTitle + '</td>';
		  row += '<td>' + notice.noticeDate + '</td>';
	  
		  row += '<td><a href="/rental-project/notice/detail?noticeNo=' + notice.noticeNo + '">상세보기</a></td>';
		  row += '</tr>';
		  tbody.append(row);
	  }
  }
  
  
  
  </script> -->
  <script>
function loadNoticeList() {
  $.ajax({
    url: '/rental-project/notice/notice2',
    method: 'GET',
    dataType: 'html', 
    success: function (data) {
      $('#notice-list').html(data);
    },
    error: function (err) {
      console.error("공지사항 목록을 가져오는 도중 오류 발생:", err);
    }
  });
}

$(document).ready(function () { 
  loadNoticeList();

  // 일정 간격으로 공지사항 목록을 업데이트하려면 아래 코드를 사용하세요.
  // setInterval(function () {
  //   loadNoticeList();
  // }, 5000); // 5초마다 업데이트
});
</script>
</body>

</html>