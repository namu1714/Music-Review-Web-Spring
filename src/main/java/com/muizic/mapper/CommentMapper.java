package com.muizic.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.muizic.domain.CommentVO;
import com.muizic.domain.Criteria;

public interface CommentMapper {
	public List<CommentVO> getListByAlbum(@Param("albumNo") Long albumNo, @Param("cri") Criteria cri);
	
	public CommentVO read(Long commentNo);
	
	public int insert(CommentVO comment);
	
	public int insertSelectKey(CommentVO comment);
	
	public int update(CommentVO comment);
	
	public int delete(Long commentNo);
	
	public int getCountByAlbum(Long albumNo);
}
