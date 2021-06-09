package com.muizic.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.muizic.domain.AlbumCommentDTO;
import com.muizic.domain.CommentVO;
import com.muizic.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class CommentServiceTests {
	
	@Setter(onMethod_ = { @Autowired })
	private CommentService service;
	
	@Test
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}
	
	public void testRegister() {
		CommentVO comment = new CommentVO();
		comment.setAlbumNo(26L);
		comment.setWriterId("namu1714");
		comment.setWriterName("나뮤이");
		comment.setContent("음악이 좀 구리네요^^2");
		
		service.register(comment);
		
		log.info("생성된 앨범 코멘트의 번호: " + comment.getCommentNo());
	}
	
	public void testGetList() {
		AlbumCommentDTO commentPage = service.getListPage(26L, (new Criteria(1, 5)));
		log.info(commentPage.getCommentCnt());
		commentPage.getList().forEach(comment -> log.info(comment));
	}
	
	public void testGet() {
		log.info(service.get(6L));
	}
	
	public void testDelete() {
		log.info("REMOVE RESULT: " + service.remove(9L));
	}
	
	@Test
	public void testModify() {
		CommentVO comment = service.get(8L);
		
		if(comment == null) {
			return;
		}
		
		CommentVO updateReq = new CommentVO();
		updateReq.setCommentNo(8L);
		updateReq.setWriterId("namu1714");
		updateReq.setContent("수정해도 음악이 좀 구리네요~~");
		log.info("MODIFY RESULT: " + service.modify(updateReq));
	}
}
