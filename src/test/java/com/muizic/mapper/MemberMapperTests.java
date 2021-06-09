package com.muizic.mapper;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.muizic.domain.AuthVO;
import com.muizic.domain.MemberVO;
import com.muizic.domain.UserUpdateDTO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
@Log4j
public class MemberMapperTests {
	@Setter(onMethod_ = @Autowired)
	private MemberMapper mapper;
	
	@Setter(onMethod_ = @Autowired)
	private PasswordEncoder pwencoder;
	
	public void testInsert() {
		String password = pwencoder.encode("7777777");
				
		UserUpdateDTO dto = new UserUpdateDTO();
		dto.setMemberid("namu1714");
		dto.setNickname("나무이");
		dto.setEmail("namu1714@naver.com");
		dto.setPassword(password);
		dto.setRegdate(new Date());
		
		mapper.insert(dto);
		
		log.info(dto);
	}
	
	@Test
	public void testInsertAuth() {
		AuthVO auth = new AuthVO();
		auth.setMemberid("namu1714");
		auth.setAuth("ROLE_ADMIN");
		mapper.insertAuth(auth);
		log.info(auth);
	}
	
	public void testRead() {
		MemberVO member = mapper.read("namu1714");
		log.info(member);
	}
	
	public void testDelete() {
		log.info("DELETE: " + mapper.delete("namu1714"));
	}
}
