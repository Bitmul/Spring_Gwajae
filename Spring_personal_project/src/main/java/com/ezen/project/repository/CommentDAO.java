package com.ezen.project.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.project.domain.CommentVO;
import com.ezen.project.domain.PagingVO;

public interface CommentDAO {

	int register(CommentVO cvo);

	int selectOneBnoTotalCount(long bno);

	List<CommentVO> getList(@Param("bno")long bno, @Param("pgvo")PagingVO pgvo);

	int modify(CommentVO cvo);

	int delete(long cno);

}
