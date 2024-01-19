package com.ezen.project.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.project.domain.BoardDTO;
import com.ezen.project.domain.BoardVO;
import com.ezen.project.domain.FileVO;
import com.ezen.project.domain.PagingVO;
import com.ezen.project.handler.FileHandler;
import com.ezen.project.handler.PagingHandler;
import com.ezen.project.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/board/*")
@RequiredArgsConstructor
@Controller
public class BoardController {

	private final BoardService bsv;
	
	private final FileHandler fh;

	@GetMapping("/register")
	public void register() {

	}

	@PostMapping("/register")
	public String register(BoardVO bvo,@RequestParam(name="files",required = false)MultipartFile[] files) {
		log.info("BVO >>>>>>>>>>>>> " + bvo);
		
		List<FileVO> flist = null;
		
		if(files[0].getSize() > 0)
		{
			flist = fh.uploadFiles(files);
		}
		
		BoardDTO bdto = new BoardDTO(bvo,flist);

		int isOk = bsv.register(bdto);

		return "index";
	}

	@GetMapping("/list")
	public String list(Model m, PagingVO pgvo) {
		List<BoardVO> list = bsv.getList(pgvo);
		int totalCount = bsv.getTotalCount(pgvo);

		PagingHandler ph = new PagingHandler(pgvo, totalCount);
		m.addAttribute("list", list);
		m.addAttribute("ph", ph);
		return "/board/list";
	}

	@GetMapping("/detail")
	public void detail(Model m,@RequestParam("bno") long bno) {
		bsv.readCount(bno);
		BoardDTO bdto = bsv.getBvoL(bno);
		m.addAttribute("bdto",bdto);

	}
	
	@GetMapping("/modify")
	public void modify(@RequestParam("bno") long bno, Model m) {
		BoardDTO bdto = bsv.getBvoL(bno);
		m.addAttribute("bdto",bdto);

	}

	@PostMapping("/modify")
	public String modify(BoardVO bvo ,@RequestParam(name="files",required = false)MultipartFile[] files) {
		
		List<FileVO> flist = null;
		
		if(files[0].getSize() > 0)
		{
			flist = fh.uploadFiles(files);
		}
		
		BoardDTO bdto = new BoardDTO(bvo,flist);
		
		int isOk = bsv.update(bdto);

		return "redirect:/board/detail?bno=" + bvo.getBno();
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("bno") long bno) {

		int isOk = bsv.delete(bno);

		return "redirect:/board/list";
	}
	
	@GetMapping("/gaechu")
	public String gaechu(@RequestParam("bno") long bno) {

		int isOk = bsv.gaechu(bno);
		
		return "redirect:/board/detail?bno=" + bno;
	}
	
	@GetMapping("/bichu")
	public String bichu(@RequestParam("bno") long bno) {

		int isOk = bsv.bichu(bno);
		
		return "redirect:/board/detail?bno=" + bno;
	}
	
	@DeleteMapping("/file/{uuid}")
	public ResponseEntity<String> removeFile(@PathVariable("uuid") String uuid){
		log.info(uuid);
		int isOk = bsv.removeFile(uuid);
		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
