<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">

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
<jsp:include page="/WEB-INF/views/modules/navbar-vertical.jsp" />
  <div class="main-content">
    <!-- Navbar -->
	<jsp:include page="/WEB-INF/views/modules/navbar-top.jsp" />
    <!-- End Navbar -->
    <!-- Header -->
    <jsp:include page="/WEB-INF/views/modules/navbar-content.jsp" />
    <div class="container-fluid mt--7">
      <div class="row">
        <div class="col-xl-12 mb-5 mb-xl-0">
          <div class="card shadow">
            <div class="card-header border-0">
              <div class="row align-items-center">
                <div class="col">
                  <h3 class="mb-1">자유게시글 상세정보</h3>
                  </div>
                  </div>
                  </div>
                  
                  <br>
                  <div class="card-body">
                  <form action="freeboardwrite" method="post">
                  
                  <div class="pl-lg-12" style="magin : 0 auto;">
                  <div class="row">
                    <div class="col-lg-6" >
                      <div class="form-group focused">
                        <label class="form-control-label"for="input-freeBoardTitle">제목</label>
                        <input disabled="disabled" type="text" id="input-freeBoardTitle"  name="freeBoardTitle" class="form-control form-control-alternative" value="${ freeBoard.freeBoardTitle }"/>
                           </div>
                    </div>
                        <div class="col-lg-1">
                      <div class="form-group">
                        <label class="form-control-label"  for="input-freeBoardNo">게시글 번호</label>
                        <input disabled="disabled" type="text" id="input-freeBoardNo" name="freeBoardNo" class="form-control form-control-alternative"  value="${ requestScope.freeBoard.freeBoardNo }"/>           
                   </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-lg-6">
                      <div class="form-group">						<!--  작성자 데이터 없어서 다른거 일단 끌고옴 -->
                        <label class="form-control-label" for="input-freeBoardName">작성자</label>
                        <input disabled="disabled" type="text" id="input-freeBoardName" name="freeBoardName" class="form-control form-control-alternative" value="${ freeBoard.freeBoardViewCount }"/>
                      </div>
                  </div>
                  
                    <div class="col-lg-6">
                      <div class="form-group">
                        <label class="form-control-label" for="input-freeBoardDate">작성 일자</label>
                        <input disabled="disabled" type="regdate" id="input-freeBoardDate" name="freeBoardDate" class="form-control form-control-alternative" value="${ freeBoard.freeBoardDate }"/>
                      </div>
                    </div>
                  </div>
                  
                  <div class="row">
                    <div class="col-lg-6">
                      <div class="form-group">
                        <label class="form-control-label" for="input-categoryName">첨부파일</label>
<%--                         <input disabled="disabled" type="text" id="input-categoryName" name="categoryName" class="form-control form-control-alternative" value="${ item.categoryName }"> --%>
                      </div>
                  </div>
                  
                    <div class="col-lg-1">
                      <div class="form-group">
                        <label class="form-control-label" for="input-freeBoardViewCount">조회수</label>
                        <input disabled="disabled" type="text" id="input-freeBoardViewCount" name="itemCode" class="form-control form-control-alternative" value="${ freeBoard.freeBoardViewCount }">
                      </div>
                    </div>
                  </div>
                  
                  <div class="row">
                    <div class="col-lg-12">
                      <div class="form-group">
                        <label class="form-control-label" for="input-freeBoardContent">글내용</label>
                        <textarea disabled="disabled" id="input-freeBoardContent" name="freeBoardContent" class="form-control form-control-alternative" rows="15" style="resize: none" >${ freeBoard.freeBoardContent }</textarea>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                  <div class="col-lg-12">
                  <div class="text-right" >
				        <input type="button" class ="btn btn-sm btn-primary" id="btnBackToList" value="목록으로 돌아가기" >
				        <input type="button" class ="btn btn-sm btn-primary" id="btnedit" value="게시글 수정하기" >
				        <input type="button" class ="btn btn-sm btn-primary" id="btndelete" value="게시글 삭제하기" >
	                </div> 
                  </div>
                  </div>
                  </div>
                  </form>	       
                </div>
              </div>
           </div> 
          </div>
          </div>

                  
 <%--                        <th>게시글 번호</th>
		                <td>
		                    ${ requestScope.freeBoard.freeBoardNo }
		                </td>
		            </tr>
		            <tr>
		                <th>게시글 제목</th>
		                <td>
		                	
		                </td>
		            </tr>
		            <tr>
		                <th>작성자</th>
		                <td>
		                	${ freeBoard.freeBoardViewCount } 
		                </td>
		            </tr>
		            <tr>
		                <th>게시글 작성 일자</th>
		                <td>
		                	<fmt:formatDate value= pattern="yyyy-MM-dd hh:mm"/>
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
		                	
		                </td>
		            </tr>
		            <tr>
		                <th>글내용</th>
		                <td>
		                	
		                </td>
		            </tr> --%> 
		<!-- To DO : 자바스크립트는 j쿼리로 바꾸기 -->
	    <script>
		    window.addEventListener("click", function(event) {
	    	const btnBackToList = document.querySelector("#btnBackToList");
	    	btnBackToList.addEventListener("click",function(event) {
	    		location.href="freeboardlist";
    	});
    });
		    
		    window.addEventListener("click", function(event) {
		    	const btnedit = document.querySelector("#btnedit");
		    	btnedit.addEventListener("click",function(event) {
		    		location.href="freeboardedit" + "?freeBoardNo=" +  ${ requestScope.freeBoard.freeBoardNo };
	    	});
	    });
		    </script>
		    
 <br>
 <br>
 		<!-- 자유게시글 댓글 쓰기 기능 구현 --> 
	<div class="container-fluid mt--4">
      <div class="row mt-4">
        <div class="col-xl-12 mb-5 mb-xl-0">
          <div class="card shadow">
            <div class="card-header border-0">
              <div class="row align-items-center">
                <div class="col">
                <h5 class="mb-0">자유게시글 댓글</h5>
                	<form id="commentform" action="freeboard-review" method="post">
                		<input type="hidden" name="freeBoardNo" value="${ freeBoard.freeBoardNo }" />
	                		<table class="table align-items-right">
	                			<tr>
	                				<td style="width: 1300px;">
	                				<textarea id="comment_content" name="replyContent" style="width: 105%; resize:none;  border-radius:80px" rows="2"> </textarea>
	                				</td>
	                				
									<td style="vertical-align: middle; text-align:right;">
									<input type="submit" class ="btn btn-sm btn-primary" id="btninsertreview" value="댓글등록하기" >
									
									</td>
	                			</tr>
	                		</table>
            		</form>
            		<br>
          		<!-- 자유게시글 댓글 보기 기능 구현 시작-->
          		
					<table id = "review-list" style="text-align:center" class="table align-items-center table-flush">
           				<thead class="thead-light">
			             <tr style="text-align:center">
			               <th scope="col" style="width:50px">댓글번호</th>
			               <th scope="col" style="width:50px">댓글작성자</th>
			               <th scope="col" style="width:300px">댓글내용</th>
			               <th scope="col" style="width:150px">댓글작성일자</th>
			               <th scope="col" style="width:150px">댓글작성일자</th>
			             </tr>
			           </thead>
			           <tbody>
			            <c:forEach var="freeBoardReview" items="${ freeBoard.freeBoardReviewList }">
			             <tr style="text-align:center">
			                <td scope="col" style="width:100px"> ${ freeBoardReview.freeBoardReplyNo } </td>
			                <td scope="col" style="width:200px"> ${ sessionScope.loginuser.memberId }
					                							 <input type="hidden" name="memberNo" value="${ loginuser.memberId }"> 
					                							 <!-- 오류 : 다시확인 로그인한 유저로 걍 다 바뀜 -->
			                <td scope="col" style="width:100px">${ freeBoardReview.replyContent} </td>
			                <td scope="col" style="width:150px"><fmt:formatDate value="${ freeBoardReview.replyCreateDate }" pattern="yyyy-MM-dd hh:mm"/></td>
			             	<td>
			             	<input type="button" class ="btn btn-sm btn-primary" id="btnedit" value="댓글수정" >
				        	<input type="button" class ="btn btn-sm btn-primary" id="btndelete" value="댓글삭제" >
			             	</td>
			             </tr>
			            </c:forEach>
			           </tbody>
			         </table>		
			         
			         <!-- 자유게시글 댓글 보기 기능 구현 끝-->	
                 </div>
              </div>
           </div> 
          </div>
        </div>
      </div>

    
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
</body>

</html>