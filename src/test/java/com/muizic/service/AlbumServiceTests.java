package com.muizic.service;

import static org.junit.Assert.assertNotNull;

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
public class AlbumServiceTests {

	@Setter(onMethod_ = { @Autowired })
	private AlbumService service;
	
	@Test
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}
	
	public void testRegister() {
		AlbumVO album = new AlbumVO();
		album.setTitle("새로 작성하는 글");
		album.setArtist("새아티스트");
		
		service.register(album);
		
		log.info("생성된 게시물의 번호: " + album.getAlbumNo());
	}
	
	@Test
	public void testGetList() {
		service.getList(new Criteria(2, 9)).forEach(album ->log.info(album));
	}
	
	public void testGet() {
		log.info(service.get(1L));
	}
	
	public void testDelete() {
		log.info("REMOVE RESULT: " + service.remove(12L));
	}
	
	public void testModify() {
		AlbumVO album = service.get(14L);
		
		if(album == null) {
			return;
		}
		
		album.setTitle("제목 수정합니다.");
		log.info("MODIFY RESULT: " + service.modify(album));
	}
	
}
