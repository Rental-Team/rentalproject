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
    세숫대여 회원가입
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

<body class="bg-default">
  <div class="main-content">
    <!-- Navbar -->
    <nav class="navbar navbar-top navbar-horizontal navbar-expand-md navbar-dark">
      <div class="container px-4">
        <a class="navbar-brand" href="/rental-project/home">
          <img src="/rental-project/resources/img/brand/225.png" />
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
          </ul>
        </div>
      </div>
    </nav>
    <!-- Header -->
    <div class="header bg-gradient-primary py-7 py-lg-8">
      <div class="container">
        <div class="header-body text-center mb-7">
          <div class="row justify-content-center">
            <div class="col-lg-5 col-md-6">
              <h1 class="text-white">환영합니다~!</h1>
              <br><br><br>
              <div class="text-lead text-light">대여하고 싶으시다구요?!</div>
              <p class="text-lead text-light">그럼 먼저 세숫대여 동료가 되세요!
            </div>
          </div>
        </div>
      </div>
      <div class="separator separator-bottom separator-skew zindex-100">
        <svg x="0" y="0" viewBox="0 0 2560 100" preserveAspectRatio="none" version="1.1" xmlns="http://www.w3.org/2000/svg">
          <polygon class="fill-default" points="2560 0 2560 100 0 100"></polygon>
        </svg>
      </div>
    </div>
    <!-- Page content -->
    <div class="container mt--8 pb-5">
      <!-- Table -->
      <div class="row justify-content-center">
        <div class="col-lg-6 col-md-8">
          <div class="card bg-secondary shadow border-0">
            
            <!-- action 시작 -->
            <div class="card-body px-lg-5 py-lg-5">
              <form:form id="registerform" action="register" method="post" modelAttribute="member">
              <form role="form">
              
              <!-- 아이디 -->
                <div class="form-group">
                  <div class="input-group input-group-alternative mb-3">
                    <div class="input-group-prepend">
                      <span class="input-group-text"><i class="ni ni-circle-08"></i></span>
                    </div>
                    <form:input id="memberId" path="memberId" class="form-control" placeholder="아이디" type="text" />
                    <button id="checkDup">
	                중복 검사
	                </button>
                  </div>
                </div>
                <input type="hidden" name="idDuplication" value="idUncheck"/>
                
                <!-- 비밀번호 -->
                <div class="form-group">
                  <div class="input-group input-group-alternative">
                    <div class="input-group-prepend">
                      <span class="input-group-text"><i class="ni ni-lock-circle-open"></i></span>
                    </div>
                    <form:input id="password"  path="password" class="form-control" placeholder="비밀번호" type="password" />
                    &nbsp;&nbsp;비밀번호는 특수문자와 영문자 포함 최소 6자, 최대 16자
                  </div>
                </div>
                <!-- <div id="passwordOption"></div> -->
                <div class="form-group">
                  <div class="input-group input-group-alternative">
                    <div class="input-group-prepend">
                      <span class="input-group-text"><i class="ni ni-lock-circle-open"></i></span>
                    </div>
                    <form:input id="passwordConfirm"  path="passwordConfirm" class="form-control" placeholder="비밀번호 확인" type="password" />
                  </div>
                </div>
                <p id="passwordCheck"></p>
                
                <!-- 이름, 별명, 전화번호 -->
                <div class="form-group">
                  <div class="input-group input-group-alternative mb-3">
                    <div class="input-group-prepend">
                      <span class="input-group-text"><i class="ni ni-hat-3"></i></span>
                    </div>
                    <form:input id="userName" path="userName" class="form-control" placeholder="이름" type="text" />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group input-group-alternative mb-3">
                    <div class="input-group-prepend">
                      <span class="input-group-text"><i class="ni ni-hat-3"></i></span>
                    </div>
                    <form:input id="nickname" path="nickname" class="form-control" placeholder="별명" type="text" />
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group input-group-alternative mb-3">
                    <div class="input-group-prepend">
                      <span class="input-group-text"><i class="ni ni-hat-3"></i></span>
                    </div>
                    <form:input id="phoneNo" path="phoneNo" class="form-control" placeholder="전화번호" type="text" />
                  </div>
                </div>
                
                <!-- 이메일 -->
                <div class="form-group">
                  <div class="input-group input-group-alternative mb-3">
                    <div class="input-group-prepend">
                      <span class="input-group-text"><i class="ni ni-email-83"></i></span>
                    </div>
                    <form:input id="email" path="email" class="form-control" placeholder="이메일" type="email" />
                    <select class="form-control" name="selectEmail" id="selectEmail" >
					<option>선택하세요</option>
					<option>@naver.com</option>
					<option>@daum.net</option>
					<option>@gmail.com</option>
					<option>@hanmail.com</option>
					<option>@yahoo.co.kr</option>
					</select>
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group input-group-alternative">

                    <button type="button" class="btn btn-primary" id="mail-Check-Btn">
	                본인 인증
	                </button>
                    <input class="form-control mail-check-input" placeholder="인증번호 6자리를 입력해주세요!" disabled="disabled" maxlength="6">
                  </div>
                </div>
                <span id="mail-check-warn"></span>
 				
 				<!-- 주소 -->
                <div class="form-group">
                  <div class="input-group input-group-alternative mb-3">
                    <div class="input-group-prepend">
                      <span class="input-group-text"></span>
                    </div>
                    <!-- <input type="text" id=addressCode class="form-control" placeholder="우편번호" /> -->
                    <form:input type="text" id="address" path="address" class="form-control" placeholder="주소" readonly="readonly" />
                    <input type="button" id="address-search" value="주소 검색"><br>
                  </div>
                  
                  <form:input type="text" name="addressDetail" path="addressDetail" class="form-control" placeholder="상세 주소" />
                </div>
                
                <div class="form-group">
                  <div class="input-group input-group-alternative mb-3">
                    <div class="input-group-prepend">
                      <span class="input-group-text"><i class="ni ni-hat-3"></i></span>
                    </div>
                    <form:input id="deposite" path="deposite" class="form-control" placeholder="보증금" type="text" />
                  </div>
                </div>
                <!-- <div class="text-muted font-italic"><small>password strength: <span class="text-success font-weight-700">strong</span></small></div>
                <div class="row my-4">
                  <div class="col-12">
                    <div class="custom-control custom-control-alternative custom-checkbox">
                      <input class="custom-control-input" id="customCheckRegister" type="checkbox">
                      <label class="custom-control-label" for="customCheckRegister">
                        <span class="text-muted">I agree with the <a href="#!">Privacy Policy</a></span>
                      </label>
                    </div>
                  </div>
                </div> -->
                <div class="text-center">
                <!-- <a href="/rental-project/account/login" class="btn btn-primary mt-4"> create account </a> -->
                  <input id="register" type="submit" class="btn btn-primary mt-4" value="계정 생성" />
				</div>
			  </form>
              </form:form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  <div></div>
  <!-- Footer -->
  <footer class="py-5">
    <div class="container">
      <div class="row align-items-center justify-content-xl-between">
        <div class="col-xl-6">
          <div class="copyright text-center text-xl-left text-muted">
            &copy; 2023 <a href="https://www.creative-tim.com" class="font-weight-bold ml-1" target="_blank">Rental Team</a>
          </div>
        </div>
        <div class="col-xl-6">
          <ul class="nav nav-footer justify-content-center justify-content-xl-end">
            <li class="nav-item">
              <a href="https://www.creative-tim.com" class="nav-link" target="_blank">Rental Team</a>
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
    </div>
  </footer>

  <!--   Core   -->
  <script src="/rental-project/resources/js/plugins/jquery/dist/jquery.min.js"></script>
  <script src="/rental-project/resources/js/plugins/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
  <!--   Optional JS   -->
  <!--   Argon JS   -->
  <script src="/rental-project/resources/js/argon-dashboard.min.js?v=1.1.2"></script>
  <script src="https://cdn.trackjs.com/agent/v3/latest/t.js"></script>
  <script src="http://code.jquery.com/jquery-3.7.1.js"></script>
  <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
  
  <script>
  // 아이디 중복 검사
  $(function(){
	  
	let dupChecked = false; // 중복검사 실행했는지의 여부: 중복체크가 아직 안됐다.
	$('#checkDup').on("click", function(event){
		event.preventDefault();
		
		const memberId = $('#memberId').val();
		if (!memberId){ // memberId가 null이거나 ""인 경우
			alert('아이디를 입력하세요');
			$('#memberId').focus();
			return;
		}
		
		$.ajax({
			"url": "check-id",
			"method": "get",
			"data": {"memberId" : memberId},
			"async": true,
			"success": function(data, status, xhr){
				if (data == "true"){
					dupChecked = true;
					alert("사용 가능한 아이디")
				} else {
					dupChecked = false;
					alert("이미 사용 중인 아이디")
				}
			},
			"error": function(xhr, status, err){
				alert("error");
			}
		});
	});
	// 아이디 중복 검사안하고 계정 생성할 시
	$('#register').on('click', function(event){
		event.preventDefault();
		
		if (!dupChecked) {
			alert("아이디 중복 검사를 실행하세요");
			return;
		}
		$('#registerform').submit();
	});
	// 아이디 중복 검사했어도 값이 새로 입력될 때 (입력창에 키up 되었을 시)
	$('#memberId').on('keyup', function(){
		dupChecked = false;
		});	
	});
  </script>
  
  <script>
  // 패스워드 일치 여부
  var passwordField = document.getElementById('password');
  var passwordConfirmField = document.getElementById('passwordConfirm');

  function checkPassword(){
	  var password = passwordField.value;
	  var passwordConfirm = passwordConfirmField.value;
	  var passwordCheck = document.getElementById('passwordCheck')
	  
/* 	  var passwordOption = document.getElementById('passwordOption')
	  var SpecialChar = ["!","@","#","$","%"];
	  var checkSpecialChar = 0;
	  
	  if(password.length < 6 || password.length>16) {
		  passwordOption.innerHTML = '비밀번호는 6글자 이상, 16글자 이하만 이용 가능합니다.';
		  passwordOption.style.color = 'red';		    
	  }
	  
	  for(var i=0; i<SpecialChar.length; i++){
		  if(password.indexOf(SpecialChar[i]) != -1){
			  checkSpecialChar = 1;			    
		  }		    
	  }
	  if(checkSpecialChar == 0){
		  passwordOption.innerHTML = '!,@,#,$,% 의 특수문자가 들어가 있지 않습니다.'  
	  }  */
	
	  if(password != '' && passwordConfirm != ''){
		  if(password == passwordConfirm){
			  passwordCheck.innerHTML = '비밀번호가 일치합니다.'
			  passwordCheck.style.color = 'green';
			  return true;			  
		  } else {
			  passwordCheck.innerHTML = '비밀번호가 일치하지 않습니다.';
			  passwordCheck.style.color = 'red';
			  return false;  
		  }  
	  }  
  }
  // input하면서 checkPassword function 실행
  passwordField.addEventListener("input", checkPassword);
  passwordConfirmField.addEventListener("input", checkPassword);
  </script>
  
  <script>
    // 이메일 인증 번호
    var code = null;
    $('#mail-Check-Btn').click(function() {
   		const eamil = $('#email').val() + $('#selectEmail').val(); // 이메일 주소값 얻어오기!
   		const checkInput = $('.mail-check-input') // 인증번호 입력하는곳 
    		
   		$.ajax({
   			"type": 'get',
   			"url": "mailCheck?email=" + eamil, // GET방식이라 Url 뒤에 email을 붙힐수있다.
   			"success": function (data) {
   				checkInput.attr('disabled',false);
   				code = data;
   				// alert('인증번호가 전송되었습니다.');
   				alert(code);
   			}			
   		}); // end ajax
   	}); // end send eamil
    	
   	// 인증번호 비교 
   	// blur -> focus가 벗어나는 경우 발생
   	$('.mail-check-input').blur(function () {
   		const inputCode = $(this).val();
   		const $resultMsg = $('#mail-check-warn');
    		
   		if(inputCode === code){
   			$resultMsg.html('인증번호가 일치합니다.');
   			$resultMsg.css('color','green');
   			$('#mail-Check-Btn').attr('disabled',true);
   			$('#userEamil1').attr('readonly',true);
   			$('#userEamil2').attr('readonly',true);
   			$('#selectEmail').attr('onFocus', 'this.initialSelect = this.selectedIndex');
   	        $('#selectEmail').attr('onChange', 'this.selectedIndex = this.initialSelect');
   		} else {
   			$resultMsg.html('인증번호가 불일치 합니다. 다시 확인해주세요!.');
   			$resultMsg.css('color','red');
   		}
   	});
   	</script>
   	
   	<script>
   	// 주소 API
   	window.onload = function(){
    $('#address-search').on("click", function(event){
        new daum.Postcode({
            oncomplete: function(data) { //선택시 입력값 세팅
            	/* document.getElementByld("addressCode").value = data.zonecode; */
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
</body>

</html>