<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- ckeditor 4 cdn ------------------------------------------------------- -->
<script src="https://cdn.ckeditor.com/4.17.2/standard/ckeditor.js"></script>    
<!-- ---------------------------------------------------------------------- -->
<script>
   $(function(){
      CKEDITOR.replace('bcontent');
      $('#bf').submit(function(){
         
      })//submit------------
      
   })//$() end----------------
</script>    

<div class="row">

<div align="center" id="bbs" class="col-md-8 offset-md-2 my-4">
   <h2>Spring Board 답변 글쓰기</h2>
   <p>
      <a href="write">글쓰기</a>| <a
         href="list">글목록</a>
      <p>
  <!--파일 업로드시
   method: POST
   enctype: multipart/form-data  -->   

   <form name="bf" id="bf" role="form" action="write" method="post" enctype="multipart/form-data" >
   <!-- hidden data---------------------------------  -->
   <input type="hidden" name="mode" value="rewrite">
   <!-- 원본글쓰기: mode=> write
       답변글쓰기: mode=> rewrite
          글수정:: mode=> edit
    -->
    <input type="hidden" name="num" value="<c:out value="${num}"/>" >
    <!-- 부모글의 글 번호를 hidden으로 넘긴다. -->
   <!-- -------------------------------------------- -->       
    <table class="table">
       <tr>
          <td style="width:20%"><b>제목</b></td>
          <td style="width:80%">
          <input type="text" name="subject" value="[RE]<c:out value="${subject }" />" id="subject" class="form-control">
          </td>
       </tr>
       <tr>
          <td style="width:20%"><b>글쓴이</b></td>
          <td style="width:80%">
          <input type="text" name="userid" id="userid" value="${loginUser.userid}"
           class="form-control">
          </td>
       </tr>       
       <tr>
          <td style="width:20%"><b>글내용</b></td>
          <td style="width:80%">
          <textarea name="content" id="bcontent" rows="10" cols="50"
                  class="form-control"></textarea>
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
         <td style="width: 20%"><b>첨부파일</b></td>
         <td style="width: 80%">
         <input type="file" name="mfilename"
            id="filename" class="form-control"></td>
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