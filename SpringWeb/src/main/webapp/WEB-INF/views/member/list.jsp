<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="user.model.*,java.util.*"   %>
<!-- 관리자여부를 체크하는 모듈 include----------------------------- -->
<%@ include file="/login/adminCheckModule.jsp" %>
<!-- -------------------------------------------------------- -->
<jsp:include page="/top.jsp"/>
<%-- <jsp:useBean id="user" class="user.model.UserVO" scope="application" />     --%>
    <div class="container">
    	<h1>회원 목록 페이지 [Admin Page]</h1>
    	<table id="userTable" border="1">
    		<tr>
    			<th class="m3">번호</th>
    			<th class="m3">이름</th>
    			<th class="m3">아이디</th>
    			<th class="m3">연락처</th>
    			<th class="m3">회원상태</th>
    			<th class="m3">수정|삭제</th>
    		</tr>
    		<!-- --------------- -->
    		<jsp:useBean id="userDao" class="user.model.UserDAO" scope="session" />
    		<%
    			//UserDAO userDao=new UserDAO();
    			List<UserVO> arr=userDao.listUser();
    			if(arr==null||arr.size()==0){
    				out.println("<tr><td colspan='6'>데이터가 없습니다</td></tr>");
    			}else{
 					for(UserVO vo:arr){   			
    		%>
			    		<tr>
			    			<td><%=vo.getIdx()%></td>
			    			<td><%=vo.getName()%></td>
			    			<td><%=vo.getUserid()%></td>
			    			<td><%=vo.getAllHp()%></td>
			    			<td class="mstate<%=vo.getMstate()%>"><%=vo.getMstateStr()%></td>
			    			<td>
			    			<a href="#" onclick="userEdit('<%=vo.getIdx()%>')">수정</a>|
			    			<a href="javascript:userDel('<%=vo.getIdx()%>')">삭제</a>
			    			</td>
			    		</tr>
    		<% 		}//for-----
    			}//else---------------------
    		%>
    		<!-- --------------- -->
    	</table>
    	<!-- 수정 또는 삭제 처리를 위한 form -->
    	<form name="userF" method="post">
    		<!-- hidden field --------------------- -->
    		<input type="hidden" name="idx">
    		<!-- --------------------------------- -->
    	</form>
    	
    </div>
    <script>
		function userEdit(num){
			userF.idx.value=num;
			userF.action='modify.jsp';
			userF.submit();
		}
		function userDel(num){
			let yn=window.confirm('정말 삭제할까요?');
			if(yn){
				userF.idx.value=num;
				userF.action='delete.jsp';
				userF.submit();
			}//if-----
		}
    </script>
    
    
<jsp:include page="/foot.jsp"/>    




