<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>


<script>
	
	function check(){
		if(!$('#upCg_code').val()){
			alert('상위 카테고리를 선택하세요');
			$('#upCg_code').focus();
			return false;
		}
		if(!$('#downCg_code').val()){
			alert('하위 카테고리를 선택하세요');
			$('#downCg_code').focus();
			return false;
		}
		if(!$('#pname').val()){
			alert('상품명을 입력하세요');
			$('#pname').focus();
			return false;
		}
		
		let $price=$('#price').val();
		let pattern=/^[0-9]+$/
		if(!pattern.test($('#pqty').val())){
			alert('수량은 숫자로 입력해야 해요');
			$('#pqty').select();
			return false;
		}
		if(!pattern.test($price)){
			alert('정가는 숫자로 입력해야 해요');
			$('#price').select();
			return false;
		}
		if(!pattern.test($('#saleprice').val())){
			alert('판매가는 숫자로 입력해야 해요');
			$('#saleprice').select();
			return false;
		}
		if(!pattern.test($('#point').val())){
			alert('포인트는 숫자로 입력해야 해요');
			$('#point').select();
			return false;
		}
		return true;
	}
</script>

   <div class="py-5">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
           <h1 class="text-center">상품 등록[Admin Page]</h1>
           
           <form name="prodF" id="prodF" method="POST"  enctype="multipart/form-data"
            action="prodInput" onsubmit="return check()">
            <!-- 파일업로드시: enctype="multipart/form-data"-->
            <table class="table table-condensed table-bordered mt-4">
               <thead>
                  <tr>
                     <th colspan="2" class="text-center">
                        <h3>:::Product Register:::</h3>
                     </th>
                  </tr>
               </thead>
               <tbody>
                  <tr>
                     <td width="20%"><b>카테고리</b></td>
                     <td width="80%">
                     <select name="upCg_code" id="upCg_code"
                        onchange="">
                           <option value="">::상위 카테고리::</option>
                           
                              <option value="1">컴퓨터.디지털.가전</option>
                              <!-- <option value="2">패션의류.잡화.뷰티</option> -->
                     </select> 
                     <span id="selectDcg"> 
                     <select name="downCg_code" id="downCg_code"
                        onchange="">
                           <option value="">::하위 카테고리::</option>
                           
                              <option value="1">노트북</option>
                              <option value="2">모니터</option>
							  <option value="3">주방가전</option>
                     </select>
                                              
                     </span></td>
                  </tr>
                  
                  <tr>
                     <td width="20%"><b>상품명</b></td>
                     <td width="80%"><input type="text" name="pname" id="pname">
                     <span style="color: red"> 
                     </span>
               		 </td>
                  </tr>
                  <tr>
                     <td width="20%"><b>제조사</b></td>
                     <td width="80%"><input type="text" name="pcompany"
                        id="pcompany"></td>
                  </tr>
                  <tr>
                     <td width="20%"><b>상품스펙</b></td>
                     <td width="80%"><select name="pspec" id="pspec">
                           <option value="NEW" selected>NEW</option>
                           <option value="HIT">HIT</option>
                           <option value="BEST">BEST</option>
                     </select></td>
                  </tr>
                  <tr>
                     <td>상품이미지</td>
                     <td>
                     <input type="file" name="pimage" accept="images/*"><br> 
                     <input type="file" name="pimage" accept="images/*"><br> 
                     <input type="file" name="pimage" accept="images/*"><br>
                  </td>
                  </tr>

                  <tr>
                     <td width="20%"><b>상품수량</b></td>
                     <td width="80%"><input type="number" name="pqty" id="pqty">
                        개
               <span style="color: red"> 
               </span></td>
                        
                  </tr>
                  <tr>
                     <td width="20%"><b>상품정가</b></td>
                     <td width="80%">
                     <input type="text" name="price" id="price">
      
                        원
                     <span style="color: red"> 
                     
               </span>         
                        </td>
                  </tr>
                  <tr>
                     <td width="20%"><b>상품판매가</b></td>
                     <td width="80%"><input type="text" name="saleprice"
                        id="saleprice"> 원
                        <span style="color: red"> 
                     
               </span>   
                        
                        </td>
                  </tr>
                  <tr>
                     <td width="20%"><b>상품설명</b></td>
                     <td width="80%"><textarea name="pcontents" id="pcontents"
                           rows="5" cols="60"></textarea></td>
                  </tr>
                  <tr>
                     <td width="20%"><b>포인트</b></td>
                     <td width="80%"><input type="number" name="point" id="point">
                        POINT</td>
                  </tr>
                  <tr>
                     <td colspan="2" class="text-center">
                        <button class="btn btn-outline-success">상품등록</button>
                     </td>
                  </tr>
               </tbody>
            </table>
         </form>
           
        </div>
      </div>
    </div>
  </div>
    