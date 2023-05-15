package com.my.myapp;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.extern.log4j.Log4j;
/*[1]라이브러리 등록
 * ===pom.xml=========
 * <!-- File Upload -->
		<!-- https://mvnrepository.com/artifact/commons-fileupload/commons-fileupload -->
		<dependency>
			<groupId>commons-fileupload</groupId>
			<artifactId>commons-fileupload</artifactId>
			<version>1.4</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/commons-io/commons-io -->
		<dependency>
			<groupId>commons-io</groupId>
			<artifactId>commons-io</artifactId>
			<version>2.6</version>
		</dependency>
 * ====================
 * 
 * [2] servlet-context.xml에 빈 등록
 * <!-- 파일업로드를 위한  MultipartResolver 설정 =============================== -->
	<!--주의: 빈의 id는 반드시 multipartResolver로 등록해야 한다.다른 아이디를 주면 DispatcherServlet이 MultipartResolver로
	인식하질 못한다.  -->
	<beans:bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
		<beans:property name="defaultEncoding" value="utf-8"/>
		<beans:property name="maxUploadSize" value="-1"/>
		<!-- -1을 주면 무제한 업로드 가능 -->
	</beans:bean>
	
	<beans:bean id="upDir" class="java.lang.String">
		<beans:constructor-arg index="0" value="C:/myjava/Upload"/>
	</beans:bean>
	<!-- String upDir=new String("C:/myjava/Upload"); -->
	
 * 
 * */

@Controller
@Log4j
public class FileController {
	
	@Resource(name="upDir") //리소스 이름으로 주입하자.
	private String upDir;
	
	@GetMapping("/fileForm")
	public String fileForm() {
		return "file/fileForm";
		//WEB-INF/views/fileForm.jsp
	}
	
	//[1] MultipartFile을 이용하는 방법 => transferTo()를 이용하여 업로드 처리
	//[2] MultipartHttpServletRequet를 이용하는 방법
	@PostMapping("/fileUp")
	public String fileUpload(Model m, @RequestParam("name") String name,
			@RequestParam("mfilename") MultipartFile mfilename) {
		log.info("name: "+name+", mfilename: "+mfilename);
		log.info("upDir: "+upDir);
		//1. 파일정보(파일명, 파일크기, 첨부 여부) 알아내기 (DB에 저장하기 위해)
		if(!mfilename.isEmpty()) {//첨부했다면
			String filename=mfilename.getOriginalFilename();//파일명
			long filesize=mfilename.getSize();//파일크기
			String ctype=mfilename.getContentType();//파일유형
			log.info("filename: "+filename+", filesize: "+filesize);
			m.addAttribute("fname",filename);
			m.addAttribute("fsize", filesize);
			m.addAttribute("ftype",ctype);
			m.addAttribute("name",name);
			//2. 파일업로드 처리 => transferTo()
			try {
			mfilename.transferTo(new File(upDir, filename));
			}catch(IOException e) {
				log.info("업로드 실패: "+e);
				log.error(e);
			}
			
		}//if-----
		return "file/fileResult";
		//WEB-INF/views/fileResult.jsp
	}//-----------------------------------------
	////[2] MultipartHttpServletRequet를 이용하는 방법
	@PostMapping("/fileUp2")
	public String fileUpload2(Model m, HttpServletRequest req) {
		MultipartHttpServletRequest mr=(MultipartHttpServletRequest)req;
		//1. 올린이 받기
		String name=mr.getParameter("name");
		//2. 첨부파일 목록 얻기 List<MultipartFile> getFiles("파라미터명")
		List<MultipartFile> fList=mr.getFiles("mfilename");
		if(fList!=null) {
			for(int i=0;i<fList.size();i++) {
				MultipartFile mf=fList.get(i);
				//첨부파일명
				//"랜덤한문자열_파일명" 
				UUID uid=UUID.randomUUID();
				String uidStr=uid.toString();
				
				//실제 업로드 되는 파일명
				String fname=uidStr+"_"+mf.getOriginalFilename();//uuid_face.png
				//사용자가 업로드한 원본파일명
				String origin=mf.getOriginalFilename();
				
				//동일한 파일명을 업로드시 나중에 업로드한 파일이 이전 파일을 덮어쓰기함
				long fsize=mf.getSize();
				String ctype=mf.getContentType();
				//업로드 처리
				try {
					mf.transferTo(new File(upDir, fname));
				} catch (Exception e) {
					log.error(e);
				}
				m.addAttribute("fname"+(i+1), fname);
				m.addAttribute("fsize"+(i+1), fsize);
				m.addAttribute("ftype"+(i+1), ctype);
				
			}//for-----
		}//if---------------------------------
		
		return "file/fileResult";
	}//----------------------------------------
	/* 데이터와 함께 헤더 상태 메시지를 전달하고자 할때 사용한다.
	 * Http Header를 다뤄야 할 경우 ResponseEntity를 통해 헤더정보나 데이터를 전달할 수 있다.
	 * HttpEntity를 상속받아 구현한 클래스
	 * - RequestEntity : 요청 헤더정보+요청 데이터
	 * - ResponseEntity(HttpStatus, HttpHeaders, HttpBody를 포함함): 응답 헤더정보+ 응답 데이터
	 * 
	 *브라우저는 컨텐트타입이 보여줄 수 있는 형식이면 브라우저에 보여주고,
	 *잘 모르는 컨텐트타입이거나 보여줄 수 있는 형식이 아니면 다운로드 창을 띄운다. 
	 * */
	
	@PostMapping(value="/fileDown", produces="application/octet-stream")
	@ResponseBody
	public ResponseEntity<org.springframework.core.io.Resource> fileDownload(
			HttpServletRequest req,
			@RequestHeader("User-Agent") String userAgent,
			@RequestParam("fname") String fname,@RequestParam("origin_fname") String origin_fname) {
		log.info("fname=="+fname);
		log.info("origin_fname=="+origin_fname);
		//1. 업로드된 디렉토리의 절대경로 얻기
		ServletContext app=req.getServletContext();
		String upDir=app.getRealPath("/resources/board_upload");
		
		String filePath=upDir+File.separator+fname;
		log.info("filePath: "+filePath);
		org.springframework.core.io.Resource resource=new FileSystemResource(filePath);
		//2. FileSystemResource객체 생성해서 파일의 절대경로 정보를 넘긴다==> 알아서 파일과 스트림 연결해서 다운로드 함
		if(!resource.exists()) {
			//해당 파일이 없다면
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);//404
		}
		//2_2. 브라우저별 인코딩 처리
		String downName=null;
		boolean checkIE=(userAgent.indexOf("MSIE")>-1||userAgent.indexOf("Trident")>-1);//ie인 경우 TRUE값 할당됨
		try {
		if(checkIE) {
			//IE인 경우
			downName=URLEncoder.encode(origin_fname, "UTF-8").replaceAll("\\+", " ");
			
		}else {
			//그외 브라우저인 경우
			origin_fname=origin_fname.replace(",", "");//크롬은 파일명에 콤마(,)가 있으면 다운로드 되지 않음
			downName=new String(origin_fname.getBytes("UTF-8"),"ISO-8859-1");
		}
		
		}catch(UnsupportedEncodingException e) {
			log.error("파일 다운로드 중 에러: "+e);
		}
		
		//3. HttpHeader통해 헤더 정보 설정해서 ResponseEntity에 담아 반환함
		HttpHeaders headers=new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename="+downName);
		return new ResponseEntity<>(resource,headers,HttpStatus.OK);//200
	}
	

}/////////////////////////////////////






















