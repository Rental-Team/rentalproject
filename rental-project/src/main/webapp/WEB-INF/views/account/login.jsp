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
  <!-- content에 자신의 OAuth2.0 클라이언트ID를 넣습니다. -->
<meta name ="google-signin-client_id" content="174300919032-nqj47kqqpmn2gjm8mt43u8kp2vb8l972.apps.googleusercontent.com">
  <title>
    세숫대여 로그인
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
  
  </style>
</head>

<body>
  <div class="main-content">
    <!-- Navbar -->
    <nav class="navbar navbar-top navbar-horizontal navbar-expand-md navbar-dark">
      <div class="container px-10">
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
        </div>
      </div>
    </nav>

	<!-- Header -->
    <div class="header bg-gradient-green py-7 py-lg-10">
     <div class="container">
       <div class="header-body text-center mb-7">
         <div class="row justify-content-center">
           <div class="col-lg-5 col-md-6">
             <h1 class="text-white">환영합니다!</h1>
             <p class="text-lead text-light">세숫대여의 방문해 주셔서 감사합니다.</p>
           </div>
         </div>
       </div>
     </div>
     <div class="separator separator-bottom separator-skew zindex-100">
       <!-- <svg x="0" y="0" viewBox="0 0 2560 100" preserveAspectRatio="none" version="1.1" xmlns="http://www.w3.org/2000/svg">
         <polygon class="fill-default" points="2560 0 2560 100 0 100"></polygon>
        </svg>
       --></div>
    </div>
    <!-- Page content -->
    <div class="container mt--8 pb-5">
      <div class="row justify-content-center">
        <div class="col-lg-5 col-md-7">
          <div class="card bg-secondary shadow border-0">
            <div class="card-header bg-transparent pb-5">
              <div class="text-muted text-center mt-2 mb-3"><small>Sign in with</small></div>
              <div class="btn-wrapper text-center">
                <a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=51aa4581e2f4765686dd6faec72c568d&redirect_uri=http://localhost:8080/rental-project/account/login" class="btn btn-neutral btn-icon" id="kakao-login-btn" >
                  <span class="btn-inner--icon"><img src="/rental-project/resources/img/icons/common/kakao.png"></span>
                  <span class="btn-inner--text">kakao</span>
                </a>
                <p id="token-result"></p>

              </div>
            </div>
            <div class="card-body px-lg-5 py-lg-5">            
              <form action="login" method="post" id="loginForm">
              <input type="hidden" name="returnUrl" value="${ returnUrl }">  <!--  10.16 -->
                <div class="form-group mb-3">
                  <div class="input-group input-group-alternative">
                    <div class="input-group-prepend">
                      <span class="input-group-text"><i class="ni ni-circle-08"></i></span>
                    </div>
                    <input name="memberId" class="form-control" id="memberId" placeholder="아이디" type="text">
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group input-group-alternative">
                    <div class="input-group-prepend">
                      <span class="input-group-text"><i class="ni ni-lock-circle-open"></i></span>
                    </div>
                    <input name="password" class="form-control" id="password" placeholder="비밀번호" type="password">
                  </div>
                </div>
                <!-- <div class="custom-control custom-control-alternative custom-checkbox">
                  <input class="custom-control-input" id=" customCheckLogin" type="checkbox">
                  <label class="custom-control-label" for=" customCheckLogin">
                    <span class="text-muted">Remember me</span>
                  </label>
                </div> -->
                <p class="text-center">
                  <input id="login" type="submit" class="btn btn-primary my-4" value="로그인" />
                </p>
                <a href="/rental-project/account/findid">아이디 찾기</a><span> / </span>
                <a href="/rental-project/account/findpw">비밀번호 찾기</a>
                <br><br>
                <div>계정이 없으시다구요?
                <a href="/rental-project/account/register">계정 생성</a>
                </div>
                
              </form>
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
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script src="/rental-project/resources/js/plugins/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
  <!--   Optional JS   -->
  <!--   Argon JS   -->
  <script src="https://t1.kakaocdn.net/kakao_js_sdk/2.4.0/kakao.min.js" 
  integrity="sha384-mXVrIX2T/Kszp6Z0aEWaA8Nm7J6/ZeWXbL8UpGRjKwWe56Srd/iyNmWMBhcItAjH" crossorigin="anonymous"></script>
  <script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
  <script src="/rental-project/resources/js/argon-dashboard.min.js?v=1.1.2"></script>
  <script src="https://cdn.trackjs.com/agent/v3/latest/t.js"></script>
  <script src="http://code.jquery.com/jquery-3.7.1.js"></script>
  <script>
$("#loginForm").submit(function (event) {
	event.preventDefault();
	var memberId = document.getElementById("memberId").value;
	var password = document.getElementById("password").value;

		var data = {
			memberId: memberId,
			password: password,
			returnUrl: '${returnUrl}'
		};
		
		$.ajax({
			type: "POST",
			url: "login",
			data: data,
			success: function (response) {
			console.log(response); // 응답을 콘솔에 출력
			
			// 로그인 성공
			if (response.check === 0) {
				const yn = confirm('로그인 실패: 이미 탈퇴된 계정입니다. 새로 계정 생성하시겠습니까?')
				if (yn){
					location.href = '/rental-project/account/register';
				}
			} else if (response.check === 1) {
				location.href = '/rental-project' + response.redirectUrl;
			} else if (response.check === 2) {
				alert('로그인 실패: 아이디 또는 패스워드가 일치하는 정보가 없습니다')
			} 
			},
			error: function () {
				// 그 외 오류(예외)
				alert("아이디와 패스워드를 입력해주세요");
				}
			});
		return false;
		});

  </script>
  <script>
  window.Kakao.init('1e893c5a78182f018f6b362c8fbbb59d');

  function loginWithKakao() {
    window.Kakao.Auth.authorize({
      redirectUri: 'http://localhost:8080/rental-project/account/login',
    });
  }

  // 아래는 데모를 위한 UI 코드입니다.
  displayToken()
  function displayToken() {
    var token = getCookie('authorize-access-token');

    if(token) {
      Kakao.Auth.setAccessToken(token);
      Kakao.Auth.getStatusInfo()
        .then(function(res) {
          if (res.status === 'connected') {
            document.getElementById('token-result').innerText
              = 'login success, token: ' + Kakao.Auth.getAccessToken();
          }
        })
        .catch(function(err) {
          Kakao.Auth.setAccessToken(null);
        });
    }
  }

  function getCookie(name) {
    var parts = document.cookie.split(name + '=');
    if (parts.length === 2) { return parts[1].split(';')[0]; }
  }
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