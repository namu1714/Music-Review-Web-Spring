package com.muizic.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AlbumCommentDTO {
	private int commentCnt;
	private List<CommentVO> list;
}
