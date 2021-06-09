package com.muizic.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.muizic.domain.AlbumCommentDTO;
import com.muizic.domain.CommentVO;
import com.muizic.domain.Criteria;
import com.muizic.mapper.CommentMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequiredArgsConstructor
@Service
@Log4j
public class CommentServiceImpl implements CommentService{

	private final CommentMapper mapper;
	
	@Override
	public int register(CommentVO comment) {
		comment.setRegdate(new Date());
		comment.setModdate(new Date());
		log.info("comment register: " + comment);
		
		return mapper.insert(comment);
	}

	@Override
	public CommentVO get(Long commentNo) {
		log.info("comment get: " + commentNo);
		return mapper.read(commentNo);
	}

	@Override
	public boolean modify(CommentVO comment) {
		log.info("comment modify" + comment);
		comment.setModdate(new Date());
		return mapper.update(comment) == 1;
	}

	@Override
	public boolean remove(Long commentNo) {
		log.info("comment remove" + commentNo);
		return mapper.delete(commentNo) == 1;
	}

	@Override
	public AlbumCommentDTO getListPage(Long albumNo, Criteria cri) {
		log.info("get comment List page.....");
		int commentCnt = mapper.getCountByAlbum(albumNo);
		List<CommentVO> commentList = mapper.getListByAlbum(albumNo, cri);
		return new AlbumCommentDTO(commentCnt, commentList);
	}

}
