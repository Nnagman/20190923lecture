package com.lecture.practice.domain;

import lombok.Data;

@Data
public class CommentVO {
	private String board_id;
	private String comment_id;
	private String content;
	private String is_delete;
	private String id;
}
