package com.muizic.service;

import com.muizic.domain.AlbumCommentDTO;
import com.muizic.domain.CommentUpdateRequestDTO;
import com.muizic.domain.CommentVO;
import com.muizic.domain.Criteria;

public interface CommentService {
	public int register(CommentVO comment);
	
	public CommentVO get(Long commentNo);
	
	public boolean modify(CommentUpdateRequestDTO commentUpdateReq);
	
	public boolean remove(Long commentNo);
	
	public AlbumCommentDTO getListPage(Long albumNo, Criteria cri);
}
