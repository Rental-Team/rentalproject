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
  .float-right {
    float: right;
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
                  <a href="list" class="btn btn btn-primary">목록</a>
                </div>
              </div>
            </div>
            <form action="write" method="post"> 
        
            <input type="hidden" name="itemNo" value="${ item.itemNo }" />
			<input type="hidden" name="pageNo" value="${ pageNo }" />
			<input type="hidden" name="writer" value="${ loginuser.memberNo }" />
			 <div class="card-body">
			 	<input disabled="disabled" type="hidden" id="itemNo"  name="itemNo" class="form-control form-control-alternative" value="${ item.itemNo }">
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
								    	<a href="download?attachNo=${itemAttach.attachNo}">${itemAttach.userFileName}</a>
								    	<img src="/resources/upload/${savedFileName}">
										<img src="${pageContext.request.contextPath}/resources/upload/${itemAttach.savedFileName}" alt="Image" height="100px" width="100px">
									</c:forEach>
			                	</div>
			                	
			  
                       </div>
                    </div>
                  
                    <div class="col-lg-2">
                      <div class="form-group">
                        <label class="form-control-label" for="input-itemPrice">상품 가격</label>
                        <input disabled="disabled" type="number" id="input-itemPrice" name="itemPrice" class="form-control form-control-alternative" value="${ item.itemPrice }">
                      	<div>
						적립 포인트 : <span class="point_span"></span>원
						</div>
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
				</div>
				</div>
                  </form>
                  <div class="row">
                    <div class="col-lg-12">
                      <div class="button_quantity">
                      	대여 수량
                      <input type="text" class="quantity_input" value="1">
                      <span>
                      	<button class="plus_btn btn-secondary">+</button>
                      	<button class="minus_btn btn-secondary">-</button>
                      </span>	
                      </div>
                      
                      <div class="text-right">
                      	<button type="button" id="btn_zzim" class='btn btn-outline-primary float-right'>찜하기</button>
                      	<button type="button" id="btn_rental" class='btn btn-outline-primary float-right'>대여하기</button>                      	
                      </div>
                    </div>
                    
                  </div>
                </div>
                
            </div>
            </div>
          </div>
        </div>
        
        <!-- 주문 폼 -->
        <form action="/order/${loginuser.memberNo}" method="get" class="order_form">
				<input type="hidden" name="orders[0].itemNo" value="${item.itemNo}">
				<input type="hidden" name="orders[0].itemCount" value="">
		</form>

      
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
  
  <script>
  $(document).ready(function(){
	  
  
  	$('#zzim_btn').click(function(e){
  		e.preventDefault();
  		
  		const itemNo = $('#itemNo').val();
  		
  		
  		$.ajax({
  			"url" : "ajax-zzim",
  			"method" : "get",
  			"data" : { "itemNo" : itemNo },
  			"success" : function(result){
  				if (result == 1){
  					alert("찜!");
  				} else {
  					alert("회원만 사용가능한 기능입니다.");
  					location.href = "/rental-project/account/login";
  				}
  			},
  			"error" : function() {
  				alert("이미 찜한 상품입니다!!");
  			}
  		});
  	});
  	
  	
  	let itemPrice = "${item.itemPrice}"
  	let point = itemPrice*0.05;
  	point = Math.floor(point);
  	$(".point_span").text(point);
});  	
  	// 주문 수량 조절
  	let quantity = $(".quantity_input").val();
  	
	$(".plus_btn").on("click", function(){
		$(".quantity_input").val(++quantity);
	});
	$(".minus_btn").on("click", function(){
		if(quantity > 1) {
		$(".quantity_input").val(--quantity);
		}
	});
	
	// 서버로 보낼 데이터
	const form = {
			memberNo : '${ loginuser.memberNo}',
			itemNo : '${ item.itemNo }',
			itemCount : ''
	}
	
	
	// 장바구니 추가 버튼
	$("#btn_zzim").on("click", function(e){
	
	form.itemCount = $(".quantity_input").val();
	$.ajax({
		url: 'zzim-add', // 호출 url
		type: 'post',  // 메서드
		data: form,  // 서버로 보낼 데이터
		success: function(result){
			zzimAlert(result);
		}
	})
	
	});
	
	function zzimAlert(result){
		if(result == '0'){
			alert("찜하기에 실패하였습니다.");
		} else if(result == '1'){
			alert("찜 하였습니다!");
		} else if(result == '2'){
			alert("이미 찜한 상품입니다!");
		} else if(result == '4'){
			alert("로그인이 필요합니다.");	
		}
	}
	
  </script>
  
  
  
</body>

</html>