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
  <style>
	  .red_color {
	    color: red; 
	}
  </style>
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
              <h3 class="text-white mb-0">내가 찜한 목록</h3>
            </div>
            <div class="table-responsive">
              <table class="table align-items-center table-white table-flush">
                <thead class="thead-white">
                  <tr>
                  	<th class="td_width_1">선택</th>
					<th class="td_width_2">이미지</th>
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
									<img src="${pageContext.request.contextPath}/resources/upload/thumbnail_${zzim.thumbnail}" alt="Image">							
								</td>
								<td class="td_width_3">${zzim.itemName}</td>
								<td class="td_width_4 price_td">
									판매가 : <span class="red_color"><fmt:formatNumber value="${zzim.itemPrice}" pattern="#,### 원" /></span><br>
									포인트 : <span class="green_color"><fmt:formatNumber value="${zzim.point}" pattern="#,###" /></span>
								</td>
								<td class="td_width_4 table_text_align_center">
									<div class="table_text_align_center count_div">
										<input type="text" value="${zzim.itemCount}" class="count_input">	
										<button class="count_btn plus_btn btn-secondary">+</button>
										<button class="count_btn minus_btn btn-secondary">-</button>
									</div>
									<a class="count_modify_btn" data-cartId="${zzim.zzimNo}">변경</a>
								</td>
								<td class="td_width_4 table_text_align_center">
									<fmt:formatNumber value="${zzim.itemPrice * zzim.itemCount}" pattern="#,### 원" />
								</td>
								<td class="td_width_4 table_text_align_center">
									<button class="delete_btn btn-primary" data-cartid="${zzim.zzimNo}">삭제</button>
								</td>
							</tr>
						</c:forEach>
                  
                </tbody>
              </table>
              
              <div class="total">
              	<div class="total_wrap">
              		<table class="table align-items-center table-white table-flush">
              			<tr>
							<td>
								<table>
									<tr>
										<td>총 상품 가격</td>
										<td>
											<span class="totalPrice_span">1000000</span> 원
										</td>
									</tr>
									<tr>
										<td>배송비</td>
										<td>
											<span class="delivery_price">3000</span>원
										</td>
									</tr>									
									<tr>
										<td>총 주문 상품수</td>
										<td><span class="totalKind_span"></span>총 <span class="totalCount_span"></span>개</td>
									</tr>
								</table>
							</td>
							<td>
								<table>
									<tr>
										<td></td>
										<td></td>
									</tr>
								</table>							
							</td>
						</tr>
              		</table>
              	</div>
              	
              	<div class="content_btn">
              		<a class="rental_btn">대여하기</a>
              	</div>
              	
              	<form action="/rental/{memberNo}" method="get" class="rental_form">
              	
              	</form>
              	
              </div>
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
  
  <script>

  // 대여하기
  $(".rental_btn").on("click", function(){
	  
	  // 폼 정보를 담을 변수
	  let form_contents = "";
	  // 대여 번호 초기화
	  let rentalNo = 0;
	  
	  // 체크된 찜 상품들을 받아올 순회 함수
	  $(".zzim_info_td").each(function(index, element){
		  
		  // 체크 박스 체크 되어있는지
		  if($(element).find(".individual_zzim_checkbox").is(":checked")== true ){
		 
			// 보내줄 itemNo 값 변수에 넣기   
			let itemNo = $(element).find("individual_itemNo_input").val();
			// 보내줄 itemCount 값 변수에 넣기 
			let itemCount = $(element).find("individual_itemCount_input").val();
	 		
			// 변수들을 서버로 보내기 위한 input 태그
			let itemNo_input = "<input name='orderDetailList[" + rentalNo + "].itemNo' type='hidden' value='" + itemNo + "'>";
			// 폼에 담고
			form_contents += itemNo_input;
		 
			// 상품 수량 값 입력 받아 input에 넣어 변수에 넣고
			let itemCount_input = "<input name='orderDetailList[" + rentalNo + "].itemCount' type='hidden' value='" + itemCount + "'>";
			// 폼에 담고
			form_contents += itemCount_input;
			
			// 끝나면 다음 체크된 상품으로
			rentalNo += 1;
			
		  }
	
	});
	
	// rental_form 안에 form_contents에 담긴 문자열들을 태그를 보존한 상태로 적용해서 변경
	// rental_form은 위에 있음
	$(".rental_form").html(form_contents);
	$(".rental_form").submit();
	 
  });

  </script>
</body>

</html>