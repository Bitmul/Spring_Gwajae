package com.ezen.project.repository;

import java.util.List;

import com.ezen.project.security.AuthVO;
import com.ezen.project.security.MemberVO;

public interface MemberDAO {

	int register(MemberVO mvo);

	int insertAuthInit(String email);

	MemberVO selectEmail(String username);

	List<AuthVO> selectAuths(String username);

	int updateLastLogin(String authEmail);

	int modify(MemberVO mvo);

	List<MemberVO> getMemberList();

	int remove(String email);
	
	int removeAuth(String email);

	MemberVO getMvo(String email);

}
