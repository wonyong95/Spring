package com.my.myapp;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shop.model.ProductVO;
import com.shop.service.AdminService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/admin")
@Log4j
public class AdminController {
	
	@Inject
	private AdminService adminService;
	
	@GetMapping("/prodForm")
	public String productForm(Model m) {
		
		return "admin/prodInput";
		//WEB-INF/views/admin/prodInput.jsp
	}
	@PostMapping("/prodInput")
	public String productInsert(Model m, @ModelAttribute ProductVO item, 
			@RequestParam("pimage") List<MultipartFile> pimage,
			HttpServletRequest req) {
		//1.업로드 디렉토리 절대경로 얻기 "/resources/product_images"
		ServletContext app=req.getServletContext();
		String upDir=app.getRealPath("/resources/product_image");
		log.info("upDir: "+upDir);
		
		//2.업로드 처리 => 반복돌면서 transferTo(), 업로드 파일명을 item에 setting한다. item.setPimage1(), item.setpimages2()...
		if(pimage!=null) {
			for(int i=0;i<pimage.size();i++) {
				MultipartFile mf=pimage.get(i);
				if(!mf.isEmpty()) {//첨부파일이 있다면
					try {
						SimpleDateFormat sdf=new SimpleDateFormat("yyyymmddhhmmss");
						String str=sdf.format(new Date());
//						String fname=str+"_"+mf.getOriginalFilename();
						String fname=mf.getOriginalFilename();
						mf.transferTo(new File(upDir,fname));
						if(i==0) {
							item.setPimage1(fname);
						}else if(i==1) {
							item.setPimage2(fname);
						}else if(i==2) {
							item.setPimage3(fname);
						}
						
					}catch(IOException e) {
						log.error("파일 업로드 실패: "+e);
					}
				}
			}
		}
		
		log.info("item=="+item);
		int n=adminService.productInsert(item); //DB에 insert문 실행
		String str=(n>0)?"등록 성공":"등록 실패";
		String loc=(n>0)?"prodList":"javascript:history.back()";
		m.addAttribute("msg",str);
		m.addAttribute("loc",loc);
		return "message";
	}
	@GetMapping("/prodList")
	public String productList(Model m) {
		List<ProductVO> prodArr=adminService.productList();
		
		m.addAttribute("itemList", prodArr);
		return "admin/prodList";
	}

}
