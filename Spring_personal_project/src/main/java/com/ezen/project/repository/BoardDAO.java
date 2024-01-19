package com.ezen.project.repository;

import java.util.List;

import com.ezen.project.domain.BoardDTO;
import com.ezen.project.domain.BoardVO;
import com.ezen.project.domain.PagingVO;

public interface BoardDAO {

	int register(BoardVO bvo);
	
	long selectOneBno();

	List<BoardVO>getList(PagingVO pgvo);

	int getTotalCount(PagingVO pgvo);

	BoardDTO getBvoL(long bno);

	int delete(long bno);

	int update(BoardVO bvo);

	void readCount(long bno);

	void readCountAdjust(long bno);

	int bichu(long bno);

	int gaechu(long bno);

	int removeFile(String uuid);

	BoardVO getDetail(long bno);

}
