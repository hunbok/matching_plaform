package com.matching.plaform.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.matching.plaform.domain.Board;
import com.matching.plaform.domain.Reply;

@Mapper
public interface BoardMapper {
	
	List<Board> boardbyCategory(int categoryCode);
	
	Board getDetail(int boardNo);
	
	void writeBoard(Board board);
	
	void updateBoard(Board board);
	
	void deleteBoard(int boardNo);
}
