package com.matching.plaform.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.matching.plaform.domain.Board;

@Mapper
public interface BoardMapper {
	
	List<Board> boardList(int categoryCode);
}
