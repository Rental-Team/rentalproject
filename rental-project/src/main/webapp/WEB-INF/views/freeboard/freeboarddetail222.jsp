<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>
    Argon Dashboard - Free Dashboard for Bootstrap 4 by Creative Tim
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

<body class="">
  <nav class="navbar navbar-vertical fixed-left navbar-expand-md navbar-light bg-white" id="sidenav-main">
    <div class="container-fluid">
      <!-- Toggler -->
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#sidenav-collapse-main" aria-controls="sidenav-main" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <!-- Brand -->
      <a class="navbar-brand pt-0" href="./index.html">
        <img src="/rental-project/resources/img/brand/blue.png" class="navbar-brand-img" alt="...">
      </a>
      <!-- User -->
      <ul class="nav align-items-center d-md-none">
        <li class="nav-item dropdown">
          <a class="nav-link nav-link-icon" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="ni ni-bell-55"></i>
          </a>
          <div class="dropdown-menu dropdown-menu-arrow dropdown-menu-right" aria-labelledby="navbar-default_dropdown_1">
            <a class="dropdown-item" href="#">Action</a>
            <a class="dropdown-item" href="#">Another action</a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="#">Something else here</a>
          </div>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <div class="media align-items-center">
              <span class="avatar avatar-sm rounded-circle">
                <img alt="Image placeholder" src="/rental-project/resources/img/theme/team-1-800x800.jpg
">
              </span>
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
      <!-- Collapse -->
      <div class="collapse navbar-collapse" id="sidenav-collapse-main">
        <!-- Collapse header -->
        <div class="navbar-collapse-header d-md-none">
          <div class="row">
            <div class="col-6 collapse-brand">
              <a href="./index.html">
                <img src="/rental-project/resources/img/brand/blue.png">
              </a>
            </div>
            <div class="col-6 collapse-close">
              <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#sidenav-collapse-main" aria-controls="sidenav-main" aria-expanded="false" aria-label="Toggle sidenav">
                <span></span>
                <span></span>
              </button>
            </div>
          </div>
        </div>
        <!-- Form -->
        <form class="mt-4 mb-3 d-md-none">
          <div class="input-group input-group-rounded input-group-merge">
            <input type="search" class="form-control form-control-rounded form-control-prepended" placeholder="Search" aria-label="Search">
            <div class="input-group-prepend">
              <div class="input-group-text">
                <span class="fa fa-search"></span>
              </div>
            </div>
          </div>
        </form>
        <!-- Navigation -->
        <ul class="navbar-nav">
          <li class="nav-item  active ">
            <a class="nav-link  active " href="./index.html">
              <i class="ni ni-tv-2 text-primary"></i> Dashboard
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link " href="./examples/icons.html">
              <i class="ni ni-planet text-blue"></i> Icons
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link " href="./examples/maps.html">
              <i class="ni ni-pin-3 text-orange"></i> Maps
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link " href="./examples/profile.html">
              <i class="ni ni-single-02 text-yellow"></i> User profile
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link " href="/rental-project/freeboard/freeboardlist">
              <i class="ni ni-bullet-list-67 text-red"></i> 자유게시판
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="./examples/login.html">
              <i class="ni ni-key-25 text-info"></i> Login
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="./examples/register.html">
              <i class="ni ni-circle-08 text-pink"></i> Register
            </a>
          </li>
        </ul>
        <!-- Divider -->
        <hr class="my-3">
        <!-- Heading -->
        <h6 class="navbar-heading text-muted">Documentation</h6>
        <!-- Navigation -->
        <ul class="navbar-nav mb-md-3">
          <li class="nav-item">
            <a class="nav-link" href="https://demos.creative-tim.com/argon-dashboard/docs/getting-started/overview.html">
              <i class="ni ni-spaceship"></i> Getting started
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="https://demos.creative-tim.com/argon-dashboard/docs/foundation/colors.html">
              <i class="ni ni-palette"></i> Foundation
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="https://demos.creative-tim.com/argon-dashboard/docs/components/alerts.html">
              <i class="ni ni-ui-04"></i> Components
            </a>
          </li>
        </ul>
        <ul class="navbar-nav">
          <li class="nav-item active active-pro">
            <a class="nav-link" href="./examples/upgrade.html">
              <i class="ni ni-send text-dark"></i> Upgrade to PRO
            </a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <div class="main-content">
    <!-- Navbar -->
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
                <span class="avatar avatar-sm rounded-circle">
                  <img alt="Image placeholder" src="/rental-project/resources/img/theme/team-4-800x800.jpg">
                </span>
                <div class="media-body ml-2 d-none d-lg-block">
                  <span class="mb-0 text-sm  font-weight-bold">Jessica Jones</span>
                </div>
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
    <!-- End Navbar -->
    <br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
    <!-- Header -->
    <div class="container-fluid mt--7">
      <div class="row mt-5">
        <div class="col-xl-8 mb-5 mb-xl-0">
          <div class="card shadow">
            <div class="card-header border-0">
              <div class="row align-items-center">
                <div class="col">
                  <h3 class="mb-0">자유게시글 상세정보</h3>
                  <br>
                  <br> 
                   <table>
		            <tr>
		                <th>게시글 번호</th>
		                <td>
		                    ${ requestScope.freeBoard.freeBoardNo }
		                </td>
		            </tr>
		            <tr>
		                <th>게시글 제목</th>
		                <td>
		                	${ freeBoard.freeBoardTitle }
		                </td>
		            </tr>
		            <tr>
		                <th>작성자</th>
		                <td>
		                	${ freeBoard.freeBoardViewCount } <!--  작성자 데이터 없어서 다른거 일단 끌고옴 -->
		                </td>
		            </tr>
		            <tr>
		                <th>게시글 작성 일자</th>
		                <td>
		                	<fmt:formatDate value="${ freeBoard.freeBoardDate }" pattern="yyyy-MM-dd HH:mm"/>
		                </td>
		            </tr>
		            <tr>
		                <th>조회수</th>
		                <td> 
		                	${ freeBoard.freeBoardViewCount }
		                </td>
		            </tr>
		            <tr>
		                <th>첨부파일</th> 
		                <td>
		                	<c:forEach var="freeBoardAttach" items="${ freeBoard.freeBoardAttachList }">
		                		<a href="download?attachNo=${ freeBoardAttach.attachNo }"> ${freeBoardAttach.attachFileName}</a>
		                	</c:forEach>
		                </td>
		            </tr>
		            <tr>
		                <th>글내용</th>
		                <td>
		                	${ freeBoard.freeBoardContent }
		                </td>
		            </tr> 
		        </table> 
			        <br>
			        <div class="col text-center" >
				        <input type="button" class ="btn btn-sm btn-primary" id="btnBackToList" value="목록으로 돌아가기" >
				        <input type="button" class ="btn btn-sm btn-primary" id="btnedit" value="게시글 수정하기" >
				        <input type="button" class ="btn btn-sm btn-primary" id="btndelete" value="게시글 삭제하기" >
	                </div> 
                </div>
              </div>
           </div> 
          </div>
        </div>
      </div>
    </div>  
<br>
<br>
<!-- 자유게시글 댓글 쓰기 기능 구현 --> 
	<div class="container-fluid mt--7">
      <div class="row mt-5">
        <div class="col-xl-8 mb-5 mb-xl-0">
          <div class="card shadow">
            <div class="card-header border-0">
              <div class="row align-items-center">
                <div class="col">
                	<h5 class="mb-0">자유게시글 댓글</h5>
                	<form id="freeBaordReivewForm" action="freeboard-review" method="post">
                		<input type="hidden" name="freeBoardNo" value="${ freeBoard.freeBoardNo }" />
	                		<table class="table align-items-center">
	                			<tr>
	                				<td style="width: 800px;">
	                				<textarea id="comment_content" name="replyContent" style="width: 105%; resize:none;  border-radius:80px" rows="2">     </textarea>
	                				</td>
									<td style="width: 50px; vertical-align: middle;">
									<input type="submit" class ="btn btn-sm btn-primary" id="btninsertreview" value="댓글등록하기" >
									
									</td>
	                			</tr>
	                		</table>
            		</form>
            		<br>
<!-- 자유게시글 댓글 리스트 보기 기능 구현 시작-->
			<table id="review-list" class="table align-items-center table-flush">
		    <thead class="thead-light">
		        <tr style="text-align:center">
		            <th scope="col" style="width: 50px">댓글번호</th> 
		            <th scope="col" style="width: 300px">댓글내용</th>
		             <th scope="col" style="width: 150px">댓글작성자</th>
		            <th scope="col" style="width: 150px">댓글작성일자</th>
		            <th scope="col" style="width: 150px">댓글수정삭제</th>
		        </tr>
		    </thead>
		    <tbody>
		        <c:forEach var="freeBoardReview" items="${freeBoard.freeBoardReviewList}">
		            <tr style="text-align:center">
		                <td style="width: 50px">${freeBoardReview.freeBoardReplyNo}</td>
		                <td style="width: 150px">
		                <c:choose>
		                	<c:when test="${ not freeBoardReview.replyDelete }">
		                	<a> ${ freeBoardReview.replyContent }</a>
							</c:when>
							<c:otherwise>
							<span class="replyDelete" style="color : gray"><< 삭제된 게시글입니다 >></span> 
							</c:otherwise>    
						</c:choose>  
						</td>     
						<td scope="col" style="width:200px"> ${ freeBoardReview.replyDelete? '' : sessionScope.loginuser.memberId } <!-- 게시글 삭제시 작성자 안보이게 설정 -->
		                							 <input type="hidden" name="memberNo" value="${ loginuser.memberId }"> 
		                							 <!-- 오류 : 다시확인 로그인한 유저로 걍 다 바뀜 -->   
		                <td style="width: 150px">
		                    <fmt:formatDate value="${freeBoardReview.replyCreateDate}" pattern="yyyy-MM-dd HH:mm" />
		                </td>
		                <td>
		                    <a class="btn btn-sm btn-primary edit-reply" data-reply-no="${freeBoardReview.freeBoardReplyNo}" href="javascript:void(0)" style="color: white">댓글수정하기</a>
		                    <a class="btn btn-sm btn-primary delete-reply" data-reply-no="${freeBoardReview.freeBoardReplyNo}" href="javascript:void(0)" style="color: white">댓글삭제하기</a>
		                </td>
		            </tr>
		            <tr>
		                <td colspan="5">
		                    <div id="reply-edit-area-${freeBoardReview.freeBoardReplyNo}" style="display: none">
		                        ${sessionScope.loginuser.memberId} &nbsp;&nbsp; [댓글 수정 시간 : ${freeBoardReview.replyCreateDate}] <br />
		                        <br />
		                        <form action="edit-reply" method="post" style="width: 105%; resize: none;">
		                            <input type="hidden" name="freeBoardReplyNo" value="${freeBoardReview.freeBoardReplyNo}" />
		                            <input type="hidden" name="freeBoardNo" value="${freeBoard.freeBoardNo}" />
		                            <textarea name="replyContent" style="width: 70%; resize: none; border-radius: 80px" rows="2" maxlength="200" >${freeBoardReview.replyContent}</textarea>
		                            <br />
		                            <div class="btn btn-sm btn-primary">
		                                <a class="update-reply" data-reply-no="${freeBoardReview.freeBoardReplyNo}" href="javascript:void(0)" style="color: white">수정완료</a>
		                            </div>
		                            <div class="btn btn-sm btn-primary">
		                                <a class="cancel-edit-reply" data-reply-no="${freeBoardReview.freeBoardReplyNo}" href="javascript:void(0)" style="color: white">수정취소</a>
		                            </div>
		                        </form>
		                    </div>
		                </td>
		            </tr>
		        </c:forEach>
		    </tbody>
		</table>
<!-- 자유게시글 댓글 리스트 보기 기능 구현 끝-->	
                 </div>
              </div>
           </div> 
          </div>
        </div>
      </div>
    </div>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>

      <!-- Footer -->
      <footer class="footer">
        <div class="row align-items-center justify-content-xl-between">
          <div class="col-xl-6">
            <div class="copyright text-center text-xl-left text-muted">
              &copy; 2018 <a href="https://www.creative-tim.com" class="font-weight-bold ml-1" target="_blank">Creative Tim</a>
            </div>
          </div>
          <div class="col-xl-6">
            <ul class="nav nav-footer justify-content-center justify-content-xl-end">
              <li class="nav-item">
                <a href="https://www.creative-tim.com" class="nav-link" target="_blank">Creative Tim</a>
              </li>
              <li class="nav-item">
                <a href="https://www.creative-tim.com/presentation" class="nav-link" target="_blank">About Us</a>
              </li>
              <li class="nav-item">
                <a href="http://blog.creative-tim.com" class="nav-link" target="_blank">Blog</a>
              </li>
              <li class="nav-item">
                <a href="https://github.com/creativetimofficial/argon-dashboard/blob/master/LICENSE.md" class="nav-link" target="_blank">MIT License</a>
              </li>
            </ul>
          </div>
        </div>
      </footer>
    </div>
  </div>
<br>
<br>
<br>
<br>
<br>
  <!--   Core   -->
  <script src="/rental-project/resources/js/plugins/jquery/dist/jquery.min.js"></script>
  <script src="/rental-project/resources/js/plugins/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
  <!--   Optional JS   -->
  <script src="/rental-project/resources/js/plugins/chart.js/dist/Chart.min.js"></script>
  <script src="/rental-project/resources/js/plugins/chart.js/dist/Chart.extension.js"></script>
  <!--   Argon JS   -->
  <script src="/rental-project/resources/js/argon-dashboard.min.js?v=1.1.2"></script>
  <script src="https://cdn.trackjs.com/agent/v3/latest/t.js"></script>
  <script>
    window.TrackJS &&
      TrackJS.install({
        token: "ee6fab19c5a04ac1a32a645abde4613a",
        application: "argon-dashboard-free"
      });
  </script>
  
		    <script>
			$(function(event) {
	    	$("#btnBackToList").on("click",function(event) {                // 자유게시판 목록으로 돌아가기 누르면 freeboardlist로 가기 
	    		location.href="freeboardlist";
    		})
			
	    	
        	$("#btnedit").on("click", function(event) {                     // 게시글 수정하기 누르면 게시글 수정하기 form으로가기
	    		location.href="freeboardedit" + "?freeBoardNo=" +  ${ freeBoard.freeBoardNo };
	    	})
	    
		   $("#btndelete").on("click",function(event) {                    // 게시글 삭제 이벤트    
		   			const yes = confirm(${ freeBoard.freeBoardNo } + "번 게시글을 삭제할까요?");
	   				if (yes) {
	   					location.href="freeboarddelete/" + ${ freeBoard.freeBoardNo };
	   						}
	   		})	
	   		$(".delete-reply").on("click",function(event) {                    // 댓글 삭제하기 이벤트 
   				const freeBoardReplyNo = $(this).attr("data-reply-no");
	   			const yn = confirm(freeBoardReplyNo  + "번 댓글을 삭제할까요?");
   				if (yn) {
   					location.href='delete-reply?freeBoardReplyNo=' + freeBoardReplyNo +
   												'&freeBoardNo=' + ${freeBoard.freeBoardNo};
   						}
	   		})	 
	   		
	   		let currentEditFreeBoardReplyNo = null;
	   		
	   		// 댓글 수정하기 클릭 이벤트 
	   		
	   		$(".edit-reply").on('click', function(event) {
	    		const freeBoardReplyNo = $(this).attr("data-reply-no");
	    		
	    		$('#reply-edit-area-' + freeBoardReplyNo).css('display', '');
	    		$('#reply-view-area-' + freeBoardReplyNo).css('display', 'none');
	    		
	    		if (currentEditFreeBoardReplyNo) {
	    			$('#reply-edit-area-' + currentEditFreeBoardReplyNo).css('display', 'none');
		    		$('#reply-view-area-' + currentEditFreeBoardReplyNo).css('display', '');
	    		}
	    		currentEditFreeBoardReplyNo = freeBoardReplyNo;
	    		
    		})
    		
    		// 댓글 수정취소 이벤트 
    		$(".cancel-edit-reply").on('click', function(event) {
	   			const freeBoardReplyNo = $(this).attr("data-reply-no");
	   			
	   			$('#reply-edit-area-' + freeBoardReplyNo).css('display', 'none');
	    		$('#reply-view-area-' + freeBoardReplyNo).css('display', '');
	   			
	    		currentEditFreeBoardReplyNo = null;
		   		
	   		})
	   		
	   		// 댓글 수정완료 이벤트 
	   		$(".update-reply").on('click', function(event) {
	   			const freeBoardReplyNo = $(this).attr("data-reply-no");
	   			$('#reply-edit-area-' + freeBoardReplyNo + ' form').submit();
	   		})
    		
	    	});
			</script>
</body>

</html>