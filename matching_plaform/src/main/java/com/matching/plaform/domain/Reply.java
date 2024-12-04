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
public class Reply {
	
	private int reply_no;
	private int board_no;
	private String reply_content;
	private String member_id;
	private Timestamp reg_date;
	private int reply_like;
	private int reply_dislike;
}
