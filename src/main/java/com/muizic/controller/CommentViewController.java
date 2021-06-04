package com.muizic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.muizic.domain.CommentVO;
import com.muizic.service.AlbumService;
import com.muizic.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/comment/")
@Controller
@Log4j
@RequiredArgsConstructor
public class CommentViewController {
	private final CommentService commentService;
	private final AlbumService albumService;
	
	@GetMapping("/modify")
	public void get(@RequestParam("no") Long commentNo, Model model) {
		CommentVO comment = commentService.get(commentNo);
		model.addAttribute("comment", comment);
		model.addAttribute("album", albumService.get(comment.getAlbumNo()));
	}
	
	@PostMapping("/modify")
	public String modify(CommentVO comment, RedirectAttributes rttr) {
		log.info("modify: " + comment);
		
		if(commentService.modify(comment)) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/album/read?no=" + comment.getAlbumNo();
	}
}
