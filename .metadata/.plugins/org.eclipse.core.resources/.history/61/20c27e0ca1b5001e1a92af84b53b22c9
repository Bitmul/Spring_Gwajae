package com.ezen.project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.project.domain.CommentVO;
import com.ezen.project.domain.PagingVO;
import com.ezen.project.handler.PagingHandler;
import com.ezen.project.repository.CommentDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

	private final CommentDAO cdao;
	
	@Override
	public int register(CommentVO cvo) {
		return cdao.register(cvo);
	}

	@Transactional
	@Override
	public PagingHandler getList(long bno, PagingVO pgvo) {
		int totalCount = cdao.selectOneBnoTotalCount(bno);
		List<CommentVO> list = cdao.getList(bno, pgvo);
		PagingHandler ph = new PagingHandler(pgvo, totalCount, list);
		return ph;
	}
	
	@Override
	public int modify(CommentVO cvo) {
		return cdao.modify(cvo);
	}

	@Override
	public int delete(long cno) {
		return cdao.delete(cno);
	}

}
