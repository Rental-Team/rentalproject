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
  <jsp:include page="/WEB-INF/views/modules/title.jsp" />
  <!-- Favicon -->
  <link href="/rental-project/resources/img/brand/favicon.png" rel="icon" type="image/png">
  <!-- Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet">
  <!-- Icons -->
  <link href="/rental-project/resources/js/plugins/nucleo/css/nucleo.css" rel="stylesheet" />
  <link href="/rental-project/resources/js/plugins/@fortawesome/fontawesome-free/css/all.min.css" rel="stylesheet" />
  <!-- CSS Files -->
  <link href="/rental-project/resources/css/argon-dashboard.css?v=1.1.2" rel="stylesheet" />
  <!-- navbar css -->
  <link href="/rental-project/resources/css/navbar-top.css" rel="stylesheet" />
 <Style>
	 #notice-list {
  		max-height: 350px;
  		overflow-y: auto;
  	} 
    .product-card {
    	
      display: flex;
      flex-direction: row;
      margin-bottom: 1px;
      margjin-top: 10px;
    }
    .product-info {
      flex: 1;
     
    }
    .notice-container {
      flex: 1;
      padding: 10px; /* 공지사항과 상품 사이의 간격을 조정하세요 */
    }
    </Style>
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
    
 <jsp:include page="/WEB-INF/views/modules/navbar-content2.jsp" /> 
    
    <div class="container-fluid mt--12" style="z-index:1;"> 
  
  <div class="row mt-12">
  <h1 style="margin-left:550px;">❗BEST PICK❗</h1> </div>
      <div class="row mt-12">
       		<div class="product-card">
          	<div class="product-info">

     		<div class="card" style="width: 20rem;">
			  <img class="card-img-top" src="/rental-project/resources/img/brand/벽걸이 에어컨.png" alt="Card image cap" width=200 height=245>

			  <div class="card-body">
			    <h1 class="card-title"style="font-size:19px;">삼성  벽걸이 인버터에어컨 </h1>
			    <a href="item/detail?itemNo=115&pageNo=1" class="btn btn-success" style="position: absolute; bottom: 0; right: 0;">상품보러가기</a>
			  </div>
			</div>
			</div>
			</div>
			<div class="product-card">
			   <div class="product-info">
			<div class="card" style="width: 18rem;">

			  <img class="card-img-top" src="/rental-project/resources/img/brand/정3.png" alt="Card image cap" width=200 height=245>
			  <div class="card-body">
			    <h1 class="card-title" style="font-size:19px;">SK 매직 미니 무전원 정수기</h1>
			    <a href="item/detail?itemNo=113&pageNo=1" class="btn btn-success" style="position: absolute; bottom: 0; right: 0;">상품보러가기</a>
			  </div>
			</div>
        </div>
        </div>
        
  <div class="product-card">
          	<div class="product-info">
          	
     		<div class="card" style="width: 18rem;">
			  <img class="card-img-top" src="/rental-project/resources/img/brand/냉5.png" alt="Card image cap" width=200 height=245>
			  <div class="card-body">
			    <h1 class="card-title" style="font-size:19px;">마루나 일반형 냉장고</h1>
			    
			    <a href="item/detail?itemNo=112&pageNo=1" class="btn btn-success" style="position: absolute; bottom: 0; right: 0;">상품보러가기</a>
			  </div>
			</div>
			</div>
			</div>
			<div class="product-card">
			   <div class="product-info">
			<div class="card" style="width: 18rem;">
			  <img class="card-img-top" src="/rental-project/resources/img/brand/그랑데2.png" alt="Card image cap" width=200 height=245>
			  <div class="card-body">
			    <h1 class="card-title" style="font-size:19px">삼성 그랑데 통버블세탁기</h1>
			    <a href="item/detail?itemNo=111&pageNo=1" class="btn btn-success" style="position: absolute; bottom: 0; right: 0;">상품보러가기</a>
			  </div>
			</div>
        </div>
        </div>

       <div class="notice-container">
                 <div class="card shadow">

            <div class="card-header border-0">
              <div class="row align-items-center">
                <div class="col" >
                  <h3 class="mb-0">공지사항</h3>
                </div>
                <div class="col text-right">
                  <a href="./notice/list" class="btn btn-sm btn-success">더보기</a>
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
 <jsp:include page="/WEB-INF/views/modules/footer.jsp" /> 
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