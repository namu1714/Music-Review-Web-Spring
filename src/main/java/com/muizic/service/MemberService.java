package com.muizic.service;

import com.muizic.domain.MemberVO;
import com.muizic.domain.UserUpdateDTO;

public interface MemberService {
	public boolean register(UserUpdateDTO dto);
	
	public MemberVO get(String id);
	
	public boolean modify(UserUpdateDTO dto);
	
	public boolean remove(String id);
	
	public boolean idCheck(String id);
}
