package com.muizic.domain;

import java.util.Date;

import lombok.Data;

@Data
public class CommentUpdateRequestDTO {
	private long commentNo;
	private String content;
	
	public CommentVO toCommentVO(){
		CommentVO comment = new CommentVO();
		comment.setCommentNo(commentNo);
		comment.setModdate(new Date());
		comment.setContent(content);
		
		return comment;
	}
}
