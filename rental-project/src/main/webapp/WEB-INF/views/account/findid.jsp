<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>
    세숫대여 아이디 찾기
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

<body>
  <div class="main-content">
    <!-- Navbar -->
    <nav class="navbar navbar-top navbar-horizontal navbar-expand-md navbar-dark">
      <div class="container px-4">
        <a class="navbar-brand" href="/rental-project/home">
          <img src="/rental-project/resources/img/brand/로고1.png" style="height: 100px; width: 100px" />
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar-collapse-main" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbar-collapse-main">
          <!-- Collapse header -->
          <div class="navbar-collapse-header d-md-none">
            <div class="row">
              <div class="col-6 collapse-brand">
                <a href="../index.html">
                  <img src="/rental-project/resources/img/brand/blue.png">
                </a>
              </div>
              <div class="col-6 collapse-close">
                <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbar-collapse-main" aria-controls="sidenav-main" aria-expanded="false" aria-label="Toggle sidenav">
                  <span></span>
                  <span></span>
                </button>
              </div>
            </div>
          </div>
          <!-- Navbar items -->
          <ul class="navbar-nav ml-auto">
            <li class="nav-item">
              <a class="nav-link nav-link-icon" href="/rental-project/account/login">
                <i class="ni ni-key-25"></i>
                <span class="nav-link-inner--text">로그인</span>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link nav-link-icon" href="/rental-project/account/register">
                <i class="ni ni-circle-08"></i>
                <span class="nav-link-inner--text">계정 생성</span>
              </a>
            </li>
          </ul>
        </div>
      </div>
    </nav>

	<!-- Header -->
    <div class="header bg-gradient-green py-7 py-lg-10">
     <div class="container">
       <div class="header-body text-center mb-7">
         <div class="row justify-content-center">
           <div class="col-lg-5 col-md-6">
             <h1 class="text-white">아이디 찾기</h1>
             <p class="text-lead text-light">현재 아이디 찾기 페이지 입니다.</p>
           </div>
         </div>
       </div>
     </div>
     <!-- <div class="separator separator-bottom separator-skew zindex-100">
       <svg x="0" y="0" viewBox="0 0 2560 100" preserveAspectRatio="none" version="1.1" xmlns="http://www.w3.org/2000/svg">
         <polygon class="fill-default" points="2560 0 2560 100 0 100"></polygon>
        </svg>
      </div> -->
    </div>
    <!-- Page content -->
    <div class="container mt--8 pb-5">
      <div class="row justify-content-center">
        <div class="col-lg-5 col-md-7">
          <div class="card bg-secondary shadow border-0">
            <div class="card-body px-lg-5 py-lg-5">
            
            <!-- action 시작 -->
              <form action="findid" method="post" id="findIdForm">
                <div class="form-group">
                  <div class="input-group input-group-alternative">
                    <div class="input-group-prepend">
                      <span class="input-group-text"><i class="ni ni-lock-circle-open"></i></span>
                    </div>
                    <input name="userName" class="form-control" placeholder="이름" type="text">
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group input-group-alternative">
                    <div class="input-group-prepend">
                      <span class="input-group-text"><i class="ni ni-lock-circle-open"></i></span>
                    </div>
                    <input name="phoneNo" class="form-control" placeholder="전화번호" type="text" maxlength="11">
                  </div>
                </div>
                <p class="text-center">
                  <input type="submit" id="find-id" class="btn btn-primary my-4" value="아이디 찾기" />
                </p>     
             </form>
                <div id="findIdResult"></div>
                
                <span>비밀번호가 기억이 안나시나요??</span>
                <span style="mergin: 100px"></span>
                <a href="/rental-project/account/findpw">비밀번호 찾기</a>
            </div>
          </div>
        </div>
      </div>
    </div>
    <footer class="py-5">
      <div class="container">
        <div class="row align-items-center justify-content-xl-between">
          <div class="col-xl-6">
            <div class="copyright text-center text-xl-left text-muted">
              &copy; 2023 <a href="https://addinedu.com/" class="font-weight-bold ml-1" target="_blank">애드인 에듀</a>
            </div>
          </div>
          <div class="col-xl-6">
            <ul class="nav nav-footer justify-content-center justify-content-xl-end">
              <li class="nav-item">
                <a href="https://github.com/Rental-Team/rentalproject" class="nav-link" target="_blank">프로젝트 1팀</a>
              </li>
              <li class="nav-item">
                <a href="https://github.com/Rental-Team/rentalproject/blob/master/README.md" class="nav-link" target="_blank">ReadMe</a>
              </li>
              <li class="nav-item">
                <a href="https://github.com/creativetimofficial/argon-dashboard/blob/master/LICENSE.md" class="nav-link" target="_blank">MIT License</a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </footer>
  </div>
  <!--   Core   -->
  <script src="/rental-project/resources/js/plugins/jquery/dist/jquery.min.js"></script>
  <script src="/rental-project/resources/js/plugins/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
  <!--   Optional JS   -->
  <!--   Argon JS   -->
  <script src="/rental-project/resources/js/argon-dashboard.min.js?v=1.1.2"></script>
  <script src="https://cdn.trackjs.com/agent/v3/latest/t.js"></script>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script>
  $(document).ready(function() {
  	$("#findIdForm").submit(function (event) {
        event.preventDefault();

        var userName = $("input[name='userName']").val();
        var phoneNo = $("input[name='phoneNo']").val();

        $.ajax({
            type: "POST",
            url: "find-id",
            data: {
                userName: userName,
                phoneNo: phoneNo,
            },
            success: function (response) {
                if (response.check === 0) {
                    $("#findIdResult").html("찾으시는 아이디는 '" + response.memberId + "' 입니다.");
                    $("#findIdResult").append('<div class="form-label-group"><a href="/rental-project/account/login" class="btn btn-lg btn-secondary btn-block text-uppercase">로그인으로 돌아가기</a></div>');
                } else {
                    $("#findIdResult").html("아이디를 찾을 수 없습니다.");
                }
            },
            error: function () {
                console.error("요청 처리 중 오류가 발생했습니다.");
            },
        });
    });
});
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