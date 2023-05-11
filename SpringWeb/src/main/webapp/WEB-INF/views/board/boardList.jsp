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
         <thead class="text-center">
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
            		<b>데이터가 없습니다</b>
            	</td>
            </c:if>
            
            <c:if test="${boardArr ne null and not empty boardArr}">
            <c:forEach var="board" items="${boardArr}">
            <tr>
               <td>
               		<c:out value="${board.num}"/>
               </td>
               <td>
               		<c:out value="${board.subject}"/>
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
             </tr>
            </c:forEach>
            </c:if>
            
            <!-- ---------------------  -->
         </tbody>
         <tfoot>
            <tr>
               <td colspan="3">페이지 네비</td>
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