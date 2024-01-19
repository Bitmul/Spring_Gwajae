package com.ezen.project.service;

import java.util.List;

import com.ezen.project.security.MemberVO;

public interface MemberService {

	int register(MemberVO mvo);

	boolean updateLastLogin(String authEmail);

	int modfiy(MemberVO mvo);

	List<MemberVO> getMemberList();

	int remove(String email);

	MemberVO getMvo(String email);
}
