package com.framework.home;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

	@GetMapping("/home")
	public String home(HttpServletRequest request, Model model, HttpSession session) {
	    log.info("메인페이지 요청");
	    
	    String jsessionid= (String) session.getId();
	    log.info("jsessionid : {}", jsessionid);

	    Cookie[] cookies = request.getCookies();
	    List<String> cookieList = new ArrayList<>();

	    if (cookies != null && cookies.length > 0) {
	        for (Cookie cookie : cookies) {
	            String info = "쿠키 이름: " + cookie.getName() + ", 값: " + cookie.getValue();
	            cookieList.add(info);
	            log.info(info);
	        }
	    } else {
	        cookieList.add("쿠키 없음");
	        log.info("쿠키 없음");
	    }

	    model.addAttribute("cookieMessages", cookieList);
	    return "home"; // home.html
	}
}
