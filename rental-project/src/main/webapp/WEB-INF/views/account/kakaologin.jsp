<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<ul>
    <li>인가코드 = ${code}</li>
    <li>유효토큰 = ${access_token}</li>
    <li>사용자정보 = ${userInfo}</li>
    <li>동의정보 = ${agreementInfo}</li>
</ul>
	<a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id={51aa4581e2f4765686dd6faec72c568d}&redirect_uri=http://localhost:8080/rental-project/home">로그아웃</a>
</body>
</html>