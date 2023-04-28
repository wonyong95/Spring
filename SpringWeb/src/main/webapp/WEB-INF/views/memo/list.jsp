<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list.jsp</title>
<link rel="stylesheet" type="text/css" href="css/memo.css">
</head>
<body>
<div id="wrap">
	

	<table border="1">
		<tr>
			<th colspan="4">
				<h2>:: 한줄 메모장 목록 ::</h2>
			</th>
		</tr>
		<tr>
			<td width="10%"><b>글번호</b></td>
			<td width="60%"><b>메모내용</b></td>
			<td width="20%"><b>작성자</b></td>
			<td width="10%"><b>수정|삭제</b></td>
		</tr>
		
		<tr>
			<td>a</td>
			<td>a<span class="wdate">[a]</span></td>
			<td>a</td>
			<td>
				<a href="memoEdit?no=1">수정</a>|
				<a href="memoDel?no=1">삭제</a>
			</td>
		</tr>

	</table>
</div>

</body>
</html>