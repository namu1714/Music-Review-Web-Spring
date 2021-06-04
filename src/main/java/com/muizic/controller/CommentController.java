package com.muizic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.muizic.domain.AlbumCommentDTO;
import com.muizic.domain.CommentUpdateRequestDTO;
import com.muizic.domain.CommentVO;
import com.muizic.domain.Criteria;
import com.muizic.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/comment/")
@RestController
@Log4j
@RequiredArgsConstructor
public class CommentController {

	private final CommentService service;

	@PostMapping(value = "/new", consumes = "application/json", produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> create(@RequestBody CommentVO vo) {
		log.info("ReplyVO " + vo);

		int insertCount = service.register(vo);

		log.info("Reply INSERT COUNT: " + insertCount);

		return insertCount == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping(value = "/{no}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<CommentVO> get(@PathVariable("no") Long commentNo) {
		log.info("get: " + commentNo);

		return new ResponseEntity<>(service.get(commentNo), HttpStatus.OK);
	}

	@GetMapping(value = "/pages/{albumNo}/{page}", produces = { 
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<AlbumCommentDTO> getList(@PathVariable("page") int page, @PathVariable("albumNo") Long albumNo) {
		log.info("getList...................");
		
		Criteria cri = new Criteria(page, 5);
		log.info("cri: " + cri);

		return new ResponseEntity<>(service.getListPage(albumNo, cri), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{no}")
	public ResponseEntity<String> remove(@RequestBody String writerId, @PathVariable("no") Long commentNo){
		log.info("remove: " + commentNo);
		
		log.info("writer id: " + writerId);
		
		return service.remove(commentNo) == true
			? new ResponseEntity<>("success", HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
