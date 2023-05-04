<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<div class="container m2">
	<script>
		const login_check=function(){
			if(!loginF.userid.value){
				alert('아이디를 입력하세요');
				loginF.userid.focus();
				return false;
			}
			if(!loginF.pwd.value){
				alert('비밀번호를 입력하세요');
				loginF.pwd.focus();
				return false;
			}
			return true;
		}
	</script>
	<%-- request.getCookies() --%>
	<c:forEach var="ck" items="${pageContext.request.cookies}">
		<%-- ${ck.name}=> ${ck.value } --%>
		<c:if test="${ck.name eq 'uid'}">
			<c:set var="uid" value="${ck.value}"/>
		</c:if>		
	</c:forEach>
	

	<h1 style='color:green;text-align:center'>Login</h1>
	<div id="loginDiv" class="row mt-4">
	<div class="col-8 offset-2">
		<form name="loginF" action="login" method="post" onsubmit="return login_check()">
		<!--  onsubmit이벤트 핸들러는 form이 전송될때 실행된다. onsubmit에서 호출하는 함수의 반환값에 따라 전송 여부를 결정한다.
				true를 반환하면 전송하고, false를 반환하면 전송하지 않는다.
			 -->
			<table border="0" class="table">
				<tr>
					<td width="20%" class="m1"><b>아이디</b></td>
					<td width="80%" class="m2">
						<input type="text" name="userid" class="form-control"
							value="${uid}"
						 id="userid" placeholder="ID">
					</td>
				</tr>
				<tr>
					<td width="20%" class="m1"><b>비밀번호</b></td>
					<td width="80%" class="m2">
						<input type="password" name="pwd" id="pwd" class="form-control" placeholder="Password">
					</td>
				</tr>
				<tr>
					<td colspan="2" class="text-center">
						<label for="saveId">
							<input type="checkbox" name="saveId"
							<c:if test="${uid ne null and not empty uid}">checked</c:if>	
							 id="saveId">아이디 저장
						</label>						
						<button class="btn btn-outline-primary">로그인</button>
						<!-- submit버튼 -->
					</td>
				</tr>
			</table>
		</form>	
	  </div><!-- col end -->
	</div><!-- row end -->
</div>
