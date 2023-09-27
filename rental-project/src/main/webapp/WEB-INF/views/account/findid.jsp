<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>find ID</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/account/login-header.jsp" />
	
    <div class="card-body px-lg-5 py-lg-5">
     <div class="text-center text-muted mb-4">
       <small>Or sign in with credentials</small>
     </div>
     <form action="findid" method="post">
     <form role="form">
       <div class="form-group mb-3">
         <div class="input-group input-group-alternative">
           <div class="input-group-prepend">
             <span class="input-group-text"><i class="ni ni-email-83"></i></span>
           </div>
           <input name="userName" class="form-control" placeholder="이름" type="text">
         </div>
       </div>
       <div class="form-group">
         <div class="input-group input-group-alternative">
           <div class="input-group-prepend">
             <span class="input-group-text"><i class="ni ni-lock-circle-open"></i></span>
           </div>
           <input name="phoneNo" class="form-control" placeholder="전화번호" type="text">
         </div>
       </div>
       <div class="custom-control custom-control-alternative custom-checkbox">
         <input class="custom-control-input" id=" customCheckLogin" type="checkbox">
         <label class="custom-control-label" for=" customCheckLogin">
         </label>
       </div>
       <div class="text-center">
         <input id="login" type="submit" class="btn btn-primary my-4" value="find ID" />
       </div>
       <a href="/rental-project/account/findpw" >비밀번호 찾기</a>
     </form>
     </form>
   </div>
</body>
</html>