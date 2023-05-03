<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 로그인 체크 모듈 include => 소스를 포함시키는 방식으로 ======= -->
<%@ include file="/login/loginCheckModule.jsp" %>
<!-- -------------------------------------------------- -->
<jsp:include page="/top.jsp"/>
    <style>
    	.myPageTable{
    		width: 70%;
    		margin: 1em auto;
    	}
    	td{
    		padding:7px;
    	}
    	td:last-child{
    		text-align:left;
    		
    	}
    </style>
    <div class="container m2">
    	<h1>MyPage-<%=member.getName() %> 님 정보</h1>
    	<p>회원 인증 페이지 - 로그인 해야 들어올 수 있는 페이지입니다</p>
    	<table border="1" class="myPageTable">
    		<tr>
    			<td width="25%">아이디</td>
    			<td width="75%"><b><%=member.getUserid() %></b></td>
    		</tr>
    		<tr>
    			<td>연락처</td>
    			<td><b><%=member.getAllHp()%></b></td>
    		</tr>
    		<tr>
    			<td>주소</td>
    			<td><b><%=member.getAllAddr()%></b></td>
    		</tr>
    		<tr>
    			<td>마일리지</td>
    			<td><b><%=member.getMileage()%>점</b></td>
    		</tr>
    		<tr>
    			<td>회원상태</td>
    			<td><b><%=member.getMstateStr()%></b></td>
    		</tr>
    		<tr>
    			<td colspan="2">
    				<form name='f' method='post' action='modify.jsp'>
    					<input type="hidden" name="idx" value="<%=member.getIdx()%>">
    					<button>정보수정|탈퇴</button>
    				</form>    				
    			</td>
    		</tr>
    	</table>
    	
    </div>
<jsp:include page="/foot.jsp"/>    