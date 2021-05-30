package com.muizic.mapper;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.muizic.domain.CommentVO;
import com.muizic.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class CommentMapperTests {
	
	@Setter(onMethod_ = @Autowired)
	private CommentMapper mapper;

	@Test
	public void testPaging() {
		Criteria cri = new Criteria();
		cri.setAmount(10);
		cri.setPageNum(1);
		
		List<CommentVO> list = mapper.getListByAlbum(22L, cri);
		
		list.forEach(comment -> log.info(comment.getContent()));
	}
	
	public void testRead() {
		CommentVO comment = mapper.read(2L);
		log.info(comment);
	}
	
	public void testInsert() {
		CommentVO comment = new CommentVO();
		comment.setAlbumNo(22L);
		comment.setContent("루리언니짱 음악오래해줘요");
		comment.setWriterId("namu1714");
		comment.setWriterName("나뮤이");
		comment.setRegdate(new Date());
		comment.setModdate(new Date());
		
		mapper.insert(comment);
		log.info(comment);
	}
	
	public void testInsertSelectKey() {
		CommentVO comment = new CommentVO();
		comment.setAlbumNo(22L);
		comment.setContent("루리언니짱 음악오래해줘요2");
		comment.setWriterId("namu1714");
		comment.setWriterName("나뮤이");
		comment.setRegdate(new Date());
		comment.setModdate(new Date());
		
		mapper.insert(comment);
		log.info(comment);
	}
	
	public void TestDelete() {
		log.info("DELETE COUNT: " + mapper.delete(7L));
	}
	
	public void TestUpdate() {
		CommentVO comment = new CommentVO();
		comment.setCommentNo(6L);
		comment.setContent("루리언니짱 음악오래해줘요!");
		comment.setModdate(new Date());
		
		int count = mapper.update(comment);
		log.info("UPDATE COUNT: " + count);
	}
	
	@Test
	public void testTotalCount() {
		log.info("TOTAL ALBUM COMMENT COUNT: " + mapper.getCountByAlbum(2L));
	}
}
