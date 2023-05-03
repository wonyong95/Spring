<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="/example/error.jsp" import="user.model.*" %>
<!-- joinEnd -->
<%
	//1. post방식일때 한글 처리
	request.setCharacterEncoding("utf-8");
	
	//2. 사용자가 입력한 값 받기
	String name=request.getParameter("name");
	String userid=request.getParameter("userid");
	String pwd=request.getParameter("pwd");
	String hp1=request.getParameter("hp1");
	String hp2=request.getParameter("hp2");
	String hp3=request.getParameter("hp3");
	String post=request.getParameter("post");
	String addr1=request.getParameter("addr1");
	String addr2=request.getParameter("addr2");
	//3. 유효성 체크(name,userid,pwd,hp1,hp2,hp3)==>join.jsp 리다이렉트 이동
	if(name==null||userid==null||pwd==null||hp1==null||hp2==null||hp3==null){
		response.sendRedirect("join.jsp");
		return;
	}
	
	//4. 2번값==>UserVO에 담기
    UserVO user=new UserVO(0,name,userid,pwd,hp1,hp2,hp3,post,addr1,addr2,null,1000,0,null);			
	
	//5. UserDAO의 insertUser()호출
	UserDAO dao=new UserDAO();
	int n=dao.insertUser(user);
	//6. 그 결과 메시지 처리 및 페이지 이동 (login/login.jsp)
	String str=(n>0)?"회원가입 처리 완료-로그인 페이지로 이동합니다":"회원가입 실패";
	String loc=(n>0)?"../login/login.jsp":"javascript:history.back()";
%>
<script>
	alert('<%=str%>');
	location.href='<%=loc%>';
</script>



