<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <head>
      <title>
   관리자 페이지
  </title>
    </head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <div> 
    <img src="/rental-project/resources/img/brand/프로젝트 1팀.png" class="navbar-brand-img" alt="..." style="width: 110px; height: auto; margin-left: 20px; margin-top: 10px;">
     </div>
    <div class="navbar-top">
<nav class="navbar-login ml-auto">
    <!-- 로그인 버튼 또는 사용자 정보 -->
    <c:choose>
        <c:when test="${loginuser eq null}">
            <!-- 로그인 전 화면 -->
            <div class="ml-auto">
                <a href="/rental-project/account/login" style="color: inherit;">
                    <span class="mb-0 text font-weight-bold">로그인</span>
                </a>
            </div>
        </c:when>
        <c:otherwise>
            <!-- 로그인 후 화면 -->
            <div class="ml-auto">
                <div class="media-body ml-2 d-none d-lg-block">
                    <div class="d-flex align-items-center">
                        <a class="nav-link" href="/rental-project/profile/profile?memberId=${loginuser.memberId}">
                            <i class="ni ni-circle-08 text-yellow mr-1"></i>
                            ${sessionScope.loginuser.memberId}님
                        </a>
                        <c:if test="${sessionScope.loginuser.admin == 2}">
                                <a href="/rental-project/home" style="color: inherit;">
                                    <span class="mb-0 text font-weight-bold mr-3">일반 화면</span>
                                </a>
                            </c:if>
                        <a href="/rental-project/account/logout" style="color: inherit;">
                            <div class="d-flex align-items-center">
                                <span class="mb-0 text font-weight-bold mr-1">로그아웃</span>
                                <i class="ni ni-user-run"></i>
                            </div>
                        </a>
                        
                    </div>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</nav>
</div>