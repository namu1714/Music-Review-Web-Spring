package com.muizic.domain;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdateDTO {
	private String memberid;
	private String password;
	private String nickname;
	private String email;
	private Date regdate;
}
