<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
        
<div class="row mt-5">
        <div class="col">
          <div class="card bg-default shadow">
            <div class="card-header bg-transparent border-0">
              <h3 class="text-white mb-0">���� ���� ���</h3>
            </div>
            <div class="table-responsive">
              <table class="table align-items-center table-white table-flush">
                <thead class="thead-white">
                  <tr>
					<th class="td_width_2">�̹���</th>
                    <th class="td_width_3">��ǰ��</th>
                    <th class="td_width_4">����</th>
                    <th class="td_width_4">����</th>
                    <th class="td_width_4">�հ�</th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach items="${zzimInfo}" var="zzim">
							<tr>								
								<td class="td_width_2">
									<img src="${pageContext.request.contextPath}/resources/upload/thumbnail_${zzim.thumbnail}" alt="Image">							
								</td>
								<td class="td_width_3">${zzim.itemName}</td>
								<td class="td_width_4 price_td">
									�ǸŰ� : <span class="red_color"><fmt:formatNumber value="${zzim.itemPrice}" pattern="#,### ��" /></span><br>
									����Ʈ : <span class="green_color"><fmt:formatNumber value="${zzim.point}" pattern="#,###" /></span>
								</td>
								<td class="td_width_4 table_text_align_center">
									<div class="table_text_align_center count_div">
										<input type="text" value="${zzim.itemCount}" class="count_input">	
										<button class="count_btn plus_btn btn-secondary">+</button>
										<button class="count_btn minus_btn btn-secondary">-</button>
									</div>
									<a class="count_modify_btn" data-cartId="${zzim.zzimNo}">����</a>
								</td>
								<td class="td_width_4 table_text_align_center">
									<fmt:formatNumber value="${zzim.itemPrice * zzim.itemCount}" pattern="#,### ��" />
								</td>
							</tr>
						</c:forEach>
                  
                </tbody>
              </table>
              
                            	
              </div>
            </div>
          </div>
        </div>
