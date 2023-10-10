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
    
    
    <div class="row mt-5">
        <div class="col">
          <div class="card bg-default shadow">
            <div class="card-header bg-transparent border-0">
              <h3 class="text-white mb-0">Card tables</h3>
            </div>
            <div class="table-responsive">
              <table class="table align-items-center table-white table-flush">
                <thead class="thead-white">
                  <tr>
                  	<th class="td_width_1"></th>
					<th class="td_width_2"></th>
                    <th class="td_width_3">상품명</th>
                    <th class="td_width_4">가격</th>
                    <th class="td_width_4">수량</th>
                    <th class="td_width_4">합계</th>
                    <th class="td_width_4">삭제</th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach items="${zzimInfo}" var="zzim">
							<tr>
								<td class="td_width_1 zzim_info_td">
									<input type="checkbox" class="individual_zzim_checkbox input_size_20" checked="checked">
									<input type="hidden" class="individual_itemPrice_input" value="${zzim.itemPrice}">
									<input type="hidden" class="individual_itemCount_input" value="${zzim.itemCount}">
									<input type="hidden" class="individual_totalPrice_input" value="${zzim.itemPrice * zzim.itemCount}">
									<input type="hidden" class="individual_point_input" value="${zzim.point}">
									<input type="hidden" class="individual_totalPoint_input" value="${zzim.totalPoint}">
									<input type="hidden" class="individual_itemNo_input" value="${zzim.itemNo}">								
								</td>
								<td class="td_width_2">
									이미지							
								</td>
								<td class="td_width_3">${zzim.itemName}</td>
								<td class="td_width_4 price_td">
									<del>가격 : <fmt:formatNumber value="${zzim.bookPrice}" pattern="#,### 원" /></del><br>
									마일리지 : <span class="green_color"><fmt:formatNumber value="${zzim.point}" pattern="#,###" /></span>
								</td>
								<td class="td_width_4 table_text_align_center">
									<div class="table_text_align_center quantity_div">
										<input type="text" value="${zzim.itemCount}" class="quantity_input">	
										<button class="quantity_btn plus_btn">+</button>
										<button class="quantity_btn minus_btn">-</button>
									</div>
									<a class="quantity_modify_btn" data-cartId="${zzim.zzimNo}">변경</a>
								</td>
								<td class="td_width_4 table_text_align_center">
									<fmt:formatNumber value="${zzim.itemPrice * zzim.itemCount}" pattern="#,### 원" />
								</td>
								<td class="td_width_4 table_text_align_center">
									<button class="delete_btn" data-cartid="${zzim.zzimNo}">삭제</button>
								</td>
							</tr>
						</c:forEach>
                  
                </tbody>
              </table>
            </div>
          </div>
        </div>
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