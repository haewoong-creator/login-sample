package com.framework.login;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {
	
	// 서비스 객체 주입
//	LoginService loginService;
//	public LoginController(LoginService loginService) {
//		this.loginService = loginService;
//	}
	
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

}
