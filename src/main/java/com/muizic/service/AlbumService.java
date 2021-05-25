package com.muizic.service;

import java.util.List;

import com.muizic.domain.AlbumVO;
import com.muizic.domain.Criteria;

public interface AlbumService {
	public void register(AlbumVO album);
	
	public AlbumVO get(Long albumNO);
	
	public boolean modify(AlbumVO album);
	
	public boolean remove(Long albumNo);
	
	public List<AlbumVO> getList(Criteria cri);
	
	public int getTotal(Criteria cri);
	
	public String getCoverImageName(Long albumNo);
}
