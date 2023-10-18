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


<nav class="navbar navbar-expand-lg navbar-light bg-content mb-2" style="border: 1px solid white;">  <!-- bg-success -->
  <div class="container-fluid">
    <!-- 좌측 정렬을 위한 그리드 시스템을 사용합니다 -->
    <div class="d-flex align-items-center">
      <a class="navbar-brand" href="/rental-project/home" style="font-size: 24px; font-weight: bold; ">
        <img src="/rental-project/resources/img/brand/225.png" class="navbar-brand-img" alt="..." style="width: 160px; height: auto;">
      </a>
    </div>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
     <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ml-auto">
        <li class="nav-item active">
          <a class="nav-link dashboard-menu" href="/rental-project/home">
            <i style="font-size: 24px; color: blue;"></i> 대시보드
          </a>
        </li>
        <li class="nav-item active">
          <a class="nav-link dashboard-menu" href="/rental-project/item/list">
            <i style="font-size: 24px; color: green;"></i> 상품 게시판
          </a>
        </li>
        <li class="nav-item active">
          <a class="nav-link dashboard-menu" href="/rental-project/freeboard/freeboardlist">
            <i style="font-size: 24px; color: yellow;"></i> 자유 게시판
          </a>
        </li>
        <li class ="nav-item active">
          <a class="nav-link dashboard-menu" href="/rental-project/notice/list">
            <i style="font-size: 24px; color: red;"></i> 공지사항
          </a>
        </li>
      </ul>
    </div>
      <!-- 사용자 관련 요소 -->
      <ul class="navbar-nav align-items-center d-none d-md-flex ml-auto">
        <li class="nav-item dropdown"> 
            <div class="media align-items-center">
              <!-- User 아이콘 또는 이미지 -->
              <c:choose>
                <c:when test="${loginuser eq null}">
                  <!-- 로그인 전 화면 -->
                  <a href="/rental-project/account/login" style="color: inherit;">
                    <span class="mb-0 text font-weight-bold">로그인</span>
                  </a>
                </c:when>
                <c:otherwise>
                  <!-- 로그인 후 화면 -->
                  <c:if test="${sessionScope.loginuser.admin == 2}">
                    <div class="media-body ml-2 d-none d-lg-block">
                      <a href="/rental-project/admin/home" style="color: inherit;">
                        <span class="mb-0 text font-weight-bold">관리자 화면</span>
                      </a>
                    </div>
                  </c:if>
                   <div class="media-body ml-2 d-none d-lg-block">
                            <div class="d-flex align-items-center">
                                <a class="nav-link" href="/rental-project/profile/profile?memberId=${loginuser.memberId}">
                                  	<c:if test="${loginuser.memberImage == null}">
									    <img src="/rental-project/resources/img/theme/default.png" draggable="false" class="style-scope yt-img-shadow" height="32" width="32">
									</c:if>
									<c:if test="${not empty loginuser.memberImage}">
									    <img src="${pageContext.request.contextPath}/resources/upload/${loginuser.memberImage}" alt="Image" draggable="false" class="style-scope yt-img-shadow" height="32" width="32">
									</c:if>
										${sessionScope.loginuser.memberId}님
                                </a>
                                <a href="/rental-project/account/logout" style="color: inherit;">
                                    <div class="d-flex align-items-center">
                                        <span class="mb-0 text font-weight-bold mr-2">로그아웃</span>
                                        <i class="ni ni-user-run"></i>
                                    </div>
                                </a>
                  </div>
                </c:otherwise>
              </c:choose>
            </div> 
          <div class="dropdown-menu dropdown-menu-arrow dropdown-menu-right">
            <div class=" dropdown-header noti-title">
              <h6 class="text-overflow m-0">Welcome!</h6>
            </div>
            <a href="./examples/profile.html" class="dropdown-item">
              <i class="ni ni-single-02"></i>
              <span>My profile</span>
            </a>
            <a href="./examples/profile.html" class="dropdown-item">
              <i class="ni ni-settings-gear-65"></i>
              <span>Settings</span>
            </a>
            <a href="./examples/profile.html" class="dropdown-item">
              <i class="ni ni-calendar-grid-58"></i>
              <span>Activity</span>
            </a>
            <a href="./examples/profile.html" class="dropdown-item">
              <i class="ni ni-support-16"></i>
              <span>Support</span>
            </a>
            <div class="dropdown-divider"></div>
            <a href="#!" class="dropdown-item">
              <i class="ni ni-user-run"></i>
              <span>Logout</span>
            </a>
          </li>
        </ul>
      </div>
    </div> 
  </nav>
    
    
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
                <p>Number of visits today: ${visitCount}</p>
    			<canvas id="weeklyVisitChart" width="400" height="200"></canvas>
              </div>
            </div>
          </div>
        </div>
      </div> 
      <div class="row mt-5">
  <div class="col-md-2 mb-4">
    <div class="card" style="width: 5cm; height: 10cm; margin-left: 20px;">
      <a href="#" style="width: 5cm; height: 8cm">
      <img class="card-img-top" src="/rental-project/resources/img/brand/fridge.png" alt="Card image cap">
   	 </a>
      <div class="card-body">
        <h3 class="card-title">냉장고</h3>
        <p class="card-text" style="font-size: 12px;">SAMSUNG 세프컬렉션 냉장고 930L, 세라블랙</p> 
      </div>
      <br>
    </div>
  </div>
  <div class="col-md-2 mb-4">
    <div class="card" style="width: 5cm; height: 10cm">
      <img class="card-img-top" src="/rental-project/resources/img/brand/range.png" alt="Card image cap" width="20%" height="60%">
      <div class="card-body">
        <h3 class="card-title">전자레인지</h3>
        <p class="card-text" style="font-size: 12px;">SK매직 MWO-M8A01, 용량20L, 출력 700W, 화이트</p> 
      </div>
    </div>
  </div>
   <div class="col-md-2 mb-4">
    <div class="card" style="width: 5cm; height: 11cm">
      <img class="card-img-top" src="/rental-project/resources/img/brand/range.png" alt="Card image cap" width="20%" height="60%">
      <div class="card-body">
        <h3 class="card-title">전자레인지</h3>
        <p class="card-text" style="font-size: 12px;">SK매직 MWO-M8A01, 용량20L, 출력 700W, 화이트</p> 
      </div>
    </div>
  </div>
   <div class="col-md-2 mb-4">
    <div class="card" style="width: 5cm; height: 11cm">
      <img class="card-img-top" src="/rental-project/resources/img/brand/range.png" alt="Card image cap" width="20%" height="60%">
      <div class="card-body">
        <h3 class="card-title">전자레인지</h3>
        <p class="card-text" style="font-size: 12px;">SK매직 MWO-M8A01, 용량20L, 출력 700W, 화이트</p>
        <a href="item/list" class="btn btn-sm btn-success">상품보러가기</a>
      </div>
    </div>
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
  <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.7.0/chart.min.js"></script>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <!--   Argon JS   -->
  <script src="/rental-project/resources/js/argon-dashboard.min.js?v=1.1.2"></script>
  <script src="https://cdn.trackjs.com/agent/v3/latest/t.js"></script>
  <script src="https://www.gstatic.com/charts/loader.js"></script>
  <canvas id="weeklyVisitChart" width="400" height="200"></canvas>
  <script>
    window.TrackJS &&
      TrackJS.install({
        token: "ee6fab19c5a04ac1a32a645abde4613a",
        application: "argon-dashboard-free"
      });
  </script>

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
 <script>
    // Function to make an AJAX request to fetch weekly visit data
    function fetchWeeklyVisitData() {
        $.ajax({
            url: 'weekly', // URL for your weekly data endpoint
            method: 'GET',
            success: function (data) {
                // Extract date labels and visit counts from the data
                var dates = data.map(function (item) {
                    return item.visitDate;
                });
                var visitCounts = data.map(function (item) {
                    return item.visitNumber;
                });

                // Create a chart using Chart.js
                var ctx = document.getElementById('weeklyVisitChart').getContext('2d');
                var myChart = new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels: dates,
                        datasets: [{
                            label: 'Weekly Visits',
                            data: visitCounts,
                            backgroundColor: 'rgba(75, 192, 192, 0.2)',
                            borderColor: 'rgba(75, 192, 192, 1)',
                            borderWidth: 1
                        }]
                    },
                    options: {
                        scales: {
                            y: {
                                beginAtZero: true
                            }
                        }
                    }
                });
            },
            error: function (error) {
                console.error('Error fetching data: ' + error);
            }
        });
    }

    // Call the function to fetch weekly visit data
    fetchWeeklyVisitData();
</script>
</body>

</html>