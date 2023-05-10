<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class = "row">
   <div class = "col-md-12">
      <h2 class = "badge badge-warning">${pspec}</h2>
   </div>
</div>

<div class = "row">
	<c:if test="${pList eq null or empty pList }">
		<div class="col-md-3">
			<h4>상품 준비 중</h4>
		</div>
	</c:if>
	<c:if test="${pList ne null and not empty pList}" >
		<!-- ------------------------- -->
		<c:forEach var="item" items="${pList}">
	      <div class = "col-md-3" style="text-align:center">
	         <a href="prodDetail?pnum=${item.pnum}">
			<c:if test="${item.pimage1 ne null}">
	         <img src = "resources/product_images/${item.pimage1}"
	         	style="width:90%;margin:auto;height:50%"
	          class = "img-fluid">
	        </c:if>
	        <c:if test="${item.pimage1 eq null}">
	         <img src = "resources/product_images/noimage.png" class = "img-fluid"
	         style = "height:220px">
			</c:if>
	         </a>
	         <br><br>
	         <h4>${item.pname}</h4>
	         <del> 
				<fmt:formatNumber value="${item.price}" pattern="###,###"/>
	         </del> 원 <br>
	         <span style="color:blue;font-weight:bold"> 
	         	<fmt:formatNumber value="${item.saleprice}" pattern="###,###"/>
	         </span> 원 <br>
	         <span class = "badge label-danger">${item.percent} %</span> <br>
	         <span class = "badge label-success">${item.point}</span> POINT <br>
	      </div> <!--  col-md-3 end -->
	      </c:forEach>
	      <!-- ----------------------------------------- -->
	    </c:if>  
      	</div> <!--  row end -->