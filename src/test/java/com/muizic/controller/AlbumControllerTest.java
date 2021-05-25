package com.muizic.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.muizic.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class AlbumControllerTest {
	@Setter(onMethod_ = {@Autowired})
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	public void testListPaging() throws Exception {
		log.info(
			mockMvc.perform(MockMvcRequestBuilders.get("/album/list")
			.param("pageNum", "1")
			.param("amount", "9"))
			.andReturn().getModelAndView().getModelMap());
	}
	
	public void testRegister() throws Exception {
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/album/register")
			.param("title", "Nurture")
			.param("artist", "Porter Robinson")
			.param("releaseYear", "2021")
			.param("coverImage", "test")
		).andReturn().getModelAndView().getViewName();
		
		log.info(resultPage);
	}
	
	public void testRead() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders
			.get("/album/read")
			.param("no", "18"))
			.andReturn()
			.getModelAndView().getModelMap());
	}
	
	public void testModify() throws Exception {
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/album/modify")
				.param("albumNo", "18")
				.param("title", "Nurture")
				.param("artist", "Porter Robinson")
				.param("releaseYear", "2021")
				.param("coverImage", "수정test")
			).andReturn().getModelAndView().getViewName();
		
		log.info(resultPage);
	}
	
	@Test
	public void testRemove() throws Exception {
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/album/remove")
				.param("albumNo", "14")
				).andReturn().getModelAndView().getViewName();
		
		log.info(resultPage);
	}
}
