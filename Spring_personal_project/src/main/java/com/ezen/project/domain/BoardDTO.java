package com.ezen.project.domain;

import java.util.List;

import com.ezen.project.domain.FileVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

	private BoardVO bvo;
	private List<FileVO> flist;
}
