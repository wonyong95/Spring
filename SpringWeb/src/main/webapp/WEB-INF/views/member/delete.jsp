<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	//[1] post방식이 아니면 페이지 돌려보내기
	
	//[2] 회원번호 받기(idx)	
	//[3] 유효성 체크
	//[4] UserDAO빈 생성
	//[5] deleteUser(idx) 호출=> mstate값을 -2로 수정처리하는 로직
	//[6] 그 결과 메시지 처리 및 이동 경로 처리 
--%>
<%
	String method=request.getMethod();
	String idxStr=request.getParameter("idx");
	if(!method.equalsIgnoreCase("post")||idxStr==null||idxStr.trim().isEmpty()){
		%>
		<script>
			alert('잘못 들어온 경로입니다');
			history.go(-1);
		</script> 
		<%
		return;
	}
	int idx=Integer.parseInt(idxStr.trim());
 %>
 <jsp:useBean id="userDao" class="user.model.UserDAO" scope="session"/>
 <%
	int n=userDao.deleteUser(idx);
 	String str=(n>0)?"회원정보 삭제 성공":"삭제 실패";
 	String loc=(n>0)?"list.jsp":"../main.jsp";
 %>
 <script>
 	alert('<%=str%>');
 	location.href='<%=loc%>';
 </script>
 
 
 
 
 
 
 
 
 
 
 