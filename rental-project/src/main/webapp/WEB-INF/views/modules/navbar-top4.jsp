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
  width: 90px;
  top: 200px; /* 사이드바가 위에서부터 시작하도록 수정 */
  right: 10px;
  background: #fff;
  z-index: 2; /* 다른 요소 위에 나타나도록 설정 */
  opacity:0.8;
  transition: opacity 0.5s;
}

.quickmenu:hover {
	opacity: 1.0;
}
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
  font-weight: bold;
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
</style>
</head>
    
<div class="quickmenu">
  <ul>
    <li><a href="/rental-project/privateboard/privateqnalist">1:1문의</a></li>
    <li><a href="/rental-project/zzim/${loginuser.memberNo}">찜목록</a></li>
  </ul>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(document).ready(function() {
  var currentPosition = parseInt($(".quickmenu").css("top"));

  $(window).scroll(function() {
    var position = $(window).scrollTop();
    $(".quickmenu").stop().animate({ "top": position + currentPosition + "px" }, 1000);
  });
});
</script>