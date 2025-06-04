package com.framework.register.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegisterMapper {
	
	// 중복 회원 여부 조회
	int selectMemberDuplicateCount(Map<String, Object> params);
	
	// 회원 정보 등록
	int insertMember(Map<String, Object> params);
	
	// 회원 정보 조회
	Map<String, Object> selectMemberInfo(Map<String, Object> params);
	
	// 회원 정보 수정
	int updateMember(Map<String, Object> params);
	
	// 회원 정보 삭제
	int deleteMember(Map<String, Object> params);
}
