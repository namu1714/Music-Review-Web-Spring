package com.muizic.domain;

import java.util.Date;

import lombok.Data;

@Data
public class CommentVO {
	private long commentNo;
	private long albumNo;
	private String writerId;
	private String writerName;
	private long likes;
	private Date regdate;
	private Date moddate;
	private String content;
}
