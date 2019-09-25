package com.lecture.practice.domain;

import lombok.Data;

@Data
public class BoardVO {
	private String board_id;
	private String id;
	private String title;
	private String content;
	private String is_delete;
	private String file_name;
	private String count;
}
