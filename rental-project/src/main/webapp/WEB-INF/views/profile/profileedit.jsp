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
<div>
  <div class="main-content">
    <!-- Navbar -->
	<jsp:include page="/WEB-INF/views/modules/navbar-top.jsp" />
   	<jsp:include page="/WEB-INF/views/modules/navbar-top2.jsp" />
   	<jsp:include page="/WEB-INF/views/modules/navbar-top3.jsp" />
   	<jsp:include page="/WEB-INF/views/modules/navbar-top4.jsp" />
    <!-- End Navbar -->
    <!-- Header -->
    <form action="profileedit" method="post" enctype="multipart/form-data">

    <div class="header pb-8 pt-5 pt-lg-8 d-flex align-items-center" style="min-height: 200px; background-image: url(../assets/img/theme/profile-cover.jpg); background-size: cover; background-position: center top;">
      <!-- Mask -->
      <span class="mask bg-purple opacity-4"></span>
      <!-- Header container -->
      <div class="container-fluid d-flex align-items-center">
        <div class="row">
          <div class="col-lg-12 col-md-10">
            <h1 class="display-1 text-white">${ profileuser.memberId }</h1>
            <input type="hidden" name="memberId" value="${profileuser.memberId }">
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
                <label for="imageInput" class="rounded-circle">
    <c:choose>
        <c:when test="${loginuser.memberImage == null}">
            <img id="preview" src="/rental-project/resources/img/theme/default.png" class="rounded-circle" name="defaultImage">
            <input type="file" id="imageInput" name="imageName" style="display: none;" accept="image/*" onchange="readURL(this);" />
        </c:when>
        <c:otherwise>
            <img id="preview" src="${pageContext.request.contextPath}/resources/upload/${loginuser.memberImage}" alt="Image" class="rounded-circle">
            <input type="file" id="imageInput" name="imageName" style="display: none;" accept="image/*" onchange="readURL(this);" />
        </c:otherwise>
    </c:choose>
</label>
</div>
</div>
</div>
<div class="card-header text-center border-0 pt-8 pt-md-4 pb-0 pb-md-4">
    <div class="d-flex justify-content-between">
        <a href="#" class="btn btn-sm btn-info mr-4" onclick="changeProfilePhoto();">사진 수정</a>
        <a href="#" class="btn btn-sm btn-default float-right" onclick="DefaultProfilePhoto();">기본 프로필</a>
    	<input type="hidden" name="useDefaultPhoto" id="useDefaultPhoto" value="1">
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
                  Jessica Jones<span class="font-weight-light">, 27</span>
                </h3>
                <div class="h5 font-weight-300">
                  <i class="ni location_pin mr-2"></i>Bucharest, Romania
                </div>
                <div class="h5 mt-4">
                  <i class="ni business_briefcase-24 mr-2"></i>Solution Manager - Creative Tim Officer
                </div>
                <div>
                  <i class="ni education_hat mr-2"></i>University of Computer Science
                </div>
                <hr class="my-4" />
                <p>Ryan — the name taken by Melbourne-raised, Brooklyn-based Nick Murphy — writes, performs and records all of his own music.</p>
                <a href="#">Show more</a>
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
                  <h3 class="mb-0">마이 페이지</h3>
                  <input type="submit" value="수정 완료" class="btn btn-info ml-auto" />
            <a href="profile?memberId=${ loginuser.memberId }" class="btn btn-info">취소</a>
                </div>
                </div>
<!--                 <div class="col-4 text-right">
                  <a href="#!" class="btn btn-sm btn-primary">패스워드 변경</a>
                </div> -->
              </div>
            </div>
            <div class="card-body">
                <h6 class="heading-small text-muted mb-4">내 정보</h6>
                
                <div class="pl-lg-4">
                  <div class="row">
                  <div class="col-lg-6">
                      <div class="form-group">
                        <label class="form-control-label" for="input-username">이름</label>
                        <input type="text" name="userName" class="form-control form-control-alternative" value="${ profileuser.userName }" />
                      </div>       
                    </div>
                    
                    <div class="col-lg-6">
                      <div class="form-group">
                        <label class="form-control-label" for="input-username">별명</label>
                        <input type="text" name="nickname" class="form-control form-control-alternative" value="${ profileuser.nickname }" />
                      </div>
                    </div>
                    <div class="col-lg-6">
                      <div class="form-group">
                        <label class="form-control-label" for="input-username">이메일</label>
						<input type="email" name="email" class="form-control form-control-alternative" value="${ profileuser.email }" />
                      </div>
                    </div>
                    <div class="col-lg-6">
                      <div class="form-group">
                        <label class="form-control-label" for="input-username">전화번호</label>
                        <input type="text" name="phoneNo" class="form-control form-control-alternative" value="${ profileuser.phoneNo }" />
                      </div>
                    </div>
                    <div class="col-lg-6">
                      <div class="form-group">
                        <label class="form-control-label" for="input-username">우편번호</label>
                        <input type="text" name="addressCode" class="form-control form-control-alternative" id="addressCode" value="${ profileuser.addressCode }" readonly="readonly"/>
                      </div>
                    </div>
                    <div class="col-lg-6">
                      <div class="form-group">
                        <label class="form-control-label" for="input-username">주소</label>
                        <input type="text" name="address" class="form-control form-control-alternative" id="address" value="${ profileuser.address }" readonly="readonly"/>
                      </div>
                    </div>
                    <div class="col-lg-6">
                      <div class="form-group">
                        <label class="form-control-label" for="input-username">상세 주소</label>
                        <input type="text" name="addressDetail" class="form-control form-control-alternative" value="${ profileuser.addressDetail }" />
                      </div>
                    </div>
                    <div class="col-lg-6">
                      <div class="form-group">
                        <label class="form-control-label" for="input-username">보증금</label>
                        <input type="text" name="deposite" class="form-control form-control-alternative" value="${ profileuser.deposite }" />
                      </div>
                    </div>
                    <div class="col-lg-6">
                      <div class="form-group">
                        <label class="form-control-label" for="input-username">등록일</label>
                        <br>
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
                    <textarea rows="4" name="introduce" class="form-control form-control-alternative">${ profileuser.introduce }</textarea>
                  </div>
                </div>
            </div>
          </div>
        </div>
      </div>
      </div>
      </form>
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
  <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
  <script>
  
  function readURL(input) {
	    if (input.files && input.files[0]) {
	        var reader = new FileReader();

	        reader.onload = function(e) {
	            // 이미지 미리보기 엘리먼트의 src 속성을 변경하여 선택한 이미지를 보여줌
	            document.getElementById("preview").src = e.target.result;
	        };

	        reader.readAsDataURL(input.files[0]);
	    }
	}
  

  function changeProfilePhoto() {
	    // 클릭 이벤트 핸들러를 연결하도록 설정한 파일 입력 필드를 클릭시킴
	    document.getElementById("imageInput").click();
	    
	    document.getElementById('useDefaultPhoto').value = "1"
	}

	// 파일 입력 필드의 값이 변경되었을 때 실행되는 함수
	function readURL(input) {
	    if (input.files && input.files[0]) {
	        var reader = new FileReader();

	        reader.onload = function(e) {
	            // 이미지 미리보기 엘리먼트의 src 속성을 변경하여 선택한 이미지를 보여줌
	            document.getElementById("preview").src = e.target.result;
	        };

	        reader.readAsDataURL(input.files[0]);
	    }
	}
	
	function DefaultProfilePhoto(){
		
		document.getElementById('useDefaultPhoto').value = "2"
		
		document.getElementById("preview").src = "/rental-project/resources/img/theme/default.png"
		
	}
	
	
	
  </script>
  
  <script>
   	// 주소 API
   	window.onload = function(){
   		document.getElementById("address").addEventListener("click", function (event){ // 주소 입력창 클릭하면
        new daum.Postcode({
            oncomplete: function(data) { //선택시 입력값 세팅
            	document.getElementById("addressCode").value = data.zonecode;
            	document.getElementById("address").value = data.address;
            	document.querySelector("input[name=addressDetail]").focus();
            }
        }).open();
    });
}
    </script>
  <script>
    window.TrackJS &&
      TrackJS.install({
        token: "ee6fab19c5a04ac1a32a645abde4613a",
        application: "argon-dashboard-free"
      });
  </script>
  <script>
  function resetImage() {
	    // 이미지 업로드 필드 초기화
	    document.getElementById('imageInput').value = '';

	    // 기본 이미지 표시
	    document.getElementById('preview').src = '/rental-project/resources/img/theme/default.png';
	}
  
  
  </script>
</body>

</html>