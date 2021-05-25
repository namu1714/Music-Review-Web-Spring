package com.muizic.mapper;

import java.util.List;

import com.muizic.domain.AlbumVO;
import com.muizic.domain.Criteria;

public interface AlbumMapper {
	public List<AlbumVO> getListWithPaging(Criteria cri);
	
	public AlbumVO read(Long albumNo);
	
	public void insert(AlbumVO album);
	
	public int insertSelectKey(AlbumVO album);
	
	public int modify(AlbumVO album);
	
	public int delete(Long albumNo);
	
	public int getTotalCount(Criteria cri);
}
