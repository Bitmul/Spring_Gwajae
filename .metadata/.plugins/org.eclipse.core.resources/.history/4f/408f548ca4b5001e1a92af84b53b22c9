package com.ezen.project.service;

import java.util.List;

import com.ezen.project.domain.BoardDTO;
import com.ezen.project.domain.BoardVO;
import com.ezen.project.domain.PagingVO;

public interface BoardService {

	int register(BoardDTO bdto);

	List<BoardVO>getList(PagingVO pgvo);

	int getTotalCount(PagingVO pgvo);

	BoardDTO getBvoL(long bno);

	int delete(long bno);

	int update(BoardDTO bdto);

	void readCount(long bno);

	void readCountAdjust(long bno);

	int gaechu(long bno);

	int bichu(long bno);

	int removeFile(String uuid);

}
