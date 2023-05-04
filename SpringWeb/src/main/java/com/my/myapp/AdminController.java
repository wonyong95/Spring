package com.my.myapp;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String productInsert(Model m, @ModelAttribute ProductVO item) {
		log.info("item=="+item);
		int n=adminService.productInsert(item);
		String str=(n>0)?"등록 성공":"등록 실패";
		String loc=(n>0)?"prodList":"javascript:history.back()";
		m.addAttribute("msg",str);
		m.addAttribute("loc",loc);
		return "message";
	}

}