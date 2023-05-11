package com.my.myapp;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.model.CartVO;
import com.shop.service.ShopService;
import com.user.model.UserVO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/user")
@Log4j
public class CartController {

	@Inject
	private ShopService shopService;

	@PostMapping("/cartAdd")
	public String addCart(
				Model m, HttpSession session,
				@RequestParam(defaultValue="0") int pnum, @RequestParam(defaultValue="0") int pqty) {
		log.info("pnum="+pnum+", pqty="+pqty);
		if(pnum==0||pqty==0) {
			return "redirect:index";
		}
		//로그인한 회원의 회원번호 
		UserVO loginUser=(UserVO)session.getAttribute("loginUser");
		int idx_fk=loginUser.getIdx();
		
		CartVO cvo=new CartVO();
		cvo.setIdx_fk(idx_fk);
		cvo.setPnum_fk(pnum);
		cvo.setPqty(pqty);
		//장바구니에 상품추가
		int n=shopService.addCart(cvo);
		
		//장바구니 목록 가져오기
		//List<CartVO> cartList=shopService.selectCartView(idx_fk);
		//m.addAttribute("cartArr",cartList);
		
		//여기서 forward이동을 하면 브라우저 새로고침시 계속 상품 수량이 추가되는 현상 발생
		//=> 장바구니 총액 증가
		//==> redirect방식으로 이동해 함
		
		//return "shop/cartList";
		return "redirect:cartList";
	}
	
	@GetMapping("/cartList")
	public String cartList(Model m, HttpSession session) {
		
		UserVO loginUser=(UserVO)session.getAttribute("loginUser");
		int idx_fk=loginUser.getIdx();
		
		//장바구니 목록 가져오기
		List<CartVO> cartList=shopService.selectCartView(idx_fk);
		//특정회원의 장바구니 총액과 총포인트 가져오기
		CartVO cvo=shopService.getCartTotal(idx_fk);
		
		m.addAttribute("cartArr",cartList);
		m.addAttribute("cartTotal",cvo);
		return "shop/cartList";
	}
	
	@PostMapping("/cartDel")
	public String cartDelete(@RequestParam(defaultValue="0") int cartNum) {
		if(cartNum==0) {
			return "redirect:cartList";
		}
		int n=shopService.delCart(cartNum); 
		
		return "redirect:cartList";
	}
	
	@PostMapping("/cartEdit")
	public String cartEdit(@ModelAttribute CartVO cvo) {
		log.info("cvo="+cvo);
		shopService.editCart(cvo);
		
		return "redirect:cartList";
	}
	
	@GetMapping("/delCartAll")
	public String cartDeleteAll(HttpSession session) {
		UserVO loginUser=(UserVO)session.getAttribute("loginUser");
		int idx_fk=loginUser.getIdx();
		CartVO cvo=new CartVO();
		cvo.setIdx_fk(idx_fk);
		
		shopService.delCartAll(cvo);
		
		return "redirect:cartList";
	}
}
