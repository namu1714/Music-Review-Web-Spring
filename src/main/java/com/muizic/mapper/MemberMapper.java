package com.muizic.mapper;

import com.muizic.domain.AuthVO;
import com.muizic.domain.MemberVO;
import com.muizic.domain.UserUpdateDTO;

public interface MemberMapper {
	public MemberVO read(String memberid);
	
	public int insert(UserUpdateDTO member);

	public int insertAuth(AuthVO auth);
	
	public int update(UserUpdateDTO member);
	
	public int delete(String memberid); //member_auth cascade
}
