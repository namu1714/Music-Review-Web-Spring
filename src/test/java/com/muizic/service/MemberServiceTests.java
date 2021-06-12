package com.muizic.service;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.muizic.domain.MemberVO;
import com.muizic.domain.UserUpdateDTO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml", 
	"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
@Log4j
public class MemberServiceTests {
	
	@Setter(onMethod_ = { @Autowired })
	private MemberService service;
	
	@Setter(onMethod_ = @Autowired)
	private PasswordEncoder pwencoder;
	
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}
	
	public void testRegister() {
		String password = pwencoder.encode("7777777");
		
		UserUpdateDTO dto = new UserUpdateDTO();
		dto.setMemberid("member1");
		dto.setNickname("멤버후무이");
		dto.setPassword(password);
		dto.setEmail("namu1714@gmail.com");
		dto.setRegdate(new Date());
		
		service.register(dto);
		
		log.info("memberid" + dto.getMemberid());
	}
	
	public void testGet() {
		MemberVO member = service.get("member1");
		log.info(member);
	}
	
	public void testRemove() {
		log.info(service.remove("member1"));
	}
	
	public void testModify() {
		String password = pwencoder.encode("7777777");
		
		UserUpdateDTO dto = new UserUpdateDTO();
		dto.setMemberid("member1");
		dto.setNickname("멤버후무이");
		dto.setPassword(password);
		dto.setEmail("namu1714@gmail.com");
		
		service.modify(dto);
		log.info("memberid" + dto.getMemberid());
	}
	
	@Test
	public void testIdCheck() {
		String id = "namu171";
		log.info(service.idCheck(id));
	}
}
