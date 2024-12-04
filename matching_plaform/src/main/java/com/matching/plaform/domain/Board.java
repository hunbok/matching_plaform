package com.matching.plaform.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Board {
	
	private int board_no;
	private String board_title;
	private String board_content;
	private Timestamp reg_date;
	private int board_view;
	private int category_code;
	private String member_id;
	private String board_file1;
	private int board_like;
	private int board_dislike;
	
}
