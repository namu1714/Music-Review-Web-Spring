package com.muizic.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class MemberVO {
	private String memberid;
	private String password;
	private String nickname;
	private String email;
	private boolean enabled;
	
	private Date regdate;
	private List<AuthVO> authList;
}
