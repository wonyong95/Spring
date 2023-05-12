<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- taglib functions -------------------------------------------- -->
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- ------------------------------------------------------------- -->    
<%--
jstl 
- core
- fmt
- function
 --%>    
    
	<h2 class="text-center my-3">Spring Board 내용 보기</h2>	
		<%-- ${board} --%>
		
		<c:if test="${board eq null}">
			<div class="alert alert-danger">
				<h3>해당 글은 없습니다.</h3>
			</div>
		</c:if>
		<c:if test="${board ne null}">
		<table class="table mt-4">
            <tr>
               <td width="20%">글번호</td>
               <td width="30%">
				<c:out value="${board.num}"/>
               </td>
               <td width="20%">작성일</td>
               <td width="30%">
				<c:out value="${board.wdate}"/>
               </td>
            </tr>
            <tr>
               <td width="20%">글쓴이</td>
               <td width="30%">
               	<c:out value="${board.userid}"/>
               </td>
               <td width="20%">조회수</td>
               <td width="30%">
               <c:out value="${board.readnum}"/>
               </td>
            </tr>

            <tr>
               <td width="20%">제목</td>
               <td colspan="3" align="left">
               	<c:out value="${board.subject}"/>
               </td>
            </tr>
            <tr height="60">
               <td width="20%">글내용</td>
               <td colspan="3" align="left">
               		${board.content}             	
               </td>
            </tr>
            <tr>
               <td width="20%">첨부파일</td>
               <td colspan="3">&nbsp; 
                     <!--  첨부파일이 있다면 -->
                <c:if test="${board.filename ne null}">     
	            	<%-- <a href="${pageContext.request.contextPath}/resources/board_upload/<c:out value="${board.filename}"/>" download> --%>
	            	
	            	<a href="#" onclick="goDown()">
	            	<c:out value="${board.originFilename}"/>
	            	</a>
	            	[ <c:out value="${board.filesize}"/> ]bytes
            	</c:if>
            	<!-- 이미지 파일일 경우 썸네일 이미지  -->
            	<!-- 파일 확장자 검사를 위해 모두 소문자로 바꾸자 -->
            	<c:set var="fname" value="${fn:toLowerCase(board.filename)}"/>
            	
				<c:if test="${fn:endsWith(fname,'.jpg') or fn:endsWith(fname,'.png') or fn:endsWith(fname,'.gif')}">
				<!-- /board/view/5 -->
					<img class="img img-thumbnail" src="../../resources/board_upload/<c:out value="${board.filename}"/>" style="width:80px">
				</c:if>                      	
               </td>
            </tr>
            <tr>
               <td colspan="4" align=center><a href="../write">글쓰기</a>| <a
                  href="../list">목록</a>| 
                  <a href="#" onclick="go('edit')">편집</a>| <a
                  href="#" onclick="go('del')">삭제</a>| 
                  <a href="javascript:goRe()">답변</a></td>
            </tr>
         </table>
         </c:if>
         <!-- 편집 또는 삭제를 위한 form ------------------------- -->
         <form name="frm" id="frm">
         	<input type="hidden" name="num" value="<c:out value="${board.num}"/>">
         	<input type="hidden" name="mode">
	         <div class="row" id='divPasswd' style="display:none">
	         	<div class="col-3 offset-1 text-right mr-2">
	         		<label for="passwd">글 비밀번호</label>
	         	</div>
	     		<div class="col-4">
	     			<input type="password" name="passwd" id="passwd" class="form-control" placeholder="Password" required="required">
	     		</div>
	     		<div class="col-3">
	     			<button class="btn btn-outline-primary" id='btn'></button>
	     		</div>    
	         </div>
         </form>
         <!-- --------------------------------------------- -->
         <!-- 답변달기 form----------------------------------- -->
         <form name="reF" id="reF" action="../rewrite" method="post">
         	<!-- hidden으로 부모글의 글번호(num) 제목(subject)을 넘기자 -->
         	<input type="hidden" name="num" value="<c:out value="${board.num}"/>">
         	<input type="hidden" name="subject" value="<c:out value="${board.subject}"/>">
         </form>
         <!-- ------------------------------------------------ -->
         
	         <!-- 파일 다운로드를 위한 form------------------------------------ -->
		<form name="fileF" id="fileF" method="POST" action="../../fileDown">
			<input type="hidden" name="fname" value="<c:out value="${board.filename}"/>">
			<input type="hidden" name="origin_fname" value="<c:out value="${board.originFilename}"/>">
		</form>
	
<script>
	//답변 글쓰기 폼으로 이동
	const goRe=function(){
		reF.submit();
	}//---------------------------------
	
	//파일 다운로드 처리
	const goDown=function(){
		fileF.submit();
	}//----------------
	
	const go=function(mode){
		//alert(mode);
		if(mode=='del'){
			$('#btn').text('글삭제');
			$('#passwd').focus();
			frm.mode='delete';
			frm.method='post';
			frm.action='../delete';
		}else if(mode=='edit'){
			$('#btn').text('글수정');
			$('#passwd').focus();
			frm.mode='edit';
			frm.method='post';
			frm.action='../edit';
		}
		$('#divPasswd').show(1000);
	}
</script>         
         
         
         
         
         
         
         
         
         
         
         
         