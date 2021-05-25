package com.muizic.domain;

import lombok.Data;

@Data
public class AlbumVO {
	private long albumNo;
	private String title;
	private String artist;
	private int releaseYear;
	private String coverImage;
}
