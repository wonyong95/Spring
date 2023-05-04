package com.common.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.user.model.UserVO;

/*Interceptor
 *  - 컨트롤러가 실행되기 전에 사전 처리할 일이 있으면 
 *    스프링에서는 인터셉터에서 구현한다.
 *  - 구현 방법
 *  1. 인터셉터 구현
 *     [1] HandlerInterceptor인터페이스를 상속받는 방법
 *     [2] HandlerInterceptorAdapter 추상클래스를 상속받는 방법
 *      
 *  2. 인터셉터 등록 => servlet-context.xml에서 등록하고 매핑 정보를 설정
 *  	<!-- interceptor 설정======================================= -->
	<interceptors>
		<interceptor>
			<mapping path="/user/**"/>
			<mapping path="/admin/**"/>
			<beans:bean class="com.common.interceptor.LoginCheckInterceptor"/>
		</interceptor>
	</interceptors> 
 * */
public class LoginCheckInterceptor implements HandlerInterceptor {
	
	Logger logger=LoggerFactory.getLogger(LoginCheckInterceptor.class);
	//[1] Controller를 실행하기 전에 호출되는 메서드. true를 반환하면 Controller로 넘어가고, false를 반환하면 Controller는 실행되지 않는다.
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		logger.info("preHandle()...");
		
		HttpSession session=req.getSession();
		UserVO user=(UserVO)session.getAttribute("loginUser");
		if(user==null) {
			//로그인하지 않았다면
			req.setAttribute("msg", "로그인해야 이용 가능");
			req.setAttribute("loc", req.getContextPath()+"/login");
			
			RequestDispatcher disp=req.getRequestDispatcher("/WEB-INF/views/message.jsp");
			disp.forward(req, res);
			return false;
		}
		
		return true;
	}
	//[2] Controller를 실행한 후, 아직 뷰를 실행하기 전에 호출되는 메서드
	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView mv) throws Exception{
		logger.info("postHandle()...");
	}
	//[3] Controller실행하고 뷰를 실행한 후에 호출되는 메서드
	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception ex) throws Exception {
		logger.info("afterCompletion()...");
	}

}