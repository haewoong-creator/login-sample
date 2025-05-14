package com.framework.login;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {
	
	// 서비스 객체 주입
	LoginService loginService;
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@GetMapping("/login-template")
	public String loginTemplate() {
	
		return "login-template"; // /templates/login-template.html 경로 작성
	}
	
	@GetMapping("/login-xhr")
	public String loginXhr() {
		return "login-xhr"; // /templates/login-xhr.html 경로 작성
	}
	
	@PostMapping("/request-login")
	@ResponseBody
	public Map<String, Object> requestLogin(@RequestBody Map<String, Object> params) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		String username = (String) params.get("username");
		String password = (String) params.get("password");
		
		log.info("{} : {}", username, password);
		
		if ("admin".equals(username) && "1234".equals(password)) {
			result.put("REPL_MSG", "성공");
			log.info("로그인 성공 !!!!");
		}
		else {
			result.put("REPL_MSG", "실패");
			log.error("로그인 실패 !!!!");
		}

		return result;
	}
	
	// 페이지 요청 Controller 메서드
	@GetMapping("/login-thymeleaf")
	public String loginThymeleaf() {
		return "login-thymeleaf"; // /templates/login-thymeleaf.html 경로 작성
	}
	
	// 로그인 요청 Controller 메서드
	@PostMapping("/request-login-thymeleaf")
	public String requestLogin(@RequestParam Map<String, Object> params, Model model, HttpSession session) {
		
		log.info("Thymeleaf 로그인 요청");
		
//		Map<String, Object> result = new HashMap<String, Object>();
//		
//		String username = (String) params.get("username");
//		String password = (String) params.get("password");
//		
//		log.info("{} : {}", username, password);
//		
//		if ("admin".equals(username) && "1234".equals(password)) {
//			result.put("REPL_PAGE_MSG", "성공");
//			log.info("로그인 성공 !!!!");
//		}
//		else {
//			result.put("REPL_PAGE_MSG", "실패");
//			log.error("로그인 실패 !!!!");
//		}
		
		// username, password가 닮긴 Map형태의 자료를 Service 메서드에 전달
		Map<String, Object> result = loginService.requestLoginThymeleaf(params);
		
		// DB에서 조회한 ID를 session 정보로 등록
		Map<String, Object> memberInfo = (Map<String, Object>) result.get("MEMBER_INFO");
		String userId = (String) memberInfo.get("ID");
		
		System.out.println(userId);
		
		session.setAttribute("userId", userId);
		model.addAttribute("result", result);
		
		return "login-thymeleaf";
	}

}
