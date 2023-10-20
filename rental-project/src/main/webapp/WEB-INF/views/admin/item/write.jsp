<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!--

=========================================================
* Argon Dashboard - v1.1.2
=========================================================

* Product Page: https://www.creative-tim.com/product/argon-dashboard
* Copyright 2020 Creative Tim (https://www.creative-tim.com)
* Licensed under MIT (https://github.com/creativetimofficial/argon-dashboard/blob/master/LICENSE.md)

* Coded by Creative Tim

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software. -->
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  
  <!-- Favicon -->
  <link href="/rental-project/resources/img/brand/favicon.png" rel="icon" type="image/png">
  <!-- Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet">
  <!-- Icons -->
  <link href="/rental-project/resources/js/plugins/nucleo/css/nucleo.css" rel="stylesheet" />
  <link href="/rental-project/resources/js/plugins/@fortawesome/fontawesome-free/css/all.min.css" rel="stylesheet" />
  <!-- CSS Files -->
  <link href="/rental-project/resources/css/argon-dashboard.css?v=1.1.2" rel="stylesheet" />
      <link href="/rental-project/resources/css/navbar-top.css" rel="stylesheet" />
  
  <style>
	  .ck_warn{						
		display: none;
	    padding-top: 10px;
	    text-align: center;
	    color: #e05757;
	    font-weight: 300;    
	}
	</style>
</head>

<body class="">
  <div class="main-content">
    <!-- Navbar -->
	<jsp:include page="/WEB-INF/views/admin/modules/navbar-top.jsp" />
	<jsp:include page="/WEB-INF/views/admin/modules/navbar-top2.jsp" />
	<jsp:include page="/WEB-INF/views/admin/modules/navbar-top3.jsp" />
    <!-- End Navbar -->
    <!-- Header -->
    <jsp:include page="/WEB-INF/views/admin/modules/navbar-content.jsp" />
    <div class="container-fluid mt--7">
      <div class="row">
        <div class="col-xl-12 mb-5 mb-xl-0">
        	<div class="card bg-secondary shadow">
            <div class="card-header bg-white border-0">
              <div class="row align-items-center">
                <div class="col-8">
                  <h3 class="mb-0">상품 등록</h3>
                </div>
                <div class="col-4 text-right">
                  <a href="list" class="btn btn-success">목록</a>
                </div>
              </div>
            </div>
            <div class="card-body">
              <form action="write" method="post" enctype="multipart/form-data" id="writeForm">
              
                <!-- <h6 class="heading-small text-muted mb-4">User information</h6> -->
                <div class="pl-lg-4">
                  <div class="row">
                    <div class="col-lg-12">
                      <div class="form-group">
                        <label class="form-control-label" for="input-itemName">상품명</label>
                        <input type="text" id="input-itemName" name="itemName"  class="form-control form-control-alternative" >
                      	<span class="ck_warn itemName_warn">상품 이름을 입력해주세요.</span>
                      </div>
                    </div>
                  </div>
             
                  <div class="row">
                    <div class="col-lg-12">
                      <div class="form-group">
                        <label class="form-control-label" for="input-itemPrice">상품 가격</label>
                        <input type="number" id="input-itemPrice" name="itemPrice" class="form-control form-control-alternative">
                        <span class="ck_warn itemPrice_warn">상품 가격을 입력해주세요.</span>
                      </div>
                    </div>
                    <div class="col-lg-12">
                      <div class="form-group">
                        <label class="form-control-label" for="input-cateCode">카테고리</label>
                        <div class="cate_wrap">
							<span>대분류</span>
							<select class="cate1">
								<option selected value="none">선택</option>
							</select>
						</div>
						
						<div class="cate_wrap">
							<span>소분류</span>
							<select class="cate2" name="cateCode">
								<option selected value="none">선택</option>
							</select>
						</div>
						<span class="ck_warn cateCode_warn">카테고리를 선택해주세요.</span>
                      </div>
                    </div>
                  </div>      
                  <div class="row">
                    <div class="col-lg-12">
                      <div class="form-group">
                        <label class="form-control-label btn btn-success" for="input-itemAttach">첨부 파일</label>
                        <input type="file" id="attach" name="attach" class="form-control form-control-alternative" multiple>
                        <div id="uploadResult">
                      		<div id="result_card">
                      			<div class="imgDeleteBtn">x</div>
                      			<img src="/resources/upload/${savedFileName}" id="imageTest" alt="Image Preview">
                      		</div>
                      		
                      	</div>             
                      </div>
                    </div>
                    <div class="col-lg-12">
                      <div class="form-group">
                        <label class="form-control-label" for="input-itemStock">수량</label>
                        <input type="number" id="input-itemStock" name="itemStock" class="form-control form-control-alternative">
                      	<span class="ck_warn itemStock_warn">상품 수량을 입력해주세요.</span>
                      </div>
                    </div>
                  </div> 
                     
                  
                  <div class="row">
                    <div class="col-lg-12">
                      <div class="form-group">
                        <label class="form-control-label" for="input-itemDetail">상세 설명</label>
                        <textarea id="input-itemDetail" name="itemDetail" class="form-control form-control-alternative" rows="15" style="resize: none"></textarea>
                      	<span class="ck_warn itemDetail_warn">상품 설명을 입력해주세요.</span>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-lg-12">
                      <div class="text-right">
                        <button type="submit" class ="btn btn-success" id="registerBtn">상품 등록</button> 
				        <button type="button" class ="btn btn-success" id="btnCancel">취소</button>
                      </div>
                    </div>
                  </div>
                </div>
                
              </form>
            </div>
          </div>
        </div>
      </div>
      <!-- Footer -->
                 <jsp:include page="/WEB-INF/views/modules/footer.jsp" /> 

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
  
  <script>
  $(function() {
	    function readURL(input) {
	      if (input.files && input.files[0]) {
	      var reader = new FileReader();
	  
	          reader.onload = function (e) {
	              $('#imageTest').attr('src', e.target.result);
	          }
	  
	          reader.readAsDataURL(input.files[0]);
	      }
	    }
	  
	    $("#attach").change(function(){
	        readURL(this);
	    });
	    
	    });
    
    let cateList = JSON.parse('${cateList}');
    
    let cate1Array = new Array();
	let cate2Array = new Array();
	let cate1Obj = new Object();
	let cate2Obj = new Object();
	
	let cateSelect1 = $(".cate1");		
	let cateSelect2 = $(".cate2");


	/* 카테고리 배열 초기화 메서드 */
	function makeCateArray(obj,array,cateList, tier){
		for(let i = 0; i < cateList.length; i++){
			if(cateList[i].tier === tier){
				obj = new Object();
				
				obj.cateName = cateList[i].cateName;
				obj.cateCode = cateList[i].cateCode;
				obj.cateParent = cateList[i].cateParent;
				
				array.push(obj);				
				
			}
		}
	}
	
	makeCateArray(cate1Obj,cate1Array,cateList,1);
	makeCateArray(cate2Obj,cate2Array,cateList,2);
	
	for(let i = 0; i < cate1Array.length; i++){
		cateSelect1.append("<option value='"+cate1Array[i].cateCode+"'>" + cate1Array[i].cateName + "</option>");
	}
	
	/* 중분류 <option> 태그 */
	$(cateSelect1).on("change",function(){
		
		let selectVal1 = $(this).find("option:selected").val();	
		
		cateSelect2.children().remove();
		
		cateSelect2.append("<option value='none'>선택</option>");
		
		for(let i = 0; i < cate2Array.length; i++){
			if(selectVal1 === cate2Array[i].cateParent){
				cateSelect2.append("<option value='"+cate2Array[i].cateCode+"'>" + cate2Array[i].cateName + "</option>");	
			}
		}// for
		
	});
		
    </script>
    
    <script>
  let writeForm = $("#writeForm")
	
  /* 취소 버튼 */
  $('#btnCancel').on('click', function(evnet) {
			location.href = 'list';	
		})

  /* 상품 등록 버튼 */
  $("#RegisterBtn").on("click",function(e){
  	
  	e.preventDefault();
	function checkResult(){
		
		alert("상품을 등록하였습니다.");
		
	}
  	
	let itemNameCk = false;
	let cateCodeCk = false;
	let itemPriceCk = false;
	let itemStockCk = false;
	
	let itemName = $("input[name='itemName']").val();
	let cateCode = $("input[name='cateCode']").val();
	let itemPrice = $("input[name='itemPrice']").val();
	let itemStock = $("input[name='itemStock']").val();

	
	if(itemName){
		$(".itemName_warn").css('display','none');
		itemNameCk = true;
	} else {
		$(".itemName_warn").css('display','block');
		itemNameCk = false;
	}
	
	if(cateCode != 'none'){
		$(".cateCode_warn").css('display','none');
		cateCodeCk = true;
	} else {
		$(".cateCode_warn").css('display','block');
		cateCodeCk = false;
	}
	
	if(itemPrice != 0){
		$(".itemPrice_warn").css('display','none');
		itempriceCk = true;
	} else {
		$(".itemPrice_warn").css('display','block');
		itempriceCk = false;
	}	
	
	if(itemStock != 0){
		$(".itemStock_warn").css('display','none');
		itemStock = true;
	} else {
		$(".itemStock_warn").css('display','block');
		itemStock = false;
	}	

	if(itemNameCk && cateCodeCk && itemPriceCk && itemStockCk ){
		//alert('통과');
		writeForm.submit();
	} else {
		return false;
	}
	
  	
  	
  });
  
  
  </script>
  
  
</body>

</html>