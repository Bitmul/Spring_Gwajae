package com.ezen.project.repository;

import java.util.List;

import com.ezen.project.domain.FileVO;

public interface FileDAO {

	int insertFile(FileVO fvo);

	List<FileVO> getFileList(long bno);

	int removeFile(String uuid);
	
}
