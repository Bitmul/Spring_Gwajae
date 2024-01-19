package com.ezen.project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.project.repository.BoardDAO;
import com.ezen.project.repository.FileDAO;
import com.ezen.project.repository.MemberDAO;
import com.ezen.project.security.MemberVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

	private final MemberDAO mdao;
	
	@Transactional
	@Override
	public int register(MemberVO mvo) {
		int isOk = mdao.register(mvo);
		return mdao.insertAuthInit(mvo.getEmail());
	}

	@Override
	public boolean updateLastLogin(String authEmail) {
		
		return mdao.updateLastLogin(authEmail) > 0 ? true : false;
	}

	@Override
	public int modfiy(MemberVO mvo) {
		return mdao.modify(mvo);
	}

	@Override
	public List<MemberVO> getMemberList() {
		return mdao.getMemberList();
	}

	@Override
	public int remove(String email) {
		int isOk = mdao.removeAuth(email);
		isOk *= mdao.remove(email);
		return isOk;
	}

	@Override
	public MemberVO getMvo(String email) {
		return mdao.getMvo(email);
	}

}
