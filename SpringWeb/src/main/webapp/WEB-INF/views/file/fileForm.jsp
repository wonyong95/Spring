<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
	<h1>File Upload Test1</h1>
	<form name="frm" action="fileUp" method="post" enctype="multipart/form-data">
		올린이: <input type="text" name="name"><br><br>
		첨부파일: <input type="file" name="mfilename"><br><br>
		<button class="btn btn-default">업로드</button>	
	</form>
	
		<h1>File Upload Test2 - 여러 개의 파일 첨부</h1>
	<form name="frm2" action="fileUp2" method="post" enctype="multipart/form-data">
		올린이: <input type="text" name="name"><br><br>
		첨부파일1(이미지): <input type="file" name="mfilename" accept="image/*"><br><br>
		<!-- accept="image/*" => image파일들만 첨부할 수 있도록 제한 -->
		첨부파일2(모든파일): <input type="file" name="mfilename"><br><br>
		<button class="btn btn-default">업로드</button>	
	</form>