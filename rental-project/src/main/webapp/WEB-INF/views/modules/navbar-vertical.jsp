<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<nav class="navbar navbar-expand-lg navbar-light bg-content mb-2" style="border: 1px solid white;">  <!-- bg-success -->
  <div class="container-fluid">
    <!-- 좌측 정렬을 위한 그리드 시스템을 사용합니다 -->
    <div class="d-flex align-items-center">
      <a class="navbar-brand" href="/rental-project/home" style="font-size: 24px; font-weight: bold; ">
        <img src="/rental-project/resources/img/brand/225.png" class="navbar-brand-img" alt="..." style="width: 160px; height: auto;">
      </a>
    </div>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
     <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ml-auto">
        <li class="nav-item active">
          <a class="nav-link dashboard-menu" href="/rental-project/home">
            <i style="font-size: 24px; color: blue;"></i> 대시보드
          </a>
        </li>
        <li class="nav-item active">
          <a class="nav-link dashboard-menu" href="/rental-project/item/list">
            <i style="font-size: 24px; color: green;"></i> 상품 게시판
          </a>
        </li>
        <li class="nav-item active">
          <a class="nav-link dashboard-menu" href="/rental-project/freeboard/freeboardlist">
            <i style="font-size: 24px; color: yellow;"></i> 자유 게시판
          </a>
        </li>
        <li class ="nav-item active">
          <a class="nav-link dashboard-menu" href="/rental-project/notice/list">
            <i style="font-size: 24px; color: red;"></i> 공지사항
          </a>
        </li>
      </ul>
    </div>
      <!-- 사용자 관련 요소 -->
      <ul class="navbar-nav align-items-center d-none d-md-flex ml-auto">
        <li class="nav-item dropdown"> 
            <div class="media align-items-center">
              <!-- User 아이콘 또는 이미지 -->
              <c:choose>
                <c:when test="${loginuser eq null}">
                  <!-- 로그인 전 화면 -->
                  <a href="/rental-project/account/login" style="color: inherit;">
                    <span class="mb-0 text font-weight-bold">로그인</span>
                  </a>
                </c:when>
                <c:otherwise>
                  <!-- 로그인 후 화면 -->
                  <c:if test="${sessionScope.loginuser.admin == 2}">
                    <div class="media-body ml-2 d-none d-lg-block">
                      <a href="/rental-project/admin/home" style="color: inherit;">
                        <span class="mb-0 text font-weight-bold">관리자 화면</span>
                      </a>
                    </div>
                  </c:if>
                   <div class="media-body ml-2 d-none d-lg-block">
                            <div class="d-flex align-items-center">
                                <a class="nav-link" href="/rental-project/profile/profile?memberId=${loginuser.memberId}">
                                  	<c:if test="${loginuser.memberImage == null}">
									    <img src="/rental-project/resources/img/theme/default.png" draggable="false" class="style-scope yt-img-shadow" height="32" width="32">
									</c:if>
									<c:if test="${not empty loginuser.memberImage}">
									    <img src="${pageContext.request.contextPath}/resources/upload/${loginuser.memberImage}" alt="Image" draggable="false" class="style-scope yt-img-shadow" height="32" width="32">
									</c:if>
										${sessionScope.loginuser.memberId}님
                                </a>
                                <a href="/rental-project/account/logout" style="color: inherit;">
                                    <div class="d-flex align-items-center">
                                        <span class="mb-0 text font-weight-bold mr-2">로그아웃</span>
                                        <i class="ni ni-user-run"></i>
                                    </div>
                                </a>
                  </div>
                </c:otherwise>
              </c:choose>
            </div> 
          <div class="dropdown-menu dropdown-menu-arrow dropdown-menu-right">
            <div class=" dropdown-header noti-title">
              <h6 class="text-overflow m-0">Welcome!</h6>
            </div>
            <a href="./examples/profile.html" class="dropdown-item">
              <i class="ni ni-single-02"></i>
              <span>My profile</span>
            </a>
            <a href="./examples/profile.html" class="dropdown-item">
              <i class="ni ni-settings-gear-65"></i>
              <span>Settings</span>
            </a>
            <a href="./examples/profile.html" class="dropdown-item">
              <i class="ni ni-calendar-grid-58"></i>
              <span>Activity</span>
            </a>
            <a href="./examples/profile.html" class="dropdown-item">
              <i class="ni ni-support-16"></i>
              <span>Support</span>
            </a>
            <div class="dropdown-divider"></div>
            <a href="#!" class="dropdown-item">
              <i class="ni ni-user-run"></i>
              <span>Logout</span>
            </a>
          </li>
        </ul>
      </div>
    </div> 
  </nav>
    