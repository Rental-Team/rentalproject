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
  <style>
	  .red_color {
	    color: red; 
	}
  </style>
</head>

<body class="">
  <div class="main-content">
    <!-- Navbar -->
	<jsp:include page="/WEB-INF/views/modules/navbar-top.jsp" />
	<jsp:include page="/WEB-INF/views/modules/navbar-top2.jsp" />
	<jsp:include page="/WEB-INF/views/modules/navbar-top3.jsp" />
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
								<input type="hidden" class="individual_zzimNo_input" value="${zzim.zzimNo}">

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
										<input type="text" value="${zzim.itemCount}" class="quantity_input">	
									<button class="quantity_btn plus_btn btn-secondary">+</button>
								<button class="quantity_btn minus_btn btn-secondary">-</button>
								</div>
								<a class="quantity_modify_btn" data-zzimNo="${zzim.zzimNo}">변경</a>
							</td>
							<td class="td_width_4 table_text_align_center">
								<fmt:formatNumber value="${zzim.itemPrice * zzim.itemCount}" pattern="#,### 원" />
							</td>
							<td class="td_width_4 table_text_align_center">
								<button id="delete_btn" class="delete_btn btn btn-sm btn-success" data-zzimNo="${zzim.zzimNo}">삭제</button>
							</td>
						</tr>
					</c:forEach>
                  
                </tbody>
              </table>
              
              
              	
              	<div class="content_btn">
              		<button class="rental_btn btn btn-success">대여하기</button>
              	</div>
              	
              	<form action="/rental-project/zzim/update" method="post" class="quantity_update_form">
					<input type="hidden" name="zzimNo" class="update_zzimNo">
					<input type="hidden" name="itemCount" class="update_itemCount">
					<input type="hidden" name="memberNo" value="${ loginuser.memberNo }">
				</form>
				
				<form action="/rental-project/zzim/delete" method="post" class="delete_form">
					<input type="hidden" name="zzimNo" class="delete_zzimNo">
					
				</form>
              	
              	<form action="/rental-project/rental" method="get" class="rental_form">
              		<input type="hidden" name='itemNos'>
              		<input type="hidden" name='itemCounts'>
              	</form>
              	
              </div>
            </div>
          </div>
                    
          
        </div>
          <jsp:include page="/WEB-INF/views/modules/footer.jsp" /> 
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
  
  $(".plus_btn").on("click", function(){
	  
	  let quantity = $(this).parent("div").find("input").val();
	  $(this).parent("div").find("input").val(++quantity);
		
	});
  
  $(".minus_btn").on("click", function(){
	  
	  let quantity = $(this).parent("div").find("input").val();
	    if(quantity > 1){
			$(this).parent("div").find("input").val(--quantity);		
		}
	    
	});
  
  $(".quantity_modify_btn").on("click", function(){
	  
	  let zzimNo = $(this).data("zzimno");
	  let itemCount = $(this).parent("td").find("input").val();
	  
	  $(".update_zzimNo").val(zzimNo);
	  $(".update_itemCount").val(itemCount);
	  $(".quantity_update_form").submit();
	  
  });
  
  $(".delete_btn").on("click", function(e){
	  e.preventDefault();
	  const zzimNo = $(this).data("zzimno");
	  
	  $(".delete_zzimNo").val(zzimNo);
	  $(".delete_form").submit();
  });

  // 대여하기
  $(".rental_btn").on("click", function(){
	
	  // 폼 정보를 담을 변수
	  let form_contents = "";
	  // 대여 번호 초기화
	  let rentalNo = 0;
	  
	  let itemNosVal = "";
	  let itemCountsVal = "";
	  // 체크된 찜 상품들을 받아올 순회 함수
	  $(".zzim_info_td").each(function(index, element){  
		  // 체크 박스 체크 되어있는지
		  if($(element).find(".individual_zzim_checkbox").is(":checked")== true ){
		 
			// 보내줄 itemNo 값 변수에 넣기   
			let itemNo = $(element).find(".individual_itemNo_input").val();
			// 보내줄 itemCount 값 변수에 넣기 
			let itemCount = $(element).find(".individual_itemCount_input").val();
			
			itemNosVal += itemNo + ",";
			itemCountsVal += itemCount + ",";
			
		  }
		  
	});
	  if (itemNosVal.length > 0) {
		  itemNosVal = itemNosVal.substring(0, itemNosVal.length - 1);
		  itemCountsVal =  itemCountsVal.substring(0, itemCountsVal.length - 1);
		  $(".rental_form input[name=itemNos]").val(itemNosVal);
		  $(".rental_form input[name=itemCounts]").val(itemCountsVal);

			$(".rental_form").submit();
	  
	  } else {
		 alert('선택하세요');
	  }
	  
	 
  });

  </script>
</body>

</html>