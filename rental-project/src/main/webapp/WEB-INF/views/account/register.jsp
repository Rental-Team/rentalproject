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

<body>
<!-- action 시작 -->
<form:form id="registerform" action="register" method="post" modelAttribute="member" enctype="multipart/form-data">
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
              <h1 class="text-white">환영합니다~!</h1>
              <br><br><br>
              <div class="text-lead text-light" style="color: black">대여하고 싶으시다구요?!</div>
              <p class="text-lead text-light" style="color: black">그럼 먼저 세숫대여 동료가 되세요! </p>
              <br><br><br>
              <div class="row justify-content-center">
			    <div class="col-lg-3 order-lg-2">
		        <div class="card-profile-image">
	            <label for="imageInput" class="rounded-circle">
	                <img id="preview" src="/rental-project/resources/img/theme/default.png">
	            </label>
	            <input type="file" id="imageInput" name="imageName" style="display: none;" accept="image/*" onchange="readURL(this);" />
		        
		        </div>
			    </div>
			</div>
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
      <!-- Table -->
      <div class="row justify-content-center">
        <div class="col-lg-6 col-md-8">
          <div class="card bg-secondary shadow border-0">
            
            
            <div class="card-body px-lg-5 py-lg-5">
              
              <form role="form">
              
              <!-- 아이디 -->
                <div class="form-group">
                  <div class="input-group input-group-alternative">
                    <div class="input-group-prepend">
                      <span class="input-group-text"><i class="ni ni-circle-08"></i></span>
                    </div>
                    
                    <form:input id="memberId" path="memberId" class="form-control" placeholder="아이디" type="text" maxlength="12"/>
                    <button id="checkDup" class="btn btn-success">중복 검사</button>
                  </div><p id="idcheck_blank"></p> 
                  &nbsp;&nbsp;아이디는 영문과 숫자 조합해 6~12자
                </div>
                <input type="hidden" name="idDuplication" value="idUncheck"/>
                
                
                <!-- 비밀번호 -->
                <div class="form-group">
                  <div class="input-group input-group-alternative">
                    <div class="input-group-prepend">
                      <span class="input-group-text"><i class="ni ni-lock-circle-open"></i></span>
                    </div>
                    <form:input id="password"  path="password" class="form-control" placeholder="비밀번호" type="password" maxlength="16"/>
                    &nbsp;&nbsp;비밀번호는 특수문자와 영문자 포함 최소 6자, 최대 16자
                  </div>
                </div>
                <!-- <div id="passwordOption"></div> -->
                <div class="form-group">
                  <div class="input-group input-group-alternative">
                    <div class="input-group-prepend">
                      <span class="input-group-text"><i class="ni ni-lock-circle-open"></i></span>
                    </div>
                    <form:input id="passwordConfirm"  path="passwordConfirm" class="form-control" placeholder="비밀번호 확인" type="password" maxlength="16"/>
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
                <p id="userNameCheck"></p>
                <div class="form-group">
                  <div class="input-group input-group-alternative mb-3">
                    <div class="input-group-prepend">
                      <span class="input-group-text"><i class="ni ni-hat-3"></i></span>
                    </div>
                    <form:input id="nickname" path="nickname" class="form-control" placeholder="별명" type="text" />
                  </div>
                </div>
                <p id="nicknameCheck"></p>
                <div class="form-group">
                  <div class="input-group input-group-alternative mb-3">
                    <div class="input-group-prepend">
                      <span class="input-group-text"><i class="ni ni-hat-3"></i></span>
                    </div>
                    <form:input id="phoneNo" path="phoneNo" class="form-control" placeholder="전화번호" type="text" maxlength="11"/>
                  </div>
                </div>
                <p id="phoneNoCheck"></p>
                <!-- 이메일 -->
                <div class="form-group">
                  <div class="input-group input-group-alternative mb-3">
                    <div class="input-group-prepend">
                    </div>
                    <div style="display: flex; align-items: center;">
                    <span class="input-group-text"><i class="ni ni-email-83"></i></span>
					  <input id="email" name="email1" class="form-control" placeholder="이메일" type="email" />
					  <span>@</span>
					  <input name="email2" type="text" class="form-control" />
					  <select class="form-control" name="selectEmail" id="selectEmail" >
					<option value="" selected>선택하세요</option>
					<option value="naver.com">@naver.com</option>
					<option value="daum.net">@daum.net</option>
					<option value="gmail.com">@gmail.com</option>
					<option value="hanmail.com">@hanmail.com</option>
					<option value="yahoo.co.kr">@yahoo.co.kr</option>
					<option value="direct">직접 입력</option> 
					</select>
					</div>
                  </div>
                </div>
                
                <div class="form-group">
                  <div class="input-group input-group-alternative">
                    <button type="button" class="btn btn-primary" id="mail-Check-Btn">
	                본인 인증
	                </button>
                    <input class="form-control" id="mail-check-input" placeholder="인증번호 6자리를 입력해주세요!" disabled="disabled" maxlength="6">
                  </div>
                </div>
                <p id="mail-check-warn"></p>
 				
 				<!-- 주소 -->
                <div class="form-group">
                  <div class="input-group input-group-alternative mb-3">
                    <div class="input-group-prepend">
                      <span class="input-group-text"></span>
                  </div>
                    <form:input type="text" id="addressCode" path="addressCode" class="form-control" placeholder="우편번호" readonly="readonly" name="addressCode" />
                    <input type="button" id="address-search" class="btn btn-success" value="주소 검색"><br>
                  </div>
                  <form:input type="text" id="address" path="address" class="form-control" placeholder="주소" readonly="readonly" />
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
                  <input id="register" type="submit" class="btn btn-success mt-4" value="계정 생성" />
				</div>
			  </form>
              
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </form:form>
  <!-- Footer -->
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
  function readURL(input) { // 프사
      if (input.files && input.files[0]) {
          var reader = new FileReader();

          reader.onload = function (e) {
              $("#preview").attr("src", e.target.result);
          };

          reader.readAsDataURL(input.files[0]);
      }
  }
  </script>
  <script>

  $(function(){
       
      let idDupChecked = false; // 중복검사 실행했는지의 여부: 중복체크가 아직 안됐다.
      let nicknameDupChecked = false;
      $('#checkDup').on("click", function(event){
          event.preventDefault();

          const getIdCheck = RegExp(/^[a-zA-Z0-9]{6,12}$/);
          const memberId = $('#memberId').val();
          if (!memberId){ // memberId가 null이거나 ""인 경우
              alert('아이디를 입력하세요');
              $('#memberId').focus();
              return;
          } else if (!getIdCheck.test(memberId)){
          alert('아이디는 영문과 숫자를 포함해 6~12자입니다');
          $('#memberId').focus();
          return
          }

          $.ajax({
              "url": "check-id",
              "method": "get",
              "data": {"memberId" : memberId},
              "async": true,
              "success": function(data, status, xhr){
                  if (data == "true"){
                      idDupChecked = true;
                      alert("사용 가능한 아이디")
                  } else {
                      idDupChecked = false;
                      alert("이미 사용 중인 아이디")
                  }
              },
              "error": function(xhr, status, err){
                  alert("error");
              }
          });
      });
      
      const nicknameField = $('#nickname'); // 닉네임 필드
      const nicknameCheck = $('#nicknameCheck'); // 결과를 보여줄 엘리먼트

      nicknameField.on('input', function(){
          nicknameDupChecked = false; // 입력 값이 변경되면 중복 체크 플래그를 재설정
      });

      nicknameField.on('blur', function(){
          const nickname = nicknameField.val();
      
          $.ajax({
              url: "check-nickname",
              method: "get",
              data: { "nickname" : nickname },
              async: true,
              success: function(data, status, xhr){
                  if (data == "true"){
                	  nicknameDupChecked = true;
                      nicknameCheck.html('사용 가능한 닉네임입니다').css('color', 'green');
                  } else {
                	  nicknameDupChecked = false;
                      nicknameCheck.html('중복된 닉네임입니다').css('color', 'red');
                  }
              },
              error: function(xhr, status, err){
                  alert("오류 발생");
              }
          });
      });

      $('#register').on('click', function(event){
          event.preventDefault();
          // 아이디
          if (!idDupChecked) { // 아이디 중복 검사안하고 계정 생성할 시
              alert("아이디 중복 검사를 실행하세요");
              $('#memberId').focus();
              return;
          }

          // 비밀번호
          if (password.value === '' ) {
              alert('비밀번호를 입력해주세요' );
              $('#password').focus();
              return;
          }
          if (passwordConfirmField.value === '') {
              alert('비밀번호확인을 해주세요' );
              $('#passwordConfirm').focus();
              return;
          }
          if (passwordField.value !== passwordConfirmField.value) {
              alert('비밀번호가 일치하지 않습니다. 다시 확인해 주세요.');
              return;
          }
          if (!passwordField.value.match(passwordRegex)) { 
              alert('비밀번호 형식이 잘못되었습니다. 다시 확인해 주세요');
              $('#password').focus();
              return;
          }

          // 이름
          if (!userNameRegex.test(userNameField.value)) {
              alert('이름에 특수문자와 숫자는 사용할 수 없습니다. 다시 확인해 주세요.');
              $('#userName').focus();
              return;
          } 
          if (userNameField.value === ""){
              alert('이름을 입력해주세요');
              $('#userName').focus();
              return;
          }

          // 닉네임
          const nickname = nicknameField.val();

          if (!nickname){
              alert('닉네임을 입력하세요');
              nickname.focus();
              return;
          }
          if (!nicknameDupChecked) {
              alert('닉네임 중복이 중복되었습니다');
              nickname.focus();
              return;
          }

          // 전화번호
          if(!phoneNoRegex.test(phoneNoField.value)){
              alert('전화번호는 숫자만 입력해주세요');
              $('#phoneNo').focus();
              return;
          }
          if(phoneNoField.value === ""){
              alert('전화번호를 입력하세요');
              $('#phoneNo').focus();
              return;
          }

          const inputCode = $('#mail-check-input').val();
          // 이메일
          if(inputCode === ""){
              alert('이메일 인증을 하세요.');
              $('#mail-check-input').focus();
              return
          }
          if(inputCode !== code){
              alert('인증번호가 일치하지 않습니다.');
              $('#mail-check-input').focus();
              return
          }
          
          // 주소
          const address = $("#address").val();
          if(!address){
              alert('주소를 입력하세요');
              return;
          }
      $('#registerform').submit();

  });
      // 아이디 중복 검사했어도 값이 새로 입력될 때 (입력창에 키up 되었을 시)
      $('#memberId').on('keyup', function(){
          idDupChecked = false;
      });	

      


  // 패스워드
  var passwordField = document.getElementById('password');
  var passwordConfirmField = document.getElementById('passwordConfirm');
  var passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@#$!%])[A-Za-z\d@#$!%]{8,16}$/;

  function checkPassword() {
      var password = passwordField.value;
      var passwordConfirm = passwordConfirmField.value;
      var passwordCheck = document.getElementById('passwordCheck');
      

      if (password !== '') {
          if (password.match(passwordRegex)) {
              passwordCheck.innerHTML = '비밀번호가 유효합니다.';
              passwordCheck.style.color = 'green';
          } else {
          passwordCheck.innerHTML = '영문, 숫자, 특수 문자(@, #, $, !, %)를 포함해 8~16자';
          passwordCheck.style.color = 'red';
          }
      } else {
          passwordCheck.innerHTML = '비밀번호를 입력해주세요';
          passwordCheck.style.color = 'red';
      }

      if (password !== '' && passwordConfirm !== '') {
          if (password === passwordConfirm) {
              passwordCheck.innerHTML = '비밀번호가 일치합니다.';
              passwordCheck.style.color = 'green';
                  if (!password.match(passwordRegex)){
                      passwordCheck.innerHTML = '일치하나 형식이 잘못되었습니다.';
                      passwordCheck.style.color = 'red';
                  }
          } else {
          passwordCheck.innerHTML = '비밀번호가 일치하지 않습니다.';
          passwordCheck.style.color = 'red';
          }
      }
  }

  passwordField.addEventListener("input", checkPassword);
  passwordConfirmField.addEventListener("input", checkPassword);

  
  var userNameField = document.getElementById('userName');
  var userNameRegex = /^[A-Za-z가-힣]+$/;
  
  userNameField.addEventListener('blur', function() {
	    checkUserName();
	});
  
  // 이름
  function checkUserName(){
      var userName = userNameField.value;
      var userNameCheck = document.getElementById('userNameCheck');

      if (userName !== '') {
    	  userNameCheck.innerHTML = "";
          if (!userNameRegex.test(userName)) {
              userNameCheck.innerHTML = '이름에 숫자와 특수문자, 공백을 사용할 수 없습니다.';
              userNameCheck.style.color = 'red';
          } 
      } else {
          userNameCheck.innerHTML = '이름을 입력해주세요';
          userNameCheck.style.color = 'red';
      }
  }

  userNameField.addEventListener('input', checkUserName);

  // 전화번호
  var phoneNoField = document.getElementById('phoneNo');
  var phoneNoRegex = /^[0-9]+$/;
  
  phoneNoField.addEventListener('blur', function() {
	    checkPhoneNo();
	});
  
  function checkPhoneNo(){
      var phoneNo = phoneNoField.value;
      var phoneNoCheck = document.getElementById('phoneNoCheck');

      if (phoneNo !== '') {
    	  phoneNoCheck.innerHTML = "";
          if (!phoneNoRegex.test(phoneNo)) {
              phoneNoCheck.innerHTML = '전화번호에 문자와 특수문자, 공백을 사용할 수 없습니다.';
              phoneNoCheck.style.color = 'red';
          } else if (phoneNo.length !== 11) {
              phoneNoCheck.innerHTML = '전화번호는 11자리여야 합니다.';
              phoneNoCheck.style.color = 'red';
          }
      } else {
          phoneNoCheck.innerHTML = '전화번호를 입력해주세요';
          phoneNoCheck.style.color = 'red';
      }
  }
  
  
  //이메일
  // 초기 상태에서 email2 입력 상자를 비활성화
  $('input[name="email2"]').attr('readonly', true);

  // selectEmail 변경 이벤트 처리
  $('#selectEmail').change(function() {
      var selectedOption = $(this).val();
      var email2 = $('input[name="email2"]');
      var checkInput = $('#mail-check-input');

      if (selectedOption === "direct") {
          // "직접 입력" 선택 시 email2 입력 상자 활성화
          email2.attr('readonly', false);
          email2.val('');
      } else {
      // 다른 옵션 선택 시 email2 입력 상자 비활성화 및 값을 선택한 옵션으로 설정
      email2.attr('readonly', true);
      email2.val(selectedOption);
      }
  });

  var code = null;

  $('#mail-Check-Btn').click(function() {
  // 이메일 주소 값을 얻어온 후 인증번호 요청
  const email1 = $('#email').val();
  const email2Value = $('input[name="email2"]').val();
  const selectedOption = $('#selectEmail').val();
  const email = selectedOption === 'direct' ? email1 + '@' + email2Value : email1 + '@' + selectedOption;
  const emailRegex = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}$/;

  if (email1 && email2Value && selectedOption !== '' || emailRegex.test(email)) {
      // 인증번호 요청
      $.ajax({
          "type": 'get',
          "url": "check-email?email=" + email,
          "success": function(data) {
              $('#mail-check-input').attr('disabled', false);
              code = data;
              alert("인증번호가 발송되었습니다.");
              $('#mail-Check-Btn').text('재전송');
          }
      });
  } else {
      alert('올바른 이메일을 입력해주세요.');
  }
  });

  // 인증번호 비교
  // blur -> focus가 벗어나는 경우 발생
  $('#mail-check-input').blur(function() {
      const inputCode = $(this).val();
      const $resultMsg = $('#mail-check-warn');

      if (inputCode === code) {
          $resultMsg.html('인증번호가 일치합니다.');
          $resultMsg.css('color', 'green');
          $('#mail-Check-Btn').attr('disabled', true);
          $('#userEmail1').attr('readonly', true);
          $('input[name="email2"]').attr('readonly', true);
          $('#selectEmail').attr('onFocus', 'this.initialSelect = this.selectedIndex');
          $('#selectEmail').attr('onChange', 'this.selectedIndex = this.initialSelect');
          $('#register').attr('disabled', false); // 계정 생성 버튼 활성화
      } else {
          $resultMsg.html('인증번호가 불일치합니다. 다시 확인해주세요!.');
          $resultMsg.css('color', 'red');
      }
  });
});

  // 주소 API
  window.onload = function() {
      $('#address-search').on("click", function(event) {
          new daum.Postcode({
              oncomplete: function(data) {
                  document.getElementById("addressCode").value = data.zonecode; // 우편번호
                  document.getElementById("address").value = data.address; // 주소
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