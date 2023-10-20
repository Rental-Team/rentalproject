<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html>
    <head>
    <link rel="stylesheet" type="text/css" href="navbar-top.css">
    <style>
   .nav-link2 {
    border: none;
    font-size: 35px;
    border-bottom: none
    position: relative; /* 요소의 위치를 상대적으로 설정합니다. */
    z-index: 1; /* 텍스트가 배경색 위에 표시될 순서를 설정합니다. */
    color: black;
   
    
  
}
@font-face {
    font-family: 'neon';
    src: url("/rental-project/resources/fonts/4.ttf") format('truetype');
}


.nav-link.active {
    border: none;
    border-bottom: none;
}

.container {
   
      z-index: 0; /* 배경색이 텍스트 뒤에 나타날 순서를 설정합니다. */
     padding: 2px; 
     
}
.nav-link2:hover {
	
    text-decoration: underline double; /* 강조선 표시 */
    text-decoration-color: rgba(255, 0, 0, 0.2); /* 강조선 색상 설정 (흰색) */
}

</style>
</head>
<nav class="navbar-top3" style="font-family: 'neon';">
   <div class="container text-center">
        <ul class="nav nav-tabs" style="border: none;">
            <li class="nav-item">
                <a class="nav-link2 active" href="/rental-project/home">홈</a>
            </li>
            <li class="nav-item">
                <a class="nav-link2 item" href="/rental-project/item/list">상품 게시판</a>
            </li>
            <li class="nav-item">
                <a class="nav-link2 free" href="/rental-project/freeboard/freeboardlist">자유 게시판</a>
            </li>
            <li class= "nav-item">
                <a class="nav-link2 notice" href="/rental-project/notice/list">공지사항</a>
            </li>
            <li class="nav-item">
                <a class="nav-link2 private" href="/rental-project/privateboard/privateqnalist">1:1 문의</a>
            </li>
        </ul>
    </div>
</nav>
</html>