package com.framework.login;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoginService {
	
	// 생성자 방식으로 Login Mapper 주입
	LoginMapper loginMapper;
	public LoginService(LoginMapper loginMapper) {
		this.loginMapper = loginMapper;
	}
	
	@Transactional(readOnly = true)
	public Map<String, Object> requestLoginThymeleaf(Map<String, Object> params) {
		log.info("Thymeleaf 서비스 요청");
		
		Map<String, Object> resultMap = new HashMap<String, Object>() ;
		resultMap.put("REPL_PAGE_MSG", "성공");
		
//		String username = (String) params.get("username");
//		String password = (String) params.get("password");
		
//		log.info("{} : {}", username, password);
//		
//		if ("admin".equals(username) && "1234".equals(password)) {
//			resultMap.put("REPL_PAGE_MSG", "성공");
//			log.info("로그인 성공 !!!!");
//		}
//		else {
//			resultMap.put("REPL_PAGE_MSG", "실패");
//			log.error("로그인 실패 !!!!");
//		}
		
		log.info("ID 검증 시작");
		
		// TODO 1. ID가 DB에 있는지 확인
		Map<String, Object> memberInfo = loginMapper.selectMemberInfo(params);
		// TODO 1-1. ID가 존재하지 않으면 로그인 실패
		if (memberInfo == null) {
			resultMap.put("REPL_PAGE_MSG", "존재하는 ID가 없습니다.");
		}
		else {
			// TODO 1-2. ID가 존재하면 회원정보 활용
			log.info("ID는 있음");
			
			// TODO 2. PW가 맞는지 확인(조회한 회원정보의 PW 값을 String 비교)
			String dbPw = (String) memberInfo.get("PW");
			String requestPw = (String) params.get("password"); 
			
			// requestPw와 dbPw 가 같은지 확인
			if (!requestPw.equals(dbPw)) {
				resultMap.put("REPL_PAGE_MSG", "PW가 일치하지 않습니다.");
			}
			
			// 회원 정보 설정
			resultMap.put("MEMBER_INFO", memberInfo);
			
			log.info("member 정보 : {}", memberInfo);
		}
		
		return resultMap;
		
	}

}
