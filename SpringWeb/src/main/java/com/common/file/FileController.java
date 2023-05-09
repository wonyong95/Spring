package com.common.file;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		<beans:constructor-arg index="0" value="D:/web/myjava/Upload"/>
	</beans:bean>
	<!-- String upDir=new String("D:/web/myjava/Upload"); -->
	
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
	
	//[1] MultipartFile을 이용하는 방법 => transfer()를 이용하여 업로드 처리
	
	@PostMapping("/fileUp")
	public String fileUpload(Model m, @RequestParam("name") String name,
			@RequestParam("mfilename") MultipartFile mfilename) {
		log.info("name: "+name+", mfilename: "+mfilename);
		log.info("upDir: "+upDir);
		//1.파일정보(파일명,파일크기 첨부여부) 알아내기 (DB에 저장하기 위해)
		if(!mfilename.isEmpty()) {
			String ctype=mfilename.getContentType();//파일유형
			String filename=mfilename.getOriginalFilename();//파일명
			long filesize=mfilename.getSize();//파일크기
			log.info("filename: "+filename+", filesize: "+filesize);
			m.addAttribute("fname",filename);
			m.addAttribute("fsize", filesize);
			m.addAttribute("ftype", ctype);
			m.addAttribute("name", name);	
		//2.파일업로드 처리 => transferTo()
			try {
			mfilename.transferTo(new File(upDir, filename));
			}catch(IOException e) {
				log.info("업로드 실패: "+e);
				log.info(e);
			}
		}//if----
		
		return "file/fileResult";
		//WEB-INF/views/fileResult.jsp
	}
	//[2] MultipartHttpServletRequet를 이용하는 방법
	@PostMapping("/fileUp2")
	public String fileUpload2(Model m,HttpServletRequest req) {
		MultipartHttpServletRequest mr=(MultipartHttpServletRequest)req;
		//1. 올린이 받기
		String name=mr.getParameter("name");
		//2. 첨부파일 얻기 List<MultipartFile> getFiles("파라미터명")
		List<MultipartFile> fList=mr.getFiles("mfilename");
		if(fList!=null) {
			for(int i=0;i<fList.size();i++) {
				MultipartFile mf=fList.get(i);
				//첨부파일명
				//랜덤한문자열_파일명
				UUID uid=UUID.randomUUID();
				String uidStr=uid.toString();
				
				//실제 업로드 되는 파일명
				String fname=uidStr+"_"+mf.getOriginalFilename();//uuid_face.png
				//사용자가 업로드한 원본파일
				String origin=mf.getOriginalFilename();
				
				//동일한 파일명을 업로드시 나중에 업로드한 파일이 이전 파일을 덮어쓰기함
				long fsize=mf.getSize();
				String ctype=mf.getContentType();
				//업로드처리
				try {
					mf.transferTo(new File(upDir, fname));
				}catch(Exception e) {
					log.error(e);
				}
				m.addAttribute("fname"+(i+1), fname);
				m.addAttribute("fsize"+(i+1), fsize);
				m.addAttribute("ftype"+(i+1), ctype);
			}
		}
		
		return "file/fileResult";
	}
}