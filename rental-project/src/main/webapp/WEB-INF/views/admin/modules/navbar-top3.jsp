<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html>
    <head>
    <style>
   
	.nav-link2 {
    border: 0; /* 모든 네비게이션 링크의 경계선을 제거합니다. */
    font-size: 35px;
    
	
}	
	@font-face {
	font-family:'neon';
	src: url("/rental-project/resources/fonts/225.ttf") format('truetype');
	
.nav-link.active {
    border: 0; /* 활성화된 네비게이션 링크에도 경계선을 제거합니다. */
}
    </style>
    </head>
<nav class="navbar-top3" style="font-family:'neon';">
   <div class="container text-center">
                <ul class="nav nav-tabs">
                    <li class="nav-item">
                        <a class="nav-link2 active" href="/rental-project/admin/home">관리자 홈</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link2" href="/rental-project/admin/item/list">상품 게시판</a>
                    </li>
                    <li class="nav-item">
			            <a class="nav-link2 " href="/rental-project/admin/rental/rentalList">주문 리스트
			            </a>
			          </li>
                    <li class="nav-item">
                        <a class="nav-link2" href="/rental-project/admin/member/list">회원 리스트</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link2" href="/rental-project/freeboard/freeboardlist">자유 게시판</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link2" href="/rental-project/admin/notice/list">공지사항</a>
                    </li>
                     <li class="nav-item">
		            	<a class="nav-link2" href="/rental-project/privateboard/privateqnalist">
		               1대1문의
		            	</a>
          		</li>
                </ul>
            </div>
</nav>
</html>