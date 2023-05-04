package com.my.myapp;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.user.model.UserVO;
import com.user.service.UserService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class UserController {
	
	//@Inject => pom.xml에 라이브러리 등록
	@Inject //@Autowired와 유사. by type으로 해당 객체를 주입한다
	private UserService userService;
	
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String joinForm() {
		
		return "/member/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String joinProcess(Model m,@ModelAttribute  UserVO user) {
		//log.info("user=="+user);
		System.out.println("user=="+user.getName());
		if(user.getName()==null||user.getUserid()==null||user.getPwd()==null||
				user.getName().trim().isEmpty()||
				user.getUserid().trim().isEmpty()||
				user.getPwd().trim().isEmpty()){
			
			return "redirect:join";
		}
		
		
		int n=userService.createUser(user);
		String str=(n>0)?"회원가입 완료-로그인 하세요":"가입 실패";
		String loc=(n>0)?"login":"javascript:history.back()";
		
		m.addAttribute("msg",str);
		m.addAttribute("loc",loc);
		return "message";
	}
	
	//get방식 요청만 처리함
	@GetMapping("/idCheck")
	public String idCheckForm() {
		
		return "member/idCheck";
	}
	
	@PostMapping("/idCheck")
	public String idCheckResult(Model m, @RequestParam(defaultValue="") String userid) {
		boolean isUse=userService.idCheck(userid);
		String result=(isUse)? "success":"fail";
		
		m.addAttribute("result", result);
		m.addAttribute("userid", userid);
		
		return "member/idCheckResult";
	}
	
	@GetMapping("/user/myPage")
	public String showMyPage(HttpSession session) {
		
		return "member/myPage";
	}
}
