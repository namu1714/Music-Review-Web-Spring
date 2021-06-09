package com.muizic.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.muizic.domain.AlbumVO;
import com.muizic.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class AlbumMapperTests {

	@Setter(onMethod_ = @Autowired)
	private AlbumMapper mapper;
	
	public void testRead() {
		AlbumVO album = mapper.read(1L);
		log.info(album);
	}
	
	public void testInsert() {
		AlbumVO album = new AlbumVO();
		album.setTitle("testTitle");
		album.setArtist("testArtist");
		album.setReleaseYear(2021);
		album.setCoverImage("test.jpg");
		
		mapper.insert(album);
		log.info(album);
	}
	
	public void testInsertSelectKey() {
		AlbumVO album = new AlbumVO();
		album.setTitle("testSelectTitle");
		album.setArtist("testSelectArtist");
		album.setReleaseYear(2021);
		album.setCoverImage("test.jpg");
		
		mapper.insertSelectKey(album);
		log.info(album);
	}
	
	public void TestDelete() {
		log.info("DELETE COUNT: " + mapper.delete(11L));
	}
	
	public void TestUpdate() {
		AlbumVO album = new AlbumVO();
		album.setAlbumNo(16L);
		album.setTitle("수정된Title");
		album.setArtist("수정된Artist");
		album.setReleaseYear(2021);
		album.setCoverImage("test.jpg");
		
		int count = mapper.modify(album);
		log.info("UPDATE COUNT: " + count);
	}
	
	public void testPaging() {
		Criteria cri = new Criteria();
		cri.setAmount(9);
		cri.setPageNum(1);
		
		List<AlbumVO> list = mapper.getListWithPaging(cri);
		
		list.forEach(album -> log.info(album.getTitle()));
	}
	
	@Test
	public void testTotalCount() {
		Criteria cri = new Criteria();
		cri.setAmount(9);
		cri.setPageNum(1);
		cri.setKeyword("예린");
		cri.setType("AT");
		log.info("TOTAL ALBUM COUNT: " + mapper.getTotalCount(cri));
	}
	
	@Test
	public void testSearch() {
		Criteria cri = new Criteria();
		cri.setKeyword("예린");
		cri.setType("AT");
		
		List<AlbumVO> list = mapper.getListWithPaging(cri);
		list.forEach(album -> log.info(album));
	}
}
