package com.muizic.service;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.muizic.domain.AuthVO;
import com.muizic.domain.MemberVO;
import com.muizic.domain.UserUpdateDTO;
import com.muizic.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequiredArgsConstructor
@Service
@Log4j
public class MemberServiceImpl implements MemberService {
	
	private final MemberMapper mapper;
	
	@Transactional
	@Override
	public boolean register(UserUpdateDTO dto) {
		log.info("member register");
		
		if(mapper.read(dto.getMemberid()) != null) {
			log.info("중복 아이디!");
			return false;
		}
		
		AuthVO auth = new AuthVO();
		auth.setMemberid(dto.getMemberid());
		auth.setAuth("ROLE_MEMBER");
		
		dto.setRegdate(new Date());

		mapper.insert(dto);
		mapper.insertAuth(auth);
		
		return true;
	}

	@Override
	public MemberVO get(String id) {
		log.info("get member");
		
		return mapper.read(id);
	}

	@Override
	public boolean modify(UserUpdateDTO dto) {
		log.info("modify member");
		return mapper.update(dto) == 1 ? true: false;
	}

	@Override
	public boolean remove(String id) {
		log.info("remove member");
		return mapper.delete(id) == 1 ? true: false;
	}

}
