package com.ezen.project.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FileVO {

	private String uuid;
	private String savedir;
	private String filename;
	private int filetype;
	private long filesize;
	private long bno;
	private String regat;
}
