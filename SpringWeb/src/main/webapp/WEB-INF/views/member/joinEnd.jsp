<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="/example/error.jsp" import="user.model.*" %>
<!-- joinEnd -->
<%
	//1. post방식일때 한글 처리
	request.setCharacterEncoding("utf-8");
	//2. 사용자가 입력한 값 받기
	//3. 유효성 체크(name,userid,pwd,hp1,hp2,hp3)==>join.jsp 리다이렉트 이동
	//4. 2번값==>UserVO에 담기
	//5. UserDAO의 insertUser()호출
	//6. 그 결과 메시지 처리 및 페이지 이동 (login/login.jsp)
	
	//useBean액션을 이용해 객체를 생성하자
	//scope: page < request < session < application
	//VO객체=> page, DAO객체=> session
	
	//application: 서버가 시작하고 종료될때까지 하나의 객체를 공유한다
%>
<jsp:useBean id="user" class="user.model.UserVO" scope="page" />
<%--  UserVO user=new UserVO(); 와 동일하다  --%>
<jsp:setProperty name="user" property="*"/>
<%--
	user.setName(request.getParameter("name"));
	user.setUserid(request.getParameter("userid"));
	...
	와 동일하다 
--%>
<jsp:useBean id="userDao" class="user.model.UserDAO" scope="session" />
<% 
	if(user.getName()==null||user.getUserid()==null||user.getHp1()==null||user.getHp2()==null||user.getHp3()==null){
		response.sendRedirect("join.jsp");
		return;
	}

	int n=userDao.insertUser(user);
	String str=(n>0)?"회원가입 완료-로그인 페이지로 이동합니다":"회원 가입 실패";
	String loc=(n>0)?"../login/login.jsp":"javascript:history.back()";
%>
<%-- <jsp:forward page="list.jsp" /> --%>
<!-- forward방식으로 이동. 서버내부에서 페이지 이동이 일어나며 이때 같은 request,response를 사용한다 --> 
<script>
	alert('<%=str%>');
	location.href='<%=loc%>';
</script>









