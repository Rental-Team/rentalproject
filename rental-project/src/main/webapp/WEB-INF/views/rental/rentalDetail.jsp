<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
  	
  <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <link href="/rental-project/resources/css/navbar-top.css" rel="stylesheet" />
  
  
  
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
    <jsp:include page="/WEB-INF/views/modules/navbar-content.jsp" />
    <div class="container-fluid mt--7">
			<div class="row">
				<div class="col-xl-12 mb-5 mb-xl-0">
					<div class="card bg-secondary shadow">
						<div class="card-header border-0">
							<div class="row align-items-center">
								<div class="col-8">
									<h3 style="font-weight: bold; margin-bottom: 20px;" class="mb-0">주문 상세 정보</h3>
								</div>
							</div>
						</div> 
						<div class="card-body" style="margin-top: -30px;"> 
							<form action="rental" method="post">
							<input type="hidden" name="memberNo" value="${loginuser.memberNo}"/>
							<div class="row mt-5">
					        <div class="col">
					          <div class="card bg-secondary shadow">
					            <div class="card-header bg-transparent border-0">
					              <h5 class="text-black mb-0">결제된 상품 목록</h5>
					            </div>
					            <div class="table-responsive">
					              <table class="table align-items-center table-white table-flush">
					                <thead class="thead-white">
					                  <tr style="text-align:center;">
					                  	<th class="td_width_2">주문번호</th>
										<th class="td_width_2">이미지</th>
					                    <th class="td_width_3">상품명</th>
					                    <th class="td_width_4">가격</th>
					                    <th class="td_width_4">수량</th>
					                    <th class="td_width_4">주문상태</th>
					                  </tr>
					                </thead>
					                <tbody>
					                  <c:forEach items="${detailLists}" var="rop">
												<tr style="text-align:center;">			
													<td class="td_width_2">${rop.orderId}</td>	 			
													<td class="td_width_2">
														<img src="${pageContext.request.contextPath}/resources/upload/thumbnail_${rop.thumbnail}" alt="Image">							
													</td>
													<td class="td_width_3">${rop.itemName}</td>
													<td class="td_width_4 price_td">
														<span class="red_color"><fmt:formatNumber value="${rop.itemPrice}" pattern="#,### 원" /></span><br>
													</td>
													<td class="td_width_4 table_text_align_center"> ${rop.itemCount} </td>
													<td class="td_width_4 table_text_align_center"> ${rop.orderState} </td>
													<%-- <td class="td_width_4 table_text_align_center">
														<fmt:formatNumber value="${rop.itemPrice * rop.itemCount}" pattern="#,### 원" />
													</td> --%>
												</tr>
											</c:forEach> 
					                </tbody>  
					              </table> 
						              <div class="text-left mt-2 mb-2" style="padding-left: 20px;">
									    <h5 class="text-black mb-0" style="display: flex; justify-content: space-between;">
									        <span>총 결제 금액</span>
									        <span class="red_color"  style="margin-right: 80px;">
									            <fmt:formatNumber value="${totalOrderPrice}" pattern="#,### 원" />
									        </span>
									    </h5>
									</div>    	
					              </div> 
					            </div>
					          </div>
					        </div> 
								<div class="card-body">
					                <div class="pl-lg-12" style="margin : 0 auto;">
					                  <div class="row">
					                    <div class="col-lg-6" > 
					                      <div class="form-group focused">
					                        <label class="form-control-label"for="input-addressUser">수령인</label>
					                       		<input type="text" id="input-addressUser"  name="addressUser" class="form-control form-control-alternative" value="${ address.addressUser }" readonly> 					              
					                      </div>
					                    </div> 
					                  </div>  
					                  <div class="row">
				                        <div class="col-lg-6">
					                      <div class="form-group">
					                        <label class="form-control-label"  for="input-orderDate">아이디</label> 
					                        <input type="text" id="input-memberId" class="form-control form-control-alternative"  value="${loginuser.memberId}" readonly>   
					                   	  </div>
				                    	</div>
			                    		</div>
					                  <div class="row">
					                    <div class="col-lg-6" >
					                      <div class="form-group focused">
					                        <label class="form-control-label"for="input-email">이메일</label>
					                        <input type="text" id="input-email" name="email" class="form-control form-control-alternative" value="${ loginuser.email }" readonly>
					                      </div>
					                    </div> 
					                  </div> 
					                  <div class="row">
					                    <div class="col-lg-10" >
					                      <div class="form-group focused">
					                        <label class="form-control-label"for="input-email">주소</label>
					                        <input type="text" name="address" id="input-address" class="form-control form-control-alternative" value="${ address.address }" readonly>
					                      </div>
					                    </div> 
					                  </div> 
						                <br>
						                <br>
						                <div class="row">
										   <button type="button" class="btn btn-success" id="btnitem">다른상품보러가기</button> 
										   <button type="button" class="btn btn-success" id="btnhome">메인으로이동</button> 
										 </div> 
					                </div>
					              </div> 
							</form>
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
  <script> 
	  $(function(event) {
		  $('#btnhome').on('click', function(event){ 
				  location.href="/rental-project/home"; 
		  	}) 
	  
	  $('#btnitem').on('click', function(event){ 
		  location.href="/rental-project/item/list"; 
	  })
  });
  </script>
  
  

</body>

</html>