package com.ezen.project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.project.domain.BoardDTO;
import com.ezen.project.domain.BoardVO;
import com.ezen.project.domain.FileVO;
import com.ezen.project.domain.PagingVO;
import com.ezen.project.repository.BoardDAO;
import com.ezen.project.repository.FileDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
	
	private final BoardDAO bdao;
	private final FileDAO fdao;

	@Override
	public int register(BoardDTO bdto) {
		log.info(">>>>>>>>>>>>>>BVO : " + bdto.getBvo());
		int isOk = bdao.register(bdto.getBvo());
		if (bdto.getFlist() == null)
		{
			return isOk;
		}
		if (isOk > 0 && bdto.getFlist().size() > 0) {
			long bno = bdao.selectOneBno();
			log.info(">>>>>>>>>>>>>>BNO : " + bno);
			for(FileVO fvo : bdto.getFlist()) {
				fvo.setBno(bno);
				isOk *= fdao.insertFile(fvo);
			}
		}
		return isOk;
	}

	@Override
	public List<BoardVO>getList(PagingVO pgvo) {
		return bdao.getList(pgvo);
	}

	@Override
	public int getTotalCount(PagingVO pgvo) {
		return bdao.getTotalCount(pgvo);
	}

	@Override
	public BoardDTO getBvoL(long bno) {
		BoardVO bvo = bdao.getDetail(bno);
		List<FileVO>flist = fdao.getFileList(bno);
		BoardDTO bdto = new BoardDTO(bvo,flist);
		return bdto;
	}

	@Override
	public int delete(long bno) {
		return bdao.delete(bno);
	}

	@Override
	@Transactional
	public int update(BoardDTO bdto) {
		int isOk = bdao.update(bdto.getBvo());
		if (bdto.getFlist() == null)
		{
			return isOk;
		}
		if (isOk > 0 && bdto.getFlist().size() > 0) {
			long bno = bdao.selectOneBno();
			log.info(">>>>>>>>>>>>>>BNO : " + bno);
			for(FileVO fvo : bdto.getFlist()) {
				fvo.setBno(bno);
				isOk *= fdao.insertFile(fvo);
			}
		}
		return isOk;
	}

	@Override
	public void readCount(long bno) {
		bdao.readCount(bno);
	}

	@Override
	public void readCountAdjust(long bno) {
		bdao.readCountAdjust(bno);
	}

	@Override
	public int gaechu(long bno) {
		// TODO Auto-generated method stub
		return bdao.gaechu(bno);
	}

	@Override
	public int bichu(long bno) {
		// TODO Auto-generated method stub
		return bdao.bichu(bno);
	}

	@Override
	public int removeFile(String uuid) {
		// TODO Auto-generated method stub
		return fdao.removeFile(uuid);
	}

}
