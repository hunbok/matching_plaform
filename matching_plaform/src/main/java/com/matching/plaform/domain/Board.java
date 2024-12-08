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
	
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private Timestamp boardRegDate;
	private int boardView;
	private int categoryCode;
	private String categoryName;
	private String memberId;
	private String boardFile1;
	private int replyLike;
	private int replyDisLike;
	
}
