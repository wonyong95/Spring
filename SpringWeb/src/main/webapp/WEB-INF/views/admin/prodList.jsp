<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
	
	<div class="container" style="text-align:center">
		<%-- ${itemList} --%>
		<div class="row">
        <div class="col-md-12">
           <h2 class="text-center m-4" style="margin:1em">::Product List [Admin Page]:::</h2>
           
           <table class="table table-striped" id="products">
              <thead>
                 <tr>
                    <th>상품번호</th>
                    <th>카테고리</th>
                    <th data-sort="string">상품명</th>
                    <th>이미지</th>
                    <th data-sort="string">가    격</th>
                    <th>수정|삭제</th>
                 </tr>
              </thead>
              <tbody>
              <!-- ------------------------ -->
				<c:choose>
					<c:when test="${itemList eq null or empty itemList}">
		              	<tr>
		              		<td colspan="6">등록된 상품이 없습니다</td>
		              	</tr>
              		</c:when>
					<c:otherwise>
						<c:forEach var="item" items="${itemList}">
		                 <tr>   
		                    <td>${item.pnum}</td>
		                    <td>
		                    ${item.upCg_name} > ${item.downCg_name}
		                    </td>
		                    <td>${item.pname}</td>
		                    <td width="15%">
		                    <a href="../resources/product_images/${item.pimage1}" target="_blank">
		                   	<img src="../resources/product_images/${item.pimage1}"
		                   	 style="width:90%;margin:auto" class="img-fluid">
		                    </a>
		                    </td>
		                    <td>
		                    <del>정  가: 
		                    <fmt:formatNumber value="${item.price}" pattern="###,###" /> 원
		                    </del> 
		                    <br>
		                    <b class="text-primary">판매가 :
		                    <fmt:formatNumber value="${item.saleprice}" pattern="###,###"/>
		                    원</b><br>
		                    <span class="badge badge-danger">${item.percent} %할인</span>
		                    </td>
		                    <td>
		                    <a href="javascript:edit('${item.pnum}')">수정</a>|
		                    <a href="#" onclick="remove('${item.pnum}')">삭제</a>
		                    </td>
		                 </tr>
		                 </c:forEach>
                 	</c:otherwise>
                 </c:choose>
            <!-- ------------------------ -->                 
              </tbody>
              
           </table>
        </div>
      </div>
		
	</div>

    