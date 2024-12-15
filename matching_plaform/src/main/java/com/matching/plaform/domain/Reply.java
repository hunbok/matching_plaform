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
	
	private int replyNo;
	private int boardNo;
	private String replyContent;
	private String memberId;
	private Timestamp replyRegDate;
	private int replyLike;
	private int replyDislike;
}
