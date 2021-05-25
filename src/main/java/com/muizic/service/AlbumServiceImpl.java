package com.muizic.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.muizic.domain.AlbumVO;
import com.muizic.domain.Criteria;
import com.muizic.mapper.AlbumMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequiredArgsConstructor
@Service
@Log4j
public class AlbumServiceImpl implements AlbumService{

	private final AlbumMapper albumMapper;
	
	@Override
	public void register(AlbumVO album) {
		log.info("album register: " + album);
		albumMapper.insertSelectKey(album);
	}

	@Override
	public AlbumVO get(Long albumNO) {
		log.info("album read: " + albumNO);
		return albumMapper.read(albumNO);
	}

	@Override
	public boolean modify(AlbumVO album) {
		log.info("album modify" + album);
		return albumMapper.modify(album) == 1;
	}

	@Override
	public boolean remove(Long albumNo) {
		log.info("album remove" + albumNo);
		return albumMapper.delete(albumNo) == 1;
	}

	@Override
	public List<AlbumVO> getList(Criteria cri) {
		log.info("get Album List.....");
		return albumMapper.getListWithPaging(cri);
	}

	@Override
	public int getTotal(Criteria cri) {
		log.info("get total count");
		return albumMapper.getTotalCount(cri);
	}

	@Override
	public String getCoverImageName(Long albumNo) {
		log.info("get cover image name: " + albumNo);
		return albumMapper.read(albumNo).getCoverImage();
	}

}
