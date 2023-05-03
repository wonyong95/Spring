package com.my.myapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController {
	
	//뷰네임을 반환하지 않으면(void)
	//@RequestMapping("/index") 로 등록한 "/index" => 뷰네임이 된다
	@RequestMapping("/index")
	public void showIndex() {
		//WEB-INF/views/index.jsp
	}
}
