package com.my.myapp;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.model.ProductVO;
import com.shop.service.ShopService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class ProductController {
	
	@Inject
	private ShopService shopService;
	
	//pspec별로 상품목록 보기
	@GetMapping("/prodPspec")
	public String productByPspec(Model m, @RequestParam(name="pspec", defaultValue="HIT") String pspec) {
		log.info("pspec: "+pspec);
		List<ProductVO> pList=shopService.selectByPspec(pspec);
		m.addAttribute("pList",pList);
		m.addAttribute("pspec",pspec);
		
		return "shop_module/mallHit";
		//WEB-INF/views/shop/mallHit.jsp
	}
	
	@GetMapping("/prodDetail")
	public String productView(Model m,@RequestParam(name="pnum", defaultValue="0") int pnum) {
		log.info("pnum="+pnum);
		if(pnum==0) {
			return "redirect:index";
		}
		ProductVO item=shopService.selectByPnum(pnum);
		m.addAttribute("item",item);
		
		return "shop/prodView";
		//WEB-INF/views/shop/prodView.jsp
	}
}
