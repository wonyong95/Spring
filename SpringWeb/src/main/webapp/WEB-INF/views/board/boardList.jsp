<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row my-3">
   <div class="col-12 text-center">
      <h2>Spring Board</h2>
      <p>
         <a href="write">글쓰기</a>
         |
         <a href="list">글목록</a>
      </p>
   </div>
</div>
<div class="row">
   <div class='col-10 offset-1'>
      <table class="table table-condensed table-striped">
         <thead>
            <th width="10%">글번호</th>
            <th width="40%">제목</th>
            <th width="20%">글쓴이</th>
            <th width="20%">날짜</th>
            <th width="10%">조회수</th>
         </thead>
         <tbody>
            <!-- ---------------------  -->
            <c:if test="${boardArr eq null or empty boardArr}">
               <td colspan="5">
                  <b>데이터가 없습니다.</b>
               </td>
            </c:if>
            <c:if test="${boardArr ne null and not empty boardArr}">
               <c:forEach var="board" items="${boardArr}">
                  <tr>
                     <td>
                        <c:out value="${board.num}"/>
                        
                     </td>
                     <td>
                     <!-- 답변 레벨에 따라 들여쓰기 -->
                     <c:forEach var="k" begin="0" end="${board.lev}">
                     	&nbsp;&nbsp;&nbsp;
                     </c:forEach>
                     <c:if test="${board.lev>0}">
                     	<img src="../images/re.png">
                     </c:if>
                     <!-- 글 제목  -->
						                     
                     
                     <!-- prodDetail?pnum=1 ==>@RequestParam("pnum")
                              prodDetail/1
                              
                              board/list
                              board/write
                              board/view/글번호  => @PathVariable("num")
                        
                         -->
                     
                        <a href="view/<c:out value="${board.num}"/>"><c:out value="${board.subject}"/></a>
                        <!-- 첨부파일 여부  -->
                        <c:if test="${board.filesize>0}">
                           <span class="float-right">
                              <img src="../images/attach.jpg" style="width:20px" title="<c:out value="${board.originFilename}"/>">
                           </span>
                        </c:if>
                        <!-- 첨부파일 여부  -->
                     </td>
                     <td>
                        <c:out value="${board.userid}"/>
                     </td>
                     <td>
                        <c:out value="${board.wdate}"/>
                     </td>
                     <td>
                        <c:out value="${board.readnum}"/>
                     </td>
                  <tr>
               </c:forEach>
            </c:if>
            <!-- ---------------------  -->
         </tbody>
         <tfoot>
            <tr>
               <td colspan="3" class="text-center">
               <ul class="pagination">
					<c:forEach var="i" begin="1" end="${pageCount}">				
						<li class="page-item <c:if test="${i eq cpage}">active</c:if>">
						<a class="page-link" href="list?cpage=<c:out value="${i}"/>">
							<c:out value="${i}"/>
						</a>
						</li>						
					</c:forEach>
				</ul>
				</td>
               <td colspan="2">총 게시글 수 :  
                  <span class="text-primary">${totalCount} 개</span>
                  <br>
                  <span class="text-danger">cpage</span>
                  / 페이지수 pages
               </td>
            </tr>
         </tfoot>
      </table>
   </div>
</div>