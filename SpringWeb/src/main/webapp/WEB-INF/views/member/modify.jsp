<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="user.model.*"   %>
<jsp:include page="/top.jsp" />
<!-- js파일 참조-------------------------------- -->
<script type="text/javascript" src="../js/userCheck.js"></script>
<!-- ------------------------------------------ -->
<% 

	String method=request.getMethod();
	if(!method.equalsIgnoreCase("post")){
		%>
		<script>
			alert('잘못 들어온 경로입니다');
			history.back();
		</script>
		<%
		return;
	}

	String idxStr=request.getParameter("idx");
	UserVO member=(UserVO)session.getAttribute("loginUser");
	int mstate=0;
	if(idxStr==null||idxStr.trim().isEmpty()){
		if(member==null){
			response.sendRedirect("list.jsp");
			return;
		}
		idxStr=member.getIdx()+"";
	}
	int idx=Integer.parseInt(idxStr.trim());
	mstate=member.getMstate();//관리자 여부 체크하기 위해(9)
%>
<jsp:useBean id="userDao" class="user.model.UserDAO" scope="session"/>
<%
	UserVO user=userDao.selectUserByIdx(idx);
	if(user==null){
		%>
		<script>
			alert('해당회원은 없습니다');
			history.go(-1);
			//history.back();
		</script>
		<%
		return;
	}
	//out.println(user);
%>

<div class="container">
	<h1>회원 정보 수정</h1>
	<form name="mf" action="modifyEnd.jsp" method="post">
	<!-- 로그인한 사람이 관리자면 9값을 갖고 일반 user면 0을 갖는다 -->	
	<input type="hidden" name="mode" value="<%=mstate%>">
	<!--  -->
	<table id="userTable" border="1">
		<tr>
			<td width="20%" class="m1"><b>회원번호</b></td>
			<td width="80%" class="m2">
			<input type="text" name="idx" value="<%=user.getIdx()%>" id="idx"
			 placeholder="Idx" readonly>
			</td>
		</tr>
		<tr>
			<td width="20%" class="m1"><b>이  름</b></td>
			<td width="80%" class="m2">
			<input type="text" name="name" id="name"  value="<%=user.getName()%>"
			 placeholder="Name">
			<br>
			<span class="ck">*이름은 한글만 가능해요</span>
			</td>
		</tr>
		<tr>
			<td width="20%" class="m1"><b>아이디</b></td>
			<td width="80%" class="m2">
			<input type="text" name="userid" id="userid" readonly  value="<%=user.getUserid()%>"
			 placeholder="User ID">
			 <button type="button" onclick="open_idcheck()">아이디 중복 체크</button>
			<br>
			<span class="ck">*아이디는 영문자,숫자,_,! 4~8자리만 사용 가능해요</span>
			</td>
		</tr>
		<% 
		if(mstate!=9){//일반회원일때만 비밀번호 수정-관리자가 비밀번호 수정 못하게
		%>
		<tr>
			<td width="20%" class="m1"><b>비밀번호</b></td>
			<td width="80%" class="m2">
			<input type="password" name="pwd" id="pwd"
			 placeholder="Password">
			<br>
			<span class="ck">*비밀번호는 문자,숫자,!,.포함해서 4~8자 이내</span>
			</td>
		</tr>
		
		<tr>
			<td width="20%" class="m1"><b>비밀번호 확인</b></td>
			<td width="80%" class="m2">
			<input type="password" name="pwd2" id="pwd2"
			 placeholder="Re Password">
			<br>
			</td>
		</tr>
		<%} %>
		<tr>
			<td width="20%" class="m1"><b>연락처</b></td>
			<td width="80%" class="m2">
			<input type="text" name="hp1" id="hp1" placeholder="HP1" maxlength="3" value="<%=user.getHp1() %>">-
			<input type="text" name="hp2" id="hp2" placeholder="HP2" maxlength="4" value="<%=user.getHp2() %>">-
			<input type="text" name="hp3" id="hp3" placeholder="HP3" maxlength="4" value="<%=user.getHp3() %>">
			<br>
			<span class="ck">*앞자리(010|011)중에 하나-(숫자 3~4자리)-(숫자 4자리)
			만 가능해요
			</span>
			</td>
		</tr>
		<tr>
			<td width="20%" class="m1"><b>우편번호</b></td>
			<td width="80%" class="m2">
			<input type="text" name="post" id="post"  value="<%=user.getPost()%>"
			 placeholder="Post" maxlength="5">
			 <button type="button" onclick="">우편번호 찾기</button>
			<br>			
			</td>
		</tr>
		
		<tr>
			<td width="20%" class="m1"><b>주  소</b></td>
			<td width="80%" class="m2">
			<input type="text" name="addr1" id="addr1"  value="<%=user.getAddr1() %>"
			 placeholder="Address1" style="margin-bottom:5px;">
			<br>
			<input type="text" name="addr2" id="addr2"  value="<%=user.getAddr2() %>"
			 placeholder="Address2">
			</td>
		</tr>
		<tr>
			<td width="20%" class="m1"><b>마일리지</b></td>
			<td width="80%" class="m2"> 
			<%if(mstate!=9){//로그인한 사람이 관리자가 아니라면
					//로그인 처리후 수정 예정
				%>
				<%=user.getMileage() %>점
			<%}else{
			//관리자일 경우 마일리지 수정 가능하게
			%>
				<input type="text" name="mileage" id="mileage"  value="<%=user.getMileage()%>"
				 placeholder="Mileage">
			 <%} %>
			<br>			
			</td>
		</tr>
		<tr>
			<td width="20%" class="m1"><b>회원상태</b></td>
			<td width="80%" class="m2">
			<span class='mstate<%=user.getMstate()%>'>
			<%=user.getMstateStr()%>
			</span><br>
			<input type="radio" name="mstate" value="0" class='radio_btn'  <%=(user.getMstate()>=0)?"checked":"" %> >활동 회원
			<input type="radio" name="mstate" value="-1" class='radio_btn' <%=(user.getMstate()==-1)?"checked":"" %> >정지 회원
			<input type="radio" name="mstate" value="-2"  class='radio_btn' <%=(user.getMstate()==-2)?"checked":"" %> >탈퇴 회원
			<%
			if(mstate==9){//관리자라면
			%>
			<input type="radio" name="mstate" value="9"  class='radio_btn' <%=(user.getMstate()==9)?"checked":"" %> >관리자
			<% 
			}
			%>
			<br>			
			</td>
		</tr>
		<tr>
			<td colspan="2" class="m2" style="text-align:center">
				<button type="button" onclick="member_check()">수정하기</button>
				<button type="reset">다시쓰기</button>
			</td>
		</tr>
	
	</table>
	
	</form>
</div>
<jsp:include page="/foot.jsp" />

