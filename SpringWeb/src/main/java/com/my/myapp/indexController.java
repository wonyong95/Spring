package com.my.myapp;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.model.ProductVO;
import com.shop.service.ShopService;

@Controller
public class indexController {
	
	@Inject
	private ShopService shopService;
		//뷰네임을 반환하지 않으면(void)
		//@RequestMapping("/index") 로 등록한 "/index" => 뷰네임이 된다
		@RequestMapping("/index")
		public void showIndex(Model m) {
			List<ProductVO> pList=shopService.selectByPspec("HIT");

			List<ProductVO> pList2=shopService.selectByPspec("NEW");
			
			m.addAttribute("pspec","HIT");
			m.addAttribute("pspec2","NEW");
			
			m.addAttribute("pList",pList);
			m.addAttribute("pList2",pList2);
			//WEB-INF/views/index.jsp
		}
}
