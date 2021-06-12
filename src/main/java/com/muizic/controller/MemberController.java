package com.muizic.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.muizic.domain.UserUpdateDTO;
import com.muizic.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequiredArgsConstructor
public class MemberController {
	private final MemberService service;
	
	private final PasswordEncoder pwencoder;
	
	@GetMapping("/login")
	public void loginPage(HttpServletRequest request) {
		log.info("login page");
		String referer = request.getHeader("Referer");
		if(referer.endsWith("joinSuccess")) {
			referer = "/album/list";
		}
	    request.getSession().setAttribute("prevPage", referer);
	}

	@GetMapping("/accessDenied")
	public void accessDenied(Authentication auth) {
		log.info("access denied : " + auth);
	}
	
	@GetMapping("/join")
	public void join() {
		log.info("join page");
	}
	
	@PostMapping("/join")
	public String join(UserUpdateDTO dto, RedirectAttributes rttr) {
		log.info("join");
		
		String encodepw = pwencoder.encode(dto.getPassword());
		dto.setPassword(encodepw);
		
		if(service.register(dto) == false) {
			rttr.addFlashAttribute("result", "중복 아이디");
			return "redirect:/join";
		}
		rttr.addFlashAttribute("result", dto.getMemberid());
		
		return "redirect:/joinSuccess";
	}
	
	@GetMapping("/joinSuccess")
	public void joinSuccess() {
		log.info("joinSuccess");
	}
}
