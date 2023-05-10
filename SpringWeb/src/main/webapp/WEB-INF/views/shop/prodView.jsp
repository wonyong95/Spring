<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>

<script>
	const openPop=function(img){
		let url='resources_product_images/'+img;
		let obj=new Image();
		obj.src=url;
		let w=obj.width;
		let h=obj.height;
		console.log('w: '+w+", h: "+h);
		
		window.open(url, 'imgView',`width=${w}px, height=${h}px, left=100px, top=100px`)
	}//----------------------------
	 
	const goCart=function(){
		//pnum, oqty가 파라미터로 넘어감
		frm.action="user/cartAdd";
		frm.method="post";//나중이 post로수정하자
		frm.submit();
		
	}
	const goOrder=function(){
		//attr(속성명,값): 정적인 속성값을 설정할떄
		//prop(속성명,값): 기능적인 속성값을 설정할떄
		//opnum, oqty가 파라미터로 넘어감
		$('#frm').prop("action",'user/order');
		//$('#frm').props('method','get');
		$('#frm').submit();
	}
	const goWish=function(){
		frm.action='user/wishAdd';
		frm.submit();
	}
	
</script>


		
		<div class="row">
         <div class="col-md-12">
            <table class="table">
               <thead>
                  <tr>
                     <th colspan="2"><h3 style="text-align:center">상품 정보</h3></th>
                  </tr>
               </thead>

               <tbody>
                  <tr>
                     <td align="center" width="50%">
                      
                     <a href="#" onclick="openPop('${item.pimage1}')">
                     <img
                           src="resources/product_images/${item.pimage1}" class="img-fluid" style="width: 70%;"> <!-- </a> -->
                     </a></td>

                     <td align="left" width="50%" style="padding-left: 40px">
                        <h4>
                           <span class="badge badge-danger">${item.pspec} </span>
                        </h4> 
                        	상품번호: ${item.pnum} <br> 
                        	상품이름: ${item.pname} <br> 
                        	정가:<del>
                            <fmt:formatNumber value="${item.price}" pattern="###,###" />
                        	</del>원<br> 
                        	판매가:<span style="color: red; font-weight: bold">
                           <fmt:formatNumber value="${item.saleprice}" pattern="###,###" />
                     			</span>원<br> 
                     	    할인율:<span style="color: red">${item.percent} %</span><br>

                        POINT:<b style="color: green">[${item.point}]</b>POINT<br>

                        <!-- form시작---------- -->
                        <form name="frm" id="frm" method="POST">
                           <!-- 상품번호를 hidden으로 넘기자------ -->
                           <input type="hidden" name="pnum" value="${item.pnum}">
                           <input type="hidden" name="opnum" value="${item.pnum}">
                           <!-- -------------------------------- -->
                           <label for="oqty">상품갯수</label> 
                           <input type="number" name="pqty"
                              id="pqty" min="1" max="50" size="2" value="1">

                        </form> 
                        <!-- form end------------ -->

                        <button type="button" onclick="goCart()" class="btn btn-primary">장바구니</button>
                        <button type="button" onclick="goOrder()"
                           class="btn btn-warning">주문하기</button>
                        <button type="button" onclick="goWish()" class="btn btn-danger">위시리시트</button>
                     </td>

                  </tr>
                  <tr style="border: 0">
                     <td align="center">
                     	<img src="resources/product_images/${item.pimage2}"
                        class="img img-thumbnail" style="width: 70%;"></td>
                     <td align="center">
                     	<img src="resources/product_images/${item.pimage3}"
                        class="img img-thumbnail" style="width: 70%;"></td>
                  </tr>
                  <tr>
                     <td colspan="2">
                        <p>상품설명</p> 
                        <pre>${item.pcontents}</pre>
                     </td>
                  </tr>
               </tbody>

            </table>
         </div>
      </div><!--.row end  -->
      
      <!-- 리뷰 글쓰기 -->
      
      <!-- 리뷰 목록 -->