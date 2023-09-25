<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
 <nav class="navbar navbar-top navbar-expand-md navbar-dark" id="navbar-main">
      <div class="container-fluid">
        <!-- Brand -->
        <a class="h4 mb-0 text-white text-uppercase d-none d-lg-inline-block" href="./index.html">Dashboard</a>
        <!-- Form -->
        <form class="navbar-search navbar-search-dark form-inline mr-3 d-none d-md-flex ml-lg-auto">
          <div class="form-group mb-0">
            <div class="input-group input-group-alternative">
              <div class="input-group-prepend">
                <span class="input-group-text"><i class="fas fa-search"></i></span>
              </div>
              <input class="form-control" placeholder="Search" type="text">
            </div>
          </div>
        </form>
        <!-- User -->
        <ul class="navbar-nav align-items-center d-none d-md-flex">
          <li class="nav-item dropdown">
            <a class="nav-link pr-0" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              <div class="media align-items-center">
              
              <!--   <span class="avatar avatar-sm rounded-circle">
                  <img alt="Image placeholder" src="/rental-project/resources/img/theme/team-4-800x800.jpg">
                </span> -->
 				<c:choose>
					<c:when test="${ loginuser eq null }">
		                <div class="media-body ml-2 d-none d-lg-block">
		                <a href="/rental-project/account/login" style="color : inherit;">
		                <span class="mb-0 text  font-weight-bold">로그인</span>
		                </a>
		                </div>
                
		                <div class="media-body ml-2 d-none d-lg-block">
		                <a href="/rental-project/account/register" style="color : inherit;">
		                <span class="mb-0 text  font-weight-bold">회원가입</span>
		                </a>
		                </div>
                	</c:when>
                	<c:otherwise>
                		<a href="/rental-project/admin/home" style="color : inherit;">일반 화면</a>
                		${ sessionScope.loginuser.memberId }님
                		<a href="/rental-project/account/logout" style="color : inherit;">로그아웃</a>
                	</c:otherwise>
                </c:choose>
                
               
              </div>
            </a>
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
            </div>
          </li>
        </ul>
      </div>
    </nav>