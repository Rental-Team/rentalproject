<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
  <head>
<style>
div, ul, li {
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
  padding: 0;
  margin: 0;
}
a {
  text-decoration: none;
}

.quickmenu {
  position: absolute;
  width: 150px;
  top: 400px; /* 사이드바가 위에서부터 시작하도록 수정 */
  right: 10px;
  background: #ffffff;
  z-index: 2; /* 다른 요소 위에 나타나도록 설정 */
  border: none; /* 테두리 제거 */
  border-radius: 10px; /* 둥근 테두리 */
  
}

/* .quickmenu:hover {
	opacity: 1.0;
	color: blue;
} */
.quickmenu ul {
  position: relative;
  float: left;
  width: 100%;
  display: inline-block;
  *display: inline;
  border: 1px solid #ddd;
}

.quickmenu ul li {
  float: left;
  width: 100%;
  border-bottom: 1px solid #ddd;
  text-align: center;
  display: inline-block;
  *display: inline;
}

.quickmenu ul li a {
  position: relative;
  float: left;
  width: 100%;
  height: 30px;
  line-height: 30px;
  text-align: center;
  color: #333;
  font-size: 10pt;
  font-weight:Semi bold;
}

.quickmenu ul li a:hover {
  color: #000;
}

.quickmenu ul li:last-child {
  border-bottom: 0;
}

.content {
  position: relative;
  min-height: 2000px;
}
.scroll-to-top{
	position:fixed;
	bottom:390px;
	right:20px;
	background: none;
	color: #fff;
	border: none;
	border-radius: 4px;
	padding: 10px 20px;
	cursor: pointer;
	display: none;	
	opacity: 1.0;
}
</style>
</head>
    
<div class="quickmenu">
 <p style="font-weight:bold; text-align:center; padding-top: 15px;">QUICK MENU </p>
  <ul>
 
    <li>
    <a href="/rental-project/privateboard/privateqnawrite">
    <img src="/rental-project/resources/img/brand/문의.png" style="width: 30px; height: auto; float: left; margin-right: -10px;">
    1:1문의 작성</a></li>
    <li><a href="/rental-project/zzim/${loginuser.memberNo}">
    <img src="/rental-project/resources/img/brand/장바구니2.png" style="width: 30px; height: auto; float: left; margin-right: -10px;">
    찜목록</a></li>
    <li><button class="scroll-to-top">⬆️</button> </li>
  </ul>
  
</div>



<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(document).ready(function() {
  var currentPosition = parseInt($(".quickmenu").css("top"));
  var $scrollToTop = $(".scroll-to-top");

  $(window).scroll(function() {
    var position = $(window).scrollTop();
    $(".quickmenu").stop().animate({ "top": position + currentPosition + "px" }, 1000);
  	
    if (position > 6) {
    	$scrollToTop.fadeIn();
		} else {
			$scrollToTop.fadeOut();
		}
  
  });
  $scrollToTop.click(function() {
	  $("html, body").animate({ scrollTop: 0 }, 500);
  });
});
</script>