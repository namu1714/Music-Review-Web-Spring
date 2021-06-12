package com.muizic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.muizic.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/member/")
@RestController
@Log4j
@RequiredArgsConstructor
public class MemberApiController {
	private final MemberService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<String> idCheck(@PathVariable("id") String memberid){
		log.info("id: " + memberid);
		return service.idCheck(memberid) == false
				? new ResponseEntity<>("ok", HttpStatus.OK)
				: new ResponseEntity<>("exist", HttpStatus.OK);
	}
}
