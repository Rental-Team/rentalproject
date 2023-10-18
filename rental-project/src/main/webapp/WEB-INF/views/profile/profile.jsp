<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!-- tag library -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
    <div class="header pb-8 pt-5 pt-lg-8 d-flex align-items-center" style="min-height: 200px; background-image: url(../assets/img/theme/profile-cover.jpg); background-size: cover; background-position: center top;">
      <!-- Mask -->
      <span class="mask bg-purple opacity-4"></span>
      <!-- Header container -->
      <div class="container-fluid d-flex align-items-center">
        <div class="row">
          <div class="col-lg-12 col-md-10">
            <h1 class="display-1 text-white">${ loginuser.memberId }</h1>
            <!-- <p class="text-white mt-0 mb-5">This is your profile page. You can see the progress you've made with your work and manage your projects or assigned tasks</p> -->
            
          </div>
        </div>
      </div>
    </div>
    <!-- Page content -->
    <div class="container-fluid mt--7">
      <div class="row">
        <div class="col-xl-4 order-xl-2 mb-5 mb-xl-0">
          <div class="card card-profile shadow">
            <div class="row justify-content-center">
              <div class="col-lg-3 order-lg-2">
                <div class="card-profile-image">
                <c:choose>
                	<c:when test="${loginuser.memberImage == null}">
                    <img src="/rental-project/resources/img/theme/default.png" class="rounded-circle">
                    </c:when>
                    <c:otherwise>
                    <img src="${pageContext.request.contextPath}/resources/upload/${loginuser.memberImage}" alt="Image" class="rounded-circle">
                    </c:otherwise>
				</c:choose>
                </div>
              </div>
            </div>
            <div class="card-header text-center border-0 pt-8 pt-md-4 pb-0 pb-md-4">
              <div class="d-flex justify-content-between">
              </div>
            </div>
            <div class="card-body pt-0 pt-md-4">
              <div class="row">
                <div class="col">
                  <div class="card-profile-stats d-flex justify-content-center mt-md-5">
                    <div>
                      <span class="heading">22</span>
                      <span class="description">Friends</span>
                    </div>
                    <div>
                      <span class="heading">10</span>
                      <span class="description">Photos</span>
                    </div>
                    <div>
                      <span class="heading">89</span>
                      <span class="description">Comments</span>
                    </div>
                  </div>
                </div>
              </div>
              <div class="text-center">
                <h3>
                  최 재 호<span class="font-weight-light">, 44</span>
                </h3>
                <div class="h5 font-weight-300">
                  <i class="ni location_pin mr-2"></i>서울, 양천구
                </div>
                <div class="h5 mt-4 dlsplay:none">
                  <i class="ni business_briefcase-24 mr-2"></i>Solution Manager - Creative Tim Officer
                </div>
                <div>
                  <i class="ni education_hat mr-2"></i>University of Computer Science
                </div>
                <hr class="my-4" />
                <p>이 사람이 바로 세숫대여의 페이지 수장 최재호입니다 모두 박수 주세요</p>
                <a href="#">Show more</a>
                <br><br>
               
                <a href="/rental-project/privateboard/privateqnalist" class="btn btn-info">문의 내역</a>
                <a href="/rental-project/zzim/${loginuser.memberNo}" class="btn btn-info">찜하기</a>
                <a href="/rental-project/freeboard/myfreeboardlist" class="btn btn-info">내가 쓴 글</a>
              </div>
            </div>
          </div>
        </div>
        <div class="col-xl-8 order-xl-1">
          <div class="card bg-secondary shadow">
            <div class="card-header bg-white border-0">
              <div class="row align-items-center">
                <div class="col-12">
                <div class="d-flex justify-content-between align-items-center">
                  <h3 class="mb-0">마이페이지</h3>
                  <a href="profileedit?memberId=${ loginuser.memberId }" class="btn btn-info ml-auto">프로필 수정</a>
                   <a href="#" id="delete-profile" class="btn btn-info">회원 탈퇴</a>
                   </div>
                </div>
					<!--<div class="col-4 text-right">
                  <a href="#!" class="btn btn-sm btn-primary">패스워드 변경</a>
                </div> -->
              </div>
            </div>
            <div class="card-body">
              <form>
                <h6 class="heading-small text-muted mb-4">내 정보</h6>
                <div class="pl-lg-4">
                  <div class="row">
                  <div class="col-lg-6">
                      <div class="form-group">
                        <label class="form-control-label" for="input-username">이름</label>
                        <div>${ loginuser.userName }</div>
                      </div>
                    </div>
                    <div class="col-lg-6">
                      <div class="form-group">
                        <label class="form-control-label" for="input-username">별명</label>
                        <div>${ loginuser.nickname }</div>
                      </div>
                    </div>
                    <div class="col-lg-6">
                      <div class="form-group">
                        <label class="form-control-label" for="input-username">이메일</label>
                        <div>${ loginuser.email }</div>
                      </div>
                    </div>
                    <div class="col-lg-6">
                      <div class="form-group">
                        <label class="form-control-label" for="input-username">전화번호</label>
                        <div>${ loginuser.phoneNo }</div>
                      </div>
                    </div>
                    <div class="col-lg-6">
                      <div class="form-group">
                        <label class="form-control-label" for="input-username">우편번호</label>
                        <div>${ loginuser.addressCode }</div>
                      </div>
                    </div>
                    <div class="col-lg-6">
                      <div class="form-group">
                        <label class="form-control-label" for="input-username">주소</label>
                        <div>${ loginuser.address }</div>
                      </div>
                    </div>
                    <div class="col-lg-6">
                      <div class="form-group">
                        <label class="form-control-label" for="input-username">상세 주소</label>
                        <div>${ loginuser.addressDetail }</div>
                      </div>
                    </div>
                    <div class="col-lg-6">
                      <div class="form-group">
                        <label class="form-control-label" for="input-username">보증금</label>
                        <div>${ loginuser.deposite }</div>
                      </div>
                    </div>
                    <div class="col-lg-6">
                      <div class="form-group">
                        <label class="form-control-label" for="input-username">등록일</label>
                        <div><fmt:formatDate value="${ loginuser.regDate }" pattern="yyyy-MM-dd" /></div>
                      </div>
                    </div>
                  </div>
                </div>
                <hr class="my-4" />
                <!-- Description -->
                <h6 class="heading-small text-muted mb-4">About me</h6>
                <div class="pl-lg-4">
                  <div class="form-group">
<tr>
<c:set var="enter" value="
" />
<%-- enter(줄 바꿈) 설정 방법: 모양 안예쁘다고 수정하지 말 것--%>
		                
		                <td>${ fn:replace(profileuser.introduce, enter, "<br>") }</td>
		                <%-- fn:replace(a, x, y)-> a에 x기능을 y기능으로 바꿔라 --%>
		                
		            </tr>                    

                  </div>
                </div>
              </form>
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
  <!--   Argon JS   -->
  <script src="/rental-project/resources/js/argon-dashboard.min.js?v=1.1.2"></script>
  <script src="https://cdn.trackjs.com/agent/v3/latest/t.js"></script>
  
   <script>
      $(function(event){
    	  $('#delete-profile').on('click', function(event) {
    		  event.preventDefault();
    		  const yn = confirm("${loginuser.memberId}님 회원 탈퇴하시겠습니까??");
    		  if (yn) {
    			  location.href = 'delete/${loginuser.memberId}';
    		  }
    	  }); 
      })
      </script>
  <script>
    window.TrackJS &&
      TrackJS.install({
        token: "ee6fab19c5a04ac1a32a645abde4613a",
        application: "argon-dashboard-free"
      });
  </script>
</body>

</html>