<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<script src="https://cdn.ckeditor.com/4.17.2/standard/ckeditor.js"></script>

<script>
   $(function(){
      CKEDITOR.replace('bcontent');
      $('#bf').submit(function(){
         // 제목
         if(!$('#subject').val()){
            alert('제목을 입력하세요');
            $('#subject').focus();
            return false;
         }
         // 작성자 아이디
         if(!$('#userid').val()){
            alert('글쓴이를 입력하세요');
            $('#userid').focus();
            return false;
         }
         
         // ckeditor 검사
         if(!CKEDITOR.instances.bcontent.getData()){
            alert('내용를 입력하세요');
            CKEDITOR.instances.bcontent.focus();
            return false;
         }
         
         
         // 비밀번호
         if(!$('#bpwd').val()){
            alert('비밀번호를 입력하세요');
            $('#bpwd').focus();
            return false;
         }
         return true;
         
         
         
         // return true
      })// submit() end ----
   }) // $() end-----


</script>

<div class="row">
   <div align="center" id="bbs" class="col-md-8 offset-md-2 my-4">
      <h2>Spring Board</h2>
      <p>
         <a href="write">글쓰기</a>| 
         <a href="list">글목록</a>
         <p>
            <!--  파일 업로드시
               method: POST
               enctype: multipart/form-data  -->   
   
      <form name="bf" id="bf" role="form" action="write" method="post" enctype="multipart/form-data">
      
            <!-- hidden data  -->
            <input type="hidden" name="mode" value="write">
            
            <!-- 원본 글쓰기 : mode => write  
                답변 글쓰기 : mode => rewrite
                글 수정  : mode => edit       -->
            <!-- hidden data  -->
            
         <table class="table">
            <tr>
               <td style="width:20%">
                  <b>제목</b>
               </td>
               <td style="width:80%">
                  <input type="text" name="subject" id="subject" class="form-control">
               </td>
            </tr>
            <tr>
               <td style="width:20%"><b>글쓴이</b></td>
               <td style="width:80%">
                  <input type="text" name="userid" id="userid" value="${loginUser.userid}" 
                     <c:if test='${loginUser ne null }'> readonly  </c:if> 
                     class="form-control">
               </td>
            </tr>       
            <tr>
               <td style="width:20%"><b>글내용</b></td>
               <td style="width:80%">
                  <textarea name="bcontent" id="bcontent" rows="10" cols="50" class="form-control">
                  </textarea>
               </td>
            </tr>
            <tr>
               <td style="width:20%"><b>비밀번호</b></td>
               <td style="width:80%">
                  <div class="col-md-5">
                     <input type="password" name="passwd" id="bpwd" class="form-control">
                  </div>
               </td>
           </tr>
           <tr>
              <td style="width: 20%">
                 <b>첨부파일</b>
              </td>
              <td style="width: 80%">
                  <input type="file" name="mfilename" id="filename" class="form-control">
               </td>
            </tr>
            <tr>
               <td colspan="2" class="text-center">
                  <button type="submit" id="btnWrite" class="btn btn-success">글쓰기</button>
                  <button type="reset" id="btnReset" class="btn btn-warning">다시쓰기</button>
               </td>
            </tr>
         </table>
      </form>       
   </div><!-- .col end-->
</div><!-- .row end-->