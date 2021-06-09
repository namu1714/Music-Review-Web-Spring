package com.muizic.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.muizic.domain.AlbumVO;
import com.muizic.domain.Criteria;
import com.muizic.domain.PageDTO;
import com.muizic.service.AlbumService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/album/*")
@RequiredArgsConstructor
public class AlbumController {
	private final AlbumService service;
	
	@GetMapping("/list")
	public void list(Integer pageNum, Integer amount, String type, String keyword, Model model) {
		Criteria cri;
		if(pageNum != null && amount != null)
			cri = new Criteria(pageNum, amount);
		else
			cri = new Criteria();
		
		cri.setKeyword(keyword);
		cri.setType(type);
		log.info("list" + cri);
		
		model.addAttribute("albumList", service.getList(cri));
		
		int total = service.getTotal(cri);
		log.info("total: " + total);
		
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/upload")
	public void register() {
		
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/upload")
	public String register(AlbumVO album, RedirectAttributes rttr) {
		
		log.info("upload: " + album);
		
		service.register(album);
		rttr.addFlashAttribute("result", album.getAlbumNo());
		
		return "redirect:/album/list";
	}
	
	@GetMapping({"/read", "/modify"})
	public void get(@RequestParam("no") Long albumNo, @ModelAttribute("cri") Criteria cri, Model model) {
		log.info("/read or modify");
		model.addAttribute("album", service.get(albumNo));
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/modify")
	public String modify(AlbumVO album, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("modify: " + album);
		
		if(service.modify(album)) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/album/list" + cri.getListLink();
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/remove")
	public String remove(Long albumNo, RedirectAttributes rttr) {
		log.info("remove:" + albumNo);
		
		String albumImage = service.getCoverImageName(albumNo);
		
		if(service.remove(albumNo)) {
			deleteImage(albumImage);
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/album/list";
	}
	
	private void deleteImage(String imageName) {
		try {
			Path file = Paths.get("C:\\upload\\" + imageName);
			Files.deleteIfExists(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
