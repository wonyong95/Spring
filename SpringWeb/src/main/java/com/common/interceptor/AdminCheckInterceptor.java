package com.common.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.user.model.UserVO;

import lombok.extern.log4j.Log4j;

//servlet-context.xml에 빈등록하고 매핑한다
/*<interceptor>
<mapping path="/admin/**"/>
<beans:bean class="com.common.interceptor.AdminCheckInterceptor"/>
</interceptor>
*/

@Log4j
public class AdminCheckInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handle) throws Exception{
		log.info("AdminCheckInterceptor preHandle()...");
		HttpSession session=req.getSession();
		UserVO user=(UserVO)session.getAttribute("loginUser");
		if(user!=null) {
			if(user.getMstate()!=9) {//mstate:0 (일반회원), -1(정지회원), -2(탈퇴회원), 9(관리자)
				req.setAttribute("msg", "관리자만 이용 가능합니다");
				req.setAttribute("loc", req.getContextPath()+"/index");
				
				RequestDispatcher disp=req.getRequestDispatcher("/WEB-INF/views/message.jsp");
				disp.forward(req, res);
				
				return false;//관리자가 아닌경우
			}
		}
		return true;
	}
}
