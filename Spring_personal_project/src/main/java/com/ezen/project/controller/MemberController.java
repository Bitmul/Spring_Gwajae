package com.ezen.project.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mysql.cj.Session;
import com.ezen.project.domain.BoardDTO;
import com.ezen.project.security.MemberVO;
import com.ezen.project.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/member/*")
@RequiredArgsConstructor
@Controller
public class MemberController {
	private final MemberService msv;
	private final BCryptPasswordEncoder beEnconder;

	@GetMapping("/register")
	public void register() {
	}

	@PostMapping("/register")
	public String register(MemberVO mvo) {
		mvo.setPwd(beEnconder.encode(mvo.getPwd()));
		log.info("" + mvo);

		int isOk = msv.register(mvo);

		return null;

	}

	@GetMapping("/login")
	public void login() {
		
	}
	
	@PostMapping("/login")
	public String loginPost(HttpServletRequest request, RedirectAttributes re) {
		re.addAttribute("email",request.getAttribute("email"));
		re.addAttribute("errMsg",request.getAttribute("errMsg"));
		
		return "redirect:/member/login";
	}
	
	@GetMapping("/list")
	public void list(Model m) {
		List<MemberVO> list = msv.getMemberList();
		m.addAttribute("mvo",list);
	}
	
	@GetMapping("/modify")
	public void modify(@RequestParam("email")String email, Model m) {
		
		MemberVO mvo = msv.getMvo(email);
		m.addAttribute("mvo", mvo);
	}
	
	@PostMapping("/modify")
	public void modify(MemberVO mvo, HttpServletRequest request,HttpServletResponse response) {
		if(mvo.getPwd().isEmpty())
		{
			MemberVO temp = msv.getMvo(mvo.getEmail());
			mvo.setPwd(temp.getPwd());
		}
		else {
			mvo.setPwd(beEnconder.encode(mvo.getPwd()));
		}
		log.info("MOD MVOS : " + mvo);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		new SecurityContextLogoutHandler().logout(request, response, authentication);
		int isOk = msv.modfiy(mvo);
	}
	
	@GetMapping("/detail")
	public void detail(Model m, @RequestParam("email")String email) {
		MemberVO mvo = msv.getMvo(email);
		m.addAttribute("mvo",mvo);
	}
	
	@GetMapping("/remove")
	public String remove(Model m, @RequestParam("email")String email) {
		int isOk = msv.remove(email);
		
		return "redirect:/member/list";
	}
	
	@GetMapping("/logout")
	public String logout(Model m, HttpServletRequest request,HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		new SecurityContextLogoutHandler().logout(request, response, authentication);
		return "index";
	}
}
