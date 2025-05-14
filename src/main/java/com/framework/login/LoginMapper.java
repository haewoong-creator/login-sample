package com.framework.login;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
	
	// ID가 존재하면 회원 정보 조회
	Map<String, Object> selectMemberInfo(Map<String, Object> params);
	
}
